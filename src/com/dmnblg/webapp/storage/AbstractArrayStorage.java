package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
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

    public Resume getItem(int index){
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

    public void update(Resume resume) {
        // Пока будем считать, что сортировочное поле Uuid не меняется
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        if (size == MAX_RESUME) {
            throw new OverflowStorageException(resume.getUuid());
        }

        int index = getIndex(resume.getUuid());
        if (index <= -1) {
            saveItem(index, resume);
            size++;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteItem(index);
            storage[size-- - 1] = null;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }
}