/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatabase;

import java.util.ArrayList;

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
     */
    public boolean addEmployee(EmployeeInfo newEmployee) {
        int empNumber = newEmployee.getEmpNumber(); // gets employee number
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
    public boolean removeEmployee(int empNumber) {
        int bucket = hash(empNumber); // call the hash function
        int index = searchInBucket(bucket, empNumber); // searches for employee by employee number
        if (index > -1) {
            // employee was found
            buckets[bucket].remove(index);
            numEmployees --;
            return true;
        } else {
            // employee was not found
            return false;
        }
    }

    /**
     * search: searches a bucket for an employee number
     * returns index or -1 if not found
     * @param bucket the index of the bucket to search in
     * @param empNumber the employee number
     * @return index or -1 if not found
     */
    public int searchInBucket(int bucket, int empNumber) {
        // loop through each item in the bucket
        for (int i = 0; i < buckets[bucket].size(); i++) {
            if (buckets[bucket].get(i).getEmpNumber() == empNumber) {
                // found employee
                return i;
            }
        }
        // not found
        return -1;
    }

    /**
     * displays the data
     * @return the display output
     */
    public String display() {
        String output = "";

        // loop through buckets
        for (int i = 0; i < numBuckets; i++) {
            // loop through each bucket
            for (int j = 0; j < buckets[i].size(); j++) {
                output += buckets[i].get(j).getEmpNumber();

                // add commas
                if (i != numBuckets - 1 || j != buckets[i].size() - 1) {
                    output += ", ";
                }
            }
        }

        System.out.println(output);
        return output;
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
     * @param key the key to hash by
     */
    private int hash(int key) {
        return key % numBuckets; // modulo by number of buckets
    }
}
