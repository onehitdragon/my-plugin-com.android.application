package org.example;

import java.io.File;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.attributes.Attribute;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.Delete;
import org.gradle.api.Transformer;

class ChangeNameTransformer implements Transformer<String, String>{
    private int counter = 0;

    public void resetCounter(){
        counter = 0;
    }
    
    @Override
    public String transform(String in) {
        if(!in.equals("classes.jar")){
            return in;
        }
        in = in.replace(".jar", "_" + counter + ".jar");
        counter++;
        return in;
    }
}

public class Myplugin implements Plugin<Project>{
    public void apply(Project project) {
        // Create config
        var configContainer = project.getConfigurations();
        var releaseCompileClasspath = configContainer.maybeCreate("releaseCompileClasspath");

        // Register a task
        final var TASK_GROUP = "MyTask";
        var taskContainer = project.getTasks();

        var destinationCopyLib = new File(project.getProjectDir(), "src/main/libs-tmp");
        taskContainer.register("deleteLib", Delete.class, task -> {
            task.setDelete(destinationCopyLib);
            task.doLast(t -> {
                System.out.println("Deleted: " + destinationCopyLib);
            });
        });
        var changeNameTransformer = new ChangeNameTransformer();
        taskContainer.register("copyLib", Copy.class, task -> {
            task.setGroup(TASK_GROUP);
            task.dependsOn("deleteLib");
            task.from(
                releaseCompileClasspath.getIncoming().artifactView(view -> {
                    view.getAttributes().attribute(Attribute.of("artifactType", String.class), "jar");
                })
                .getFiles()
            );
            task.setDestinationDir(destinationCopyLib);
            task.doFirst(t -> {
                changeNameTransformer.resetCounter();
            });
            task.rename(changeNameTransformer);
            task.doLast(t -> {
                System.out.println("Copied to: " + destinationCopyLib);
            });
        });
    }
}
