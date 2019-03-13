package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.NotExistStorageException;
import com.jbrise.webapp.model.Resume;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    void clear() {
    }

    @Test
    void update() {
    }

    @Test
    void getAll() {
    }

    @Test
    void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    void get() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test()
    public void getNotExist() throws Exception {
        assertThrows(NotExistStorageException.class,
                () -> storage.get("dummy"));
    }
}