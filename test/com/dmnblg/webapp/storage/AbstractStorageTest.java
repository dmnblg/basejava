package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {

    protected AbstractStorage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    protected static final String NAME_1 = "Лермонтов М.Ю.";
    protected static final String NAME_2 = "Островский А.Н.";
    protected static final String NAME_3 = "Чехов А.П.";

    protected static final Resume RESUME_1 = new Resume(NAME_1, UUID_1);
    protected static final Resume RESUME_2 = new Resume(NAME_2, UUID_2);
    protected static final Resume RESUME_3 = new Resume(NAME_3, UUID_3);

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
        Resume resume = new Resume("dummy", uuid);
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
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void getAllSorted() {
        List<Resume> all = storage.getAllSorted();
        List<Resume> reference = new ArrayList<>();
        assertEquals(3, all.size());

        reference.add(RESUME_1);
        reference.add(RESUME_2);
        reference.add(RESUME_3);

        assertEquals(reference, all);
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(NAME_3, UUID_3);
        storage.update(resume);
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