package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {

    protected AbstractStorage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    protected static final Resume RESUME_1 = new Resume(UUID_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3);

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() {
        String uuid = UUID.randomUUID().toString();
        Resume resume = new Resume(uuid);
        storage.save(resume);
        assertEquals(resume, storage.get(uuid));
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_2);
    }

    @Test
    public void get() {
        assertEquals(storage.get(UUID_2), RESUME_2);
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        Resume[] reference = new Resume[3];
        assertEquals(3, all.length);

        reference[0] = RESUME_1;
        reference[1] = RESUME_2;
        reference[2] = RESUME_3;

        assertArrayEquals(reference, all);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_3);
        storage.update(RESUME_3);
        assertSame(resume, storage.get(UUID_3));
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

}