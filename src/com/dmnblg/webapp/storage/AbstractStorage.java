package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object key = getExistResumeKey(uuid);
        return getItem(key);
    }

    public void save(Resume resume) {
        Object key = getNotExistResumeKey(resume.getUuid());
        saveItem(key, resume);
    }

    public void update(Resume resume) {
        Object key = getExistResumeKey(resume.getUuid());
        setItem(key, resume);
    }

    public void delete(String uuid) {
        Object key = getExistResumeKey(uuid);
        deleteItem(key);
    }

    private Object getExistResumeKey(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            return key;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Object getNotExistResumeKey(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            return key;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract Object getKey(String uuid);

    protected abstract void deleteItem(Object key);

    protected abstract boolean isExist(Object key);

    protected abstract void saveItem(Object key, Resume resume);

    protected abstract Resume getItem(Object key);

    protected abstract void setItem(Object key, Resume resume);
}