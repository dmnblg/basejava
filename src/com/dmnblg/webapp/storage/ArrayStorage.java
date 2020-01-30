package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void doSave(Integer key, Resume resume) {
        storage[size] = resume;
    }

    @Override
    public void doDelete(Integer key) {
        storage[key] = storage[size - 1];
    }

}