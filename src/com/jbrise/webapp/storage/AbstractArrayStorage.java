package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.StorageException;
import com.jbrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];


    protected abstract void insertResume(int idx, Resume resume);

    protected abstract void shiftOnDeletedResume(int deletedResumeIdx);

    protected abstract Integer getSearchKey(String uuid);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    @Override
    protected void doUpdate(Object index, Resume r) {
        storage[(Integer) index] = r;
    }

    @Override
    protected void doSave(Object index, Resume resume) {
        if (size < storage.length) {
            insertResume((Integer) index, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected boolean isExistSearchKey(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    public List<Resume> doGetStorageItems() {
        return new ArrayList<>(Arrays.asList(Arrays.copyOf(storage, size)));
    }

    public void doDelete(Object index) {
        shiftOnDeletedResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public Resume doGet(Object index) {
        return storage[(Integer) index];
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

}
