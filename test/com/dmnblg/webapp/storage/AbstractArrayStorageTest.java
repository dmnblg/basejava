package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.ExistStorageException;
import com.dmnblg.webapp.exception.NotExistStorageException;
import com.dmnblg.webapp.exception.OverflowStorageException;
import com.dmnblg.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    public AbstractArrayStorage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        Assert.assertEquals(3, storage.size);
        storage.clear();
        Assert.assertEquals(0, storage.size);
    }

    @Test
    public void get() {
        Resume resume = storage.get(UUID_2);
        Assert.assertEquals(UUID_2, resume.getUuid());
    }

    @Test
    public void getAll() {
        Resume[] all = storage.getAll();
        Assert.assertEquals(3, all.length);
        Assert.assertEquals(UUID_1, all[0].getUuid());
        Assert.assertEquals(UUID_2, all[1].getUuid());
        Assert.assertEquals(UUID_3, all[2].getUuid());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size);
    }

    @Test
    public void save() {
        String uuid = java.util.UUID.randomUUID().toString();
        Resume resume = new Resume(uuid);
        storage.save(resume);
        Assert.assertEquals(resume, storage.get(uuid));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size);
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

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = OverflowStorageException.class)
    public void capacityTest() throws Exception {

        // MAX_RESUME изменить не получилось, т.к. final
        // пока будем заполнять весь массив
        // Class storageClass = AbstractArrayStorage.class;
        // Field max = storageClass.getDeclaredField("MAX_RESUME");
        // max.setAccessible(true);
        // max.setInt(storage, 5);

        storage.clear();
        for (int i = 0; i < 10_000; i++) {
            try{
                storage.save(new Resume());
            }
            catch (OverflowStorageException e){
                Assert.fail();
            }
        }
        storage.save(new Resume());
    }
}