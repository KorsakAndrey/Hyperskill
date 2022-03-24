package server;

import com.google.gson.JsonElement;

import java.io.*;
import java.util.HashMap;

public class Methods {
    private static final int START_KEY = 1;
    public static String delete(JsonElement element, String[] keys) {
        try {
            for (int i = START_KEY; i < keys.length;i++){
                if(i == keys.length - 1){
                    element.getAsJsonObject().remove(keys[i]);
                    break;
                }
                element = element.getAsJsonObject().get(keys[i]);
            }
            return "OK";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public static String get(JsonElement element, String[] keys) {
        try {
            for (int i = START_KEY; i < keys.length; i++) {
                element = element.getAsJsonObject().get(keys[i]);
            }
            return element.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public static String set(JsonElement element, String[] keys, JsonElement value) {
        try {
            for (int i = START_KEY; i < keys.length;i++){
                if(i == keys.length - 1){
                    element.getAsJsonObject().add(keys[i], value);
                    break;
                }
                if(!element.getAsJsonObject().has(keys[i])) {
                    element.getAsJsonObject().addProperty(keys[i],"" );
                }
                element = element.getAsJsonObject().get(keys[i]);
            }
            return "OK";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public static void write(HashMap<String, String> database, File file) throws Exception {
        try {
            FileOutputStream fos=new FileOutputStream(file, false);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(database);
            oos.flush();
            oos.close();
            fos.close();
        } catch(Exception e) {
            throw new Exception(e);
        }
    }

    public static HashMap<String, String> read( File file) throws Exception {
        try {
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);
            try {
                HashMap<String, String> database = (HashMap<String, String>) ois.readObject();
                return database;

            }catch (Exception e) {};
            ois.close();
            fis.close();
            return new HashMap<String, String>();

        } catch(Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>();

    }
}