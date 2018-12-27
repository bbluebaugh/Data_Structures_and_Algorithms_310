/*
 * @author Brian Bluebaugh
 * @Version 9/12/16
 */

package edu.frostburg.cosc310.bluebaugh;
import java.util.Scanner;//import scanner to accept external input

/**
 *
 * @author bbluebaugh
 */
public class SlangTranslator {
    /**
     * @param args the command line arguments
     */
    static String[] wordList;//make an array for the words
    static String[] wordCheck;//array to check the words in the list
    static boolean greaterThanTen;//boolean to determine if the amount of words is more than 10
    static boolean yoChance;//variable for the chance to get yo
    static String userInput;//input from the user
    static String newWord;//new word to go into the array for printing out
    
    public SlangTranslator(){
        wordList = new String[11];
        wordList[10] = null;
        greaterThanTen = false;
        yoChance = false;
    }
    public static void runAll(){
        addWordsToArray();
        fiftyFifty();
        addIzzle();
    }
    //use easy to understand method names; method adds the words into the array
    public static void addWordsToArray(){
        Scanner wordScanner = new Scanner(System.in);
        System.out.println("Please enter the sentence you wish to translate: ");
        userInput = wordScanner.next();
        wordList = userInput.split("\\s+");
        for (int i = 0; i < 11; ++i){
            wordList[i] = wordList[i].replaceAll("[^\\w", "");
            System.out.println(i + wordList[i]);
        }
        if(wordList[11] != null){
            greaterThanTen = true;
        }
    }
    //method to run probability on getting Yo at the beginning of the sentence;
    public static void fiftyFifty(){
        yoChance = true;
    }
    //the main part that exevutes everything I chose to check specific cases first like fun and cool
    public static void addIzzle(){
        for(int i = 0; i < 10; ++i){
            if(wordList[i] == "cool" || wordList[i] == "fun"){
                wordList[i] = "fly";//swap fun with fly
            }            
            else if(wordList[i] == "for"){
                wordList[i] = "fo";
            }
            else if(wordList[i] == "sure"){
                wordList[i] = "shizzle";
            }
            else if(wordList[i].length() < 3){
                //break
                break;
            }
            wordToLetters(wordList[i]);
            for(int j = 0; j < wordCheck.length; ++j){
                if(wordCheck[j].equals("a")){
                    wordList[i] = wordList[i];
                    break;
                }
                else if(wordCheck[j].equals("e")){
                    wordList[i] = wordList[i];
                    break;
                }
                else if(wordCheck[j].equals("i")){
                    wordList[i] = wordList[i];
                    break;
                }
                else if(wordCheck[j].equals("o")){
                    wordList[i] = wordList[i];
                    break;
                }
                else if(wordCheck[j].equals("e")){
                    wordList[i] = wordList[i];
                    break;
                }
                else{
                     String temp;
                     temp = wordCheck[j];
                     newWord = newWord + temp;
                }
            }        
        }
    }
    public static String wordToLetters(String str){
        wordCheck = new String[str.length()];
        for(int i = 0; i < str.length(); ++i){
            char letter = str.charAt(i);
            wordCheck[i] = letter + "";
        }
        return str;
    }
    public static void printTheSentence() {
        if(yoChance == true && greaterThanTen == true) {
            System.out.print("Yo, ");
            for(int i = 0; i < 10; i++) {
                System.out.print(wordList[i] + " ");
                break;
            }
            System.out.println("yadda, yadda, yadda");
        }
        else if(yoChance == true) {
            System.out.print("Yo, ");
            for(int i = 0; i < 10; i++) {
                System.out.print(wordList[i] + " ");
                break;
            }
        }
        else if(greaterThanTen == true) {
            for(int i = 0; i < 10; i++) {
                System.out.print(wordList[i] + " ");
                break;
            }
            System.out.println("yadda, yadda, yadda");
        }
        else {
            for(int i = 0; i < 10; i++) {
                System.out.println(wordList[i] + " ");
            }
        }
    }
}

