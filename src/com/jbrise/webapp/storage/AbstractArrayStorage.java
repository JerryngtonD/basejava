package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return size;
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

    protected abstract int getIndex(String uuid);
}
