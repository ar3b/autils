package com.ar3b.log;

/**
 * Created by ar3b on 12.05.2016.
 */
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.Formatter;

public class Log {

    public static final String _V = "v";
    public static final String _D = "d";
    private static final String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"wotdblog.txt";

    public static String type = _V;

    public static void w(String format, Object... args) {
        Log.write(false, format, args);
    }

    public static void w(boolean with_location, String format, Object... args) {
        Formatter f = new Formatter();
        String s = f.format(format, args).toString();
        if (type.equals(_V))
            android.util.Log.v("", (with_location?getLocation():"")+s);
        else
            android.util.Log.d("", (with_location?getLocation():"")+s);
    }

    public static void write(String format, Object... args) {
        Log.write(false, format, args);
    }

    public static void tag(String tag, String format, Object... args) {
        Formatter f = new Formatter();
        String s = f.format(format, args).toString();
        if (type.equals(_V))
            android.util.Log.v(tag, s);
        else
            android.util.Log.d(tag, s);
    }

    public static void write(boolean with_location, String format, Object... args) {
        Formatter f = new Formatter();
        String s = f.format(format, args).toString();
        if (type.equals(_V))
            android.util.Log.v("AAA", (with_location?getLocation():"")+s);
        else
            android.util.Log.d("AAA", (with_location?getLocation():"")+s);
    }

    private static String getLocation() {
        final String className = Log.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        boolean found = false;

        for (int i = 0; i < traces.length; i++) {
            StackTraceElement trace = traces[i];

            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
                    }
                }
                else if (trace.getClassName().startsWith(className)) {
                    found = true;
                    continue;
                }
            }
            catch (ClassNotFoundException e) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }

}