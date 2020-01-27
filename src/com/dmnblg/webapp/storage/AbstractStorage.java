package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Integer index = getExistResumeIndex(uuid);
        return getItem(index);
    }

    public void save(Resume resume) {
        Integer index = getNotExistResumeIndex(resume.getUuid());
        saveItem(index, resume);
    }

    public void update(Resume resume) {
        Integer index = getExistResumeIndex(resume.getUuid());
        setItem(index, resume);
    }

    public void delete(String uuid) {
        Integer index = getExistResumeIndex(uuid);
        deleteItem(index);
    }

    private Integer getExistResumeIndex(String uuid) {
        Integer index = getIndex(uuid);
        if (isExist(index)) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private Integer getNotExistResumeIndex(String uuid) {
        Integer index = getIndex(uuid);
        if (!isExist(index)) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void deleteItem(Integer index);

    protected abstract boolean isExist(Integer index);

    protected abstract void saveItem(Integer index, Resume resume);

    protected abstract Resume getItem(Integer index);

    protected abstract void setItem(Integer index, Resume resume);
}