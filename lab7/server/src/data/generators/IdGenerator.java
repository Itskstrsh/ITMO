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
        int id = (int)Math.floor(Math.random()*12312312) + 1231212;
        while (idList.contains(id)){
            id = (int)Math.floor(Math.random()*(123123123)) + 234234;
        }
        idList.add(id);
        return id;
    }

    public static boolean idIsUnique(int id) {
        if (idList.contains(id)) {
            return false;
        }
        return true;
    }

    public static void add(int id) {
        idList.add(id);
    }
}
