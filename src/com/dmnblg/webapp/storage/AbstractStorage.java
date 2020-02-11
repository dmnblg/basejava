package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume resume, Resume t1) {
            if (resume.getFullName().equals(t1.getFullName())) {
                return resume.getUuid().compareTo(t1.getUuid());
            } else {
                return resume.getFullName().compareTo(t1.getFullName());
            }
        }
    };

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

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = getAllList();
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    protected abstract Object getKey(String uuid);

    protected abstract void deleteItem(Object key);

    protected abstract boolean isExist(Object key);

    protected abstract void saveItem(Object key, Resume resume);

    protected abstract Resume getItem(Object key);

    protected abstract void setItem(Object key, Resume resume);

    protected abstract List<Resume> getAllList();
}