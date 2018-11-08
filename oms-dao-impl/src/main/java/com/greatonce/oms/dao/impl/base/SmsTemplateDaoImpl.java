package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.SmsTemplateDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.SmsTemplate;
import com.greatonce.oms.query.base.SmsTemplateQuery;
import org.springframework.stereotype.Repository;

/**
 * SmsTemplate <br/>
 * 短信模板
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SmsTemplateDaoImpl extends AbstractOmsDao<SmsTemplate,SmsTemplateQuery> implements SmsTemplateDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.SmsTemplateMapper";
    }
    
}