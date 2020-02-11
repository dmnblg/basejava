package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getKey(String uuid) {
        Resume resume = new Resume(uuid, uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    public void doSave(Integer key, Resume resume) {
        int pos = -key - 1;
        System.arraycopy(storage, pos, storage, pos + 1, size - pos);
        storage[pos] = resume;
    }

    @Override
    public void doDelete(Integer key) {
        System.arraycopy(storage, key + 1, storage, key, size - key - 1);
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

}