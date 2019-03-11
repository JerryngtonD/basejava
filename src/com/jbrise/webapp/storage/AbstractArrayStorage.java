package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.ExistStorageException;
import com.jbrise.webapp.exception.NotExistStorageException;
import com.jbrise.webapp.exception.StorageException;
import com.jbrise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int resumeIdx = getIndex(resume.getUuid());
        if (resumeIdx >= 0) {
            storage[resumeIdx] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int resumeIdx = getIndex(uuid);
        if (resumeIdx >= 0) {
            return storage[resumeIdx];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            int idx = getIndex(resume.getUuid());
            if (idx < 0) {
                insertResume(idx, resume);
                size++;
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int resumeIdx = getIndex(uuid);
        if (resumeIdx >= 0) {
            shiftOnDeletedResume(resumeIdx);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void insertResume(int idx, Resume resume);

    protected abstract void shiftOnDeletedResume(int deletedResumeIdx);

    protected abstract int getIndex(String uuid);
}
