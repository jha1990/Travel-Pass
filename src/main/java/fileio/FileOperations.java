package fileio;

import  gui.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class FileOperations {

    public static void writeOperation(Map<String, User> userObject){

        try(FileOutputStream fileWriter = new FileOutputStream("mytisystem.app");
            ObjectOutputStream oos = new ObjectOutputStream(fileWriter)){

            oos.writeObject(userObject);

        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("'mytisystem.app' file is missing. Once you clicked save button then will be created");
        }

    }

    public static Map<String, User> readOperation(){

        try(FileInputStream inputStream = new FileInputStream("mytisystem.app");
        ObjectInputStream ois = new ObjectInputStream(inputStream)){

            Object obj = ois.readObject();

            if(obj instanceof Map){
                Map<String, User> mapObj = (Map<String, User>) obj;
                return mapObj;
            }

        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("'mytisystem.app' file is missing. Once you clicked save button then will be created");
        }

        return null;
    }
}
