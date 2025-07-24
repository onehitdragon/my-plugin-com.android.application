package org.example.tasks;

import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.work.InputChanges;

public abstract class CompileTask extends JavaCompile{
    @Override
    protected void compile(InputChanges inputs) {
        System.out.println("compile java version: " + getSourceCompatibility());
        System.out.println("Compiling using classpath: " + getClasspath().getAsPath());
        super.compile(inputs);
    }
}

