package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.GlobalSettingDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.GlobalSetting;
import com.greatonce.oms.query.admin.GlobalSettingQuery;
import org.springframework.stereotype.Repository;

/**
 * GlobalSetting <br/>
 * 全局配置
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class GlobalSettingDaoImpl extends AbstractAdminDao<GlobalSetting,GlobalSettingQuery> implements GlobalSettingDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.admin.GlobalSettingMapper";
    }
    
}