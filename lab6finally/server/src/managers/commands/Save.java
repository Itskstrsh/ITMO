package managers.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.Person;
import managers.FileManager;
import managers.PersonManager;
import network.Request;
import network.Response;

import java.io.*;
import java.util.TreeSet;

public class Save extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 123131673L;


    public Save(){
        super("save", false);

    }
    @Override
    public Response execute(Request request) {
        try {
            FileManager.saveCollection("collectionforloading.json");
        }catch (IOException e){
            System.out.println("Error with saving " + e.getMessage());
        }
        return new Response("Collection is saved");
    }
}
