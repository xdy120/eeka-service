package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.DataDictItemDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.query.base.DataDictItemQuery;
import org.springframework.stereotype.Repository;

/**
 * DataDictItem <br/>
 * 数据字典项
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class DataDictItemDaoImpl extends AbstractOmsDao<DataDictItem,DataDictItemQuery> implements DataDictItemDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.DataDictItemMapper";
    }

}