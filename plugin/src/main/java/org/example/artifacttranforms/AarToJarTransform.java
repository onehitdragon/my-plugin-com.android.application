package org.example.artifacttranforms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.gradle.api.artifacts.transform.InputArtifact;
import org.gradle.api.artifacts.transform.TransformAction;
import org.gradle.api.artifacts.transform.TransformOutputs;
import org.gradle.api.artifacts.transform.TransformParameters;
import org.gradle.api.file.FileSystemLocation;
import org.gradle.api.provider.Provider;

public abstract class AarToJarTransform implements TransformAction<TransformParameters.None>{
    @InputArtifact
    public abstract Provider<FileSystemLocation> getInputArtifact();

    @Override
    public void transform(TransformOutputs outputs) {
        var inputAar = getInputArtifact().get().getAsFile();
        var outputJar = outputs.file(
            inputAar.getName().replace(".aar", ".jar")
        );
        System.out.println("AarToJar: " + inputAar);
        System.out.println("To: " + outputJar);
        try{
            upzip(inputAar, outputJar);
        }
        catch(IOException exp){
            throw new RuntimeException(exp);
        }
    }

    private void upzip(File input, File output) throws IOException{
        try(var zis = new ZipInputStream(new FileInputStream(input))){
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null){
                if(entry.getName().equals("classes.jar")){
                    try(var fos = new FileOutputStream(output)){
                        var buffer = new byte[4096];
                        var length = 0;
                        while((length = zis.read(buffer)) > 0){
                            fos.write(buffer, 0, length);
                        }
                    }
                    return;
                }
                zis.closeEntry();
            }
        }
        throw new RuntimeException("Dont find classes.jar in " + input);
    }
}
