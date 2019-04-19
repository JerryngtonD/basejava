package com.jbrise.webapp.storage;


import com.jbrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOnResumeStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();


    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Object oldResume, Resume newResume) {
        map.put(newResume.getUuid(), newResume);
    }

    @Override
    protected void doSave(Object resumeSearchKey, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object resume) {
        map.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected boolean isExistSearchKey(Object resume) {
        return resume != null;
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
