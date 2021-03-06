package Assignments.five.Oppg1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HashTable {

    private final Node[] array;
    private final int arraySize;

    private int collision = 0;
    private int numberOfNames = 0;

    public HashTable(int m){
        arraySize = m;
        array = new Node[m];
        try {
            readFile();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     *
     * @return the average collisons each element
     */

    public double getAverageCollisions(){
        return (double) collision/numberOfNames;
    }

    /**
     * Add a value to the hashTable
     * @param value the String value to be added
     */

    private void addValue(String value) {
        numberOfNames++;
        Node n = new Node(value);

        int index = generateIndex(value);


        if (array[index] != null) { //Collision
            System.out.println(array[index].getValue() + ", " + n.getValue());
            n.setPointer(array[index]);
            collision++;
        }
        array[index] = n;
    }

    /**
     * MEthod for finding the position of a value in the HashTable
     * @param n value to find position of
     * @return position
     */

    public String findPosition(String n){
        int index = generateIndex(n);

        Node current = array[index];

        int count = 0;

        while (!current.getValue().equals(n)){
            count++;
            current = current.getPointer();
        }
        if(current == null){
            return "not found";
        }else {
           return "index: " + index + ", place: " + count;
        }
    }

    /**
     * gets an integer based on the value of each character in the string
     * @param value string to find value og
     * @return the value of the string
     */
    private int generateIndex(String value){

        int valueInteger = 0;
        int i;
        for (i = 0; i < value.length(); i++) {
            char c = value.charAt(0);
            valueInteger += c + Math.pow(i, 2);
        }

        return hash(valueInteger);
    }

    /**
     *
     * @param k integer based on the string
     * @return an index for the string
     */
    private int hash(int k){
        return k % arraySize;
    }

    /**
     * Method for reading navn.txt and adding the values to the list.
     * @throws IOException if the file is not found
     */
    private void readFile() throws IOException {
        File file = new File("Assignments/five/Oppg1/navn.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null){
            addValue(st);
        }
    }
    public double calculateLoadFactor(){
        return (double) numberOfNames/arraySize;
    }
}
