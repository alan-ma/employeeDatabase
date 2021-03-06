/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatabase;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alan
 *
 * HashTable: an open-hashing, closed-addressing hash table
 */
public class HashTable {
    private ArrayList<EmployeeInfo>[] buckets; // the buckets to sort to
    private int numBuckets; // the number of buckets
    private int numEmployees; // number of employees
    private String csvFile = "employeeDatabase.csv";

    /**
     * constructor: creates a new hash table with k buckets
     * @param k number of buckets
     */
    public HashTable(int k) {
        numBuckets = k; // sets numBuckets
        numEmployees = 0; // sets number of employees
        buckets = new ArrayList[k]; // instantiates the hash table

        for (int i = 0; i < k; i++) {
            buckets[i] = new ArrayList<>(); // initializes each ArrayList
        }
    }

    /**
     * addEmployee: adds a new employee
     * returns true on successful addition
     * @param newEmployee the new employee object
     * @return true after adding employee
     */
    public boolean addEmployee(EmployeeInfo newEmployee) {
        String empNumber = newEmployee.getEmpNumber(); // gets employee number
        int bucket = hash(empNumber); // call the hash function
        buckets[bucket].add(newEmployee); // adds to the correct bucket
        numEmployees ++;
        saveDatabase();
        return true;
    }
    
    /**
     * addEmployee: adds a new employee without writing data
     * returns true on successful addition
     * @param newEmployee the new employee object
     * @return true after adding employee
     */
    public boolean addEmployee(EmployeeInfo newEmployee, boolean noSave) {
        String empNumber = newEmployee.getEmpNumber(); // gets employee number
        int bucket = hash(empNumber); // call the hash function
        buckets[bucket].add(newEmployee); // adds to the correct bucket
        numEmployees ++;
        return true;
    }

    /**
     * removeEmployee: removes an employee by employee number
     * returns true on successful deletion, false if employee was not found
     * @param empNumber employee to remove
     * @return true for deleted, false for not found
     */
    public boolean removeEmployee(String empNumber) {
        int bucket = hash(empNumber); // call the hash function
        int index = searchInBucket(bucket, empNumber); // searches for employee by employee number
        if (index > -1) {
            // employee was found
            buckets[bucket].remove(index);
            numEmployees --;
            saveDatabase();
            return true;
        } else {
            // employee was not found
            return false;
        }
    }
    
    /**
     * saveDatabase: saves the employees to a file
     * @return false if save fails
     */
    public boolean saveDatabase() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(csvFile));
            String[][] databaseSave;
            databaseSave = displaySaveData();
            for (int row = 0; row < databaseSave.length; row++) {
                for (int col = 0; col < databaseSave[row].length; col++) {
                    bw.write(databaseSave[row][col]);
                    if (col < databaseSave[row].length - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(HashTable.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    return false;
                }
            }
        }
    }
    
    /**
     * search: searches entire hash table for an employee number
     * @param empNumber
     * @return employee class or null if not found
     */
    public EmployeeInfo search(String empNumber) {
        int bucketNum = hash(empNumber);
        if (bucketNum > -1) {
            int bucketIndex = searchInBucket(bucketNum, empNumber);
            if (bucketIndex > -1) {
                return buckets[bucketNum].get(bucketIndex);
            }
        }
        
        return null;
    }

    /**
     * search: searches a bucket for an employee number
     * returns index or -1 if not found
     * @param bucket the index of the bucket to search in
     * @param empNumber the employee number
     * @return index or -1 if not found
     */
    public int searchInBucket(int bucket, String empNumber) {
        // loop through each item in the bucket
        for (int i = 0; i < buckets[bucket].size(); i++) {
            if (buckets[bucket].get(i).getEmpNumber().equals(empNumber)) {
                // found employee
                return i;
            }
        }
        // not found
        return -1;
    }

    /**
     * displays the data
     * @return the display output in an array
     */
    public String[][] display() {
        List<String[]> outputList = new ArrayList<>();

        // loop through buckets
        for (int i = 0; i < numBuckets; i++) {
            // loop through each bucket
            for (int j = 0; j < buckets[i].size(); j++) {
                outputList.add(buckets[i].get(j).display());
            }
        }

        String[][] outputArray = new String[outputList.size()][];
        outputArray = outputList.toArray(outputArray);
        
        return outputArray;
    }
    
    /**
     * displays the data with a filter on employee number
     * @param filterKey the key to filter by
     * @return the display output in an array
     */
    public String[][] display(String filterKey) {
        List<String[]> outputList = new ArrayList<>();

        // loop through buckets
        for (int i = 0; i < numBuckets; i++) {
            // loop through each bucket
            for (int j = 0; j < buckets[i].size(); j++) {
                if (buckets[i].get(j).getEmpNumber().contains(filterKey)) {
                    outputList.add(buckets[i].get(j).display());
                }
            }
        }

        String[][] outputArray = new String[outputList.size()][];
        outputArray = outputList.toArray(outputArray);
        
        return outputArray;
    }
    
        /**
     * displays the data for the save file
     * @return the display output in an array
     */
    public String[][] displaySaveData() {
        List<String[]> outputList = new ArrayList<>();

        // loop through buckets
        for (int i = 0; i < numBuckets; i++) {
            // loop through each bucket
            for (int j = 0; j < buckets[i].size(); j++) {
                outputList.add(buckets[i].get(j).displaySaveInfo());
            }
        }

        String[][] outputArray = new String[outputList.size()][];
        outputArray = outputList.toArray(outputArray);
        
        return outputArray;
    }
    
    /**
     * getter for number of employees
     * @return number of employees
     */
    public int getNumEmployees() {
        return numEmployees;
    }
    
    /**
     * getter for number of buckets
     * @return number of buckets
     */
    public int getNumBuckets() {
        return numBuckets;
    }
    
    /**
     * hash: hash function
     * modulo by number of buckets
     * @param key the key to hash by or -1 if not valid
     */
    private int hash(String key) {
        try {
            int empNum = Integer.parseInt(key);
            if (empNum >= 0) {
                return empNum % numBuckets; // modulo by number of buckets
            } else {
                return -1;
            }
        }
        catch (NumberFormatException nfe) {
            return -1;
        }
    }
}
