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
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected void deleteItem(int index) {
        storage.remove(index);
    }

    @Override
    protected void saveItem(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected Resume getItem(int index) {
        return storage.get(index);
    }

    @Override
    protected void setItem(int index, Resume resume) {
        storage.set(index, resume);
    }
}