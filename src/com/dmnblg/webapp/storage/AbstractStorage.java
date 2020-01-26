package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        int index = getExistResumeIndex(uuid);
        return getItem(index);
    }

    public void update(Resume resume) {
        int index = getExistResumeIndex(resume.getUuid());
        setItem(index, resume);
    }

    public void delete(String uuid) {
        int index = getExistResumeIndex(uuid);
        deleteItem(index);
    }

    public int getExistResumeIndex(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index <= -1) {
            saveItem(index, resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    protected abstract int getIndex(String uuid);

    abstract protected void deleteItem(int index);

    abstract protected void saveItem(int index, Resume resume);

    abstract protected Resume getItem(int index);

    abstract protected void setItem(int index, Resume resume);
}