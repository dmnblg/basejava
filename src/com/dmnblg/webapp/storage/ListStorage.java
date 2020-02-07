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
    public List<Resume> getAllSorted() {
        List<Resume> result = new ArrayList<Resume>();
        result.addAll(storage);
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void deleteItem(Object key) {
        storage.remove(((Integer) key).intValue());
    }

    @Override
    protected boolean isExist(Object key) {
        return (key != null);
    }

    @Override
    protected void saveItem(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getItem(Object key) {
        return storage.get((Integer) key);
    }

    @Override
    protected void setItem(Object key, Resume resume) {
        storage.set((Integer) key, resume);
    }
}