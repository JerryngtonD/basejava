package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
     private Map<String, Resume> storage = new HashMap<>();


    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Object uuid, Resume resume) {
        storage.put((String) uuid, resume);
    }

    @Override
    protected void doSave(Object uuid, Resume resume) {
        storage.put((String) uuid, resume);
    }

    @Override
    protected void doDelete(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    protected boolean isExistSearchKey(Object uuid) {
        return storage.containsKey((String) uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        System.out.println(storage.values().toArray(new Resume[storage.size()]));
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
