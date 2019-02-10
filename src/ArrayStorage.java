import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int size;

    ArrayStorage() {
        this.size = 0;
    }

    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < this.size(); i++) {
            this.storage[i] = null;
        }
    }

    void save(Resume r) {
        if (this.size() < this.storage.length) {
            this.storage[this.size()] = r;
            this.size++;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (this.storage[i].toString().equals(uuid)) {
                return this.storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (this.storage[i].toString().equals(uuid)) {
                for (int j = i; j < this.size(); j++) {
                    this.storage[j] = this.storage[j + 1];
                }
                this.size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(this.storage, 0, this.size());
    }

    int size() {
        return this.size;
    }
}
