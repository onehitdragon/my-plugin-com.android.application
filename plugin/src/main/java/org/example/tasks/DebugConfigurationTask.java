package org.example.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.tasks.TaskAction;

public class DebugConfigurationTask extends DefaultTask{
    private ConfigurationContainer configContainer;

    public DebugConfigurationTask(){
        configContainer = this.getProject().getConfigurations();
    }

    private void printConfig(Configuration config){
        System.out.println(config.getName());
        // System.out.println("isCanBeDeclared: " + config.isCanBeDeclared());
        // System.out.println("isCanBeResolved: " + config.isCanBeResolved());
        // System.out.println("isCanBeConsumed: " + config.isCanBeConsumed());
        // System.out.println("Declared dependencies directly contained in this configuration: " + config.getDependencies().size());
        // config.getDependencies().forEach(dep -> {
        //     System.out.println("  ->" + dep);
        // });
        // System.out.println("Declared dependencies including those contributed by superconfigurations: " + config.getAllDependencies().size());
        // config.getAllDependencies().forEach(dep -> {
        //     System.out.println("  ->" + dep);
        // });
    }

    @TaskAction
    public void debug(){
        configContainer.forEach(cf -> {
            printConfig(cf);
            System.out.println("=================================");
        });
    }
}
