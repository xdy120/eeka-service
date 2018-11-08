package com.greatonce.oms.dao.impl.base;

import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.dao.base.StockUploadStrategyDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.StockUploadRule;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.query.base.StockUploadStrategyQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StockUploadStrategy <br/>
 * 库存上传策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockUploadStrategyDaoImpl extends AbstractOmsDao<StockUploadStrategy,StockUploadStrategyQuery> implements StockUploadStrategyDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.StockUploadStrategyMapper";
    }

    @Override
    public List<StockUploadStrategy> listByIsUploadIsManualUpload() {
        List<StockUploadStrategy> list = getSqlSessionDecorator().selectList(getStatement("listByIsUploadIsManualUpload"));
        for (StockUploadStrategy stockUpload : list){
            stockUpload.setRule(JsonUtil.toObject(stockUpload.getSettingJson(),StockUploadRule.class));
        }
        return list;
    }

    @Override
    public List<StockUploadStrategy> listSettingStockUpload(Long storeId) {
        List<StockUploadStrategy> list = getSqlSessionDecorator().selectList(getStatement("listSettingStockUpload"), storeId);
        for (StockUploadStrategy stockUpload : list){
            stockUpload.setRule(JsonUtil.toObject(stockUpload.getSettingJson(),StockUploadRule.class));
        }
        return list;
    }
}