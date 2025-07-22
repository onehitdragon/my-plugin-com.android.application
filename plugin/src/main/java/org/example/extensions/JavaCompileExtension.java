package org.example.extensions;

import org.gradle.api.provider.Property;

public interface JavaCompileExtension {
    public Property<Integer> getVersion();
}
