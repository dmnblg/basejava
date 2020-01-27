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

    protected int getExistResumeIndex(String uuid) {
        Integer index = getIndex(uuid);
        if ((index != null) && (index >= 0)) {
            return index.intValue();
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        int index = getNotExistResumeIndex(resume.getUuid());
        saveItem(index, resume);
    }

    protected int getNotExistResumeIndex(String uuid) {
        Integer index = getIndex(uuid);
        if (index == null) {
            return 0;
        } else if (index < 0) {
            return index.intValue();
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void deleteItem(int index);

    protected abstract void saveItem(int index, Resume resume);

    protected abstract Resume getItem(int index);

    protected abstract void setItem(int index, Resume resume);
}