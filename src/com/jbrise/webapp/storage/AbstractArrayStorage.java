package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.StorageException;
import com.jbrise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];


    protected abstract void insertResume(int idx, Resume resume);

    protected abstract void shiftOnDeletedResume(int deletedResumeIdx);

    protected abstract Integer getSearchKey(String uuid);

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }


    @Override
    protected void doUpdate(Integer index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void doSave(Integer index, Resume resume) {
        if (size < storage.length) {
            insertResume(index, resume);
            size++;
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    @Override
    protected boolean isExistSearchKey(Integer index) {
        return index >= 0;
    }

    @Override
    public List<Resume> doGetStorageItems() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public void doDelete(Integer index) {
        shiftOnDeletedResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    public int size() {
        return size;
    }

}
