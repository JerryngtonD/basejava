package com.jbrise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = "./.gitignore";
        File file = new File(filePath);
        System.out.println(file.exists());
        System.out.println("/--------------------------/\n");

        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        System.out.println("/--------------------------/\n");


        File dir = new File("./src/com/jbrise/webapp");
        System.out.println(dir.isDirectory());
        System.out.println("/--------------------------/\n");

        String[] fileEntities = dir.list();
        if(fileEntities != null) {
            for(String name : fileEntities) {
                System.out.println(name);
            }
        }

        try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
            System.out.println(fileInputStream.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
