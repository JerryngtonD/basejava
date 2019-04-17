package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.StorageException;
import com.jbrise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        this.getStorage().clear();
        Resume overflowInsertedResume = new Resume();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                this.getStorage().save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("The storage will be full and there should not be an overflow, but something went wrong: " + e);
        }
        this.getStorage().save(overflowInsertedResume);
    }
}
