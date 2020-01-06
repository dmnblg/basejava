import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        int position = GetFirstNullIndex();
        Array.set(storage, position, r);
    }

    Resume get(String uuid) {
        Resume result = new Resume();

        int i = GetIndexByUuid(uuid);
        if (i > -1) {
            result = storage[i];
        }
        return result;
    }

    void delete(String uuid) {
        int del = GetIndexByUuid(uuid);
        int last = GetFirstNullIndex();
        storage[del] = storage[last - 1];
        storage[last - 1] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int lastIndex = GetFirstNullIndex();
        return Arrays.copyOf(storage, lastIndex);
    }

    int size() {
        return GetFirstNullIndex();
    }

    int GetFirstNullIndex() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return i;
            }
        }
        return 0;
    }

    int GetIndexByUuid(String uuid) {
        int r = -1;
        int lastIndex = GetFirstNullIndex();

        for (int i = 0; i < lastIndex; i++) {
            if (storage[i].uuid.equals(uuid)) {
                r = i;
            }
        }
        return r;
    }
}
