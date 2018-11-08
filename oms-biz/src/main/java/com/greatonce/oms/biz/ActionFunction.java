package com.greatonce.oms.biz;

@FunctionalInterface
public interface ActionFunction<T, U> {
    public void apply(T t, U u);
}
