package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.DataDict;
import com.greatonce.oms.query.base.DataDictQuery;

/**
* DataDict <br/>
* 数据字典
*
* @author code-generator
* @author Shenzhen Greatonce Co Ltd
* @version 3.0
*/

public interface DataDictDao extends QueryDao<DataDict,DataDictQuery>{
    int updateChildrenQuantity(Long id, int quantity);
}
