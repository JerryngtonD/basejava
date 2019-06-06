package com.jbrise.webapp.storage;

import com.jbrise.webapp.exception.StorageException;
import com.jbrise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }

        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }

        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(File file, Resume resume) {

    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    @Override
    protected void doDelete(File file) {

    }

    @Override
    protected Resume doGet(File file) {
        return null;
    }

    @Override
    protected List<Resume> doGetStorageItems() {
        return null;
    }

    @Override
    protected boolean isExistSearchKey(File file) {
        return file.exists();
    }

    @Override
    public void update(Resume resume) {
        super.update(resume);
    }

    @Override
    public Resume get(String uuid) {
        return super.get(uuid);
    }

    @Override
    public void save(Resume resume) {
        super.save(resume);
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        return super.getAllSorted();
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }
}
