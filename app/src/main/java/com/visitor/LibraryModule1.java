package com.visitor;

/**
 * Created by jzj on 2018/4/19.
 */
public class LibraryModule1 extends LibraryModule {
    private static String aaa = "aaaaaaaaaaaaaaaaa";
    private final String bbb = "aaaaaaaaaaaaaaaaa";
    private final String ccc;
    private final String ss;

    public LibraryModule1() {
        ccc = "aaaaaaaaaaaaaaaaa";
        ss = null;
    }

    public LibraryModule1(String ss) {
        ccc = "aaaaaaaaaaaaaaaaa";
        this.ss = ss;
    }


    @Override
    public String getModuleName() {
        System.out.println("LibraryModule1 " + aaa);
        System.out.println("LibraryModule1 " + bbb);
        System.out.println("LibraryModule1 " + ccc);
        System.out.println("LibraryModule1 " + "aaaaaaaaaaaaaaaaa");
        return ss;
    }
}
