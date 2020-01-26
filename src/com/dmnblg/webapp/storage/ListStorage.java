package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
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
    public void save(Resume resume) {
        if (-1 == getIndex(resume.getUuid())) {
            storage.add(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
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
    protected int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                index = i;
            }
        }
        return index;
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
    protected void setNullLastItem() {
        // Как обойтись без этого метода "костыля"?
    }

    @Override
    protected void setItem(int index, Resume resume) {
        storage.set(index, resume);
    }
}