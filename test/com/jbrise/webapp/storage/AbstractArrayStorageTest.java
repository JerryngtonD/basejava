package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.NotExistStorageException;
import com.jbrise.webapp.model.Resume;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    /**
     * Util part
     */
    static class MyAssertions {
        public static void assertDoesNotThrow(FailingRunnable action) {
            try {
                action.run();
            } catch (Exception ex) {
                throw new Error("expected action not to throw, but it did!", ex);
            }
        }
    }

    @FunctionalInterface
    interface FailingRunnable {
        void run() throws Exception;
    }

    /**
     * Mock-data part
     */
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private void checkSize(int size) {
        assertEquals(size, storage.size());
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() {
        storage.clear();
        checkSize(0);
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_1);
        assertEquals(newResume.getUuid(), storage.get(UUID_1).getUuid());
        storage.update(newResume);
    }

    @Test
    void getAll() {
        Resume[] resumeList = storage.getAll();
        assertEquals(3, resumeList.length);
        MyAssertions.assertDoesNotThrow(() -> Stream.of(RESUME_1, RESUME_2, RESUME_3).map(resume -> storage.get(resume.getUuid())));
    }

    @Test
    void size() {
        checkSize(3);
    }

    @Test
    void get() {
        MyAssertions.assertDoesNotThrow(() -> storage.get(UUID_1));
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        checkSize(4);
        assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test
    void delete() {
        storage.delete(UUID_3);
        checkSize(2);
        assertThrows(NotExistStorageException.class,
                () -> storage.get(UUID_3));
    }

    @Test()
    public void getNotExist() {
        assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }
}