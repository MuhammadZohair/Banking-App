package com.shimirokach.bankingapp;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * The type Example instrumented test.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /**
     * Use app context.
     */
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.shimirokach.myapplication", appContext.getPackageName());
    }
}
