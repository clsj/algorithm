package com.sj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    private static final SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task{
        void excute();
    }

    public static void time(String title, Task task) {
        if (task == null) {
            return;
        }

        System.out.println("【" + title + "】");

        System.out.println("开始时间：" + sf.format(new Date()));
        long begin = System.currentTimeMillis();

        task.excute();
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + sf.format(new Date()));
        System.out.println("总消耗时间：" + (end - begin) + "ms");
        System.out.println("================================================");
    }

}
