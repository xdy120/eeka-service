package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.StoreDownloadConfigDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.query.base.StoreDownloadConfigQuery;
import org.springframework.stereotype.Repository;

/**
 * StoreDownloadConfig <br/>
 * 下载配置
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StoreDownloadConfigDaoImpl extends AbstractOmsDao<StoreDownloadConfig,StoreDownloadConfigQuery> implements StoreDownloadConfigDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.StoreDownloadConfigMapper";
    }

}