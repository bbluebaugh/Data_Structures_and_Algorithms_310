/*
 * This is a program that simulated the game of duck duck goose
 * Using a Circularly Linked list it starts with the first person and goes around in a circle
 * until it picks goose then the goose chases It back to the goose's spot whoever makes it first wins
 */
package edu.frostburg.cosc310.bluebaugh;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author bbluebaugh
 */
public class DuckDuckGoose extends CircleList {
    boolean snacks = false;//is it snack time yet?
    boolean pokemonGo = false;//who wants to play pokemonGO?
    boolean gooseWins;//easiest way to chose a winner is picking one and then set cases for the chosen one
    char gender;//what are you?
    int age;//how old are you?
    int duckAmount;//how many players
    int startSpeedOfGoose;//how fast the goose starts
    int startSpeedOfIt;//how fast the original "it" player is
    Node it;//who is currently it
    Node goose;//who has been chosen
    String name;//what is the name of the person
    
    public void DuckDuckGoose(){
        
    }
    //run all the things
    public static void main(String[] args){
        DuckDuckGoose game = new DuckDuckGoose();
        CircleList list = new CircleList();
        game.runEverything(list);
    }
    //is it snack time?
    public void snackTime(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(50) + 1;
        if(10 > randomNumber){
            snacks = true;
        }
    }
    //time for pokemon go?
    public void pokemonGo(){
        pokemonGo = false;
        Random rand = new Random();
        int randomNumber = rand.nextInt(50) + 1;
        if(randomNumber < 25){
            pokemonGo = true;
        }
    }
    //kids joining the game at start, ran out of time wasn't able to read from file 
    public void newKids(CircleList list){
        //used for testing initially
        //list.addNode("John", 8, 'm');
        //list.addNode("Jessica", 27, 'f');
        //list.addNode("Cameron", 20, 'm');
        //list.addNode("Steven", 35, 'm');
        //list.addNode("Tabby", 10, 'f');
        //list.addNode("Joe", 16, 'm');
        //list.addNode("Katelynn", 5, 'm');
        File file = new File("children.txt");
        
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String name = sc.next();
                int age = sc.nextInt();
                String gender = sc.next();
                list.addNode(name, age, gender);
                System.out.println(line);
            }
            sc.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    //who is it
    public void setIt(){
        it = root;//start with first node
        deleteNode(1);
        System.out.println(it.name + " is it!");
    }
    //who has been chosen
    public void goose(){
        duckAmount = 1;
        Random rand = new Random();
        int randomNumber = rand.nextInt(10);//make sure its fair for everyone
        current = root;
        for(int i = 0; i<= randomNumber; i++){
            current = current.nextNode;
            duckAmount++;
        }
        goose = current;
    }
    //this person is it
    public void it(){
        for(int i = 1; i< duckAmount; i++){
            System.out.print("duck ");
        }
        System.out.println("goose");
        if(gooseWins == true){
            System.out.println(it.name + " got wrecked by the goose " + goose.name + " and is still it.");//i assumed you would take of for "misspellings" even though wrecked in pop culture is spelled rekt so i made it how it is normally spelled
            gooseWins = false;
            goose.speed = startSpeedOfGoose;
            it.speed = startSpeedOfIt;
        }
        else{
            System.out.println(it.name + " is victorious and beat the goose. " + goose.name + " is now it.");
            Node temp;
            goose.speed = startSpeedOfGoose;
            it.speed = startSpeedOfIt;
            temp = goose;
            goose = it;
            it = temp;
        }
        System.out.println();
    }
    //this will decide who wins and how
    public void itAgainstGoose(){
        if(goose.speed == it.speed){
            gooseWins = false;//since it picked first if speed is equal then it should always win
        }
        else if(goose.speed > it.speed){
            gooseWins = true; //goose was faster than it
        }
        else if(it.speed > goose.speed){
            gooseWins = false;//it beat goose therefore goose loses
        }
    }
    //alteration of speeds or else the slowest person will remain it until everyone leaves
    public void slowDown(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(50) + 1;
        if(randomNumber < 25){
            if(goose.age > 8){
                startSpeedOfGoose = (int) goose.speed;
                goose.speed = (goose.speed)/2;   
            }
            if(it.age > 8){
                startSpeedOfIt = (int) it.speed;
                it.speed = (it.speed)/2;
            }
        }
    }
            
    public void leaving(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(Node.noOfLinkedList) + 1;
        current = root;
        for(int i =0; i < randomNumber - 1; i++){
            System.out.print(current.name + " ");
            current = current.nextNode;
        }
        System.out.println("Number: " + randomNumber);
        System.out.println(current.name + " has left to play PokemonGO!");
        deleteNode(randomNumber);
    }
    
    public void runEverything(CircleList list){
        newKids(list);
        setIt();
        do{
            pokemonGo();//if someone leaves immediatedly
            if(pokemonGo == true){
                leaving();
            }
            current = root;
            for(int i = 0; i < Node.noOfLinkedList; i++){
                System.out.print(current.name + " ");
                current = current.nextNode;
            }
            System.out.println();
            goose();
            startSpeedOfIt = (int) it.speed;
            startSpeedOfGoose = (int) goose.speed;
            slowDown();
            itAgainstGoose();
            it();
            snackTime();
            if(snacks == true){
                System.out.println("It is snack time, everyone leaves to get snacks");
                break;
            }            
        }while(Node.noOfLinkedList > 4);//play until only 4 remain
        System.out.println("There are not enough to continue playing, GG No Rematch");                
    }
}
