package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOnUuidStorage extends AbstractStorage<String> {
     private Map<String, Resume> map = new HashMap<>();


    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(String uuid, Resume resume) {
        map.put(uuid, resume);
    }

    @Override
    protected void doSave(String uuid, Resume resume) {
        map.put(uuid, resume);
    }

    @Override
    protected void doDelete(String uuid) {
        map.remove( uuid);
    }

    @Override
    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExistSearchKey(String uuid) {
        return map.containsKey(uuid);
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
