package com.kodilla.datahandler;

import java.io.*;

public class DataHandler {

    public Object loadFile(String fileName) {
        File file = getFile(fileName);
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
            s.printStackTrace();
        }

        return o;
    }

    public void saveFile(Object o, String fileName) {
        try {
            File file = getFile(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
        } catch (IOException s) {
            s.printStackTrace();
        }
    }

    private File getFile(String fileName) {
        String directoryName = "data";
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return new File(directoryName + "/" + fileName);
    }
}
