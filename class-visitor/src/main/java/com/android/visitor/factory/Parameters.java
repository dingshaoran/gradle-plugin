package com.android.visitor.factory;

import com.android.build.api.instrumentation.InstrumentationParameters;

import org.gradle.api.provider.MapProperty;
import org.gradle.api.tasks.Input;

import java.util.Map;

public interface Parameters extends InstrumentationParameters {

    @Input
    MapProperty<String, Map<String, ?>> getVisit();
}