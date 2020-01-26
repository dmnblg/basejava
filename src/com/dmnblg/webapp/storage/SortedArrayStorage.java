package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    public void doSave(int index, Resume resume) {
        int pos = -index - 1; // Можно обойтись и без временной переменной, но так код понятней
        System.arraycopy(storage, pos, storage, pos + 1, size - pos);
        storage[pos] = resume;
    }

    @Override
    public void doDelete(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

}