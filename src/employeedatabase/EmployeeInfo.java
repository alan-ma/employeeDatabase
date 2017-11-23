/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatabase;

/**
 *
 * @author alan
 */
public class EmployeeInfo {
    protected String empNumber;
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String workLocation;
    protected double deductionsRate;

    /**
     * constructor for employee info object
     * @param empNumber the employee number
     * @param firstName the first name
     * @param lastName the last name
     * @param gender the gender encoded as an integer (0 male, 1 female, 2 other)
     * @param workLocation work location encoded as integer (0 Mississauga, 1 Ottawa, 2 Chicago)
     * @param deductionsRate the rate of deduction
     */
    public EmployeeInfo(String empNumber, String firstName, String lastName, String gender, String workLocation, double deductionsRate) {
        this.empNumber = empNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.workLocation = workLocation;
        this.deductionsRate = deductionsRate;
    }

    // getter and setter methods for each property
    public String getEmpNumber() {
        return empNumber;
    }
    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getWorkLocation() {
        return workLocation;
    }
    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }
    public double getDeductionsRate() {
        return deductionsRate;
    }
    public void setDeductionsRate(double deductionsRate) {
        this.deductionsRate = deductionsRate;
    }
    
    /**
     * returns the employee info for the dashboard table
     * also decodes information
     * @return 
     */
    public String[] display() {
        String[] employeeInfo = {
            empNumber,
            firstName,
            lastName,
            gender,
            workLocation,
            ""
        };
        
        return employeeInfo;
    }
}
