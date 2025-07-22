package org.example.tasks;

import org.example.extensions.JavaCompileExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.work.InputChanges;

public abstract class CompileTask extends JavaCompile{
    @Override
    protected void compile(InputChanges inputs) {
        var classPath = getClasspath();
        System.out.println("compile java version: " + getSourceCompatibility());
        System.out.println("Compiling using classpath: " + getClasspath().getAsPath());
        super.compile(inputs);
    }
}

