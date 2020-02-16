package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapKeyStorage extends AbstractStorage<Resume> {

    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void deleteItem(Resume key) {
        storage.remove(key.getUuid());
    }

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected void saveItem(Resume key, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getItem(Resume key) {
        return key;
    }

    @Override
    protected void setItem(Resume key, Resume resume) {
        storage.put(key.getUuid(), resume);
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