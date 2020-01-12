package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage {
    protected static final int MAX_RESUME = 10_000;
    protected Resume[] storage = new Resume[MAX_RESUME];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        return null;
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

    public void update(Resume resume) {
        // Пока будем считать, что сортировочное поле Uuid не меняется
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("В хранилище отсутствут резюме с UID " + resume.getUuid());
        }
    }

    protected abstract int getIndex(String uuid);

    public void save(Resume resume) {

        if (size == MAX_RESUME) {
            System.out.println("В хранилище уже содержится макимальное количество резюме");
            return;
        }

        int index = getIndex(resume.getUuid());
        if (index == -1) {
            storage[size++] = resume;
        } else if (index < -1) {
            int pos = -index - 1; // Можно обойтись и без временной переменной, но так код понятней
            Resume[] temp = Arrays.copyOfRange(storage, pos, size);
            for (int i = 0; i < temp.length; i++) {
                storage[i + pos + 1] = temp[i];
            }
            storage[pos] = resume;
            size++;
        } else {
            //index >= 0
            System.out.println("В хранилище уже существует резюме с UID " + resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteItem(index);
            storage[size-- - 1] = null;
        } else {
            System.out.println("В хранилище отсутствут резюме с UID " + uuid);
        }
    }

    abstract protected void deleteItem(int index);

}