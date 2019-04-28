package com.jbrise.webapp.storage;

import com.jbrise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> list = new ArrayList<>();


    @Override
    protected Integer getSearchKey(String uuid) {
        for (int position = 0; position < list.size(); position++) {
            if (list.get(position).getUuid().equals(uuid)) {
                return position;
            }
        }
        return -1;
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        list.set(searchKey, resume);
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        list.add(resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        list.remove((searchKey).intValue());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return list.get(searchKey);
    }

    @Override
    protected boolean isExistSearchKey(Integer searchKey) {
        return searchKey != -1;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> doGetStorageItems() {
        return  new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}
