package com.jbrise.webapp.storage;


import com.jbrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapOnResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> map = new HashMap<>();


    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume oldResume, Resume newResume) {
        map.put(newResume.getUuid(), newResume);
    }

    @Override
    protected void doSave(Resume resumeSearchKey, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume resume) {
        map.remove(resume.getUuid());
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected boolean isExistSearchKey(Resume resume) {
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
