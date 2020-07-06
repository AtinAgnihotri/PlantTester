package com.agnihotri.planttester.MockUtils;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.test.runner.AndroidJUnitRunner;
import androidx.test.ext.junit.runners.AndroidJUnit4;

public class MockTestRunner extends AndroidJUnitRunner {
    @Override
    public void onCreate(Bundle arguments) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        super.onCreate(arguments);
    }

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return super.newApplication(cl, TestSearchPlantsActivity.class.getName(), context);
    }
}
