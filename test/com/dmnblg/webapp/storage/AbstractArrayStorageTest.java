package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.OverflowStorageException;
import com.dmnblg.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
    }

    @Test(expected = OverflowStorageException.class)
    public void overflowTest() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.MAX_RESUME; i++) {
                storage.save(new Resume("Пушкин А.С."));
            }
        } catch (OverflowStorageException e) {
            fail("Fail test. Overflow storage.");
        }
        storage.save(new Resume("Достоевский Ф.М."));
    }

}