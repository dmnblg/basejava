package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.println("В хранилище отсутствут резюме с UID " + uuid);
        return -1;
    }

    @Override
    public void save(Resume resume) {
        if (size == MAX_RESUME) {
            System.out.println("В хранилище уже содержится макимальное количество резюме");
            return;
        }

        int index = getIndex(resume.getUuid());
        if (index == -1) {
            storage[size++] = resume;
        } else {
            System.out.println("В хранилище уже существует резюме с UID " + resume.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size-- - 1] = null;
        }
    }
}