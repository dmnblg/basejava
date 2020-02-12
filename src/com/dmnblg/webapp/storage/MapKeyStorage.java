package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapKeyStorage extends AbstractStorage {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteItem(Object key) {
        Resume resumeKey = (Resume) key;
        storage.remove(resumeKey.getUuid());
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void saveItem(Object key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getItem(Object key) {
        return (Resume) key;
    }

    @Override
    protected void setItem(Object key, Resume resume) {
        Resume resumeKey = (Resume) key;
        storage.put(resumeKey.getUuid(), resume);
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
    public int size() {
        return storage.size();
    }
}