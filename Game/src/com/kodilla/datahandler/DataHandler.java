package com.kodilla.datahandler;

import com.kodilla.userinterface.view.ranking.UserScore;

import java.io.*;
import java.util.ArrayList;

public class DataHandler {


    public Object loadFile(File file) {
        if (file.length() == 0) {
            return new ArrayList<UserScore>();
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

    public void saveFile(Object o,File file) {
        try {
            file.delete();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
