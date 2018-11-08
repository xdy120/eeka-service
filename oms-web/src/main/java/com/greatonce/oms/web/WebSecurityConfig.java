package com.greatonce.oms.web;

import com.greatonce.oms.web.filter.AuthenticationFilter;
import com.greatonce.oms.web.filter.LoginFilter;
import com.greatonce.oms.web.filter.OptionsFilter;
import com.greatonce.oms.web.filter.TenantFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author buer
 * @version 2018-01-17 15:04
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  AuthenticationProvider authenticationProvider;
  @Value("${multi.tenant:false}")
  boolean multiTenant;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.cors().and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers(HttpMethod.POST, WebConstants.LOGIN_URL).permitAll()
        .antMatchers(HttpMethod.POST, "/advance/query/condition/rebuild").permitAll()
        .antMatchers(HttpMethod.GET, WebConstants.AUTO_EXPORT_URL).permitAll()
        .and()
        .addFilterBefore(new LoginFilter(WebConstants.LOGIN_URL, authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new AuthenticationFilter(authenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new OptionsFilter(), LoginFilter.class);
    if (multiTenant) {
      http.addFilterBefore(new TenantFilter(), OptionsFilter.class);
    }
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider);
  }
}