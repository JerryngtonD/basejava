package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void insertResume(int idx, Resume newResume) {
        // this is a new value to insert (not a duplicate).
        int insertIdx = -idx - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = newResume;
    }

    @Override
    protected void shiftOnDeletedResume(int deletedResumeIdx) {
        int shiftingResumesCount = size - deletedResumeIdx - 1;
        if (shiftingResumesCount > 0) {
            System.arraycopy(storage, deletedResumeIdx + 1, storage, deletedResumeIdx, shiftingResumesCount);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
