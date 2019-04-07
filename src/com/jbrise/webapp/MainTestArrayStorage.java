package com.jbrise.webapp;

import com.jbrise.webapp.model.Resume;
import com.jbrise.webapp.storage.ArrayStorage;

import com.jbrise.webapp.exception.NotExistStorageException;


/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume();
        final Resume r2 = new Resume();
        final Resume r3 = new Resume();
        Resume updatableResume = new Resume(r3.getUuid());

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Check all resumes before update:");
        printAll();
        ARRAY_STORAGE.update(updatableResume);
        System.out.println("Check all resumes after update:");
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (NotExistStorageException exception) {
            System.out.println("Expected NotExistStorageException so it's okey, cause dummy doesn't exist in storage");
        }

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static private void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
        System.out.println("\n");
    }
}
