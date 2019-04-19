package com.jbrise.webapp.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;


/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private String uuid;

    private String fullName;


    public Resume() {
        this.fullName = "incognito";
        this.uuid = UUID.randomUUID().toString();
    }

    public Resume(String fullName) {
        this();
        this.fullName = fullName;
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.getUuid()) && fullName.equals(resume.getFullName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        return Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName).compare(this, o);
    }
}
