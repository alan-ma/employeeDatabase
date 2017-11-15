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
public class FullTimeEmployee extends EmployeeInfo {
    private double yearlySalary;

    /**
     * constructor for full-time employee object, uses super to set EmployeeInfo's properties
     * @param empNumber the employee number
     * @param firstName the first name
     * @param lastName the last name
     * @param gender the gender encoded as an integer (0 male, 1 female, 2 other)
     * @param workLocation work location encoded as integer (0 Mississauga, 1 Ottawa, 2 Chicago)
     * @param deductionsRate the rate of deduction
     * @param yearlySalary the annual salary
     */
    public FullTimeEmployee(int empNumber, String firstName, String lastName, int gender, int workLocation, double deductionsRate, double yearlySalary) {
        super(empNumber, firstName, lastName, gender, workLocation, deductionsRate);

        this.yearlySalary = yearlySalary;
    }

    // getters and setters
    public double getYearlySalary() {
        return yearlySalary;
    }
    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    /**
     * calcAnnualNetIncome
     * calculates annual income
     * @return net annual income
     */
    public double calcAnnualNetIncome() {
        return yearlySalary * (1 - deductionsRate);
    }
}
