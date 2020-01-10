package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume findElement = new Resume();
        findElement.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, findElement);
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
        } else if (index >= 0) {
            System.out.println("В хранилище уже существует резюме с UID " + resume.getUuid());
        } else if (index < -1) {
            int pos = -index - 1;

            Resume[] temp = Arrays.copyOfRange(storage, pos, size);

            for (int i = 0; i < temp.length; i++) {
                storage[i + pos + 1] = temp[i];
            }

            storage[pos] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {

            Resume[] temp = Arrays.copyOfRange(storage, index + 1, size);
            for (int i = 0; i < temp.length; i++) {
                storage[i + index] = temp[i];
            }
            storage[size--] = null;
        } else {
            System.out.println("В хранилище отсутствут резюме с UID " + uuid);
        }

    }
}