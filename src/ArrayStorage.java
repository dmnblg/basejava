import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        System.out.println("try to clear");
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }

    }

    void save(Resume r) {
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
