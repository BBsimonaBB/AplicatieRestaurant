package Data;

import BusinessLogic.BaseProduct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializator {


    public static void writeObject(Object v, String destinationFile) throws IOException{
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destinationFile))){
            os.writeObject(v);
            os.flush();
        }
    }


    public static Object readObject(String sourceFile) throws IOException, ClassNotFoundException{
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(sourceFile))){
            return in.readObject();
        }
    }


}