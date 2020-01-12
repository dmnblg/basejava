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
    protected void deleteItem(int index) {
        Resume[] temp = Arrays.copyOfRange(storage, index + 1, size);
        for (int i = 0; i < temp.length; i++) {
            storage[i + index] = temp[i];
        }
    }
}