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
        final Resume resume_1 = new Resume("Some_1");
        final Resume resume_2 = new Resume("Some_2");
        final Resume resume_3 = new Resume("Some_3");
        Resume updatableResume = new Resume(resume_3.getUuid(), "updated name");

        ARRAY_STORAGE.save(resume_1);
        ARRAY_STORAGE.save(resume_2);
        ARRAY_STORAGE.save(resume_3);

        System.out.println("Check all resumes before update:");
        printAll();
        ARRAY_STORAGE.update(updatableResume);
        System.out.println("Check all resumes after update:");
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(resume_1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (NotExistStorageException exception) {
            System.out.println("Expected NotExistStorageException so it's okey, cause dummy doesn't exist in storage");
        }

        printAll();
        ARRAY_STORAGE.delete(resume_1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static private void printAll() {
        System.out.println("\nGet All");
        for (Resume resume : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(resume);
        }
        System.out.println("\n");
    }
}
