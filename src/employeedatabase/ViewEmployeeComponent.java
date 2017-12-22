/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatabase;

import java.awt.event.KeyEvent;
import java.awt.*;

/**
 *
 * @author alan
 */
public class ViewEmployeeComponent extends javax.swing.JFrame {
    // <editor-fold defaultstate="collapsed" desc="variables">
    
    /**
     * display control variables
     */
    private boolean editing;
    private EmployeeInfo employee;
    
    /**
     * variables of a new employee template
     */
    protected int type; // 0 full time, 1 part time
    private boolean typeError;
    protected String firstName;
    private boolean firstNameError;
    protected String lastName;
    private boolean lastNameError;
    protected String gender;
    private boolean genderError;
    protected String employeeNumber;
    private boolean employeeNumberError;
    protected String deductionsRate;
    private boolean deductionsRateError;
    protected String workLocation;
    private boolean workLocationError;
    protected String yearlySalary;
    private boolean yearlySalaryError;
    protected String hourlyWage;
    private boolean hourlyWageError;
    protected String hoursPerWeek;
    private boolean hoursPerWeekError;
    protected String weeksPerYear;
    private boolean weeksPerYearError;
    private boolean employeeUniqueError;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="initialization">
    
    /**
     * Creates new form AddEmployeeForm
     */
    public ViewEmployeeComponent(EmployeeInfo employee) {
        initComponents();
        
        this.employee = employee;
        editing = false;
        
        updateViewDisplay();
        
        reset();
    }
    
    /**
     * initialize color service
     */
    private final ColorSelectorService colorSelector = new ColorSelectorService();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="update view display">
    
