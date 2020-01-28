package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = new Resume[storage.size()];
        return storage.toArray(result);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void deleteItem(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    protected boolean isExist(Object index) {
        return (index != null);
    }

    @Override
    protected void saveItem(Object index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getItem(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void setItem(Object index, Resume resume) {
        storage.add(resume);
    }
}