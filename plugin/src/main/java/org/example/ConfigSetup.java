package org.example;

import org.gradle.api.artifacts.Configuration;
import org.gradle.api.attributes.Attribute;

public class ConfigSetup {
    public static void RealseCompileAttributes(Configuration config){
        // com.android.build.api.attributes.BuildTypeAttr: release
        // org.gradle.usage: java-api
        // org.gradle.jvm.environment: android
        // com.android.build.api.attributes.AgpVersionAttr: 8.11.0
        // org.gradle.category: library
        // org.jetbrains.kotlin.platform.type: androidJvm

        var configAtts = config.getAttributes();
        configAtts.attribute(Attribute.of("com.android.build.api.attributes.BuildTypeAttr", String.class), "release");
        configAtts.attribute(Attribute.of("org.gradle.usage", String.class), "java-api");
        configAtts.attribute(Attribute.of("org.gradle.jvm.environment", String.class), "android");
        configAtts.attribute(Attribute.of("com.android.build.api.attributes.AgpVersionAttr", String.class), "8.11.0");
        configAtts.attribute(Attribute.of("org.gradle.category", String.class), "library");
        configAtts.attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String.class), "androidJvm");
    }
    public static void RealseRuntimeAttributes(Configuration config){
        // com.android.build.api.attributes.BuildTypeAttr: release
        // org.gradle.usage: java-runtime
        // org.gradle.jvm.environment: android
        // com.android.build.api.attributes.AgpVersionAttr: 8.11.0
        // org.gradle.category: library
        // org.jetbrains.kotlin.platform.type: androidJvm

        var configAtts = config.getAttributes();
        configAtts.attribute(Attribute.of("com.android.build.api.attributes.BuildTypeAttr", String.class), "release");
        configAtts.attribute(Attribute.of("org.gradle.usage", String.class), "java-runtime");
        configAtts.attribute(Attribute.of("org.gradle.jvm.environment", String.class), "android");
        configAtts.attribute(Attribute.of("com.android.build.api.attributes.AgpVersionAttr", String.class), "8.11.0");
        configAtts.attribute(Attribute.of("org.gradle.category", String.class), "library");
        configAtts.attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String.class), "androidJvm");
    }
}
