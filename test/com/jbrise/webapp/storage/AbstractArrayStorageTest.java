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
        getStorage().clear();
        Resume overflowInsertedResume = new Resume("OverflowedResume");
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                getStorage().save(new Resume("Some__" + i));
            }
        } catch (StorageException e) {
            Assert.fail("The storage will be full and there should not be an overflow, but something went wrong: " + e);
        }
        getStorage().save(overflowInsertedResume);
    }
}
