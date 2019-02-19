package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size;

    private Resume[] storage = new Resume[10000];

    private int isExistResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.println("WARNING: resume " + uuid + " is not contained by storage");
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(String oldUuid, String newUuid) {
        int resumeIdx = isExistResume(oldUuid);
        if (resumeIdx >= 0) {
            storage[resumeIdx].setUuid(newUuid);
        } else {
            System.out.println("ERROR: storage does not contain the resume with this id: " + oldUuid);
        }
    }

    public void save(Resume r) {
        if (size < storage.length) {
            if (isExistResume(r.getUuid()) >= 0) {
                storage[size] = r;
                size++;
            }
        } else {
            System.out.println("ERROR: storage is limited");
        }
    }

    public Resume get(String uuid) {
        int resumeIdx = isExistResume(uuid);
        if (resumeIdx >= 0) {
            return storage[resumeIdx];
        } else {
            System.out.println("ERROR: storage does not contain resume on this id: " + uuid);
            return null;
        }
    }

    public void delete(String uuid) {
        int resumeIdx = isExistResume(uuid);
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
}
