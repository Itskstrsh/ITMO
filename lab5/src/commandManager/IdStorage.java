package commandManager;

/**
 * The IdStorage class manages the generation of unique IDs.
 */
public class IdStorage {
    static int currId = 1;
    private static IdStorage instance;

    public static IdStorage getInstance(){
        if (instance == null){
            instance = new IdStorage();
        }
        return instance;

    }
    /**
     * Retrieves the next available ID.
     *
     * @return the next available ID
     */
    static public int getId() {
//        currId += 1;
//        return currId - 1;
         return currId++;
    }

    public static void setCurrId(int currId) {
        IdStorage.currId = currId;
    }
}

