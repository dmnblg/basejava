package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object index = getExistResumeIndex(uuid);
        return getItem(index);
    }

    public void save(Resume resume) {
        Integer index = getNotExistResumeIndex(resume.getUuid());
        saveItem(index, resume);
    }

    public void update(Resume resume) {
        Object index = getExistResumeIndex(resume.getUuid());
        setItem(index, resume);
    }

    public void delete(String uuid) {
        Object index = getExistResumeIndex(uuid);
        deleteItem(index);
    }

    private Object getExistResumeIndex(String uuid) {
        Object index = getIndex(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Integer getNotExistResumeIndex(String uuid) {
        Integer index = (Integer) getIndex(uuid);
        if (!isExist(index)) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void deleteItem(Object index);

    protected abstract boolean isExist(Object index);

    protected abstract void saveItem(Object index, Resume resume);

    protected abstract Resume getItem(Object index);

    protected abstract void setItem(Object index, Resume resume);
}