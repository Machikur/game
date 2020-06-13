package com.kodilla.datahandler;

import java.io.*;

public class DataHandler {

    public Object loadFile(String path) {
        File file = new File(path);
        if (file.length() == 0) {
            return null;
        }
        Object o = new Object();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            o = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException s) {
        }

        return o;
    }

    public void saveFile(Object o, String path) {
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
