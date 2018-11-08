package com.greatonce.oms.biz;

import java.util.List;

/**
 * @author buer
 * @version 2017-11-14 18:02
 */
public interface TreeBizService<T>{

    /**
     * 更新子节点数量
     * @param id
     * @param quantity
     */
    int updateChildrenQuantity(Long id, int quantity);

    /**
     * 获取子数据
     * @param parentId
     */
    List<T> listChildren(Long parentId);
}
