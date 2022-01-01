package _WriteReadFile;

import java.io.*;
import java.util.ArrayList;

public class IOFile<E> {
    public void writeFile(ArrayList<E> arrayData, String pathName) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(pathName)));
            objectOutputStream.writeObject(arrayData);
            objectOutputStream.close();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public ArrayList<E> readFile(String pathName) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(pathName));
            return (ArrayList<E>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
        }
        return null;
    }
}
