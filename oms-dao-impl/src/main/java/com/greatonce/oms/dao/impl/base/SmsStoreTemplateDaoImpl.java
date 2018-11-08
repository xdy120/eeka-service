package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.SmsStoreTemplateDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.SmsStoreTemplate;
import com.greatonce.oms.query.base.SmsStoreTemplateQuery;
import org.springframework.stereotype.Repository;

/**
 * SmsStoreTemplate <br/>
 * 短信店铺
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class SmsStoreTemplateDaoImpl extends AbstractOmsDao<SmsStoreTemplate,SmsStoreTemplateQuery> implements SmsStoreTemplateDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.SmsStoreTemplateMapper";
    }

}