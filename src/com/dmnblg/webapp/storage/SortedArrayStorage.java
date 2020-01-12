package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume findElement = new Resume();
        findElement.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, findElement);
    }

    @Override
    protected void deleteItem(int index) {
        System.arraycopy(storage, index+1, storage, index, size - 1);
    }

    @Override
    protected void saveItem(int index, Resume resume) {
        int pos = -index - 1; // Можно обойтись и без временной переменной, но так код понятней
        System.arraycopy(storage, pos + 1, storage, pos, size);
        storage[pos] = resume;
        size++;
    }
}