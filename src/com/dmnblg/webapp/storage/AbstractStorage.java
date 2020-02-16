package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

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
        LOG.info("Get " + uuid);
        T key = getExistResumeKey(uuid);
        return getItem(key);
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        T key = getNotExistResumeKey(resume.getUuid());
        saveItem(key, resume);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        T key = getExistResumeKey(resume.getUuid());
        setItem(key, resume);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        T key = getExistResumeKey(uuid);
        deleteItem(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> result = getAllList();
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    private T getExistResumeKey(String uuid) {
        T key = getKey(uuid);
        if (isExist(key)) {
            return key;
        } else {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    private T getNotExistResumeKey(String uuid) {
        T key = getKey(uuid);
        if (!isExist(key)) {
            return key;
        } else {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract T getKey(String uuid);

    protected abstract void deleteItem(T key);

    protected abstract boolean isExist(T key);

    protected abstract void saveItem(T key, Resume resume);

    protected abstract Resume getItem(T key);

    protected abstract void setItem(T key, Resume resume);

    protected abstract List<Resume> getAllList();
}