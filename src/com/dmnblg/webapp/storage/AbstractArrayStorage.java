package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.OverflowStorageException;
import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int MAX_RESUME = 10_000;
    protected Resume[] storage = new Resume[MAX_RESUME];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume getItem(Integer index) {
        return storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void saveItem(Integer index, Resume resume) {
        if (size < MAX_RESUME) {
            doSave(index, resume);
            size++;
        } else {
            throw new OverflowStorageException(resume.getUuid());
        }
    }

    public void deleteItem(Integer index) {
        doDelete(index);
        storage[size--] = null;
    }

    @Override
    protected boolean isExist(Integer index) {
        return (index >= 0);
    }

    protected void setItem(Integer index, Resume resume) {
        storage[index] = resume;
    }

    protected abstract void doSave(Integer index, Resume resume);

    protected abstract void doDelete(Integer index);

}