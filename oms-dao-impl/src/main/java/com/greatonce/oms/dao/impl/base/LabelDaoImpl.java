package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.LabelDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Label;
import com.greatonce.oms.query.base.LabelQuery;
import org.springframework.stereotype.Repository;

/**
 * Label <br/>
 * 标签
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class LabelDaoImpl extends AbstractOmsDao<Label,LabelQuery> implements LabelDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.base.LabelMapper";
    }

}