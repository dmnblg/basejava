package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return getItem(index);
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract int getIndex(String uuid);

    abstract protected void deleteItem(int index);

    abstract protected void saveItem(int index, Resume resume);

    abstract protected Resume getItem(int index);
}
