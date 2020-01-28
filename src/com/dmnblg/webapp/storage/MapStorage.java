package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    protected HashMap<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected void deleteItem(Object index) {
        storage.remove(index);
    }

    @Override
    protected boolean isExist(Object index) {
        return storage.containsKey(index);
    }

    @Override
    protected void saveItem(Object index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getItem(Object index) {
        return storage.get(index);
    }

    @Override
    protected void setItem(Object index, Resume resume) {
        storage.put((String) index, resume);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
