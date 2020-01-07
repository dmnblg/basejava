package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_RESUME = 10000;
    Resume[] storage = new Resume[MAX_RESUME];
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == MAX_RESUME){
            System.out.println("В хранилище уже содержится макимальное количество резюме");
            return;
        }

        int i = getIndexByUuid(r.uuid);
        if (i == -1) {
            storage[size++] = r;
        } else {
            System.out.println("В хранилище уже существует резюме с UID ".concat(r.uuid));
        }
    }

    public void update(Resume r, String newUuid) {
        int i = getIndexByUuid(r.uuid);
        if (i > -1) {
            storage[i].uuid = newUuid; // Пока других полей нет, будем менять uuid
        } else {
            resumeNotFound(r.uuid);
        }
    }

    public Resume get(String uuid) {
        int i = getIndexByUuid(uuid);
        if (i > -1) {
            return storage[i];
        } else {
            resumeNotFound(uuid);
        }
        return null;
    }

    public void delete(String uuid) {
        int del = getIndexByUuid(uuid);
        if (del > -1) {
            storage[del] = storage[size - 1];
            storage[size-- - 1] = null;
        } else {
            resumeNotFound(uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    int getIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void resumeNotFound(String uuid) {
        System.out.println("В хранилище отсутствут резюме с UID ".concat(uuid));
    }
}