    /**
     * update view display method
     */
    private void updateViewDisplay() {
        if (editing) {
            viewEmployeePanel.setVisible(false);
            editEmployeePanel.setVisible(true);
        } else {
            viewEmployeePanel.setVisible(true);
            editEmployeePanel.setVisible(false);
        }
        
        employeeTitle.setText(employee.getLastName() + ", " + employee.getFirstName());
        employeeFirstName.setText(employee.getFirstName());
        employeeLastName.setText(employee.getLastName());
        employeeEmployeeNumber.setText(employee.getEmpNumber());
        employeeGender.setText(employee.getGender());
        employeeWorkLocation.setText(employee.getWorkLocation());
        employeeDeductionsRate.setText(Double.toString(employee.getDeductionsRate()));
        
        if (employee instanceof FullTimeEmployee) {
            FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employee;
            employeeType.setText("full-time");
            employeeYearlySalary.setText("$" + String.format("%.2f", fullTimeEmployee.getYearlySalary()));
            employeeAnnualIncome.setText("$" + String.format("%.2f", fullTimeEmployee.calcAnnualNetIncome()));
            
            yearlySalaryFormatter1.setVisible(true);
            hourlyWageFormatter1.setVisible(false);
            hoursPerWeekFormatter1.setVisible(false);
            weeksPerYearFormatter1.setVisible(false);

        } else if (employee instanceof PartTimeEmployee) {
            PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employee;
            employeeType.setText("part-time");
            employeeHourlyWage.setText("$" + String.format("%.2f", partTimeEmployee.getHourlyWage()));
            employeeHoursPerWeek.setText(String.format("%.2f", partTimeEmployee.getHoursPerWeek()));
            employeeWeeksPerYear.setText(String.format("%.2f", partTimeEmployee.getWeeksPerYear()));
            employeeAnnualIncome.setText("$" + String.format("%.2f", partTimeEmployee.calcAnnualNetIncome()));
            
            yearlySalaryFormatter1.setVisible(false);
            hourlyWageFormatter1.setVisible(true);
            hoursPerWeekFormatter1.setVisible(true);
            weeksPerYearFormatter1.setVisible(true);
        } else {
            yearlySalaryFormatter1.setVisible(false);
            hourlyWageFormatter1.setVisible(false);
            hoursPerWeekFormatter1.setVisible(false);
            weeksPerYearFormatter1.setVisible(false);
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="reset">
    
    /**
     * reset method
     * resets all the fields of the new employee
     */
    public final void reset() {
        // RESET ALL FIELDS
        
        typeError = false;
        firstName = employee.getFirstName();
        firstNameError = false;
        lastName = employee.getLastName();
        lastNameError = false;
        gender = employee.getGender();
        genderError = false;
        employeeNumber = employee.getEmpNumber();
        employeeNumberError = false;
        deductionsRate = Double.toString(employee.getDeductionsRate());
        deductionsRateError = false;
        workLocation = employee.getWorkLocation();
        workLocationError = false;
        
        if (employee instanceof FullTimeEmployee) {
            FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employee;
            type = 0;
            yearlySalary = Double.toString(fullTimeEmployee.getYearlySalary());
            hourlyWage = "";
            hoursPerWeek = "";
            weeksPerYear = "";
        } else if (employee instanceof PartTimeEmployee) {
            PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employee;
            type = 1;
            hourlyWage = Double.toString(partTimeEmployee.getHourlyWage());
            hoursPerWeek = Double.toString(partTimeEmployee.getHoursPerWeek());
            weeksPerYear = Double.toString(partTimeEmployee.getWeeksPerYear());
            yearlySalary = "";
        }
        
        yearlySalaryError = false;
        hourlyWageError = false;
        hoursPerWeekError = false;
        weeksPerYearError = false;
        employeeUniqueError = false;

        firstNameField.requestFocus();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="error check general">
    
    /**
     * error check method
     * checks for errors and returns true if error is found
     * @return boolean, true if errors exist, false otherwise
     */
    public boolean checkErrors() {
        boolean errorsExist = false;
        
        if (checkType()) {
            errorsExist = true;
        }
        
        if (checkGender()) {
            errorsExist = true;
        }
        
        if (checkFirstName()) {
            errorsExist = true;
        }
        
        if (checkLastName()) {
            errorsExist = true;
        }
        
        if (checkEmployeeNumber()) {
            errorsExist = true;
        }
        
        if (checkDeductionsRate()) {
            errorsExist = true;
        }
        
        if (checkWorkLocation()) {
            errorsExist = true;
        }
        
        if (checkYearlySalary() && type == 0) {
            errorsExist = true;
        }
        
        if (checkHourlyWage() && type == 1) {
            errorsExist = true;
        }
        
        if (checkHoursPerWeek() && type == 1) {
            errorsExist = true;
        }
        
        if (checkWeeksPerYear() && type == 1) {
            errorsExist = true;
        }
        
        return errorsExist;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="error message update">
    
    /**
     * error message update method
     * checks if error labels exist and displays messages
     * @return boolean, true if errors exist, false otherwise
     */
    public boolean updateErrorMessage() {
        boolean errorsExist = false;
        String errorMessage = "";
        
        if (typeErrorLabel.isVisible()) {
            errorsExist = true;
            errorMessage = "Error: The employee type must be set.";
        }
        
        if (checkGenderErrorLabel.isVisible()) {
            errorsExist = true;
            errorMessage = "Error: The gender must be set.";
        }
        
        if (firstNameErrorLabel.isVisible()) {
            errorsExist = true;
            errorMessage = "Error: The first name must be at least two characters long.";
        }
        
        if (lastNameErrorLabel.isVisible()) {
            errorsExist = true;
            errorMessage = "Error: The last name must be at least two characters long.";
        }
        
        if (employeeNumberErrorLabel.isVisible()) {
            errorsExist = true;
            if (employeeUniqueError) {
                errorMessage = "Error: The employee number is not unique.";
            } else {
                errorMessage = "Error: The employee number must be a six-digit integer.";
            }
        }
        
        if (deductionsRateErrorLabel.isVisible()) {
            errorsExist = true;
            errorMessage = "Error: The deductions rate must be a number between 0 and 1.";
        }
        
        if (workLocationErrorLabel.isVisible()) {
            errorsExist = true;
            errorMessage = "Error: The work location must be set.";
        }
        
        if (yearlySalaryErrorLabel.isVisible() && type == 0) {
            errorsExist = true;
            errorMessage = "Error: The yearly salary must be set as a number.";
        }
        
        if (hourlyWageErrorLabel.isVisible() && type == 1) {
            errorsExist = true;
            errorMessage = "Error: The hourly wage must be set as a number";
        }
        
        if (hoursPerWeekErrorLabel.isVisible() && type == 1) {
            errorsExist = true;
            errorMessage = "Error: The hours per week must be set as a number";
        }
        
        if (weeksPerYearErrorLabel.isVisible() && type == 1) {
            errorsExist = true;
            errorMessage = "Error: The weeks per year must be set as a number";
        }
        
        errorText.setText("<html>" + errorMessage + "</html>");
        
        return errorsExist;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="custom error checking">
    
    /**
     * check employee type method
     * @return true if employee type is not selected
     */
    private boolean checkType() {
        if (type == -1) {
            typeError = true;
            return true;
        } else {
            typeError = false;
            return false;
        }
    }
    
    /**
     * check first name method
     * @return true if first name is not at least 2 character long
     */
    private boolean checkFirstName() {
        if (firstName.length() <= 1) {
            firstNameError = true;
            return true;
        } else {
            firstNameError = false;
            return false;
        }
    }
    
    /**
     * check last name method
     * @return true if last name is not at least 2 character long
     */
    private boolean checkLastName() {
        if (lastName.length() <= 1) {
            lastNameError = true;
            return true;
        } else {
            lastNameError = false;
            return false;
        }
    }
    
    /**
     * check gender method
     * @return true if gender is not selected
     */
    private boolean checkGender() {
        if (gender.length() == 0) {
            genderError = true;
            return true;
        } else {
            genderError = false;
            return false;
        }
    }
    
    /**
     * check employee number method
     * @return true if employee number is not a 6 digit number
     */
    private boolean checkEmployeeNumber() {
        if (employeeNumber.length() == 6) {
            try {
                int empNum = Integer.parseInt(employeeNumber);
                if (empNum >= 0) {
                    if (checkEmployeeNumberUnique()) {
                        employeeUniqueError = true;
                        employeeNumberError = true;
                    } else {
                        employeeUniqueError = false;
                        employeeNumberError = false;
                    }
                    return false;
                } else {
                    employeeNumberError = true;
                    employeeUniqueError = false;
                    return true;
                }
            }
            catch (NumberFormatException nfe) {
                employeeNumberError = true;
                employeeUniqueError = false;
                return true;
            }
        } else {
            employeeNumberError = true;
            employeeUniqueError = false;
            return true;
        }
    }
    
    /**
     * check employee number unique method
     * @return true if employee number is not unique
     */
    private boolean checkEmployeeNumberUnique() {
        return MainComponent.employeeDatabase.search(employeeNumber) != null && !employeeNumber.equals(employee.getEmpNumber());
    }
    
    /**
     * check deductions rate method
     * @return true if deductions rate is not between 0 and 1
     */
    private boolean checkDeductionsRate() {
        try {
            double dedRate = Double.parseDouble(deductionsRate);
            if (dedRate >= 0 && dedRate <= 1) {
                deductionsRateError = false;
                return false;
            } else {
                deductionsRateError = true;
                return true;
            }
        }
        catch (NumberFormatException nfe) {
            deductionsRateError = true;
            return true;
        }
    }
    
    /**
     * check work location method
     * @return true if work location is empty
     */
    private boolean checkWorkLocation() {
        if (workLocation.length() > 0) {
            workLocationError = false;
            return false;
        } else {
            workLocationError = true;
            return true;
        }
    }
    
    /**
     * check yearly salary method
     * @return true if yearly salary is invalid
     */
    private boolean checkYearlySalary() {
        try {
            double yearlySal = Double.parseDouble(yearlySalary);
            if (yearlySal >= 0) {
                yearlySalaryError = false;
                return false;
            } else {
                yearlySalaryError = true;
                return true;
            }
        }
        catch (NumberFormatException nfe) {
            yearlySalaryError = true;
            return true;
        }
    }
    
    /**
     * check hourly wage method
     * @return true if hourly wage is invalid
     */
    private boolean checkHourlyWage() {
        try {
            double hourWage = Double.parseDouble(hourlyWage);
            if (hourWage >= 0) {
                hourlyWageError = false;
                return false;
            } else {
                hourlyWageError = true;
                return true;
            }
        }
        catch (NumberFormatException nfe) {
            hourlyWageError = true;
            return true;
        }
    }
    
    /**
     * check hours per week method
     * @return true if hours per week is invalid
     */
    private boolean checkHoursPerWeek() {
        try {
            double hoursWeek = Double.parseDouble(hoursPerWeek);
            if (hoursWeek >= 0) {
                hoursPerWeekError = false;
                return false;
            } else {
                hoursPerWeekError = true;
                return true;
            }
        }
        catch (NumberFormatException nfe) {
            hoursPerWeekError = true;
            return true;
        }
    }
    
    /**
     * check weeks per year method
     * @return true if weeks per year is invalid
     */
    private boolean checkWeeksPerYear() {
        try {
            double weeksYear = Double.parseDouble(weeksPerYear);
            if (weeksYear > 0) {
                weeksPerYearError = false;
                return false;
            } else {
                weeksPerYearError = true;
                return true;
            }
        }
        catch (NumberFormatException nfe) {
            weeksPerYearError = true;
            return true;
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="update edit display">
    
    /**
     * update edit display method
     */
    public void updateEditDisplay() {
        switch (type) {
            case 0:
                // set to full-time
                checkFullEmployeeTypeButton.setBackground(colorSelector.check_active_background);
                checkPartEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                yearlySalaryFormatter.setVisible(true);
                hourlyWageFormatter.setVisible(false);
                hoursPerWeekFormatter.setVisible(false);
                weeksPerYearFormatter.setVisible(false);
                break;
            case 1:
                // set to part-time
                checkFullEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                checkPartEmployeeTypeButton.setBackground(colorSelector.check_active_background);
                yearlySalaryFormatter.setVisible(false);
                hourlyWageFormatter.setVisible(true);
                hoursPerWeekFormatter.setVisible(true);
                weeksPerYearFormatter.setVisible(true);
                break;
            default:
                checkPartEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                checkFullEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                yearlySalaryFormatter.setVisible(false);
                hourlyWageFormatter.setVisible(false);
                hoursPerWeekFormatter.setVisible(false);
                weeksPerYearFormatter.setVisible(false);
                break;
        }
        
        switch (gender) {
            case "M":
                // male is selected
                checkGenderMale.setBackground(colorSelector.check_active_background);
                checkGenderFemale.setBackground(colorSelector.check_inactive_background);
                checkGenderOther.setBackground(colorSelector.check_inactive_background);
                break;
            case "F":
                // female is selected
                checkGenderMale.setBackground(colorSelector.check_inactive_background);
                checkGenderFemale.setBackground(colorSelector.check_active_background);
                checkGenderOther.setBackground(colorSelector.check_inactive_background);
                break;
            case "O":
                // other is selected
                checkGenderMale.setBackground(colorSelector.check_inactive_background);
                checkGenderFemale.setBackground(colorSelector.check_inactive_background);
                checkGenderOther.setBackground(colorSelector.check_active_background);
                break;
            default:
                checkGenderMale.setBackground(colorSelector.check_inactive_background);
                checkGenderFemale.setBackground(colorSelector.check_inactive_background);
                checkGenderOther.setBackground(colorSelector.check_inactive_background);
                break;
        }
        
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
        employeeNumberField.setText(employeeNumber);
        deductionsRateField.setText(deductionsRate);
        workLocationField.setText(workLocation);
        yearlySalaryField.setText(yearlySalary);
        hourlyWageField.setText(hourlyWage);
        hoursPerWeekField.setText(hoursPerWeek);
        weeksPerYearField.setText(weeksPerYear);
        
        if (typeError) {
            typeErrorLabel.setVisible(true);
        } else {
            typeErrorLabel.setVisible(false);
        }
        
        if (firstNameError) {
            firstNameErrorLabel.setVisible(true);
        } else {
            firstNameErrorLabel.setVisible(false);
        }
        
        if (lastNameError) {
            lastNameErrorLabel.setVisible(true);
        } else {
            lastNameErrorLabel.setVisible(false);
        }
        
        if (genderError) {
            checkGenderErrorLabel.setVisible(true);
        } else {
            checkGenderErrorLabel.setVisible(false);
        }
        
        if (employeeNumberError) {
            employeeNumberErrorLabel.setVisible(true);
        } else {
            employeeNumberErrorLabel.setVisible(false);
        }
        
        if (deductionsRateError) {
            deductionsRateErrorLabel.setVisible(true);
        } else {
            deductionsRateErrorLabel.setVisible(false);
        }
        
        if (workLocationError) {
            workLocationErrorLabel.setVisible(true);
        } else {
            workLocationErrorLabel.setVisible(false);
        }
        
        if (yearlySalaryError) {
            yearlySalaryErrorLabel.setVisible(true);
        } else {
            yearlySalaryErrorLabel.setVisible(false);
        }
        
        if (hourlyWageError) {
            hourlyWageErrorLabel.setVisible(true);
        } else {
            hourlyWageErrorLabel.setVisible(false);
        }
        
        if (hoursPerWeekError) {
            hoursPerWeekErrorLabel.setVisible(true);
        } else {
            hoursPerWeekErrorLabel.setVisible(false);
        }
        
        if (weeksPerYearError) {
            weeksPerYearErrorLabel.setVisible(true);
        } else {
            weeksPerYearErrorLabel.setVisible(false);
        }
        
        updateErrorMessage();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="traversal policy">
    
    public class MyFocusTraversalPolicy extends FocusTraversalPolicy {
        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            if (editing) {
                if (aComponent.equals(firstNameField)) return lastNameField;
                else if (aComponent.equals(lastNameField)) return checkFullEmployeeTypeButton;
                else if (aComponent.equals(checkFullEmployeeTypeButton)) return checkPartEmployeeTypeButton;
                else if (aComponent.equals(checkPartEmployeeTypeButton)) return checkGenderMale;
                else if (aComponent.equals(checkGenderMale)) return checkGenderFemale;
                else if (aComponent.equals(checkGenderFemale)) return checkGenderOther;
                else if (aComponent.equals(checkGenderOther)) return employeeNumberField;
                else if (aComponent.equals(employeeNumberField)) return deductionsRateField;
                else if (aComponent.equals(deductionsRateField)) return workLocationField;
                else if (aComponent.equals(workLocationField) && type == -1) return confirmButton;
                else if (aComponent.equals(workLocationField) && type == 0) return yearlySalaryField;
                else if (aComponent.equals(workLocationField) && type == 1) return hourlyWageField;
                else if (aComponent.equals(hourlyWageField)) return hoursPerWeekField;
                else if (aComponent.equals(hoursPerWeekField)) return weeksPerYearField;
                else if (aComponent.equals(yearlySalaryField)) return confirmButton;
                else if (aComponent.equals(weeksPerYearField)) return confirmButton;
                else return firstNameField;
            } else {
                if (aComponent.equals(editButton)) return deleteButton;
                else return editButton;
            }
        }
      
        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            if (editing) {
                if (aComponent.equals(resetButton)) return confirmButton;
                else if (aComponent.equals(confirmButton) && type == -1) return workLocationField;
                else if (aComponent.equals(confirmButton) && type == 0) return yearlySalaryField;
                else if (aComponent.equals(confirmButton) && type == 1) return weeksPerYearField;
                else if (aComponent.equals(weeksPerYearField)) return hoursPerWeekField;
                else if (aComponent.equals(hoursPerWeekField)) return hourlyWageField;
                else if (aComponent.equals(hourlyWageField)) return workLocationField;
                else if (aComponent.equals(yearlySalaryField)) return workLocationField;
                else if (aComponent.equals(workLocationField)) return deductionsRateField;
                else if (aComponent.equals(deductionsRateField)) return employeeNumberField;
                else if (aComponent.equals(employeeNumberField)) return checkGenderOther;
                else if (aComponent.equals(checkGenderOther)) return checkGenderFemale;
                else if (aComponent.equals(checkGenderFemale)) return checkGenderMale;
                else if (aComponent.equals(checkGenderMale)) return checkPartEmployeeTypeButton;
                else if (aComponent.equals(checkPartEmployeeTypeButton)) return checkFullEmployeeTypeButton;
                else if (aComponent.equals(checkFullEmployeeTypeButton)) return lastNameField;
                else if (aComponent.equals(lastNameField)) return firstNameField;
                else return resetButton;
            } else {
                if (aComponent.equals(deleteButton)) return editButton;
                else return deleteButton;
            }
            
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
            if (editing) {
                return firstNameField;
            } else {
                return editButton;
            }
        }

        public Component getFirstComponent(Container focusCycleRoot) {
            if (editing) {
                return firstNameField;
            } else {
                return editButton;
            }          
        }

        public Component getLastComponent(Container focusCycleRoot) {
            if (editing) {
                return resetButton;
            } else {
                return deleteButton;
            }
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="check new employee">
    
    /**
     * checks the new employee being added
     */
    private void checkNewEmployee() {
        // add employee and reset fields
        
        boolean errorsExist = checkErrors();
        EmployeeInfo newEmployee;
        
        if (!errorsExist) {
            if (type == 0) {
                // full time
                newEmployee = new FullTimeEmployee(
                        employeeNumber,
                        firstName,
                        lastName,
                        gender,
                        workLocation,
                        Double.valueOf(deductionsRate),
                        Double.valueOf(yearlySalary)
                );
            } else {
                // part time
                newEmployee = new PartTimeEmployee(
                        employeeNumber,
                        firstName,
                        lastName,
                        gender,
                        workLocation,
                        Double.valueOf(deductionsRate),
                        Double.valueOf(hourlyWage),
                        Double.valueOf(hoursPerWeek),
                        Double.valueOf(weeksPerYear)
                );
            }
            
            MainComponent.employeeDatabase.removeEmployee(employee.getEmpNumber());
            MainComponent.employeeDatabase.addEmployee(newEmployee);
            
            employee = MainComponent.employeeDatabase.search(newEmployee.getEmpNumber());
            reset();
            editing = false;
            updateViewDisplay();
            MainComponent.updateDashboard();
        }
        
        updateEditDisplay();
    }
    
    // </editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewEmployeePanel = new javax.swing.JPanel();
        employeeTitle = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        formatter11 = new javax.swing.JPanel();
        genderLabel1 = new javax.swing.JLabel();
        employeeGender = new javax.swing.JLabel();
        formatter19 = new javax.swing.JPanel();
        workLocationLabel1 = new javax.swing.JLabel();
        employeeWorkLocation = new javax.swing.JLabel();
        formatter13 = new javax.swing.JPanel();
        firstNameLabel1 = new javax.swing.JLabel();
        employeeFirstName = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        yearlySalaryFormatter1 = new javax.swing.JPanel();
        yearlySalaryLabel1 = new javax.swing.JLabel();
        employeeYearlySalary = new javax.swing.JLabel();
        hourlyWageFormatter1 = new javax.swing.JPanel();
        hourlyWageLabel1 = new javax.swing.JLabel();
        employeeHourlyWage = new javax.swing.JLabel();
        weeksPerYearFormatter1 = new javax.swing.JPanel();
        weeksPerYearLabel1 = new javax.swing.JLabel();
        employeeWeeksPerYear = new javax.swing.JLabel();
        formatter26 = new javax.swing.JPanel();
        lastNameLabel1 = new javax.swing.JLabel();
        employeeLastName = new javax.swing.JLabel();
        hoursPerWeekFormatter1 = new javax.swing.JPanel();
        hoursPerWeekLabel1 = new javax.swing.JLabel();
        employeeHoursPerWeek = new javax.swing.JLabel();
        formatter29 = new javax.swing.JPanel();
        employeeNumberLabel1 = new javax.swing.JLabel();
        employeeEmployeeNumber = new javax.swing.JLabel();
        formatter31 = new javax.swing.JPanel();
        deductionsRateLabel1 = new javax.swing.JLabel();
        employeeDeductionsRate = new javax.swing.JLabel();
        formatter33 = new javax.swing.JPanel();
        typeLabel1 = new javax.swing.JLabel();
        employeeType = new javax.swing.JLabel();
        hoursPerWeekFormatter2 = new javax.swing.JPanel();
        annualIncomeLabel = new javax.swing.JLabel();
        employeeAnnualIncome = new javax.swing.JLabel();
        formatter12 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JLabel();
        editButton = new javax.swing.JLabel();
        ghostSubmit1 = new javax.swing.JButton();
        editEmployeePanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        formatter1 = new javax.swing.JPanel();
        resetButton = new javax.swing.JLabel();
        confirmButton = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        errorText = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        formatter7 = new javax.swing.JPanel();
        checkGenderMale = new javax.swing.JLabel();
        checkGenderFemale = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        formatter8 = new javax.swing.JPanel();
        checkGenderErrorLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        checkGenderOther = new javax.swing.JLabel();
        formatter17 = new javax.swing.JPanel();
        workLocationLabel = new javax.swing.JLabel();
        formatter18 = new javax.swing.JPanel();
        workLocationErrorLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        workLocationWrapper = new javax.swing.JPanel();
        workLocationField = new javax.swing.JTextField();
        formatter3 = new javax.swing.JPanel();
        firstNameLabel = new javax.swing.JLabel();
        formatter4 = new javax.swing.JPanel();
        firstNameErrorLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        firstNameWrapper = new javax.swing.JPanel();
        firstNameField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        yearlySalaryFormatter = new javax.swing.JPanel();
        yearlySalaryLabel = new javax.swing.JLabel();
        formatter20 = new javax.swing.JPanel();
        yearlySalaryErrorLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        yearlySalaryWrapper = new javax.swing.JPanel();
        yearlySalaryField = new javax.swing.JTextField();
        hourlyWageFormatter = new javax.swing.JPanel();
        hourlyWageLabel = new javax.swing.JLabel();
        formatter24 = new javax.swing.JPanel();
        hourlyWageErrorLabel = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        hourlyWageWrapper = new javax.swing.JPanel();
        hourlyWageField = new javax.swing.JTextField();
        weeksPerYearFormatter = new javax.swing.JPanel();
        weeksPerYearLabel = new javax.swing.JLabel();
        formatter46 = new javax.swing.JPanel();
        weeksPerYearErrorLabel = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        weeksPerYearWrapper = new javax.swing.JPanel();
        weeksPerYearField = new javax.swing.JTextField();
        formatter5 = new javax.swing.JPanel();
        lastNameLabel = new javax.swing.JLabel();
        formatter6 = new javax.swing.JPanel();
        lastNameErrorLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lastNameWrapper = new javax.swing.JPanel();
        lastNameField = new javax.swing.JTextField();
        hoursPerWeekFormatter = new javax.swing.JPanel();
        hoursPerWeekLabel = new javax.swing.JLabel();
        formatter22 = new javax.swing.JPanel();
        hoursPerWeekErrorLabel = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        hoursPerWeekWrapper = new javax.swing.JPanel();
        hoursPerWeekField = new javax.swing.JTextField();
        formatter9 = new javax.swing.JPanel();
        employeeNumberLabel = new javax.swing.JLabel();
        formatter10 = new javax.swing.JPanel();
        employeeNumberErrorLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        employeeNumberWrapper = new javax.swing.JPanel();
        employeeNumberField = new javax.swing.JTextField();
        formatter15 = new javax.swing.JPanel();
        deductionsRateLabel = new javax.swing.JLabel();
        formatter16 = new javax.swing.JPanel();
        deductionsRateErrorLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        deductionsRateWrapper = new javax.swing.JPanel();
        deductionsRateField = new javax.swing.JTextField();
        formatter = new javax.swing.JPanel();
        checkFullEmployeeTypeButton = new javax.swing.JLabel();
        checkPartEmployeeTypeButton = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        formatter2 = new javax.swing.JPanel();
        typeErrorLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(250, 250, 250));
        setBounds(new java.awt.Rectangle(900, 23, 0, 0));
        setFocusTraversalPolicy(new MyFocusTraversalPolicy());
        setSize(new java.awt.Dimension(370, 480));
        getContentPane().setLayout(new java.awt.CardLayout());

        viewEmployeePanel.setBackground(new java.awt.Color(250, 250, 250));
        viewEmployeePanel.setPreferredSize(new java.awt.Dimension(738, 480));

        employeeTitle.setBackground(new java.awt.Color(255, 255, 255));
        employeeTitle.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        employeeTitle.setForeground(new java.awt.Color(0, 0, 0));
        employeeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeeTitle.setText("employee info");

        jPanel14.setOpaque(false);

        formatter11.setOpaque(false);

        genderLabel1.setBackground(new java.awt.Color(250, 250, 250));
        genderLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        genderLabel1.setForeground(new java.awt.Color(0, 0, 0));
        genderLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        genderLabel1.setText("<html><b>gender:</b></html>");
        genderLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        genderLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        genderLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        genderLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeGender.setBackground(new java.awt.Color(250, 250, 250));
        employeeGender.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeGender.setForeground(new java.awt.Color(0, 0, 0));
        employeeGender.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeGender.setText("gender");
        employeeGender.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeGender.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeGender.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeGender.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout formatter11Layout = new javax.swing.GroupLayout(formatter11);
        formatter11.setLayout(formatter11Layout);
        formatter11Layout.setHorizontalGroup(
            formatter11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(genderLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        formatter11Layout.setVerticalGroup(
            formatter11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genderLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter19.setFocusTraversalPolicyProvider(true);
        formatter19.setOpaque(false);

        workLocationLabel1.setBackground(new java.awt.Color(250, 250, 250));
        workLocationLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        workLocationLabel1.setForeground(new java.awt.Color(0, 0, 0));
        workLocationLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        workLocationLabel1.setText("<html><b>location:</b></html>");
        workLocationLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        workLocationLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        workLocationLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        workLocationLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeWorkLocation.setBackground(new java.awt.Color(250, 250, 250));
        employeeWorkLocation.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeWorkLocation.setForeground(new java.awt.Color(0, 0, 0));
        employeeWorkLocation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeWorkLocation.setText("work location");
        employeeWorkLocation.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeWorkLocation.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeWorkLocation.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeWorkLocation.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout formatter19Layout = new javax.swing.GroupLayout(formatter19);
        formatter19.setLayout(formatter19Layout);
        formatter19Layout.setHorizontalGroup(
            formatter19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter19Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(workLocationLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeWorkLocation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        formatter19Layout.setVerticalGroup(
            formatter19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter19Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workLocationLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeWorkLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter13.setFocusTraversalPolicyProvider(true);
        formatter13.setOpaque(false);

        firstNameLabel1.setBackground(new java.awt.Color(250, 250, 250));
        firstNameLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        firstNameLabel1.setForeground(new java.awt.Color(0, 0, 0));
        firstNameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNameLabel1.setText("<html><b>first:</b></html>");
        firstNameLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        firstNameLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        firstNameLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        firstNameLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeFirstName.setBackground(new java.awt.Color(250, 250, 250));
        employeeFirstName.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeFirstName.setForeground(new java.awt.Color(0, 0, 0));
        employeeFirstName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeFirstName.setText("first name");
        employeeFirstName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeFirstName.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeFirstName.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeFirstName.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout formatter13Layout = new javax.swing.GroupLayout(formatter13);
        formatter13.setLayout(formatter13Layout);
        formatter13Layout.setHorizontalGroup(
            formatter13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter13Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(firstNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        formatter13Layout.setVerticalGroup(
            formatter13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter13Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(250, 250, 250));
        jPanel18.setOpaque(false);

        yearlySalaryFormatter1.setFocusTraversalPolicyProvider(true);
        yearlySalaryFormatter1.setOpaque(false);

        yearlySalaryLabel1.setBackground(new java.awt.Color(250, 250, 250));
        yearlySalaryLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        yearlySalaryLabel1.setForeground(new java.awt.Color(0, 0, 0));
        yearlySalaryLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearlySalaryLabel1.setText("<html><b>salary:</b></html>");
        yearlySalaryLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        yearlySalaryLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        yearlySalaryLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        yearlySalaryLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeYearlySalary.setBackground(new java.awt.Color(250, 250, 250));
        employeeYearlySalary.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeYearlySalary.setForeground(new java.awt.Color(0, 0, 0));
        employeeYearlySalary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeYearlySalary.setText("yearly salary");
        employeeYearlySalary.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeYearlySalary.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeYearlySalary.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeYearlySalary.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout yearlySalaryFormatter1Layout = new javax.swing.GroupLayout(yearlySalaryFormatter1);
        yearlySalaryFormatter1.setLayout(yearlySalaryFormatter1Layout);
        yearlySalaryFormatter1Layout.setHorizontalGroup(
            yearlySalaryFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearlySalaryFormatter1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(yearlySalaryLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeYearlySalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        yearlySalaryFormatter1Layout.setVerticalGroup(
            yearlySalaryFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, yearlySalaryFormatter1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(yearlySalaryFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yearlySalaryLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeYearlySalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        hourlyWageFormatter1.setFocusTraversalPolicyProvider(true);
        hourlyWageFormatter1.setOpaque(false);

        hourlyWageLabel1.setBackground(new java.awt.Color(250, 250, 250));
        hourlyWageLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        hourlyWageLabel1.setForeground(new java.awt.Color(0, 0, 0));
        hourlyWageLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hourlyWageLabel1.setText("<html><b>wage:</b></html>");
        hourlyWageLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hourlyWageLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        hourlyWageLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        hourlyWageLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeHourlyWage.setBackground(new java.awt.Color(250, 250, 250));
        employeeHourlyWage.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeHourlyWage.setForeground(new java.awt.Color(0, 0, 0));
        employeeHourlyWage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeHourlyWage.setText("hourly wage");
        employeeHourlyWage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeHourlyWage.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeHourlyWage.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeHourlyWage.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout hourlyWageFormatter1Layout = new javax.swing.GroupLayout(hourlyWageFormatter1);
        hourlyWageFormatter1.setLayout(hourlyWageFormatter1Layout);
        hourlyWageFormatter1Layout.setHorizontalGroup(
            hourlyWageFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hourlyWageFormatter1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hourlyWageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeHourlyWage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        hourlyWageFormatter1Layout.setVerticalGroup(
            hourlyWageFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hourlyWageFormatter1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(hourlyWageFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hourlyWageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeHourlyWage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        weeksPerYearFormatter1.setFocusTraversalPolicyProvider(true);
        weeksPerYearFormatter1.setOpaque(false);

        weeksPerYearLabel1.setBackground(new java.awt.Color(250, 250, 250));
        weeksPerYearLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        weeksPerYearLabel1.setForeground(new java.awt.Color(0, 0, 0));
        weeksPerYearLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        weeksPerYearLabel1.setText("<html><b>weeks:</b></html>");
        weeksPerYearLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        weeksPerYearLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        weeksPerYearLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        weeksPerYearLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeWeeksPerYear.setBackground(new java.awt.Color(250, 250, 250));
        employeeWeeksPerYear.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeWeeksPerYear.setForeground(new java.awt.Color(0, 0, 0));
        employeeWeeksPerYear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeWeeksPerYear.setText("weeks per year");
        employeeWeeksPerYear.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeWeeksPerYear.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeWeeksPerYear.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeWeeksPerYear.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout weeksPerYearFormatter1Layout = new javax.swing.GroupLayout(weeksPerYearFormatter1);
        weeksPerYearFormatter1.setLayout(weeksPerYearFormatter1Layout);
        weeksPerYearFormatter1Layout.setHorizontalGroup(
            weeksPerYearFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(weeksPerYearFormatter1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(weeksPerYearLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeWeeksPerYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        weeksPerYearFormatter1Layout.setVerticalGroup(
            weeksPerYearFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, weeksPerYearFormatter1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(weeksPerYearFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(weeksPerYearLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeWeeksPerYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hourlyWageFormatter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(weeksPerYearFormatter1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(yearlySalaryFormatter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(yearlySalaryFormatter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hourlyWageFormatter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weeksPerYearFormatter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        formatter26.setFocusTraversalPolicyProvider(true);
        formatter26.setOpaque(false);

        lastNameLabel1.setBackground(new java.awt.Color(250, 250, 250));
        lastNameLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameLabel1.setForeground(new java.awt.Color(0, 0, 0));
        lastNameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastNameLabel1.setText("<html><b>last:</html></b>");
        lastNameLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lastNameLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        lastNameLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        lastNameLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeLastName.setBackground(new java.awt.Color(250, 250, 250));
        employeeLastName.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeLastName.setForeground(new java.awt.Color(0, 0, 0));
        employeeLastName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeLastName.setText("last name");
        employeeLastName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeLastName.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeLastName.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeLastName.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout formatter26Layout = new javax.swing.GroupLayout(formatter26);
        formatter26.setLayout(formatter26Layout);
        formatter26Layout.setHorizontalGroup(
            formatter26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter26Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        formatter26Layout.setVerticalGroup(
            formatter26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter26Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        hoursPerWeekFormatter1.setFocusTraversalPolicyProvider(true);
        hoursPerWeekFormatter1.setOpaque(false);

        hoursPerWeekLabel1.setBackground(new java.awt.Color(250, 250, 250));
        hoursPerWeekLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        hoursPerWeekLabel1.setForeground(new java.awt.Color(0, 0, 0));
        hoursPerWeekLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hoursPerWeekLabel1.setText("<html><b>hours:</b></html>");
        hoursPerWeekLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hoursPerWeekLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        hoursPerWeekLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        hoursPerWeekLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeHoursPerWeek.setBackground(new java.awt.Color(250, 250, 250));
        employeeHoursPerWeek.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeHoursPerWeek.setForeground(new java.awt.Color(0, 0, 0));
        employeeHoursPerWeek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeHoursPerWeek.setText("hours per week");
        employeeHoursPerWeek.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeHoursPerWeek.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeHoursPerWeek.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeHoursPerWeek.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout hoursPerWeekFormatter1Layout = new javax.swing.GroupLayout(hoursPerWeekFormatter1);
        hoursPerWeekFormatter1.setLayout(hoursPerWeekFormatter1Layout);
        hoursPerWeekFormatter1Layout.setHorizontalGroup(
            hoursPerWeekFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoursPerWeekFormatter1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hoursPerWeekLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeHoursPerWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        hoursPerWeekFormatter1Layout.setVerticalGroup(
            hoursPerWeekFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoursPerWeekFormatter1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(hoursPerWeekFormatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hoursPerWeekLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeHoursPerWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter29.setFocusTraversalPolicyProvider(true);
        formatter29.setOpaque(false);
        formatter29.setPreferredSize(new java.awt.Dimension(250, 52));

        employeeNumberLabel1.setBackground(new java.awt.Color(250, 250, 250));
        employeeNumberLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeNumberLabel1.setForeground(new java.awt.Color(0, 0, 0));
        employeeNumberLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        employeeNumberLabel1.setText("<html><b>emp. #:</b></html>");
        employeeNumberLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeNumberLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeNumberLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeNumberLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeEmployeeNumber.setBackground(new java.awt.Color(250, 250, 250));
        employeeEmployeeNumber.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeEmployeeNumber.setForeground(new java.awt.Color(0, 0, 0));
        employeeEmployeeNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeEmployeeNumber.setText("employee number");
        employeeEmployeeNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeEmployeeNumber.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeEmployeeNumber.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeEmployeeNumber.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout formatter29Layout = new javax.swing.GroupLayout(formatter29);
        formatter29.setLayout(formatter29Layout);
        formatter29Layout.setHorizontalGroup(
            formatter29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter29Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(employeeNumberLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeEmployeeNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        formatter29Layout.setVerticalGroup(
            formatter29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter29Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeNumberLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter31.setFocusTraversalPolicyProvider(true);
        formatter31.setOpaque(false);

        deductionsRateLabel1.setBackground(new java.awt.Color(250, 250, 250));
        deductionsRateLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deductionsRateLabel1.setForeground(new java.awt.Color(0, 0, 0));
        deductionsRateLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deductionsRateLabel1.setText("<html><b>ded. rate:</b></html>");
        deductionsRateLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deductionsRateLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        deductionsRateLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        deductionsRateLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeDeductionsRate.setBackground(new java.awt.Color(250, 250, 250));
        employeeDeductionsRate.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeDeductionsRate.setForeground(new java.awt.Color(0, 0, 0));
        employeeDeductionsRate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeDeductionsRate.setText("ded rate");
        employeeDeductionsRate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeDeductionsRate.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeDeductionsRate.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeDeductionsRate.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout formatter31Layout = new javax.swing.GroupLayout(formatter31);
        formatter31.setLayout(formatter31Layout);
        formatter31Layout.setHorizontalGroup(
            formatter31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter31Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(deductionsRateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeDeductionsRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        formatter31Layout.setVerticalGroup(
            formatter31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter31Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deductionsRateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeDeductionsRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter33.setOpaque(false);

        typeLabel1.setBackground(new java.awt.Color(250, 250, 250));
        typeLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        typeLabel1.setForeground(new java.awt.Color(0, 0, 0));
        typeLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        typeLabel1.setText("<html><b>type:</b></html>");
        typeLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        typeLabel1.setMaximumSize(new java.awt.Dimension(75, 30));
        typeLabel1.setMinimumSize(new java.awt.Dimension(75, 30));
        typeLabel1.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeType.setBackground(new java.awt.Color(250, 250, 250));
        employeeType.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeType.setForeground(new java.awt.Color(0, 0, 0));
        employeeType.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeType.setText("type");
        employeeType.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeType.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeType.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeType.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout formatter33Layout = new javax.swing.GroupLayout(formatter33);
        formatter33.setLayout(formatter33Layout);
        formatter33Layout.setHorizontalGroup(
            formatter33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter33Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(typeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        formatter33Layout.setVerticalGroup(
            formatter33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter33Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        hoursPerWeekFormatter2.setFocusTraversalPolicyProvider(true);
        hoursPerWeekFormatter2.setOpaque(false);

        annualIncomeLabel.setBackground(new java.awt.Color(250, 250, 250));
        annualIncomeLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        annualIncomeLabel.setForeground(new java.awt.Color(0, 0, 0));
        annualIncomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        annualIncomeLabel.setText("<html><b>income:</b></html>");
        annualIncomeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        annualIncomeLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        annualIncomeLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        annualIncomeLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        employeeAnnualIncome.setBackground(new java.awt.Color(250, 250, 250));
        employeeAnnualIncome.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeAnnualIncome.setForeground(new java.awt.Color(0, 0, 0));
        employeeAnnualIncome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeAnnualIncome.setText("annual income");
        employeeAnnualIncome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeAnnualIncome.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeAnnualIncome.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeAnnualIncome.setPreferredSize(new java.awt.Dimension(75, 30));

        javax.swing.GroupLayout hoursPerWeekFormatter2Layout = new javax.swing.GroupLayout(hoursPerWeekFormatter2);
        hoursPerWeekFormatter2.setLayout(hoursPerWeekFormatter2Layout);
        hoursPerWeekFormatter2Layout.setHorizontalGroup(
            hoursPerWeekFormatter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoursPerWeekFormatter2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(annualIncomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeAnnualIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        hoursPerWeekFormatter2Layout.setVerticalGroup(
            hoursPerWeekFormatter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoursPerWeekFormatter2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(hoursPerWeekFormatter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annualIncomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeAnnualIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formatter33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formatter29, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(formatter19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hoursPerWeekFormatter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hoursPerWeekFormatter2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formatter13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(formatter26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addComponent(formatter11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formatter31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(formatter13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(formatter33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(formatter26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatter11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(formatter31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(formatter19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hoursPerWeekFormatter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hoursPerWeekFormatter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        formatter12.setBackground(new java.awt.Color(250, 250, 250));

        deleteButton.setBackground(new java.awt.Color(255, 205, 210));
        deleteButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(0, 0, 0));
        deleteButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteButton.setText("delete");
        deleteButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 205, 210), 2));
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.setMaximumSize(new java.awt.Dimension(75, 30));
        deleteButton.setMinimumSize(new java.awt.Dimension(75, 30));
        deleteButton.setOpaque(true);
        deleteButton.setPreferredSize(new java.awt.Dimension(52, 20));
        deleteButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                deleteButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                deleteButtonFocusLost(evt);
            }
        });
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deleteButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deleteButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteButtonMouseEntered(evt);
            }
        });
        deleteButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deleteButtonKeyPressed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(255, 236, 179));
        editButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        editButton.setForeground(new java.awt.Color(0, 0, 0));
        editButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editButton.setText("edit");
        editButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 236, 179), 2));
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.setMaximumSize(new java.awt.Dimension(75, 30));
        editButton.setMinimumSize(new java.awt.Dimension(75, 30));
        editButton.setOpaque(true);
        editButton.setPreferredSize(new java.awt.Dimension(75, 30));
        editButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                editButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                editButtonFocusLost(evt);
            }
        });
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                editButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editButtonMouseEntered(evt);
            }
        });
        editButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editButtonKeyPressed(evt);
            }
        });

        ghostSubmit1.setBackground(new java.awt.Color(250, 250, 250));
        ghostSubmit1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        ghostSubmit1.setForeground(new java.awt.Color(0, 0, 0));
        ghostSubmit1.setBorder(null);
        ghostSubmit1.setBorderPainted(false);
        ghostSubmit1.setContentAreaFilled(false);
        ghostSubmit1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ghostSubmit1.setFocusPainted(false);
        ghostSubmit1.setPreferredSize(new java.awt.Dimension(50, 30));
        ghostSubmit1.setSize(new java.awt.Dimension(20, 30));

        javax.swing.GroupLayout formatter12Layout = new javax.swing.GroupLayout(formatter12);
        formatter12.setLayout(formatter12Layout);
        formatter12Layout.setHorizontalGroup(
            formatter12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ghostSubmit1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formatter12Layout.setVerticalGroup(
            formatter12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formatter12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ghostSubmit1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formatter12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout viewEmployeePanelLayout = new javax.swing.GroupLayout(viewEmployeePanel);
        viewEmployeePanel.setLayout(viewEmployeePanelLayout);
        viewEmployeePanelLayout.setHorizontalGroup(
            viewEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(employeeTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
            .addGroup(viewEmployeePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewEmployeePanelLayout.createSequentialGroup()
                        .addGap(0, 55, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addComponent(formatter12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        viewEmployeePanelLayout.setVerticalGroup(
            viewEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewEmployeePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(employeeTitle)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formatter12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        getContentPane().add(viewEmployeePanel, "card2");

        editEmployeePanel.setBackground(new java.awt.Color(250, 250, 250));
        editEmployeePanel.setForeground(new java.awt.Color(0, 0, 0));
        editEmployeePanel.setPreferredSize(new java.awt.Dimension(738, 484));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("edit employee");

        formatter1.setBackground(new java.awt.Color(250, 250, 250));

        resetButton.setBackground(new java.awt.Color(255, 205, 210));
        resetButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        resetButton.setForeground(new java.awt.Color(0, 0, 0));
        resetButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resetButton.setText("cancel");
        resetButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 205, 210), 2));
        resetButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resetButton.setMaximumSize(new java.awt.Dimension(75, 30));
        resetButton.setMinimumSize(new java.awt.Dimension(75, 30));
        resetButton.setOpaque(true);
        resetButton.setPreferredSize(new java.awt.Dimension(52, 20));
        resetButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                resetButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                resetButtonFocusLost(evt);
            }
        });
        resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                resetButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                resetButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                resetButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                resetButtonMouseEntered(evt);
            }
        });
        resetButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                resetButtonKeyPressed(evt);
            }
        });

        confirmButton.setBackground(new java.awt.Color(197, 225, 165));
        confirmButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(0, 0, 0));
        confirmButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        confirmButton.setText("save");
        confirmButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 225, 165), 2));
        confirmButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmButton.setMaximumSize(new java.awt.Dimension(75, 30));
        confirmButton.setMinimumSize(new java.awt.Dimension(75, 30));
        confirmButton.setOpaque(true);
        confirmButton.setPreferredSize(new java.awt.Dimension(75, 30));
        confirmButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmButtonFocusLost(evt);
            }
        });
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                confirmButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                confirmButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButtonMouseEntered(evt);
            }
        });
        confirmButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout formatter1Layout = new javax.swing.GroupLayout(formatter1);
        formatter1.setLayout(formatter1Layout);
        formatter1Layout.setHorizontalGroup(
            formatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter1Layout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        formatter1Layout.setVerticalGroup(
            formatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel7.setBackground(new java.awt.Color(250, 250, 250));
        jPanel7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(121, 50));

        errorText.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        errorText.setForeground(new java.awt.Color(244, 67, 54));
        errorText.setText("<html>error message</html>");
        errorText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorText, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel13.setOpaque(false);

        formatter7.setOpaque(false);

        checkGenderMale.setBackground(new java.awt.Color(250, 250, 250));
        checkGenderMale.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        checkGenderMale.setForeground(new java.awt.Color(0, 0, 0));
        checkGenderMale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkGenderMale.setText("M");
        checkGenderMale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 202, 249), 2));
        checkGenderMale.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkGenderMale.setMaximumSize(new java.awt.Dimension(75, 30));
        checkGenderMale.setMinimumSize(new java.awt.Dimension(75, 30));
        checkGenderMale.setOpaque(true);
        checkGenderMale.setPreferredSize(new java.awt.Dimension(75, 30));
        checkGenderMale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                checkGenderMaleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkGenderMaleFocusLost(evt);
            }
        });
        checkGenderMale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkGenderMaleMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkGenderMaleMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkGenderMaleMouseEntered(evt);
            }
        });
        checkGenderMale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkGenderMaleKeyPressed(evt);
            }
        });

        checkGenderFemale.setBackground(new java.awt.Color(250, 250, 250));
        checkGenderFemale.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        checkGenderFemale.setForeground(new java.awt.Color(0, 0, 0));
        checkGenderFemale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkGenderFemale.setText("F");
        checkGenderFemale.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 202, 249), 2));
        checkGenderFemale.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkGenderFemale.setMaximumSize(new java.awt.Dimension(75, 30));
        checkGenderFemale.setMinimumSize(new java.awt.Dimension(75, 30));
        checkGenderFemale.setOpaque(true);
        checkGenderFemale.setPreferredSize(new java.awt.Dimension(75, 30));
        checkGenderFemale.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                checkGenderFemaleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkGenderFemaleFocusLost(evt);
            }
        });
        checkGenderFemale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkGenderFemaleMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkGenderFemaleMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkGenderFemaleMouseEntered(evt);
            }
        });
        checkGenderFemale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkGenderFemaleKeyPressed(evt);
            }
        });

        genderLabel.setBackground(new java.awt.Color(250, 250, 250));
        genderLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        genderLabel.setForeground(new java.awt.Color(0, 0, 0));
        genderLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        genderLabel.setText("gender:");
        genderLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        genderLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        genderLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        genderLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter8.setOpaque(false);

        checkGenderErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        checkGenderErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        checkGenderErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkGenderErrorLabel.setText("⚠");

        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel4.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter8Layout = new javax.swing.GroupLayout(formatter8);
        formatter8.setLayout(formatter8Layout);
        formatter8Layout.setHorizontalGroup(
            formatter8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(checkGenderErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter8Layout.setVerticalGroup(
            formatter8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter8Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(checkGenderErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        checkGenderOther.setBackground(new java.awt.Color(250, 250, 250));
        checkGenderOther.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        checkGenderOther.setForeground(new java.awt.Color(0, 0, 0));
        checkGenderOther.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkGenderOther.setText("O");
        checkGenderOther.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 202, 249), 2));
        checkGenderOther.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkGenderOther.setMaximumSize(new java.awt.Dimension(75, 30));
        checkGenderOther.setMinimumSize(new java.awt.Dimension(75, 30));
        checkGenderOther.setOpaque(true);
        checkGenderOther.setPreferredSize(new java.awt.Dimension(75, 30));
        checkGenderOther.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                checkGenderOtherFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkGenderOtherFocusLost(evt);
            }
        });
        checkGenderOther.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkGenderOtherMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkGenderOtherMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkGenderOtherMouseEntered(evt);
            }
        });
        checkGenderOther.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkGenderOtherKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout formatter7Layout = new javax.swing.GroupLayout(formatter7);
        formatter7.setLayout(formatter7Layout);
        formatter7Layout.setHorizontalGroup(
            formatter7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(checkGenderMale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(checkGenderFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(checkGenderOther, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter7Layout.setVerticalGroup(
            formatter7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formatter7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkGenderMale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkGenderFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkGenderOther, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(formatter8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter17.setFocusTraversalPolicyProvider(true);
        formatter17.setOpaque(false);

        workLocationLabel.setBackground(new java.awt.Color(250, 250, 250));
        workLocationLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        workLocationLabel.setForeground(new java.awt.Color(0, 0, 0));
        workLocationLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        workLocationLabel.setText("location:");
        workLocationLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        workLocationLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        workLocationLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        workLocationLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter18.setOpaque(false);

        workLocationErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        workLocationErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        workLocationErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        workLocationErrorLabel.setText("⚠");

        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel9.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter18Layout = new javax.swing.GroupLayout(formatter18);
        formatter18.setLayout(formatter18Layout);
        formatter18Layout.setHorizontalGroup(
            formatter18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workLocationErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter18Layout.setVerticalGroup(
            formatter18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter18Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(workLocationErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        workLocationWrapper.setBackground(new java.awt.Color(255, 252, 219));
        workLocationWrapper.setForeground(new java.awt.Color(0, 0, 0));
        workLocationWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                workLocationWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                workLocationWrapperMouseEntered(evt);
            }
        });

        workLocationField.setBackground(new java.awt.Color(255, 252, 219));
        workLocationField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        workLocationField.setForeground(new java.awt.Color(0, 0, 0));
        workLocationField.setBorder(null);
        workLocationField.setOpaque(false);
        workLocationField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        workLocationField.setSelectionColor(new java.awt.Color(0, 0, 0));
        workLocationField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                workLocationFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                workLocationFieldFocusLost(evt);
            }
        });
        workLocationField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                workLocationFieldMouseEntered(evt);
            }
        });
        workLocationField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                workLocationFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                workLocationFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout workLocationWrapperLayout = new javax.swing.GroupLayout(workLocationWrapper);
        workLocationWrapper.setLayout(workLocationWrapperLayout);
        workLocationWrapperLayout.setHorizontalGroup(
            workLocationWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workLocationWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(workLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        workLocationWrapperLayout.setVerticalGroup(
            workLocationWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workLocationWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(workLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter17Layout = new javax.swing.GroupLayout(formatter17);
        formatter17.setLayout(formatter17Layout);
        formatter17Layout.setHorizontalGroup(
            formatter17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter17Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(workLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(workLocationWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter17Layout.setVerticalGroup(
            formatter17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter17Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(workLocationWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(workLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter3.setFocusTraversalPolicyProvider(true);
        formatter3.setOpaque(false);

        firstNameLabel.setBackground(new java.awt.Color(250, 250, 250));
        firstNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        firstNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNameLabel.setText("first:");
        firstNameLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        firstNameLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        firstNameLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        firstNameLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter4.setOpaque(false);

        firstNameErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        firstNameErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        firstNameErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstNameErrorLabel.setText("⚠");

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel2.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter4Layout = new javax.swing.GroupLayout(formatter4);
        formatter4.setLayout(formatter4Layout);
        formatter4Layout.setHorizontalGroup(
            formatter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(firstNameErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter4Layout.setVerticalGroup(
            formatter4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(firstNameErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        firstNameWrapper.setBackground(new java.awt.Color(255, 252, 219));
        firstNameWrapper.setForeground(new java.awt.Color(0, 0, 0));
        firstNameWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                firstNameWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                firstNameWrapperMouseEntered(evt);
            }
        });

        firstNameField.setBackground(new java.awt.Color(255, 252, 219));
        firstNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        firstNameField.setForeground(new java.awt.Color(0, 0, 0));
        firstNameField.setBorder(null);
        firstNameField.setOpaque(false);
        firstNameField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        firstNameField.setSelectionColor(new java.awt.Color(0, 0, 0));
        firstNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameFieldFocusLost(evt);
            }
        });
        firstNameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                firstNameFieldMouseEntered(evt);
            }
        });
        firstNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                firstNameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstNameFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout firstNameWrapperLayout = new javax.swing.GroupLayout(firstNameWrapper);
        firstNameWrapper.setLayout(firstNameWrapperLayout);
        firstNameWrapperLayout.setHorizontalGroup(
            firstNameWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstNameWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        firstNameWrapperLayout.setVerticalGroup(
            firstNameWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstNameWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter3Layout = new javax.swing.GroupLayout(formatter3);
        formatter3.setLayout(formatter3Layout);
        formatter3Layout.setHorizontalGroup(
            formatter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(firstNameWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter3Layout.setVerticalGroup(
            formatter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstNameWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(250, 250, 250));
        jPanel6.setOpaque(false);

        yearlySalaryFormatter.setFocusTraversalPolicyProvider(true);
        yearlySalaryFormatter.setOpaque(false);

        yearlySalaryLabel.setBackground(new java.awt.Color(250, 250, 250));
        yearlySalaryLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        yearlySalaryLabel.setForeground(new java.awt.Color(0, 0, 0));
        yearlySalaryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearlySalaryLabel.setText("salary:");
        yearlySalaryLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        yearlySalaryLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        yearlySalaryLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        yearlySalaryLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter20.setOpaque(false);

        yearlySalaryErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        yearlySalaryErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        yearlySalaryErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yearlySalaryErrorLabel.setText("⚠");

        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel10.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter20Layout = new javax.swing.GroupLayout(formatter20);
        formatter20.setLayout(formatter20Layout);
        formatter20Layout.setHorizontalGroup(
            formatter20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(yearlySalaryErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter20Layout.setVerticalGroup(
            formatter20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter20Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(yearlySalaryErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        yearlySalaryWrapper.setBackground(new java.awt.Color(255, 252, 219));
        yearlySalaryWrapper.setForeground(new java.awt.Color(0, 0, 0));
        yearlySalaryWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                yearlySalaryWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yearlySalaryWrapperMouseEntered(evt);
            }
        });

        yearlySalaryField.setBackground(new java.awt.Color(255, 252, 219));
        yearlySalaryField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        yearlySalaryField.setForeground(new java.awt.Color(0, 0, 0));
        yearlySalaryField.setBorder(null);
        yearlySalaryField.setOpaque(false);
        yearlySalaryField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        yearlySalaryField.setSelectionColor(new java.awt.Color(0, 0, 0));
        yearlySalaryField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                yearlySalaryFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                yearlySalaryFieldFocusLost(evt);
            }
        });
        yearlySalaryField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yearlySalaryFieldMouseEntered(evt);
            }
        });
        yearlySalaryField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yearlySalaryFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearlySalaryFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout yearlySalaryWrapperLayout = new javax.swing.GroupLayout(yearlySalaryWrapper);
        yearlySalaryWrapper.setLayout(yearlySalaryWrapperLayout);
        yearlySalaryWrapperLayout.setHorizontalGroup(
            yearlySalaryWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, yearlySalaryWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(yearlySalaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        yearlySalaryWrapperLayout.setVerticalGroup(
            yearlySalaryWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearlySalaryWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(yearlySalaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout yearlySalaryFormatterLayout = new javax.swing.GroupLayout(yearlySalaryFormatter);
        yearlySalaryFormatter.setLayout(yearlySalaryFormatterLayout);
        yearlySalaryFormatterLayout.setHorizontalGroup(
            yearlySalaryFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yearlySalaryFormatterLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(yearlySalaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(yearlySalaryWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        yearlySalaryFormatterLayout.setVerticalGroup(
            yearlySalaryFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, yearlySalaryFormatterLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(yearlySalaryFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yearlySalaryWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearlySalaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        hourlyWageFormatter.setFocusTraversalPolicyProvider(true);
        hourlyWageFormatter.setOpaque(false);

        hourlyWageLabel.setBackground(new java.awt.Color(250, 250, 250));
        hourlyWageLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        hourlyWageLabel.setForeground(new java.awt.Color(0, 0, 0));
        hourlyWageLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hourlyWageLabel.setText("wage:");
        hourlyWageLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hourlyWageLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        hourlyWageLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        hourlyWageLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter24.setOpaque(false);

        hourlyWageErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        hourlyWageErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        hourlyWageErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hourlyWageErrorLabel.setText("⚠");

        jPanel12.setOpaque(false);
        jPanel12.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel12.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter24Layout = new javax.swing.GroupLayout(formatter24);
        formatter24.setLayout(formatter24Layout);
        formatter24Layout.setHorizontalGroup(
            formatter24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hourlyWageErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter24Layout.setVerticalGroup(
            formatter24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter24Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(hourlyWageErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        hourlyWageWrapper.setBackground(new java.awt.Color(255, 252, 219));
        hourlyWageWrapper.setForeground(new java.awt.Color(0, 0, 0));
        hourlyWageWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hourlyWageWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hourlyWageWrapperMouseEntered(evt);
            }
        });

        hourlyWageField.setBackground(new java.awt.Color(255, 252, 219));
        hourlyWageField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        hourlyWageField.setForeground(new java.awt.Color(0, 0, 0));
        hourlyWageField.setBorder(null);
        hourlyWageField.setOpaque(false);
        hourlyWageField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        hourlyWageField.setSelectionColor(new java.awt.Color(0, 0, 0));
        hourlyWageField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hourlyWageFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hourlyWageFieldFocusLost(evt);
            }
        });
        hourlyWageField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hourlyWageFieldMouseEntered(evt);
            }
        });
        hourlyWageField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hourlyWageFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hourlyWageFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout hourlyWageWrapperLayout = new javax.swing.GroupLayout(hourlyWageWrapper);
        hourlyWageWrapper.setLayout(hourlyWageWrapperLayout);
        hourlyWageWrapperLayout.setHorizontalGroup(
            hourlyWageWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hourlyWageWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hourlyWageField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        hourlyWageWrapperLayout.setVerticalGroup(
            hourlyWageWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hourlyWageWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hourlyWageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout hourlyWageFormatterLayout = new javax.swing.GroupLayout(hourlyWageFormatter);
        hourlyWageFormatter.setLayout(hourlyWageFormatterLayout);
        hourlyWageFormatterLayout.setHorizontalGroup(
            hourlyWageFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hourlyWageFormatterLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hourlyWageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(hourlyWageWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        hourlyWageFormatterLayout.setVerticalGroup(
            hourlyWageFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hourlyWageFormatterLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(hourlyWageFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hourlyWageWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hourlyWageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        weeksPerYearFormatter.setFocusTraversalPolicyProvider(true);
        weeksPerYearFormatter.setOpaque(false);

        weeksPerYearLabel.setBackground(new java.awt.Color(250, 250, 250));
        weeksPerYearLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        weeksPerYearLabel.setForeground(new java.awt.Color(0, 0, 0));
        weeksPerYearLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        weeksPerYearLabel.setText("weeks:");
        weeksPerYearLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        weeksPerYearLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        weeksPerYearLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        weeksPerYearLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter46.setOpaque(false);

        weeksPerYearErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        weeksPerYearErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        weeksPerYearErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        weeksPerYearErrorLabel.setText("⚠");

        jPanel23.setOpaque(false);
        jPanel23.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel23.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter46Layout = new javax.swing.GroupLayout(formatter46);
        formatter46.setLayout(formatter46Layout);
        formatter46Layout.setHorizontalGroup(
            formatter46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(weeksPerYearErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter46Layout.setVerticalGroup(
            formatter46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter46Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(weeksPerYearErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        weeksPerYearWrapper.setBackground(new java.awt.Color(255, 252, 219));
        weeksPerYearWrapper.setForeground(new java.awt.Color(0, 0, 0));
        weeksPerYearWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                weeksPerYearWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                weeksPerYearWrapperMouseEntered(evt);
            }
        });

        weeksPerYearField.setBackground(new java.awt.Color(255, 252, 219));
        weeksPerYearField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        weeksPerYearField.setForeground(new java.awt.Color(0, 0, 0));
        weeksPerYearField.setBorder(null);
        weeksPerYearField.setOpaque(false);
        weeksPerYearField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        weeksPerYearField.setSelectionColor(new java.awt.Color(0, 0, 0));
        weeksPerYearField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                weeksPerYearFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                weeksPerYearFieldFocusLost(evt);
            }
        });
        weeksPerYearField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                weeksPerYearFieldMouseEntered(evt);
            }
        });
        weeksPerYearField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                weeksPerYearFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                weeksPerYearFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout weeksPerYearWrapperLayout = new javax.swing.GroupLayout(weeksPerYearWrapper);
        weeksPerYearWrapper.setLayout(weeksPerYearWrapperLayout);
        weeksPerYearWrapperLayout.setHorizontalGroup(
            weeksPerYearWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, weeksPerYearWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(weeksPerYearField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        weeksPerYearWrapperLayout.setVerticalGroup(
            weeksPerYearWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(weeksPerYearWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(weeksPerYearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout weeksPerYearFormatterLayout = new javax.swing.GroupLayout(weeksPerYearFormatter);
        weeksPerYearFormatter.setLayout(weeksPerYearFormatterLayout);
        weeksPerYearFormatterLayout.setHorizontalGroup(
            weeksPerYearFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(weeksPerYearFormatterLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(weeksPerYearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(weeksPerYearWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        weeksPerYearFormatterLayout.setVerticalGroup(
            weeksPerYearFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, weeksPerYearFormatterLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(weeksPerYearFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(weeksPerYearWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weeksPerYearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(weeksPerYearFormatter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(yearlySalaryFormatter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(hourlyWageFormatter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(yearlySalaryFormatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(hourlyWageFormatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(weeksPerYearFormatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        formatter5.setFocusTraversalPolicyProvider(true);
        formatter5.setOpaque(false);

        lastNameLabel.setBackground(new java.awt.Color(250, 250, 250));
        lastNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        lastNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastNameLabel.setText("last:");
        lastNameLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lastNameLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        lastNameLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        lastNameLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter6.setOpaque(false);

        lastNameErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        lastNameErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        lastNameErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastNameErrorLabel.setText("⚠");

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel3.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter6Layout = new javax.swing.GroupLayout(formatter6);
        formatter6.setLayout(formatter6Layout);
        formatter6Layout.setHorizontalGroup(
            formatter6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lastNameErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter6Layout.setVerticalGroup(
            formatter6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter6Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lastNameErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        lastNameWrapper.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapperMouseEntered(evt);
            }
        });

        lastNameField.setBackground(new java.awt.Color(255, 252, 219));
        lastNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameField.setForeground(new java.awt.Color(0, 0, 0));
        lastNameField.setBorder(null);
        lastNameField.setOpaque(false);
        lastNameField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        lastNameField.setSelectionColor(new java.awt.Color(0, 0, 0));
        lastNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameFieldFocusLost(evt);
            }
        });
        lastNameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameFieldMouseEntered(evt);
            }
        });
        lastNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lastNameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapperLayout = new javax.swing.GroupLayout(lastNameWrapper);
        lastNameWrapper.setLayout(lastNameWrapperLayout);
        lastNameWrapperLayout.setHorizontalGroup(
            lastNameWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapperLayout.setVerticalGroup(
            lastNameWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter5Layout = new javax.swing.GroupLayout(formatter5);
        formatter5.setLayout(formatter5Layout);
        formatter5Layout.setHorizontalGroup(
            formatter5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lastNameWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter5Layout.setVerticalGroup(
            formatter5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        hoursPerWeekFormatter.setFocusTraversalPolicyProvider(true);
        hoursPerWeekFormatter.setOpaque(false);

        hoursPerWeekLabel.setBackground(new java.awt.Color(250, 250, 250));
        hoursPerWeekLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        hoursPerWeekLabel.setForeground(new java.awt.Color(0, 0, 0));
        hoursPerWeekLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hoursPerWeekLabel.setText("hours:");
        hoursPerWeekLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        hoursPerWeekLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        hoursPerWeekLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        hoursPerWeekLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter22.setOpaque(false);

        hoursPerWeekErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        hoursPerWeekErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        hoursPerWeekErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hoursPerWeekErrorLabel.setText("⚠");

        jPanel11.setOpaque(false);
        jPanel11.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel11.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter22Layout = new javax.swing.GroupLayout(formatter22);
        formatter22.setLayout(formatter22Layout);
        formatter22Layout.setHorizontalGroup(
            formatter22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hoursPerWeekErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter22Layout.setVerticalGroup(
            formatter22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter22Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(hoursPerWeekErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        hoursPerWeekWrapper.setBackground(new java.awt.Color(255, 252, 219));
        hoursPerWeekWrapper.setForeground(new java.awt.Color(0, 0, 0));
        hoursPerWeekWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hoursPerWeekWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hoursPerWeekWrapperMouseEntered(evt);
            }
        });

        hoursPerWeekField.setBackground(new java.awt.Color(255, 252, 219));
        hoursPerWeekField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        hoursPerWeekField.setForeground(new java.awt.Color(0, 0, 0));
        hoursPerWeekField.setBorder(null);
        hoursPerWeekField.setOpaque(false);
        hoursPerWeekField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        hoursPerWeekField.setSelectionColor(new java.awt.Color(0, 0, 0));
        hoursPerWeekField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hoursPerWeekFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hoursPerWeekFieldFocusLost(evt);
            }
        });
        hoursPerWeekField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hoursPerWeekFieldMouseEntered(evt);
            }
        });
        hoursPerWeekField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hoursPerWeekFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hoursPerWeekFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout hoursPerWeekWrapperLayout = new javax.swing.GroupLayout(hoursPerWeekWrapper);
        hoursPerWeekWrapper.setLayout(hoursPerWeekWrapperLayout);
        hoursPerWeekWrapperLayout.setHorizontalGroup(
            hoursPerWeekWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoursPerWeekWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hoursPerWeekField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        hoursPerWeekWrapperLayout.setVerticalGroup(
            hoursPerWeekWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoursPerWeekWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hoursPerWeekField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout hoursPerWeekFormatterLayout = new javax.swing.GroupLayout(hoursPerWeekFormatter);
        hoursPerWeekFormatter.setLayout(hoursPerWeekFormatterLayout);
        hoursPerWeekFormatterLayout.setHorizontalGroup(
            hoursPerWeekFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hoursPerWeekFormatterLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hoursPerWeekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(hoursPerWeekWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        hoursPerWeekFormatterLayout.setVerticalGroup(
            hoursPerWeekFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hoursPerWeekFormatterLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(hoursPerWeekFormatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hoursPerWeekWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoursPerWeekLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter9.setFocusTraversalPolicyProvider(true);
        formatter9.setOpaque(false);

        employeeNumberLabel.setBackground(new java.awt.Color(250, 250, 250));
        employeeNumberLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeNumberLabel.setForeground(new java.awt.Color(0, 0, 0));
        employeeNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        employeeNumberLabel.setText("emp. #:");
        employeeNumberLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        employeeNumberLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        employeeNumberLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        employeeNumberLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter10.setOpaque(false);

        employeeNumberErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        employeeNumberErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        employeeNumberErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeeNumberErrorLabel.setText("⚠");

        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel5.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter10Layout = new javax.swing.GroupLayout(formatter10);
        formatter10.setLayout(formatter10Layout);
        formatter10Layout.setHorizontalGroup(
            formatter10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(employeeNumberErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter10Layout.setVerticalGroup(
            formatter10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter10Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(employeeNumberErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        employeeNumberWrapper.setBackground(new java.awt.Color(255, 252, 219));
        employeeNumberWrapper.setForeground(new java.awt.Color(0, 0, 0));
        employeeNumberWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                employeeNumberWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                employeeNumberWrapperMouseEntered(evt);
            }
        });

        employeeNumberField.setBackground(new java.awt.Color(255, 252, 219));
        employeeNumberField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeNumberField.setForeground(new java.awt.Color(0, 0, 0));
        employeeNumberField.setBorder(null);
        employeeNumberField.setOpaque(false);
        employeeNumberField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        employeeNumberField.setSelectionColor(new java.awt.Color(0, 0, 0));
        employeeNumberField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                employeeNumberFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                employeeNumberFieldFocusLost(evt);
            }
        });
        employeeNumberField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                employeeNumberFieldMouseEntered(evt);
            }
        });
        employeeNumberField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                employeeNumberFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeNumberFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout employeeNumberWrapperLayout = new javax.swing.GroupLayout(employeeNumberWrapper);
        employeeNumberWrapper.setLayout(employeeNumberWrapperLayout);
        employeeNumberWrapperLayout.setHorizontalGroup(
            employeeNumberWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeNumberWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(employeeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        employeeNumberWrapperLayout.setVerticalGroup(
            employeeNumberWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeNumberWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(employeeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter9Layout = new javax.swing.GroupLayout(formatter9);
        formatter9.setLayout(formatter9Layout);
        formatter9Layout.setHorizontalGroup(
            formatter9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(employeeNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(employeeNumberWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter9Layout.setVerticalGroup(
            formatter9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeeNumberWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter15.setFocusTraversalPolicyProvider(true);
        formatter15.setOpaque(false);

        deductionsRateLabel.setBackground(new java.awt.Color(250, 250, 250));
        deductionsRateLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deductionsRateLabel.setForeground(new java.awt.Color(0, 0, 0));
        deductionsRateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deductionsRateLabel.setText("ded. rate:");
        deductionsRateLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deductionsRateLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        deductionsRateLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        deductionsRateLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter16.setOpaque(false);

        deductionsRateErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        deductionsRateErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        deductionsRateErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deductionsRateErrorLabel.setText("⚠");

        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel8.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter16Layout = new javax.swing.GroupLayout(formatter16);
        formatter16.setLayout(formatter16Layout);
        formatter16Layout.setHorizontalGroup(
            formatter16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deductionsRateErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter16Layout.setVerticalGroup(
            formatter16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter16Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(deductionsRateErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        deductionsRateWrapper.setBackground(new java.awt.Color(255, 252, 219));
        deductionsRateWrapper.setForeground(new java.awt.Color(0, 0, 0));
        deductionsRateWrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deductionsRateWrapperMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deductionsRateWrapperMouseEntered(evt);
            }
        });

        deductionsRateField.setBackground(new java.awt.Color(255, 252, 219));
        deductionsRateField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deductionsRateField.setForeground(new java.awt.Color(0, 0, 0));
        deductionsRateField.setBorder(null);
        deductionsRateField.setOpaque(false);
        deductionsRateField.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        deductionsRateField.setSelectionColor(new java.awt.Color(0, 0, 0));
        deductionsRateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                deductionsRateFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                deductionsRateFieldFocusLost(evt);
            }
        });
        deductionsRateField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deductionsRateFieldMouseEntered(evt);
            }
        });
        deductionsRateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deductionsRateFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                deductionsRateFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout deductionsRateWrapperLayout = new javax.swing.GroupLayout(deductionsRateWrapper);
        deductionsRateWrapper.setLayout(deductionsRateWrapperLayout);
        deductionsRateWrapperLayout.setHorizontalGroup(
            deductionsRateWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deductionsRateWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(deductionsRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        deductionsRateWrapperLayout.setVerticalGroup(
            deductionsRateWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deductionsRateWrapperLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(deductionsRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter15Layout = new javax.swing.GroupLayout(formatter15);
        formatter15.setLayout(formatter15Layout);
        formatter15Layout.setHorizontalGroup(
            formatter15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter15Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(deductionsRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(deductionsRateWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter15Layout.setVerticalGroup(
            formatter15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deductionsRateWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deductionsRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter.setOpaque(false);

        checkFullEmployeeTypeButton.setBackground(new java.awt.Color(250, 250, 250));
        checkFullEmployeeTypeButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        checkFullEmployeeTypeButton.setForeground(new java.awt.Color(0, 0, 0));
        checkFullEmployeeTypeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkFullEmployeeTypeButton.setText("full-time");
        checkFullEmployeeTypeButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 202, 249), 2));
        checkFullEmployeeTypeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkFullEmployeeTypeButton.setMaximumSize(new java.awt.Dimension(75, 30));
        checkFullEmployeeTypeButton.setMinimumSize(new java.awt.Dimension(75, 30));
        checkFullEmployeeTypeButton.setOpaque(true);
        checkFullEmployeeTypeButton.setPreferredSize(new java.awt.Dimension(75, 30));
        checkFullEmployeeTypeButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                checkFullEmployeeTypeButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkFullEmployeeTypeButtonFocusLost(evt);
            }
        });
        checkFullEmployeeTypeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkFullEmployeeTypeButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkFullEmployeeTypeButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkFullEmployeeTypeButtonMouseEntered(evt);
            }
        });
        checkFullEmployeeTypeButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkFullEmployeeTypeButtonKeyPressed(evt);
            }
        });

        checkPartEmployeeTypeButton.setBackground(new java.awt.Color(250, 250, 250));
        checkPartEmployeeTypeButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        checkPartEmployeeTypeButton.setForeground(new java.awt.Color(0, 0, 0));
        checkPartEmployeeTypeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkPartEmployeeTypeButton.setText("part-time");
        checkPartEmployeeTypeButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 202, 249), 2));
        checkPartEmployeeTypeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkPartEmployeeTypeButton.setMaximumSize(new java.awt.Dimension(75, 30));
        checkPartEmployeeTypeButton.setMinimumSize(new java.awt.Dimension(75, 30));
        checkPartEmployeeTypeButton.setOpaque(true);
        checkPartEmployeeTypeButton.setPreferredSize(new java.awt.Dimension(75, 30));
        checkPartEmployeeTypeButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                checkPartEmployeeTypeButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkPartEmployeeTypeButtonFocusLost(evt);
            }
        });
        checkPartEmployeeTypeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPartEmployeeTypeButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkPartEmployeeTypeButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkPartEmployeeTypeButtonMouseEntered(evt);
            }
        });
        checkPartEmployeeTypeButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkPartEmployeeTypeButtonKeyPressed(evt);
            }
        });

        typeLabel.setBackground(new java.awt.Color(250, 250, 250));
        typeLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        typeLabel.setForeground(new java.awt.Color(0, 0, 0));
        typeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        typeLabel.setText("type:");
        typeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        typeLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        typeLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        typeLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter2.setOpaque(false);

        typeErrorLabel.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        typeErrorLabel.setForeground(new java.awt.Color(244, 67, 54));
        typeErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeErrorLabel.setText("⚠");

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(26, 2));
        jPanel1.setSize(new java.awt.Dimension(26, 2));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formatter2Layout = new javax.swing.GroupLayout(formatter2);
        formatter2.setLayout(formatter2Layout);
        formatter2Layout.setHorizontalGroup(
            formatter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(typeErrorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter2Layout.setVerticalGroup(
            formatter2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(typeErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout formatterLayout = new javax.swing.GroupLayout(formatter);
        formatter.setLayout(formatterLayout);
        formatterLayout.setHorizontalGroup(
            formatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatterLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(checkFullEmployeeTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(checkPartEmployeeTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(formatter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatterLayout.setVerticalGroup(
            formatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(formatterLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formatterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(typeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkFullEmployeeTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkPartEmployeeTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(formatter2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(formatter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hoursPerWeekFormatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(formatter17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(formatter5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formatter7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(formatter15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(formatter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(formatter5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(formatter7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(formatter15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(formatter17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hoursPerWeekFormatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout editEmployeePanelLayout = new javax.swing.GroupLayout(editEmployeePanel);
        editEmployeePanel.setLayout(editEmployeePanelLayout);
        editEmployeePanelLayout.setHorizontalGroup(
            editEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(formatter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(editEmployeePanelLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(editEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        editEmployeePanelLayout.setVerticalGroup(
            editEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editEmployeePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formatter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        getContentPane().add(editEmployeePanel, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="confirm cancel event handling">
    
    private void confirmButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMousePressed
        // TODO add your handling code here:
        confirmButton.setBackground(colorSelector.button_confirm_pressed);
    }//GEN-LAST:event_confirmButtonMousePressed

    private void confirmButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseReleased
        // TODO add your handling code here:
        confirmButton.setBackground(colorSelector.button_confirm_background);
    }//GEN-LAST:event_confirmButtonMouseReleased

    private void confirmButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseExited
        // TODO add your handling code here:
        confirmButton.setBackground(colorSelector.button_confirm_background);
    }//GEN-LAST:event_confirmButtonMouseExited

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered
        // TODO add your handling code here:
        confirmButton.setBackground(colorSelector.button_confirm_hover);
    }//GEN-LAST:event_confirmButtonMouseEntered

    private void resetButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMousePressed
        // TODO add your handling code here:
        resetButton.setBackground(colorSelector.button_cancel_pressed);
    }//GEN-LAST:event_resetButtonMousePressed

    private void resetButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseReleased
        // TODO add your handling code here:
        resetButton.setBackground(colorSelector.button_cancel_background);
    }//GEN-LAST:event_resetButtonMouseReleased

    private void resetButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseExited
        // TODO add your handling code here:
        resetButton.setBackground(colorSelector.button_cancel_background);
    }//GEN-LAST:event_resetButtonMouseExited

    private void resetButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseEntered
        // TODO add your handling code here:
        resetButton.setBackground(colorSelector.button_cancel_hover);
    }//GEN-LAST:event_resetButtonMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="check employee type">
    
    private void checkFullEmployeeTypeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkFullEmployeeTypeButtonMouseEntered
        // TODO add your handling code here:
        if (type != 0) {
            checkFullEmployeeTypeButton.setBackground(colorSelector.check_hover);
        }
    }//GEN-LAST:event_checkFullEmployeeTypeButtonMouseEntered

    private void checkFullEmployeeTypeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkFullEmployeeTypeButtonMouseExited
        // TODO add your handling code here:
        if (type == 0) {
            // full-time
            checkFullEmployeeTypeButton.setBackground(colorSelector.check_active_background);
        } else {
            // part-time
            checkFullEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkFullEmployeeTypeButtonMouseExited

    private void checkFullEmployeeTypeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkFullEmployeeTypeButtonMouseClicked
        // TODO add your handling code here:
        // set to full-time
        type = 0;
        checkType();
        updateEditDisplay();
    }//GEN-LAST:event_checkFullEmployeeTypeButtonMouseClicked

    private void checkPartEmployeeTypeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonMouseClicked
        // TODO add your handling code here:
        // set to part-time
        type = 1;
        checkType();
        updateEditDisplay();
    }//GEN-LAST:event_checkPartEmployeeTypeButtonMouseClicked

    private void checkPartEmployeeTypeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonMouseExited
        // TODO add your handling code here:
        if (type == 1) {
            // full-time
            checkPartEmployeeTypeButton.setBackground(colorSelector.check_active_background);
        } else {
            // part-time
            checkPartEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkPartEmployeeTypeButtonMouseExited

    private void checkPartEmployeeTypeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonMouseEntered
        // TODO add your handling code here:
        if (type != 1) {
            checkPartEmployeeTypeButton.setBackground(colorSelector.check_hover);
        }
    }//GEN-LAST:event_checkPartEmployeeTypeButtonMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="reset mouse button click">
    
    private void resetButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetButtonMouseClicked
        // TODO add your handling code here:
        editing = false;
        updateViewDisplay();
    }//GEN-LAST:event_resetButtonMouseClicked

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="first name">
    
    private void firstNameWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstNameWrapperMouseEntered
        // TODO add your handling code here:
        if (!firstNameField.hasFocus()) {
            firstNameWrapper.setBackground(colorSelector.text_field_hover);
            firstNameField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_firstNameWrapperMouseEntered

    private void firstNameWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstNameWrapperMouseExited
        // TODO add your handling code here:
        if (!firstNameField.hasFocus()) {
            firstNameWrapper.setBackground(colorSelector.text_field_background);
            firstNameField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_firstNameWrapperMouseExited

    private void firstNameFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstNameFieldMouseEntered
        // TODO add your handling code here:
        if (!firstNameField.hasFocus()) {
            firstNameWrapper.setBackground(colorSelector.text_field_hover);
            firstNameField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_firstNameFieldMouseEntered

    private void firstNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFieldFocusGained
        // TODO add your handling code here:
        firstNameWrapper.setBackground(colorSelector.text_field_focus);
        firstNameField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_firstNameFieldFocusGained

    private void firstNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFieldFocusLost
        // TODO add your handling code here:
        firstNameWrapper.setBackground(colorSelector.text_field_background);
        firstNameField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_firstNameFieldFocusLost

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="last name">
    
    private void lastNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFieldFocusGained
        // TODO add your handling code here:
        lastNameWrapper.setBackground(colorSelector.text_field_focus);
        lastNameField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_lastNameFieldFocusGained

    private void lastNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFieldFocusLost
        // TODO add your handling code here:
        lastNameWrapper.setBackground(colorSelector.text_field_background);
        lastNameField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_lastNameFieldFocusLost

    private void lastNameFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameFieldMouseEntered
        // TODO add your handling code here:
        if (!lastNameField.hasFocus()) {
            lastNameWrapper.setBackground(colorSelector.text_field_hover);
            lastNameField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_lastNameFieldMouseEntered

    private void lastNameWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapperMouseExited
        // TODO add your handling code here:
        if (!lastNameField.hasFocus()) {
            lastNameWrapper.setBackground(colorSelector.text_field_background);
            lastNameField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_lastNameWrapperMouseExited

    private void lastNameWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapperMouseEntered
        // TODO add your handling code here:
        if (!lastNameField.hasFocus()) {
            lastNameWrapper.setBackground(colorSelector.text_field_hover);
            lastNameField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_lastNameWrapperMouseEntered

    // </editor-fold>
       
    // <editor-fold defaultstate="collapsed" desc="gender">
    
    private void checkGenderMaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderMaleMouseClicked
        // TODO add your handling code here:
        gender = "M";
        checkGender();
        updateEditDisplay();
    }//GEN-LAST:event_checkGenderMaleMouseClicked

    private void checkGenderMaleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderMaleMouseExited
        // TODO add your handling code here:
        if (gender.equals("M")) {
            checkGenderMale.setBackground(colorSelector.check_active_background);
        } else {
            checkGenderMale.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkGenderMaleMouseExited

    private void checkGenderMaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderMaleMouseEntered
        // TODO add your handling code here:
        if (!gender.equals("M")) {
            checkGenderMale.setBackground(colorSelector.check_hover);
        }
    }//GEN-LAST:event_checkGenderMaleMouseEntered

    private void checkGenderFemaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderFemaleMouseClicked
        // TODO add your handling code here:
        gender = "F";
        checkGender();
        updateEditDisplay();
    }//GEN-LAST:event_checkGenderFemaleMouseClicked

    private void checkGenderFemaleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderFemaleMouseExited
        // TODO add your handling code here:
        if (gender.equals("F")) {
            checkGenderFemale.setBackground(colorSelector.check_active_background);
        } else {
            checkGenderFemale.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkGenderFemaleMouseExited

    private void checkGenderFemaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderFemaleMouseEntered
        // TODO add your handling code here:
        if (!gender.equals("F")) {
            checkGenderFemale.setBackground(colorSelector.check_hover);
        }
    }//GEN-LAST:event_checkGenderFemaleMouseEntered

    private void checkGenderOtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderOtherMouseClicked
        // TODO add your handling code here:
        gender = "O";
        checkGender();
        updateEditDisplay();
    }//GEN-LAST:event_checkGenderOtherMouseClicked

    private void checkGenderOtherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderOtherMouseExited
        // TODO add your handling code here:
        if (gender.equals("O")) {
            checkGenderOther.setBackground(colorSelector.check_active_background);
        } else {
            checkGenderOther.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkGenderOtherMouseExited

    private void checkGenderOtherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderOtherMouseEntered
        // TODO add your handling code here:
        if (!gender.equals("O")) {
            checkGenderOther.setBackground(colorSelector.check_hover);
        }
    }//GEN-LAST:event_checkGenderOtherMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="first last name key pressed">
    
    private void firstNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            firstName = firstNameField.getText();
            checkFirstName();
            updateEditDisplay();
        }
    }//GEN-LAST:event_firstNameFieldKeyReleased
    
    private void lastNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            lastName = lastNameField.getText();
            checkLastName();
            updateEditDisplay();
        }
    }//GEN-LAST:event_lastNameFieldKeyReleased

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="custom focus">
    
    private void checkFullEmployeeTypeButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkFullEmployeeTypeButtonFocusGained
        // TODO add your handling code here:
        checkFullEmployeeTypeButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_focus, 2));
    }//GEN-LAST:event_checkFullEmployeeTypeButtonFocusGained

    private void checkFullEmployeeTypeButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkFullEmployeeTypeButtonFocusLost
        // TODO add your handling code here:
        checkFullEmployeeTypeButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_active_background, 2));
    }//GEN-LAST:event_checkFullEmployeeTypeButtonFocusLost

    private void checkPartEmployeeTypeButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonFocusGained
        // TODO add your handling code here:
        checkPartEmployeeTypeButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_focus, 2));
    }//GEN-LAST:event_checkPartEmployeeTypeButtonFocusGained

    private void checkPartEmployeeTypeButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonFocusLost
        // TODO add your handling code here:
        checkPartEmployeeTypeButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_active_background, 2));
    }//GEN-LAST:event_checkPartEmployeeTypeButtonFocusLost

    private void checkGenderMaleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkGenderMaleFocusGained
        // TODO add your handling code here:
        checkGenderMale.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_focus, 2));
    }//GEN-LAST:event_checkGenderMaleFocusGained

    private void checkGenderMaleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkGenderMaleFocusLost
        // TODO add your handling code here:
        checkGenderMale.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_active_background, 2));
    }//GEN-LAST:event_checkGenderMaleFocusLost

    private void checkGenderFemaleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkGenderFemaleFocusGained
        // TODO add your handling code here:
        checkGenderFemale.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_focus, 2));
    }//GEN-LAST:event_checkGenderFemaleFocusGained

    private void checkGenderFemaleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkGenderFemaleFocusLost
        // TODO add your handling code here:
        checkGenderFemale.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_active_background, 2));
    }//GEN-LAST:event_checkGenderFemaleFocusLost

    private void checkGenderOtherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkGenderOtherFocusGained
        // TODO add your handling code here:
        checkGenderOther.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_focus, 2));
    }//GEN-LAST:event_checkGenderOtherFocusGained

    private void checkGenderOtherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkGenderOtherFocusLost
        // TODO add your handling code here:
        checkGenderOther.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.check_active_background, 2));
    }//GEN-LAST:event_checkGenderOtherFocusLost

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="custom focus press">
    
    private void checkFullEmployeeTypeButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkFullEmployeeTypeButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            type = 0;
            
            checkType();
            updateEditDisplay();
            
            checkGenderMale.requestFocus();
        }
    }//GEN-LAST:event_checkFullEmployeeTypeButtonKeyPressed

    private void checkPartEmployeeTypeButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            type = 1;
            
            checkType();
            updateEditDisplay();
            
            checkGenderMale.requestFocus();
        }
    }//GEN-LAST:event_checkPartEmployeeTypeButtonKeyPressed

    private void checkGenderMaleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGenderMaleKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            gender = "M";
            
            checkGender();
            updateEditDisplay();
            
            employeeNumberField.requestFocus();
        }
    }//GEN-LAST:event_checkGenderMaleKeyPressed

    private void checkGenderFemaleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGenderFemaleKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            gender = "F";
            
            checkGender();
            updateEditDisplay();
            
            employeeNumberField.requestFocus();
        }
    }//GEN-LAST:event_checkGenderFemaleKeyPressed

    private void checkGenderOtherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGenderOtherKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            gender = "O";
            
            checkGender();
            updateEditDisplay();
            
            employeeNumberField.requestFocus();
        }
    }//GEN-LAST:event_checkGenderOtherKeyPressed

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="confirm cancel custom focus">
    
    private void confirmButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmButtonFocusGained
        // TODO add your handling code here:
        confirmButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.button_confirm_focus, 2));
    }//GEN-LAST:event_confirmButtonFocusGained

    private void confirmButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmButtonFocusLost
        // TODO add your handling code here:
        confirmButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.button_confirm_background, 2));
    }//GEN-LAST:event_confirmButtonFocusLost

    private void resetButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_resetButtonFocusGained
        // TODO add your handling code here:
        resetButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.button_cancel_focus, 2));
    }//GEN-LAST:event_resetButtonFocusGained

    private void resetButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_resetButtonFocusLost
        // TODO add your handling code here:
        resetButton.setBorder(javax.swing.BorderFactory.createLineBorder(colorSelector.button_cancel_background, 2));
    }//GEN-LAST:event_resetButtonFocusLost

    private void resetButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resetButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            editing = false;
            updateViewDisplay();
        }
    }//GEN-LAST:event_resetButtonKeyPressed

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="employee number">
    
    private void employeeNumberFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeNumberFieldFocusGained
        // TODO add your handling code here:
        employeeNumberWrapper.setBackground(colorSelector.text_field_focus);
        employeeNumberField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_employeeNumberFieldFocusGained

    private void employeeNumberFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeNumberFieldFocusLost
        // TODO add your handling code here:
        employeeNumberWrapper.setBackground(colorSelector.text_field_background);
        employeeNumberField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_employeeNumberFieldFocusLost

    private void employeeNumberFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeNumberFieldMouseEntered
        // TODO add your handling code here:
        if (!employeeNumberField.hasFocus()) {
            employeeNumberWrapper.setBackground(colorSelector.text_field_hover);
            employeeNumberField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_employeeNumberFieldMouseEntered

    private void employeeNumberFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeNumberFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            employeeNumber = employeeNumberField.getText();
            checkEmployeeNumber();
            updateEditDisplay();
        }
    }//GEN-LAST:event_employeeNumberFieldKeyReleased

    private void employeeNumberWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeNumberWrapperMouseExited
        // TODO add your handling code here:
        if (!employeeNumberField.hasFocus()) {
            employeeNumberWrapper.setBackground(colorSelector.text_field_background);
            employeeNumberField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_employeeNumberWrapperMouseExited

    private void employeeNumberWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeNumberWrapperMouseEntered
        // TODO add your handling code here:
        if (!employeeNumberField.hasFocus()) {
            employeeNumberWrapper.setBackground(colorSelector.text_field_hover);
            employeeNumberField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_employeeNumberWrapperMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="deductions rate">
    
    private void deductionsRateFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deductionsRateFieldFocusGained
        // TODO add your handling code here:
        deductionsRateWrapper.setBackground(colorSelector.text_field_focus);
        deductionsRateField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_deductionsRateFieldFocusGained

    private void deductionsRateFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deductionsRateFieldFocusLost
        // TODO add your handling code here:
        deductionsRateWrapper.setBackground(colorSelector.text_field_background);
        deductionsRateField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_deductionsRateFieldFocusLost

    private void deductionsRateFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionsRateFieldMouseEntered
        // TODO add your handling code here:
        if (!deductionsRateField.hasFocus()) {
            deductionsRateWrapper.setBackground(colorSelector.text_field_hover);
            deductionsRateField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_deductionsRateFieldMouseEntered

    private void deductionsRateFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionsRateFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            deductionsRate = deductionsRateField.getText();
            checkDeductionsRate();
            updateEditDisplay();
        }
    }//GEN-LAST:event_deductionsRateFieldKeyReleased

    private void deductionsRateWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionsRateWrapperMouseExited
        // TODO add your handling code here:
        if (!deductionsRateField.hasFocus()) {
            deductionsRateWrapper.setBackground(colorSelector.text_field_background);
            deductionsRateField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_deductionsRateWrapperMouseExited

    private void deductionsRateWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionsRateWrapperMouseEntered
        // TODO add your handling code here:
        if (!deductionsRateField.hasFocus()) {
            deductionsRateWrapper.setBackground(colorSelector.text_field_hover);
            deductionsRateField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_deductionsRateWrapperMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="work location">
    
    private void workLocationFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_workLocationFieldFocusGained
        // TODO add your handling code here:
        workLocationWrapper.setBackground(colorSelector.text_field_focus);
        workLocationField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_workLocationFieldFocusGained

    private void workLocationFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_workLocationFieldFocusLost
        // TODO add your handling code here:
        workLocationWrapper.setBackground(colorSelector.text_field_background);
        workLocationField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_workLocationFieldFocusLost

    private void workLocationFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workLocationFieldMouseEntered
        // TODO add your handling code here:
        if (!workLocationField.hasFocus()) {
            workLocationWrapper.setBackground(colorSelector.text_field_hover);
            workLocationField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_workLocationFieldMouseEntered

    private void workLocationFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workLocationFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            workLocation = workLocationField.getText();
            checkWorkLocation();
            updateEditDisplay();
        }
    }//GEN-LAST:event_workLocationFieldKeyReleased

    private void workLocationWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workLocationWrapperMouseExited
        // TODO add your handling code here:
        if (!workLocationField.hasFocus()) {
            workLocationWrapper.setBackground(colorSelector.text_field_background);
            workLocationField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_workLocationWrapperMouseExited

    private void workLocationWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workLocationWrapperMouseEntered
        // TODO add your handling code here:
        if (!workLocationField.hasFocus()) {
            workLocationWrapper.setBackground(colorSelector.text_field_hover);
            workLocationField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_workLocationWrapperMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="yearly salary">
    
    private void yearlySalaryFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearlySalaryFieldFocusGained
        // TODO add your handling code here:
        yearlySalaryWrapper.setBackground(colorSelector.text_field_focus);
        yearlySalaryField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_yearlySalaryFieldFocusGained

    private void yearlySalaryFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearlySalaryFieldFocusLost
        // TODO add your handling code here:
        yearlySalaryWrapper.setBackground(colorSelector.text_field_background);
        yearlySalaryField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_yearlySalaryFieldFocusLost

    private void yearlySalaryFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearlySalaryFieldMouseEntered
        // TODO add your handling code here:
        if (!yearlySalaryField.hasFocus()) {
            yearlySalaryWrapper.setBackground(colorSelector.text_field_hover);
            yearlySalaryField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_yearlySalaryFieldMouseEntered

    private void yearlySalaryFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearlySalaryFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            yearlySalary = yearlySalaryField.getText();
            checkYearlySalary();
            updateEditDisplay();
        }
    }//GEN-LAST:event_yearlySalaryFieldKeyReleased

    private void yearlySalaryWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearlySalaryWrapperMouseExited
        // TODO add your handling code here:
        if (!yearlySalaryField.hasFocus()) {
            yearlySalaryWrapper.setBackground(colorSelector.text_field_background);
            yearlySalaryField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_yearlySalaryWrapperMouseExited

    private void yearlySalaryWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearlySalaryWrapperMouseEntered
        // TODO add your handling code here:
        if (!yearlySalaryField.hasFocus()) {
            yearlySalaryWrapper.setBackground(colorSelector.text_field_hover);
            yearlySalaryField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_yearlySalaryWrapperMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="hours per week">
    
    private void hoursPerWeekFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldFocusGained
        // TODO add your handling code here:
        hoursPerWeekWrapper.setBackground(colorSelector.text_field_focus);
        hoursPerWeekField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_hoursPerWeekFieldFocusGained

    private void hoursPerWeekFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldFocusLost
        // TODO add your handling code here:
        hoursPerWeekWrapper.setBackground(colorSelector.text_field_background);
        hoursPerWeekField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_hoursPerWeekFieldFocusLost

    private void hoursPerWeekFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldMouseEntered
        // TODO add your handling code here:
        if (!hoursPerWeekField.hasFocus()) {
            hoursPerWeekWrapper.setBackground(colorSelector.text_field_hover);
            hoursPerWeekField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_hoursPerWeekFieldMouseEntered

    private void hoursPerWeekFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            hoursPerWeek = hoursPerWeekField.getText();
            checkHoursPerWeek();
            updateEditDisplay();
        }
    }//GEN-LAST:event_hoursPerWeekFieldKeyReleased

    private void hoursPerWeekWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoursPerWeekWrapperMouseExited
        // TODO add your handling code here:
        if (!hoursPerWeekField.hasFocus()) {
            hoursPerWeekWrapper.setBackground(colorSelector.text_field_background);
            hoursPerWeekField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_hoursPerWeekWrapperMouseExited

    private void hoursPerWeekWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hoursPerWeekWrapperMouseEntered
        // TODO add your handling code here:
        if (!hoursPerWeekField.hasFocus()) {
            hoursPerWeekWrapper.setBackground(colorSelector.text_field_hover);
            hoursPerWeekField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_hoursPerWeekWrapperMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="hourly wage">
    
    private void hourlyWageFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hourlyWageFieldFocusGained
        // TODO add your handling code here:
        hourlyWageWrapper.setBackground(colorSelector.text_field_focus);
        hourlyWageField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_hourlyWageFieldFocusGained

    private void hourlyWageFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hourlyWageFieldFocusLost
        // TODO add your handling code here:
        hourlyWageWrapper.setBackground(colorSelector.text_field_background);
        hourlyWageField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_hourlyWageFieldFocusLost

    private void hourlyWageFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hourlyWageFieldMouseEntered
        // TODO add your handling code here:
        if (!hourlyWageField.hasFocus()) {
            hourlyWageWrapper.setBackground(colorSelector.text_field_hover);
            hourlyWageField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_hourlyWageFieldMouseEntered

    private void hourlyWageFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hourlyWageFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            hourlyWage = hourlyWageField.getText();
            checkHourlyWage();
            updateEditDisplay();
        }
    }//GEN-LAST:event_hourlyWageFieldKeyReleased

    private void hourlyWageWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hourlyWageWrapperMouseExited
        // TODO add your handling code here:
        if (!hourlyWageField.hasFocus()) {
            hourlyWageWrapper.setBackground(colorSelector.text_field_background);
            hourlyWageField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_hourlyWageWrapperMouseExited

    private void hourlyWageWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hourlyWageWrapperMouseEntered
        // TODO add your handling code here:
        if (!hourlyWageField.hasFocus()) {
            hourlyWageWrapper.setBackground(colorSelector.text_field_hover);
            hourlyWageField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_hourlyWageWrapperMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="weeks per year">
    
    private void weeksPerYearFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_weeksPerYearFieldFocusGained
        // TODO add your handling code here:
        weeksPerYearWrapper.setBackground(colorSelector.text_field_focus);
        weeksPerYearField.setBackground(colorSelector.text_field_focus);
    }//GEN-LAST:event_weeksPerYearFieldFocusGained

    private void weeksPerYearFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_weeksPerYearFieldFocusLost
        // TODO add your handling code here:
        weeksPerYearWrapper.setBackground(colorSelector.text_field_background);
        weeksPerYearField.setBackground(colorSelector.text_field_background);
    }//GEN-LAST:event_weeksPerYearFieldFocusLost

    private void weeksPerYearFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_weeksPerYearFieldMouseEntered
        // TODO add your handling code here:
        if (!weeksPerYearField.hasFocus()) {
            weeksPerYearWrapper.setBackground(colorSelector.text_field_hover);
            weeksPerYearField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_weeksPerYearFieldMouseEntered

    private void weeksPerYearFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_weeksPerYearFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            weeksPerYear = weeksPerYearField.getText();
            checkWeeksPerYear();
            updateEditDisplay();
        }
    }//GEN-LAST:event_weeksPerYearFieldKeyReleased

    private void weeksPerYearWrapperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_weeksPerYearWrapperMouseExited
        // TODO add your handling code here:
        if (!weeksPerYearField.hasFocus()) {
            weeksPerYearWrapper.setBackground(colorSelector.text_field_background);
            weeksPerYearField.setBackground(colorSelector.text_field_background);
        }
    }//GEN-LAST:event_weeksPerYearWrapperMouseExited

    private void weeksPerYearWrapperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_weeksPerYearWrapperMouseEntered
        // TODO add your handling code here:
        if (!weeksPerYearField.hasFocus()) {
            weeksPerYearWrapper.setBackground(colorSelector.text_field_hover);
            weeksPerYearField.setBackground(colorSelector.text_field_hover);
        }
    }//GEN-LAST:event_weeksPerYearWrapperMouseEntered

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="text field enter key movement">
    
    private void firstNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            lastNameField.requestFocus();
        }
    }//GEN-LAST:event_firstNameFieldKeyPressed

    private void lastNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkFullEmployeeTypeButton.requestFocus();
        }
    }//GEN-LAST:event_lastNameFieldKeyPressed

    private void employeeNumberFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeNumberFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            deductionsRateField.requestFocus();
        }
    }//GEN-LAST:event_employeeNumberFieldKeyPressed

    private void deductionsRateFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionsRateFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            workLocationField.requestFocus();
        }
    }//GEN-LAST:event_deductionsRateFieldKeyPressed

    private void workLocationFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workLocationFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (type) {
                case 0:
                    yearlySalaryField.requestFocus();
                    break;
                case 1:
                    hourlyWageField.requestFocus();
                    break;
                default:
                    confirmButton.requestFocus();
                    break;
            }
        }
    }//GEN-LAST:event_workLocationFieldKeyPressed

    private void yearlySalaryFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearlySalaryFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmButton.requestFocus();
        }
    }//GEN-LAST:event_yearlySalaryFieldKeyPressed

    private void hourlyWageFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hourlyWageFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hoursPerWeekField.requestFocus();
        }
    }//GEN-LAST:event_hourlyWageFieldKeyPressed

    private void hoursPerWeekFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hoursPerWeekFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            weeksPerYearField.requestFocus();
        }
    }//GEN-LAST:event_hoursPerWeekFieldKeyPressed

    private void weeksPerYearFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_weeksPerYearFieldKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmButton.requestFocus();
        }
    }//GEN-LAST:event_weeksPerYearFieldKeyPressed

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="delete button">
    
    private void deleteButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deleteButtonFocusGained
        // TODO add your handling code here:
        deleteButton.setBackground(colorSelector.button_cancel_hover);
    }//GEN-LAST:event_deleteButtonFocusGained

    private void deleteButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deleteButtonFocusLost
        // TODO add your handling code here:
        deleteButton.setBackground(colorSelector.button_cancel_background);
    }//GEN-LAST:event_deleteButtonFocusLost

    private void deleteButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMousePressed
        // TODO add your handling code here:
        deleteButton.setBackground(colorSelector.button_cancel_pressed);
    }//GEN-LAST:event_deleteButtonMousePressed

    private void deleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseReleased
        // TODO add your handling code here:
        deleteButton.setBackground(colorSelector.button_cancel_background);
    }//GEN-LAST:event_deleteButtonMouseReleased

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        // TODO add your handling code here:
        if (MainComponent.employeeDatabase.removeEmployee(employee.empNumber)) {
            MainComponent.updateDashboard();
            this.dispose();
        }
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void deleteButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseExited
        // TODO add your handling code here:
        deleteButton.setBackground(colorSelector.button_cancel_background);
    }//GEN-LAST:event_deleteButtonMouseExited

    private void deleteButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseEntered
        // TODO add your handling code here:
        deleteButton.setBackground(colorSelector.button_cancel_hover);
    }//GEN-LAST:event_deleteButtonMouseEntered

    private void deleteButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deleteButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (MainComponent.employeeDatabase.removeEmployee(employee.empNumber)) {
                MainComponent.updateDashboard();
                this.dispose();
            }
        }
    }//GEN-LAST:event_deleteButtonKeyPressed

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="edit button">
    
    private void editButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editButtonFocusGained
        // TODO add your handling code here:
        editButton.setBackground(colorSelector.button_edit_hover);
    }//GEN-LAST:event_editButtonFocusGained

    private void editButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_editButtonFocusLost
        // TODO add your handling code here:
        editButton.setBackground(colorSelector.button_edit_background);
    }//GEN-LAST:event_editButtonFocusLost

    private void editButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMousePressed
        // TODO add your handling code here:
        editButton.setBackground(colorSelector.button_edit_pressed);
    }//GEN-LAST:event_editButtonMousePressed

    private void editButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseReleased
        // TODO add your handling code here:
        editButton.setBackground(colorSelector.button_edit_background);
    }//GEN-LAST:event_editButtonMouseReleased

    private void editButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseExited
        // TODO add your handling code here:
        editButton.setBackground(colorSelector.button_edit_background);
    }//GEN-LAST:event_editButtonMouseExited

    private void editButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseEntered
        // TODO add your handling code here:
        editButton.setBackground(colorSelector.button_edit_hover);
    }//GEN-LAST:event_editButtonMouseEntered

    private void editButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseClicked
        // TODO add your handling code here:
        reset();
        editing = true;
        updateEditDisplay();
        updateViewDisplay();
    }//GEN-LAST:event_editButtonMouseClicked

    private void editButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            reset();
            editing = true;
            updateEditDisplay();
            updateViewDisplay();
        }
    }//GEN-LAST:event_editButtonKeyPressed

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="save button">
    
    private void confirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseClicked
        // TODO add your handling code here:
        checkNewEmployee();
    }//GEN-LAST:event_confirmButtonMouseClicked

    private void confirmButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkNewEmployee();
        }
    }//GEN-LAST:event_confirmButtonKeyPressed

    // </editor-fold>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    
    // <editor-fold defaultstate="collapsed" desc="generated variables">

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel annualIncomeLabel;
    public javax.swing.JLabel checkFullEmployeeTypeButton;
    private javax.swing.JLabel checkGenderErrorLabel;
    public javax.swing.JLabel checkGenderFemale;
    public javax.swing.JLabel checkGenderMale;
    public javax.swing.JLabel checkGenderOther;
    public javax.swing.JLabel checkPartEmployeeTypeButton;
    public javax.swing.JLabel confirmButton;
    private javax.swing.JLabel deductionsRateErrorLabel;
    private javax.swing.JTextField deductionsRateField;
    public javax.swing.JLabel deductionsRateLabel;
    public javax.swing.JLabel deductionsRateLabel1;
    private javax.swing.JPanel deductionsRateWrapper;
    public javax.swing.JLabel deleteButton;
    public javax.swing.JLabel editButton;
    public javax.swing.JPanel editEmployeePanel;
    public javax.swing.JLabel employeeAnnualIncome;
    public javax.swing.JLabel employeeDeductionsRate;
    public javax.swing.JLabel employeeEmployeeNumber;
    public javax.swing.JLabel employeeFirstName;
    public javax.swing.JLabel employeeGender;
    public javax.swing.JLabel employeeHourlyWage;
    public javax.swing.JLabel employeeHoursPerWeek;
    public javax.swing.JLabel employeeLastName;
    private javax.swing.JLabel employeeNumberErrorLabel;
    private javax.swing.JTextField employeeNumberField;
    public javax.swing.JLabel employeeNumberLabel;
    public javax.swing.JLabel employeeNumberLabel1;
    private javax.swing.JPanel employeeNumberWrapper;
    private javax.swing.JLabel employeeTitle;
    public javax.swing.JLabel employeeType;
    public javax.swing.JLabel employeeWeeksPerYear;
    public javax.swing.JLabel employeeWorkLocation;
    public javax.swing.JLabel employeeYearlySalary;
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel firstNameErrorLabel;
    private javax.swing.JTextField firstNameField;
    public javax.swing.JLabel firstNameLabel;
    public javax.swing.JLabel firstNameLabel1;
    private javax.swing.JPanel firstNameWrapper;
    private javax.swing.JPanel formatter;
    private javax.swing.JPanel formatter1;
    private javax.swing.JPanel formatter10;
    private javax.swing.JPanel formatter11;
    private javax.swing.JPanel formatter12;
    private javax.swing.JPanel formatter13;
    private javax.swing.JPanel formatter15;
    private javax.swing.JPanel formatter16;
    private javax.swing.JPanel formatter17;
    private javax.swing.JPanel formatter18;
    private javax.swing.JPanel formatter19;
    private javax.swing.JPanel formatter2;
    private javax.swing.JPanel formatter20;
    private javax.swing.JPanel formatter22;
    private javax.swing.JPanel formatter24;
    private javax.swing.JPanel formatter26;
    private javax.swing.JPanel formatter29;
    private javax.swing.JPanel formatter3;
    private javax.swing.JPanel formatter31;
    private javax.swing.JPanel formatter33;
    private javax.swing.JPanel formatter4;
    private javax.swing.JPanel formatter46;
    private javax.swing.JPanel formatter5;
    private javax.swing.JPanel formatter6;
    private javax.swing.JPanel formatter7;
    private javax.swing.JPanel formatter8;
    private javax.swing.JPanel formatter9;
    public javax.swing.JLabel genderLabel;
    public javax.swing.JLabel genderLabel1;
    public javax.swing.JButton ghostSubmit1;
    private javax.swing.JLabel hourlyWageErrorLabel;
    private javax.swing.JTextField hourlyWageField;
    private javax.swing.JPanel hourlyWageFormatter;
    private javax.swing.JPanel hourlyWageFormatter1;
    public javax.swing.JLabel hourlyWageLabel;
    public javax.swing.JLabel hourlyWageLabel1;
    private javax.swing.JPanel hourlyWageWrapper;
    private javax.swing.JLabel hoursPerWeekErrorLabel;
    private javax.swing.JTextField hoursPerWeekField;
    private javax.swing.JPanel hoursPerWeekFormatter;
    private javax.swing.JPanel hoursPerWeekFormatter1;
    private javax.swing.JPanel hoursPerWeekFormatter2;
    public javax.swing.JLabel hoursPerWeekLabel;
    public javax.swing.JLabel hoursPerWeekLabel1;
    private javax.swing.JPanel hoursPerWeekWrapper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lastNameErrorLabel;
    private javax.swing.JTextField lastNameField;
    public javax.swing.JLabel lastNameLabel;
    public javax.swing.JLabel lastNameLabel1;
    private javax.swing.JPanel lastNameWrapper;
    public javax.swing.JLabel resetButton;
    private javax.swing.JLabel title;
    private javax.swing.JLabel typeErrorLabel;
    public javax.swing.JLabel typeLabel;
    public javax.swing.JLabel typeLabel1;
    private javax.swing.JPanel viewEmployeePanel;
    private javax.swing.JLabel weeksPerYearErrorLabel;
    private javax.swing.JTextField weeksPerYearField;
    private javax.swing.JPanel weeksPerYearFormatter;
    private javax.swing.JPanel weeksPerYearFormatter1;
    public javax.swing.JLabel weeksPerYearLabel;
    public javax.swing.JLabel weeksPerYearLabel1;
    private javax.swing.JPanel weeksPerYearWrapper;
    private javax.swing.JLabel workLocationErrorLabel;
    private javax.swing.JTextField workLocationField;
    public javax.swing.JLabel workLocationLabel;
    public javax.swing.JLabel workLocationLabel1;
    private javax.swing.JPanel workLocationWrapper;
    private javax.swing.JLabel yearlySalaryErrorLabel;
    private javax.swing.JTextField yearlySalaryField;
    private javax.swing.JPanel yearlySalaryFormatter;
    private javax.swing.JPanel yearlySalaryFormatter1;
    public javax.swing.JLabel yearlySalaryLabel;
    public javax.swing.JLabel yearlySalaryLabel1;
    private javax.swing.JPanel yearlySalaryWrapper;
    // End of variables declaration//GEN-END:variables

    // </editor-fold>
}
