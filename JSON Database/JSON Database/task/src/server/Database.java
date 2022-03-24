package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class Database {
    private HashMap<String, String> database;
    private final File file;
    private final Lock readLock;
    private final Lock writeLock;
    private final int MAP_KEY = 0;

    public Database(File file, ReadWriteLock lock) {
        this.file = file;
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    public String set(String[] k,  JsonElement data) {
        writeLock.lock();
        try {
            database = Methods.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(k.length > 1) {
            JsonElement element = JsonParser.parseString(database.get(k[MAP_KEY]));
            Methods.set(element, k, data);
            database.put(k[MAP_KEY],element.toString());
        } else {
            this.database.put(k[MAP_KEY], data.toString());
        }

        try {
            Methods.write(database, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        database.clear();
        writeLock.unlock();
        return "OK";
    }

    public String get(String[] k) {
        readLock.lock();
        try {
           database = Methods.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!database.containsKey(k[MAP_KEY])) {
            database.clear();
            readLock.unlock();
            return "ERROR";
        } else {
            JsonElement element = JsonParser.parseString(database.get(k[MAP_KEY]));
            database.clear();
            readLock.unlock();
            if(k.length > 1) {
                return Methods.get(element, k);
            } else {
                return element.toString();
            }

        }
    }

    public String delete(String[] k) {
        writeLock.lock();
        try {
            database = Methods.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!this.database.containsKey(k[MAP_KEY])) {
            database.clear();
            writeLock.unlock();
            return "ERROR";
        } else {
            if(k.length > 1) {
                JsonElement element = JsonParser.parseString(database.get(k[MAP_KEY]));
                Methods.delete(element, k);
                database.put(k[MAP_KEY],element.toString());
            } else {
                this.database.remove(k[MAP_KEY]);
            }

            try {
                Methods.write(database, file);
            } catch (Exception e) {
                e.printStackTrace();
            }

            database.clear();
            writeLock.unlock();
            return "OK";

        }
    }
}
