package com.greatonce.oms.util;


import com.greatonce.core.util.Assert;

/**
 * @author buer
 * @version 2017-11-17 10:37
 */
public final class CidBuilder {

    /**
     * 生成cid，如果parentCid为空，使用parentId作为parentCid
     *
     * @param parentId
     * @param parentCid
     * @param id
     */
    public static String createCid(Long parentId, String parentCid, Long id) {
        return (Assert.isEmpty(parentCid) ? parentId : parentCid) + "/" + id;
    }
}
