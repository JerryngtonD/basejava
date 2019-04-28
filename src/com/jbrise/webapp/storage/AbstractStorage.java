package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.ExistStorageException;
import com.jbrise.webapp.exception.NotExistStorageException;
import com.jbrise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SearchKeyType> implements Storage {

    protected abstract SearchKeyType getSearchKey(String uuid);

    protected abstract void doUpdate(SearchKeyType searchKey, Resume resume);

    protected abstract void doSave(SearchKeyType searchKey, Resume resume);

    protected abstract void doDelete(SearchKeyType searchKey);

    protected abstract Resume doGet(SearchKeyType searchKey);

    protected abstract List<Resume> doGetStorageItems();

    protected abstract boolean isExistSearchKey(SearchKeyType searchKey);

    @Override
    public void update(Resume resume) {
        SearchKeyType searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        SearchKeyType searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void save(Resume resume) {
        SearchKeyType searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        SearchKeyType searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> allItems = doGetStorageItems();
        Collections.sort(allItems);
        return allItems;
    }

    private SearchKeyType getExistedSearchKey(String uuid) {
        SearchKeyType searchKey = getSearchKey(uuid);

        if (!isExistSearchKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }

        return searchKey;
    }

    private SearchKeyType getNotExistedSearchKey(String uuid) {
        SearchKeyType searchKey = getSearchKey(uuid);

        if (isExistSearchKey(searchKey)) {
            throw new ExistStorageException(uuid);
        }

        return searchKey;
    }
}
