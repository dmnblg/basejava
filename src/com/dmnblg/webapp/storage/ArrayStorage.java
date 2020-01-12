package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteItem(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void saveItem(int index, Resume resume) {
        storage[size++] = resume;
    }

}