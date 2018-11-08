package com.greatonce.oms.bo;

/**
 * @author buer
 * @version 2017-12-28 14:14
 */
public class VersionBO<T>{

    private Integer version;
    private T domain;

    public Integer getVersion(){
        return version;
    }

    public void setVersion(Integer version){
        this.version = version;
    }

    public T getDomain(){
        return domain;
    }

    public void setDomain(T domain){
        this.domain = domain;
    }
}
