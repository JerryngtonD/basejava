package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
     private Map<String, Resume> map = new HashMap<>();


    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Object uuid, Resume resume) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void doSave(Object uuid, Resume resume) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void doDelete(Object uuid) {
        map.remove((String) uuid);
    }

    @Override
    protected Resume doGet(Object uuid) {
        return map.get((String) uuid);
    }

    @Override
    protected boolean isExistSearchKey(Object uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> doGetStorageItems() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
