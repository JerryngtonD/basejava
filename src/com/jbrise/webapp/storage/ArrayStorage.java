package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertResume(int idx, Resume r) {
        storage[size] = r;
    }

    @Override
    protected void shiftOnDeletedResume(int deletedResumeIdx) {
        storage[deletedResumeIdx] = storage[size - 1];
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
