package data.generators;

import java.util.ArrayList;
import java.util.Map;

/**
 * The IdStorage class manages the generation of unique IDs.
 */
public class IdGenerator {
    private static ArrayList<Integer> idList = new ArrayList<>();
    private static int nextId = 0;

    public IdGenerator() {
        idList = new ArrayList<>();
    }

    public static int generateId() {
        int id = nextId;
        idList.add(id);
        nextId++;
        return id;
    }

    public static boolean idIsUnique(int id) {
        if (idList.contains(id)) {
            return false;
        }
        return true;
    }

    public static void remove(int id) {
        idList.remove(id);
        if (id < nextId) {
            nextId = id;
        }
    }

    public static void add(int id) {
        if (id >= nextId) {
            nextId = id + 1;
        }
        idList.add(id);
    }
}
