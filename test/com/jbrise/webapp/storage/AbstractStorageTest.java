package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.ExistStorageException;
import com.jbrise.webapp.exception.NotExistStorageException;
import com.jbrise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String DEFAULT_FULLNAME = "incognito";

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1, "Alexander Alexandrov");

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2, "Boris Borisov");

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3, DEFAULT_FULLNAME);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4, DEFAULT_FULLNAME);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected Storage getStorage() {
        return storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_3);
        storage.save(RESUME_2);
        storage.save(RESUME_1);
    }

    @Test
    public void clear() {
        storage.clear();
        checkSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "Igor Alehin");
        storage.update(newResume);
        assertEquals(newResume.getUuid(), storage.get(UUID_1).getUuid());
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume resumeWithNotExistUuid = new Resume("dummy");
        storage.update(resumeWithNotExistUuid);
    }

    @Test
    public void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getIfNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumeList = storage.getAllSorted();
        assertEquals(3, resumeList.size());

        //check order of elements on output
        assertEquals(resumeList, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        checkSize(4);
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_3);
        checkSize(2);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void size() {
        checkSize(3);
    }

    private void checkSize(int size) {
        assertEquals(size, storage.size());
    }
}