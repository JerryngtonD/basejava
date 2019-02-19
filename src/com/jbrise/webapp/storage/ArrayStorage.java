package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIdx = getIndex(resume.getUuid());
        if (resumeIdx >= 0) {
            storage[resumeIdx] = resume;
        } else {
            System.out.println("ERROR: storage does not contain the resume with this id: " + resume.getUuid());
        }
    }

    public void save(Resume r) {
        if (size < storage.length) {
            if (getIndex(r.getUuid()) < 0) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("ERROR: storage contains this resume already");
            }
        } else {
            System.out.println("ERROR: storage is limited");
        }
    }

    public Resume get(String uuid) {
        int resumeIdx = getIndex(uuid);
        if (resumeIdx >= 0) {
            return storage[resumeIdx];
        } else {
            System.out.println("ERROR: storage does not contain resume on this id: " + uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int resumeIdx = getIndex(uuid);
        if (resumeIdx >= 0) {
            storage[resumeIdx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERRPR: storage does not contain resume on this id: " + uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
