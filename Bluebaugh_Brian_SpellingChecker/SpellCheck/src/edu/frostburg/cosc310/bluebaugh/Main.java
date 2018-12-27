/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;
import java.io.*;
import java.util.*;

/**
 *
 * @author brian
 */
public class Main {
    public static HashMap benZobrist = new HashMap();
    public static SpellCheck johnCena = new SpellCheck(benZobrist);
    //static String word;
    //static Scanner wordScanner = new Scanner(System.in);
    //word = wordScanner.next();
    public static void readIn() throws IOException{
        File file = new File("C:\\Users\\brian\\Documents\\NetBeansProjects\\SpellCheck\\src\\edu\\frostburg\\cosc310\\bluebaugh\\lexicon.txt");//used a file called wordList.txt for testing
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        while((line = br.readLine()) != null){
            benZobrist.put(line);
            
        }
        //System.out.println("Zpbrost 4 [resodenta");
        br.close();
    }
    public static void main(String[] args){
        boolean run = true;
        while(run == true){
            System.out.println("Welcome to my spelling checker, enter a word to spell check or, to stop just press 'enter key without any text.");
            Scanner wordScan = new Scanner(System.in);
            String userInput;
            userInput = wordScan.nextLine();
            userInput = userInput.toLowerCase();
            if(userInput.equals("")){
                run = false;
            }
            if(run == true){
                try{
                    readIn();
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("your results: ");
                System.out.println(benZobrist.get(userInput));
                System.out.println(johnCena.dyslexic(userInput));
                System.out.println(johnCena.addLetter(userInput));
                System.out.println(johnCena.removeLetter(userInput));
            }
            //testing individual cases and such
        //try{
          //  readIn();
        //} catch(Exception e){
         //   e.printStackTrace();
        //}
        
        //System.out.println(benZobrist.get("ungoy"));
        //System.out.println("\n");
        //System.out.print(johnCena.dyslexic(wordScanner).toString());
       //System.out.println("\n");
        //System.out.println(benZobrist.get("ungodpy"));
        //System.out.println("\n");
        //System.out.print(johnCena.addLetter("ungodlly").toString());
        //System.out.println("\n");
        //System.out.print(johnCena.removeLetter("ungody").toString());
    }
    }
}
