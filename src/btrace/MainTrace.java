package btrace;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author jianpeng.zhang
 * @since 2017/4/11.
 */
public class MainTrace
{
    private static boolean flag = true;
    private int i = 0;
    private static int j = 0;
    private static Object[] objects = new Object[2];
    public static void main(String[] args) throws Exception
    {
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("total memory : " + Runtime.getRuntime().totalMemory());
        System.out.println("max memory : " + Runtime.getRuntime().maxMemory());

        while (flag)
        {
            int[] longs = new int[3];
            new MainTrace().bar();
            foo("param" + j);
        }
    }



    private int bar() throws InterruptedException
    {
        Thread.sleep(5000);
        add();
        int[] longs = new int[5];
        longs[1] = 234;
        getI();
        // System.out.println(longs[1]);
        System.getProperty("aaa");
        if (!flag)
        {
            cut();
        }
        return i;
    }

    public int getI()
    {
        return i;
    }

    public void setI(int i)
    {
        this.i = i;
    }

    private void add()
    {
        int[] longs = new int[1];
        longs[0] = 234;
        objects = new Object[100];
        setI(i + 1);
        j = j + 1;
    }

    private void cut()
    {
        i--;
    }

    private static void foo(String param) throws InterruptedException
    {
        Thread.sleep(1000);
        // System.out.println(param);
        int[] longs = new int[6];
        new ArrayList<Integer>();
        objects = new Object[100];
        for (int i = 0; i < 100; i++)
        {
            objects[i] = new Object();
        }
        objects = null;
    }
}

class MyThread extends Thread
{
    private Object[] objects;

    @Override
    public void run()
    {
        try
        {
            // Runtime.getRuntime().gc();
            sleep(3000);
            int[][] longs = new int[7][2];
            longs[1][1] = 234;
            new LinkedList<String>();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        // System.out.println("MyThread running");
        objects = new Object[100];
        for (int i = 0; i < 100; i++)
        {
            objects[i] = new Object();
            objects[i].toString();
        }
        objects = null;
        new MyThread().start();
    }
}
