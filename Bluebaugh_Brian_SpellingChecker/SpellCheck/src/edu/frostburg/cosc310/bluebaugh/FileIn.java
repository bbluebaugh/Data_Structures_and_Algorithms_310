/*
 * Never Actually implemented
 */
package edu.frostburg.cosc310.bluebaugh;
import java.io.*;
/**
 *
 * @author brian
 */
public class FileIn {
    public void read() throws IOException{
        File file = new File("lexicon.txt");
        //file.createNewFile();
        FileReader fr = new FileReader(file);
        char[] a = new char[50];
        fr.read(a);
        for(char c : a){
            System.out.print(c);
        }
        fr.close();
    }
}
