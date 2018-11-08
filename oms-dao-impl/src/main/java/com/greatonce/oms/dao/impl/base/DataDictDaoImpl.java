package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.DataDictDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.DataDict;
import com.greatonce.oms.query.base.DataDictQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * DataDict <br/>
 * 数据字典
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class DataDictDaoImpl extends AbstractOmsDao<DataDict,DataDictQuery> implements DataDictDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.base.DataDictMapper";
    }

    @Override
    public int updateChildrenQuantity(Long id, int quantity){
        Map<String,Object> map = new HashMap<>(3);
        map.put("dataDictId", id);
        map.put("quantity", quantity);
        map.put("modifiedTime", LocalDateTime.now());
        return getSqlSessionDecorator().update(getStatement("updateChildrenQuantity"), map);
    }
}