/*
 * @author Brian Bluebaugh
 *@Version 9/13/16
 */
package edu.frostburg.cosc310.bluebaugh;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author Brian Bluebaugh
 */
public class SlangTranslator {
    static Scanner inputScanner = new Scanner(System.in);//scanner for external input
    static ArrayList<String> wordList;//used to store the words
    static boolean greaterThanTen = false;//check to see if you need yada yada yada
    static boolean yoChance = false;//boolean variable to change the possiblity of getting yo to start
    static String userInput;//the input of the user
    static String newWord = "";//new word to put into array for printing
    static String[] wordCheck;//used to check the words
    static int wordSize;//used to check array size
    static boolean programContinue = true;//used to check to see if there is more to translate
    //constructor
    public SlangTranslator() {
        greaterThanTen = false;
        yoChance = false;
    }
    //this method places the words into an array from a sentence 
    public static void putWordsInArray() {
        String string = userInput;
        string = string.toLowerCase();//convert everything to lowercase for easier if statements
        string = string.trim();//trim off the spaces for less chance of error
        if(string.equals("")){//exit if enter key is input without a sentence is typed
            programContinue = false;
        }
        wordList = new ArrayList<String>();
        for(String str : string.split(" ")){
            wordList.add(str);
        }
        if(wordList.size() > 10){
            greaterThanTen = true;
            wordList.subList(0, 9);
        } 
    }
    //Converts words to individual letters to easily make changes
    public static String wordToLetters(String str) {
        wordCheck = new String[str.length()];
        wordSize = str.length();
        for(int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            wordCheck[i] = letter + "";
        }
        return str;
    }
    //Approximate 50% chance of getting yo
    public static void fiftyFifty() {
        Random rand = new Random();//use random and integers to better understand how the probability is achieved
        int randomNumber = rand.nextInt(50) + 1;
        if(randomNumber > 24){
            yoChance = true;
        }
    }
    //Checks to see if a word needs changed and if so how it needs changed, i.e. fun to fly or hello to hizzle
    public static void addIzzle() {
        for(int i = 0; i < wordList.size(); i++) {
            if(wordList.get(i).equals("fun")  || wordList.get(i).equals("cool")) {
                wordList.set(i, "fly");
            }
            else if(wordList.get(i).equals("for")) {
                wordList.set(i, "fo");
            }
            else if(wordList.get(i).equals("sure")) {
                wordList.set(i, "shizzle");
            }
            else if(wordList.get(i).length() < 3) {
                newWord = "";
            }
            else if(wordList.get(i).length() > 2) {
                wordToLetters(wordList.get(i));
                for(int j = 0; j < wordSize; j++) {
                    if(wordCheck[j].equals("a")){
                        break;
                    }
                    else if(wordCheck[j].equals("e")){
                        break;
                    }
                    else if(wordCheck[j].equals("i")){
                        break;
                    }
                    else if(wordCheck[j].equals("o")){
                        break;
                    }
                    else if(wordCheck[j].equals("u")){
                        break;
                    }
                    else {
                        String temp;
                        temp = wordCheck[j];
                        newWord = newWord + temp;
                    }
                }
                newWord = newWord + "izzle";
                wordList.set(i, newWord);
            }
            newWord = "";
        }
    }   
    //Converts the input from the user to the 90s slang equivalent also adds yo or yada etc.
    public static void sentence() {
        if(yoChance == true && greaterThanTen == true) {
            System.out.print("Yo, ");
            for(int i = 0; i < wordList.size(); i++) {
                System.out.print(wordList.get(i) + " ");
            }
            System.out.println("yadda, yadda, yadda");
        }
        else if(yoChance == true) {
            System.out.print("Yo, ");
            for(int i = 0; i < wordList.size(); i++) {
                System.out.print(wordList.get(i) + " ");
            }
            System.out.println();
        }
        else if(greaterThanTen == true) {
            for(int i = 0; i < wordList.size(); i++) {
                System.out.print(wordList.get(i) + " ");
            }
            System.out.println("yadda, yadda, yadda");
        }
        else {
            for(int i = 0; i < wordList.size(); i++) {
                System.out.print(wordList.get(i) + " ");
            }
            System.out.println();
        }
    }
    //I use this method to run the other methods
    public static void runEverything() {
        while(programContinue == true){
        System.out.println("Press enter to leave or input a sentence to translate");
        userInput = inputScanner.nextLine();
        putWordsInArray();
            if(programContinue == true){
                fiftyFifty();
                addIzzle();
                sentence();
            }
        }
        System.out.println("Thanks for using this translator");
    }
}