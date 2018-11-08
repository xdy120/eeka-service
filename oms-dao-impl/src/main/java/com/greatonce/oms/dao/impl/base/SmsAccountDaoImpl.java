package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.SmsAccountDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.SmsAccount;
import com.greatonce.oms.query.base.SmsAccountQuery;
import org.springframework.stereotype.Repository;

/**
 * SmsAccount <br/>
 * 短信帐号
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SmsAccountDaoImpl extends AbstractOmsDao<SmsAccount,SmsAccountQuery> implements SmsAccountDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.SmsAccountMapper";
    }

}