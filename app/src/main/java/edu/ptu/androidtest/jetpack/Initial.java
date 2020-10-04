package edu.ptu.androidtest.jetpack;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;
import androidx.startup.StartupLogger;

import java.util.Collections;
import java.util.List;
//StartupLogger.DEBUG
public class Initial implements Initializer<Object> {
    @NonNull
    @Override
    public Object create(@NonNull Context context) {
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
