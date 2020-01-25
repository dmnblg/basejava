package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    abstract protected void deleteItem(int index);

    abstract protected void saveItem(int index, Resume resume);
}
