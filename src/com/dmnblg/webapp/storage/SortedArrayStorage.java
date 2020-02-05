package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    private static class MyComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume resume, Resume t1) {
            return resume.getUuid().compareTo(t1.getUuid());
        }
    }

    private static final MyComparator myComparator = new MyComparator();

    @Override
    protected Integer getKey(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume, RESUME_COMPARATOR);
    }

    @Override
    public void doSave(Integer key, Resume resume) {
        int pos = -key - 1;
        System.arraycopy(storage, pos, storage, pos + 1, size - pos);
        storage[pos] = resume;
    }

    @Override
    public void doDelete(Integer key) {
        System.arraycopy(storage, key + 1, storage, key, size - key - 1);
    }

}