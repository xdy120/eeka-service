package com.greatonce.oms.biz;

import com.greatonce.core.database.PageList;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/22
 */
@FunctionalInterface
public interface PageFunction<Filter, T> {
    PageList<T> listPage(Filter filter, Integer page, Integer pageSize);
}
