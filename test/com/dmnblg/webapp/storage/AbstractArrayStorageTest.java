package com.dmnblg.webapp.storage;

import com.dmnblg.webapp.exception.OverflowStorageException;
import com.dmnblg.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorage storage;

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
        this.storage = storage;
    }

    @Test(expected = OverflowStorageException.class)
    public void overflowTest() throws Exception {
        storage.clear();
        for (int i = 0; i < AbstractArrayStorage.MAX_RESUME; i++) {
            try {
                storage.save(new Resume());
            } catch (OverflowStorageException e) {
                fail();
            }
        }
        storage.save(new Resume());
    }

}