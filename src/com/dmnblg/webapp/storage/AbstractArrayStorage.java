package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage {
    protected static final int MAX_RESUME = 10_000;
    protected Resume[] storage = new Resume[MAX_RESUME];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        return null;
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
        if (index > -1) {
            storage[index] = resume;
        }
    }

    protected abstract int getIndex(String uuid);

    public abstract void save(Resume resume);

    public abstract void delete(String uuid);
}