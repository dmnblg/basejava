package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void deleteItem(Object key) {
        storage.remove(key);
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey(key);
    }

    @Override
    protected void saveItem(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getItem(Object key) {
        return storage.get(key);
    }

    @Override
    protected void setItem(Object key, Resume resume) {
        storage.put((String) key, resume);
    }

    @Override
    protected List<Resume> getAllList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = Arrays.asList(storage.values().toArray(new Resume[0]));
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
