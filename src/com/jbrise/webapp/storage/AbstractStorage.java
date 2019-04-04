package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.ExistStorageException;
import com.jbrise.webapp.exception.NotExistStorageException;
import com.jbrise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract int getSearchKey(String uuid);

    protected abstract boolean isExistSearchKey(Object searchKey);

    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }


    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }


    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);

        if (!isExistSearchKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);

        if (isExistSearchKey(searchKey)) {
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }
}
