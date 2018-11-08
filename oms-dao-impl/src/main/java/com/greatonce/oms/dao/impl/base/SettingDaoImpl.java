package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.SettingDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Setting;
import com.greatonce.oms.query.base.SettingQuery;
import org.springframework.stereotype.Repository;

/**
 * Setting <br/>
 * 基础配置
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SettingDaoImpl extends AbstractOmsDao<Setting,SettingQuery> implements SettingDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.SettingMapper";
    }
    
}