package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    public void doSave(Integer index, Resume resume) {
        int pos = -index - 1;
        System.arraycopy(storage, pos, storage, pos + 1, size - pos);
        storage[pos] = resume;
    }

    @Override
    public void doDelete(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }

}