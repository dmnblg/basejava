package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.OverflowStorageException;
import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int MAX_RESUME = 10_000;
    protected Resume[] storage = new Resume[MAX_RESUME];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume getItem(Object index) {
        return storage[(Integer) index];
    }

    public int size() {
        return size;
    }

    public void saveItem(Object index, Resume resume) {
        if (size < MAX_RESUME) {
            doSave((Integer) index, resume);
            size++;
        } else {
            throw new OverflowStorageException(resume.getUuid());
        }
    }

    public void deleteItem(Object index) {
        doDelete((Integer) index);
        storage[--size] = null;
    }

    @Override
    protected List<Resume> getAllList() {
        return Arrays.asList(storage);
    }

    @Override
    protected boolean isExist(Object index) {
        return ((Integer) index >= 0);
    }

    protected void setItem(Object index, Resume resume) {
        storage[(Integer) index] = resume;
    }

    protected abstract void doSave(Integer index, Resume resume);

    protected abstract void doDelete(Integer index);

}