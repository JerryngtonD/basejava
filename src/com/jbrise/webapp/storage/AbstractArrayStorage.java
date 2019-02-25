package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int resumeIdx = getIndex(r.getUuid());
        if (resumeIdx >= 0) {
            delete(r.getUuid());
            save(r);
        } else {
            System.out.println("ERROR: storage does not contain the resume with this id: " + r.getUuid());
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

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

    public void save(Resume r) {
        if (size < storage.length) {
            int idx = getIndex(r.getUuid());
            if (idx < 0) {
                insertResume(idx, r);
                size++;
            } else {
                System.out.println("ERROR: storage contains this resume already");
            }
        } else {
            System.out.println("ERROR: storage is limited");
        }
    }

    public void delete(String uuid) {
        int resumeIdx = getIndex(uuid);
        if (resumeIdx >= 0) {
            shiftOnDeletedResume(resumeIdx);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: storage does not contain resume on this id: " + uuid);
        }
    }

    protected abstract void insertResume(int idx, Resume r);

    protected abstract void shiftOnDeletedResume(int deletedResumeIdx);

    protected abstract int getIndex(String uuid);
}
