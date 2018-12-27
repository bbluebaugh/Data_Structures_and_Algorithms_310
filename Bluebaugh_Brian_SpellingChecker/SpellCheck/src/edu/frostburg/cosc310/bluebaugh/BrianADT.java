/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc310.bluebaugh;

/**
 *
 * @author brian
 */
public interface BrianADT {
    String get(String wordInput);
    void put(String wordInput);
    boolean remove(String wordInput);
    int size();
    boolean isEmpty();
    void entrySet();
    void keySet();
    void values();
}
