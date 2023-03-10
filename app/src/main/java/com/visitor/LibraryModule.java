package com.visitor;

/**
 * Created by jzj on 2018/3/29.
 */
public abstract class LibraryModule {

    public abstract CharSequence getModuleName();

    @Override
    public String toString() {
        return "Module: " + getModuleName();
    }
}
