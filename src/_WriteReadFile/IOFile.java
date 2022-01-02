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
        File file = new File(pathName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                return (ArrayList<E>) objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println();
        }
        return null;
    }
}
