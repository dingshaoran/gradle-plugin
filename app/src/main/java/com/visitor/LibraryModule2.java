package com.visitor;


/**
 * Created by jzj on 2018/4/19.
 */
public class LibraryModule2 extends LibraryModule {
    private static String aaa = "aaaaaaaaaaaaaaaaa";
    private final String bbb = "aaaaaaaaaaaaaaaaa";
    private final String ccc;

    private final CharSequence ss;

    public LibraryModule2() {
        ccc = "aaaaaaaaaaaaaaaaa";
        ss = null;
    }

    public LibraryModule2(CharSequence ss) {
        ccc = "aaaaaaaaaaaaaaaaa";
        this.ss = ss;
    }

    public static LibraryModule2 getInstances(int ss) {
        return new LibraryModule2(ss + "");
    }

    public static LibraryModule2 getInstances() {
        return new LibraryModule2(null);
    }

    @Override
    public CharSequence getModuleName() {
        String tag = "LibraryModule2 ";
        System.out.println(tag + aaa);
        System.out.println(tag + bbb);
        System.out.println(tag + ccc);
        System.out.println(tag + "aaaaaaaaaaaaaaaaa");
        return ss;
    }
}
