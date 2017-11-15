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
public class PartTimeEmployee extends EmployeeInfo {
    private double hourlyWage;
    private double hoursPerWeek;
    private double weeksPerYear;

    /**
     * constructor for part-time employee object, uses super to set EmployeeInfo's properties
     * @param empNumber the employee number
     * @param firstName the first name
     * @param lastName the last name
     * @param gender the gender encoded as an integer (0 male, 1 female, 2 other)
     * @param workLocation work location encoded as integer (0 Mississauga, 1 Ottawa, 2 Chicago)
     * @param deductionsRate the rate of deduction
     * @param hourlyWage the hourly wage
     * @param hoursPerWeek hours worked per week
     * @param weeksPerYear weeks worked per year
     */
    public PartTimeEmployee(int empNumber, String firstName, String lastName, int gender, int workLocation, double deductionsRate, double hourlyWage, double hoursPerWeek, double weeksPerYear) {
        super(empNumber, firstName, lastName, gender, workLocation, deductionsRate);

        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
        this.weeksPerYear = weeksPerYear;
    }

    // getters and setters
    public double getHourlyWage() {
        return hourlyWage;
    }
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
    public double getHoursPerWeek() {
        return hoursPerWeek;
    }
    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
    public double getWeeksPerYear() {
        return weeksPerYear;
    }
    public void setWeeksPerYear(double weeksPerYear) {
        this.weeksPerYear = weeksPerYear;
    }

    /**
     * calcAnnualNetIncome
     * calculates annual income
     * @return net annual income
     */
    public double calcAnnualNetIncome() {
        return hourlyWage * hoursPerWeek * weeksPerYear * (1 - deductionsRate);
    }
}
