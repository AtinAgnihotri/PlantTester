package com.agnihotri.planttester.UiUtils;

import android.os.Debug;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogUITest {

    private static String UiTestTag = "PlantTester_UITest";

    public static void verbose(String logStr) {
        Log.v(UiTestTag, "Verbose: " + logStr);
    }

    public static void debug(String logStr) {
        Log.d(UiTestTag, logStr);
    }

    public static void info(String logStr) {
        Log.i(UiTestTag, logStr);
    }

    public static void warn(String logStr) {
        Log.w(UiTestTag, "Warn: " + logStr);
    }

    public static void error(String logStr) {
        Log.e(UiTestTag, "Error: " + logStr);
    }

    public static String getCallerMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[4];

        for (int i = 5; stackTraceElement.toString().contains("assert"); ++i) {
            stackTraceElement = stackTraceElements[i];
        }
        return stackTraceElement.getMethodName();
    }

    public static void startLogging(String logName){
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss", Locale.getDefault());
        String logDate = dateFormat.format(new Date());
        // Applies the date and time to the name of the trace log.
        Debug.startMethodTracing(
                 logName + "_" + logDate);
    }

    public static void stopLogging(){
        Debug.stopMethodTracing();
    }
}
