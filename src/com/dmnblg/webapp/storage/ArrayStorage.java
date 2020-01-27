package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void doSave(Integer index, Resume resume) {
        storage[size] = resume;
    }

    @Override
    public void doDelete(Integer index) {
        storage[index] = storage[size - 1];
    }

}