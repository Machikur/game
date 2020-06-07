package com.kodilla.datahandler;

import java.io.*;

public class DataHandler {

    private File file = new File("Game/resources/ranking.txt");

    public Object loadFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Object o = new Object();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            o = objectInputStream.read();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return o;
    }

    public void saveFile(Object o) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
