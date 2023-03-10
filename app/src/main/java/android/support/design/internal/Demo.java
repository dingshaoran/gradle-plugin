package android.support.design.internal;

import java.util.concurrent.Callable;

public class Demo {

    public String demo1(String aa, Object bb) throws Exception {
        Callable callable = () -> {
            demo11(aa, bb);
            return null;
        };
        return demo3(callable, this, aa, bb);
    }

    public String demo2(String aa, Object bb) throws Exception {
        Callable callable = () -> demo22(aa, bb);
        return demo3(callable, this, aa, bb);
    }

    public static String demo3(Callable<Void> callable, Demo demo, String aa, Object bb) throws Exception {
        return "";
    }

    public String demo(String aa, Object bb) {
        int i = 111111;
        int j = 111111;
        return "demo " + i + j + aa + bb;
    }

    public void demo11(String aa, Object bb) {
        int i = 111111;
        int j = 111111;
    }

    public long demo22(String aa, Object bb) {
        int i = 111111;
        int j = 111111;
        return 113425234524L;
    }

    public static String demo(int a, String aa, Object bb) {
        int i = 111111;
        int j = 111111;
        return "demo " + i + j + aa + bb;
    }
}
