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
public class AddEmployeeComponent extends javax.swing.JFrame {
    /**
     * variables of a new employee template
     */
    private int type; // 0 full time, 1 part time
    private boolean typeError;
    private String firstName;
    private boolean firstNameError;
    private String lastName;
    private boolean lastNameError;
    private int gender; // 0 male, 1 female, 2 other
    private boolean genderError;

    /**
     * Creates new form AddEmployeeForm
     */
    public AddEmployeeComponent() {
        initComponents();
        
        reset();
    }
    
    /**
     * initialize color service
     */
    private final ColorSelectorService colorSelector = new ColorSelectorService();
    
    /**
     * reset method
     * resets all the fields of the new employee
     */
    public final void reset() {
        // RESET ALL FIELDS
        type = -1;
        typeError = false;
        firstName = "";
        firstNameError = false;
        lastName = "";
        lastNameError = false;
        gender = -1;
        genderError = false;
        
        firstNameField.requestFocus();
    }
    
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
        
        return errorsExist;
    }
    
    /**
     * check employee type method
     * @return false if employee type is not selected
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
     * @return false if first name is not at least 2 character long
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
     * @return false if last name is not at least 2 character long
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
     * @return false if gender is not selected
     */
    private boolean checkGender() {
        if (gender == -1) {
            genderError = true;
            return true;
        } else {
            genderError = false;
            return false;
        }
    }
    
    /**
     * update display method
     */
    public void updateDisplay() {
        switch (type) {
            case 0:
                // set to full-time
                checkFullEmployeeTypeButton.setBackground(colorSelector.check_active_background);
                checkPartEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                break;
            case 1:
                // set to part-time
                checkFullEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                checkPartEmployeeTypeButton.setBackground(colorSelector.check_active_background);
                break;
            default:
                checkPartEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                checkFullEmployeeTypeButton.setBackground(colorSelector.check_inactive_background);
                break;
        }
        
        switch (gender) {
            case 0:
                // male is selected
                checkGenderMale.setBackground(colorSelector.check_active_background);
                checkGenderFemale.setBackground(colorSelector.check_inactive_background);
                checkGenderOther.setBackground(colorSelector.check_inactive_background);
                break;
            case 1:
                // female is selected
                checkGenderMale.setBackground(colorSelector.check_inactive_background);
                checkGenderFemale.setBackground(colorSelector.check_active_background);
                checkGenderOther.setBackground(colorSelector.check_inactive_background);
                break;
            case 2:
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
    }
    
    public class MyFocusTraversalPolicy extends FocusTraversalPolicy {
        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(firstNameField)) return lastNameField;
            else if (aComponent.equals(lastNameField)) return checkFullEmployeeTypeButton;
            else if (aComponent.equals(checkFullEmployeeTypeButton)) return checkPartEmployeeTypeButton;
            else if (aComponent.equals(checkPartEmployeeTypeButton)) return checkGenderMale;
            else if (aComponent.equals(checkGenderMale)) return checkGenderFemale;
            else if (aComponent.equals(checkGenderFemale)) return checkGenderOther;
            else if (aComponent.equals(checkGenderOther)) return confirmButton;
            else return firstNameField;
        }
      
        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(resetButton)) return confirmButton;
            else if (aComponent.equals(confirmButton)) return checkGenderOther;
            else if (aComponent.equals(checkGenderOther)) return checkGenderFemale;
            else if (aComponent.equals(checkGenderFemale)) return checkGenderMale;
            else if (aComponent.equals(checkGenderMale)) return checkPartEmployeeTypeButton;
            else if (aComponent.equals(checkPartEmployeeTypeButton)) return checkFullEmployeeTypeButton;
            else if (aComponent.equals(checkFullEmployeeTypeButton)) return lastNameField;
            else if (aComponent.equals(lastNameField)) return firstNameField;
            else return resetButton;
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
            return firstNameField;
        }

        public Component getFirstComponent(Container focusCycleRoot) {
            return firstNameField;
        }

        public Component getLastComponent(Container focusCycleRoot) {
            return checkGenderOther;
        }
   }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addEmployeePanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        formatter = new javax.swing.JPanel();
        checkFullEmployeeTypeButton = new javax.swing.JLabel();
        checkPartEmployeeTypeButton = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        formatter2 = new javax.swing.JPanel();
        typeErrorLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        formatter1 = new javax.swing.JPanel();
        resetButton = new javax.swing.JLabel();
        confirmButton = new javax.swing.JLabel();
        ghostSubmit = new javax.swing.JButton();
        formatter3 = new javax.swing.JPanel();
        firstNameLabel = new javax.swing.JLabel();
        formatter4 = new javax.swing.JPanel();
        firstNameErrorLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        firstNameWrapper = new javax.swing.JPanel();
        firstNameField = new javax.swing.JTextField();
        formatter5 = new javax.swing.JPanel();
        lastNameLabel = new javax.swing.JLabel();
        formatter6 = new javax.swing.JPanel();
        lastNameErrorLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lastNameWrapper = new javax.swing.JPanel();
        lastNameField = new javax.swing.JTextField();
        formatter7 = new javax.swing.JPanel();
        checkGenderMale = new javax.swing.JLabel();
        checkGenderFemale = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        formatter8 = new javax.swing.JPanel();
        checkGenderErrorLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        checkGenderOther = new javax.swing.JLabel();
        formatter9 = new javax.swing.JPanel();
        employeeNumberLabel = new javax.swing.JLabel();
        formatter10 = new javax.swing.JPanel();
        employeeNumberErrorLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lastNameWrapper1 = new javax.swing.JPanel();
        employeeNumberField = new javax.swing.JTextField();
        formatter15 = new javax.swing.JPanel();
        deductionsRateLabel = new javax.swing.JLabel();
        formatter16 = new javax.swing.JPanel();
        deductionsRateErrorLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lastNameWrapper2 = new javax.swing.JPanel();
        deductionsRateField = new javax.swing.JTextField();
        formatter17 = new javax.swing.JPanel();
        workLocationLabel = new javax.swing.JLabel();
        formatter18 = new javax.swing.JPanel();
        workLocationErrorLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lastNameWrapper3 = new javax.swing.JPanel();
        workLocationField = new javax.swing.JTextField();
        formatter19 = new javax.swing.JPanel();
        yearlySalaryLabel = new javax.swing.JLabel();
        formatter20 = new javax.swing.JPanel();
        yearlySalaryErrorLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lastNameWrapper4 = new javax.swing.JPanel();
        yearlySalaryField = new javax.swing.JTextField();
        formatter21 = new javax.swing.JPanel();
        lastNameLabel7 = new javax.swing.JLabel();
        formatter22 = new javax.swing.JPanel();
        lastNameErrorLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lastNameWrapper5 = new javax.swing.JPanel();
        lastNameField5 = new javax.swing.JTextField();
        formatter23 = new javax.swing.JPanel();
        hourlyWageLabel = new javax.swing.JLabel();
        formatter24 = new javax.swing.JPanel();
        lastNameErrorLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lastNameWrapper6 = new javax.swing.JPanel();
        lastNameField6 = new javax.swing.JTextField();
        formatter45 = new javax.swing.JPanel();
        lastNameLabel19 = new javax.swing.JLabel();
        formatter46 = new javax.swing.JPanel();
        lastNameErrorLabel19 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        lastNameWrapper7 = new javax.swing.JPanel();
        lastNameField7 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(250, 250, 250));
        setBounds(new java.awt.Rectangle(720, 23, 0, 0));
        setFocusTraversalPolicy(new MyFocusTraversalPolicy());
        setSize(new java.awt.Dimension(370, 480));

        addEmployeePanel.setBackground(new java.awt.Color(250, 250, 250));
        addEmployeePanel.setForeground(new java.awt.Color(0, 0, 0));
        addEmployeePanel.setSize(new java.awt.Dimension(370, 480));

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("add employee");

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
        typeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        formatter1.setBackground(new java.awt.Color(250, 250, 250));

        resetButton.setBackground(new java.awt.Color(255, 205, 210));
        resetButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        resetButton.setForeground(new java.awt.Color(0, 0, 0));
        resetButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resetButton.setText("reset");
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
        confirmButton.setText("confirm");
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
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButtonMouseEntered(evt);
            }
        });

        ghostSubmit.setBackground(new java.awt.Color(250, 250, 250));
        ghostSubmit.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        ghostSubmit.setForeground(new java.awt.Color(0, 0, 0));
        ghostSubmit.setBorder(null);
        ghostSubmit.setBorderPainted(false);
        ghostSubmit.setContentAreaFilled(false);
        ghostSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ghostSubmit.setFocusPainted(false);
        ghostSubmit.setPreferredSize(new java.awt.Dimension(50, 30));
        ghostSubmit.setSize(new java.awt.Dimension(20, 30));

        javax.swing.GroupLayout formatter1Layout = new javax.swing.GroupLayout(formatter1);
        formatter1.setLayout(formatter1Layout);
        formatter1Layout.setHorizontalGroup(
            formatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ghostSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formatter1Layout.setVerticalGroup(
            formatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ghostSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(formatter1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        formatter3.setFocusTraversalPolicyProvider(true);
        formatter3.setOpaque(false);

        firstNameLabel.setBackground(new java.awt.Color(250, 250, 250));
        firstNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        firstNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        firstNameLabel.setText("first:");
        firstNameLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        formatter5.setFocusTraversalPolicyProvider(true);
        formatter5.setOpaque(false);

        lastNameLabel.setBackground(new java.awt.Color(250, 250, 250));
        lastNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        lastNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastNameLabel.setText("last:");
        lastNameLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        genderLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        formatter9.setFocusTraversalPolicyProvider(true);
        formatter9.setOpaque(false);

        employeeNumberLabel.setBackground(new java.awt.Color(250, 250, 250));
        employeeNumberLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        employeeNumberLabel.setForeground(new java.awt.Color(0, 0, 0));
        employeeNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        employeeNumberLabel.setText("emp. #:");
        employeeNumberLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        lastNameWrapper1.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper1.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapper1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapper1MouseEntered(evt);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeNumberFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapper1Layout = new javax.swing.GroupLayout(lastNameWrapper1);
        lastNameWrapper1.setLayout(lastNameWrapper1Layout);
        lastNameWrapper1Layout.setHorizontalGroup(
            lastNameWrapper1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapper1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(employeeNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapper1Layout.setVerticalGroup(
            lastNameWrapper1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapper1Layout.createSequentialGroup()
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
                .addComponent(lastNameWrapper1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter9Layout.setVerticalGroup(
            formatter9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter9Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        deductionsRateLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        lastNameWrapper2.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper2.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapper2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapper2MouseEntered(evt);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                deductionsRateFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapper2Layout = new javax.swing.GroupLayout(lastNameWrapper2);
        lastNameWrapper2.setLayout(lastNameWrapper2Layout);
        lastNameWrapper2Layout.setHorizontalGroup(
            lastNameWrapper2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapper2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(deductionsRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapper2Layout.setVerticalGroup(
            lastNameWrapper2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapper2Layout.createSequentialGroup()
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
                .addComponent(lastNameWrapper2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter15Layout.setVerticalGroup(
            formatter15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deductionsRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter17.setFocusTraversalPolicyProvider(true);
        formatter17.setOpaque(false);

        workLocationLabel.setBackground(new java.awt.Color(250, 250, 250));
        workLocationLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        workLocationLabel.setForeground(new java.awt.Color(0, 0, 0));
        workLocationLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        workLocationLabel.setText("location:");
        workLocationLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        lastNameWrapper3.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper3.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapper3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapper3MouseEntered(evt);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                workLocationFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapper3Layout = new javax.swing.GroupLayout(lastNameWrapper3);
        lastNameWrapper3.setLayout(lastNameWrapper3Layout);
        lastNameWrapper3Layout.setHorizontalGroup(
            lastNameWrapper3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapper3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(workLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapper3Layout.setVerticalGroup(
            lastNameWrapper3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapper3Layout.createSequentialGroup()
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
                .addComponent(lastNameWrapper3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter17Layout.setVerticalGroup(
            formatter17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter17Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(workLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter19.setFocusTraversalPolicyProvider(true);
        formatter19.setOpaque(false);

        yearlySalaryLabel.setBackground(new java.awt.Color(250, 250, 250));
        yearlySalaryLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        yearlySalaryLabel.setForeground(new java.awt.Color(0, 0, 0));
        yearlySalaryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        yearlySalaryLabel.setText("salary:");
        yearlySalaryLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        lastNameWrapper4.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper4.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapper4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapper4MouseEntered(evt);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yearlySalaryFieldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapper4Layout = new javax.swing.GroupLayout(lastNameWrapper4);
        lastNameWrapper4.setLayout(lastNameWrapper4Layout);
        lastNameWrapper4Layout.setHorizontalGroup(
            lastNameWrapper4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapper4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(yearlySalaryField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapper4Layout.setVerticalGroup(
            lastNameWrapper4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapper4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(yearlySalaryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter19Layout = new javax.swing.GroupLayout(formatter19);
        formatter19.setLayout(formatter19Layout);
        formatter19Layout.setHorizontalGroup(
            formatter19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter19Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(yearlySalaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lastNameWrapper4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter19Layout.setVerticalGroup(
            formatter19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter19Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearlySalaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter21.setFocusTraversalPolicyProvider(true);
        formatter21.setOpaque(false);

        lastNameLabel7.setBackground(new java.awt.Color(250, 250, 250));
        lastNameLabel7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameLabel7.setForeground(new java.awt.Color(0, 0, 0));
        lastNameLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastNameLabel7.setText("last:");
        lastNameLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lastNameLabel7.setMaximumSize(new java.awt.Dimension(75, 30));
        lastNameLabel7.setMinimumSize(new java.awt.Dimension(75, 30));
        lastNameLabel7.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter22.setOpaque(false);

        lastNameErrorLabel7.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        lastNameErrorLabel7.setForeground(new java.awt.Color(244, 67, 54));
        lastNameErrorLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastNameErrorLabel7.setText("⚠");

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
            .addComponent(lastNameErrorLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter22Layout.setVerticalGroup(
            formatter22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter22Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lastNameErrorLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        lastNameWrapper5.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper5.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapper5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapper5MouseEntered(evt);
            }
        });

        lastNameField5.setBackground(new java.awt.Color(255, 252, 219));
        lastNameField5.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameField5.setForeground(new java.awt.Color(0, 0, 0));
        lastNameField5.setBorder(null);
        lastNameField5.setOpaque(false);
        lastNameField5.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        lastNameField5.setSelectionColor(new java.awt.Color(0, 0, 0));
        lastNameField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameField5FocusLost(evt);
            }
        });
        lastNameField5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameField5MouseEntered(evt);
            }
        });
        lastNameField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameField5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapper5Layout = new javax.swing.GroupLayout(lastNameWrapper5);
        lastNameWrapper5.setLayout(lastNameWrapper5Layout);
        lastNameWrapper5Layout.setHorizontalGroup(
            lastNameWrapper5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapper5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapper5Layout.setVerticalGroup(
            lastNameWrapper5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapper5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter21Layout = new javax.swing.GroupLayout(formatter21);
        formatter21.setLayout(formatter21Layout);
        formatter21Layout.setHorizontalGroup(
            formatter21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter21Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lastNameWrapper5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter21Layout.setVerticalGroup(
            formatter21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter21Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter23.setFocusTraversalPolicyProvider(true);
        formatter23.setOpaque(false);

        hourlyWageLabel.setBackground(new java.awt.Color(250, 250, 250));
        hourlyWageLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        hourlyWageLabel.setForeground(new java.awt.Color(0, 0, 0));
        hourlyWageLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        hourlyWageLabel.setText("last:");
        hourlyWageLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hourlyWageLabel.setMaximumSize(new java.awt.Dimension(75, 30));
        hourlyWageLabel.setMinimumSize(new java.awt.Dimension(75, 30));
        hourlyWageLabel.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter24.setOpaque(false);

        lastNameErrorLabel8.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        lastNameErrorLabel8.setForeground(new java.awt.Color(244, 67, 54));
        lastNameErrorLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastNameErrorLabel8.setText("⚠");

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
            .addComponent(lastNameErrorLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter24Layout.setVerticalGroup(
            formatter24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter24Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lastNameErrorLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        lastNameWrapper6.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper6.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapper6MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapper6MouseEntered(evt);
            }
        });

        lastNameField6.setBackground(new java.awt.Color(255, 252, 219));
        lastNameField6.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameField6.setForeground(new java.awt.Color(0, 0, 0));
        lastNameField6.setBorder(null);
        lastNameField6.setOpaque(false);
        lastNameField6.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        lastNameField6.setSelectionColor(new java.awt.Color(0, 0, 0));
        lastNameField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameField6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameField6FocusLost(evt);
            }
        });
        lastNameField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameField6MouseEntered(evt);
            }
        });
        lastNameField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameField6KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapper6Layout = new javax.swing.GroupLayout(lastNameWrapper6);
        lastNameWrapper6.setLayout(lastNameWrapper6Layout);
        lastNameWrapper6Layout.setHorizontalGroup(
            lastNameWrapper6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapper6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField6, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapper6Layout.setVerticalGroup(
            lastNameWrapper6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapper6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter23Layout = new javax.swing.GroupLayout(formatter23);
        formatter23.setLayout(formatter23Layout);
        formatter23Layout.setHorizontalGroup(
            formatter23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter23Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(hourlyWageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lastNameWrapper6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter23Layout.setVerticalGroup(
            formatter23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter23Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hourlyWageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        formatter45.setFocusTraversalPolicyProvider(true);
        formatter45.setOpaque(false);

        lastNameLabel19.setBackground(new java.awt.Color(250, 250, 250));
        lastNameLabel19.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameLabel19.setForeground(new java.awt.Color(0, 0, 0));
        lastNameLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lastNameLabel19.setText("last:");
        lastNameLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lastNameLabel19.setMaximumSize(new java.awt.Dimension(75, 30));
        lastNameLabel19.setMinimumSize(new java.awt.Dimension(75, 30));
        lastNameLabel19.setPreferredSize(new java.awt.Dimension(75, 30));

        formatter46.setOpaque(false);

        lastNameErrorLabel19.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        lastNameErrorLabel19.setForeground(new java.awt.Color(244, 67, 54));
        lastNameErrorLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastNameErrorLabel19.setText("⚠");

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
            .addComponent(lastNameErrorLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        formatter46Layout.setVerticalGroup(
            formatter46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter46Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lastNameErrorLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        lastNameWrapper7.setBackground(new java.awt.Color(255, 252, 219));
        lastNameWrapper7.setForeground(new java.awt.Color(0, 0, 0));
        lastNameWrapper7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lastNameWrapper7MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameWrapper7MouseEntered(evt);
            }
        });

        lastNameField7.setBackground(new java.awt.Color(255, 252, 219));
        lastNameField7.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        lastNameField7.setForeground(new java.awt.Color(0, 0, 0));
        lastNameField7.setBorder(null);
        lastNameField7.setOpaque(false);
        lastNameField7.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        lastNameField7.setSelectionColor(new java.awt.Color(0, 0, 0));
        lastNameField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameField7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameField7FocusLost(evt);
            }
        });
        lastNameField7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lastNameField7MouseEntered(evt);
            }
        });
        lastNameField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameField7KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout lastNameWrapper7Layout = new javax.swing.GroupLayout(lastNameWrapper7);
        lastNameWrapper7.setLayout(lastNameWrapper7Layout);
        lastNameWrapper7Layout.setHorizontalGroup(
            lastNameWrapper7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lastNameWrapper7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField7, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        lastNameWrapper7Layout.setVerticalGroup(
            lastNameWrapper7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastNameWrapper7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout formatter45Layout = new javax.swing.GroupLayout(formatter45);
        formatter45.setLayout(formatter45Layout);
        formatter45Layout.setHorizontalGroup(
            formatter45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formatter45Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lastNameLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(lastNameWrapper7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(formatter46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        formatter45Layout.setVerticalGroup(
            formatter45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formatter45Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(formatter45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastNameWrapper7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout addEmployeePanelLayout = new javax.swing.GroupLayout(addEmployeePanel);
        addEmployeePanel.setLayout(addEmployeePanelLayout);
        addEmployeePanelLayout.setHorizontalGroup(
            addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(formatter1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(addEmployeePanelLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addEmployeePanelLayout.createSequentialGroup()
                        .addComponent(formatter21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(formatter23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addEmployeePanelLayout.createSequentialGroup()
                        .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(formatter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(formatter3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(formatter5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(formatter7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(formatter9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatter15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatter17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatter19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addEmployeePanelLayout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(formatter45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        addEmployeePanelLayout.setVerticalGroup(
            addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEmployeePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addEmployeePanelLayout.createSequentialGroup()
                        .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addEmployeePanelLayout.createSequentialGroup()
                                .addComponent(title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(formatter3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(formatter9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(formatter5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(formatter15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(formatter17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(formatter7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addEmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formatter21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formatter23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formatter45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formatter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addEmployeePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(addEmployeePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="confirm reset event handling">
    
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
        updateDisplay();
    }//GEN-LAST:event_checkFullEmployeeTypeButtonMouseClicked

    private void checkPartEmployeeTypeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonMouseClicked
        // TODO add your handling code here:
        // set to part-time
        type = 1;
        checkType();
        updateDisplay();
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
        reset();
        updateDisplay();
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
        gender = 0;
        checkGender();
        updateDisplay();
    }//GEN-LAST:event_checkGenderMaleMouseClicked

    private void checkGenderMaleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderMaleMouseExited
        // TODO add your handling code here:
        if (gender == 0) {
            checkGenderMale.setBackground(colorSelector.check_active_background);
        } else {
            checkGenderMale.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkGenderMaleMouseExited

    private void checkGenderMaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderMaleMouseEntered
        // TODO add your handling code here:
        if (gender != 0) {
            checkGenderMale.setBackground(colorSelector.check_hover);
        }
    }//GEN-LAST:event_checkGenderMaleMouseEntered

    private void checkGenderFemaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderFemaleMouseClicked
        // TODO add your handling code here:
        gender = 1;
        checkGender();
        updateDisplay();
    }//GEN-LAST:event_checkGenderFemaleMouseClicked

    private void checkGenderFemaleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderFemaleMouseExited
        // TODO add your handling code here:
        if (gender == 1) {
            checkGenderFemale.setBackground(colorSelector.check_active_background);
        } else {
            checkGenderFemale.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkGenderFemaleMouseExited

    private void checkGenderFemaleMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderFemaleMouseEntered
        // TODO add your handling code here:
        if (gender != 1) {
            checkGenderFemale.setBackground(colorSelector.check_hover);
        }
    }//GEN-LAST:event_checkGenderFemaleMouseEntered

    private void checkGenderOtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderOtherMouseClicked
        // TODO add your handling code here:
        gender = 2;
        checkGender();
        updateDisplay();
    }//GEN-LAST:event_checkGenderOtherMouseClicked

    private void checkGenderOtherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderOtherMouseExited
        // TODO add your handling code here:
        if (gender == 2) {
            checkGenderOther.setBackground(colorSelector.check_active_background);
        } else {
            checkGenderOther.setBackground(colorSelector.check_inactive_background);
        }
    }//GEN-LAST:event_checkGenderOtherMouseExited

    private void checkGenderOtherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkGenderOtherMouseEntered
        // TODO add your handling code here:
        if (gender != 2) {
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
            updateDisplay();
        }
    }//GEN-LAST:event_firstNameFieldKeyReleased
    
    private void lastNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameFieldKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() != KeyEvent.VK_ENTER && evt.getKeyCode() != KeyEvent.VK_SHIFT) {
            lastName = lastNameField.getText();
            checkLastName();
            updateDisplay();
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
            updateDisplay();
            
            checkGenderMale.requestFocus();
        }
    }//GEN-LAST:event_checkFullEmployeeTypeButtonKeyPressed

    private void checkPartEmployeeTypeButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkPartEmployeeTypeButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            type = 1;
            
            checkType();
            updateDisplay();
            
            checkGenderMale.requestFocus();
        }
    }//GEN-LAST:event_checkPartEmployeeTypeButtonKeyPressed

    private void checkGenderMaleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGenderMaleKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            gender = 0;
            
            checkGender();
            updateDisplay();
        }
    }//GEN-LAST:event_checkGenderMaleKeyPressed

    private void checkGenderFemaleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGenderFemaleKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            gender = 1;
            
            checkGender();
            updateDisplay();
        }
    }//GEN-LAST:event_checkGenderFemaleKeyPressed

    private void checkGenderOtherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkGenderOtherKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            gender = 2;
            
            checkGender();
            updateDisplay();
        }
    }//GEN-LAST:event_checkGenderOtherKeyPressed

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="confirm reset custom focus">
    
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
            reset();
            updateDisplay();
        }
    }//GEN-LAST:event_resetButtonKeyPressed

    private void employeeNumberFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeNumberFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeNumberFieldFocusGained

    private void employeeNumberFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeNumberFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeNumberFieldFocusLost

    private void employeeNumberFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeNumberFieldMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeNumberFieldMouseEntered

    private void employeeNumberFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeNumberFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeNumberFieldKeyReleased

    private void lastNameWrapper1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper1MouseExited

    private void lastNameWrapper1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper1MouseEntered

    private void deductionsRateFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deductionsRateFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionsRateFieldFocusGained

    private void deductionsRateFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deductionsRateFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionsRateFieldFocusLost

    private void deductionsRateFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deductionsRateFieldMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionsRateFieldMouseEntered

    private void deductionsRateFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deductionsRateFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_deductionsRateFieldKeyReleased

    private void lastNameWrapper2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper2MouseExited

    private void lastNameWrapper2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper2MouseEntered

    private void workLocationFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_workLocationFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_workLocationFieldFocusGained

    private void workLocationFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_workLocationFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_workLocationFieldFocusLost

    private void workLocationFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_workLocationFieldMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_workLocationFieldMouseEntered

    private void workLocationFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workLocationFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_workLocationFieldKeyReleased

    private void lastNameWrapper3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper3MouseExited

    private void lastNameWrapper3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper3MouseEntered

    private void yearlySalaryFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearlySalaryFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_yearlySalaryFieldFocusGained

    private void yearlySalaryFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_yearlySalaryFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_yearlySalaryFieldFocusLost

    private void yearlySalaryFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearlySalaryFieldMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_yearlySalaryFieldMouseEntered

    private void yearlySalaryFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yearlySalaryFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_yearlySalaryFieldKeyReleased

    private void lastNameWrapper4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper4MouseExited

    private void lastNameWrapper4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper4MouseEntered

    private void lastNameField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameField5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField5FocusGained

    private void lastNameField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameField5FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField5FocusLost

    private void lastNameField5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameField5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField5MouseEntered

    private void lastNameField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameField5KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField5KeyReleased

    private void lastNameWrapper5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper5MouseExited

    private void lastNameWrapper5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper5MouseEntered

    private void lastNameField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameField6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField6FocusGained

    private void lastNameField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameField6FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField6FocusLost

    private void lastNameField6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameField6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField6MouseEntered

    private void lastNameField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameField6KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField6KeyReleased

    private void lastNameWrapper6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper6MouseExited

    private void lastNameWrapper6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper6MouseEntered

    private void lastNameField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameField7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField7FocusGained

    private void lastNameField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameField7FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField7FocusLost

    private void lastNameField7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameField7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField7MouseEntered

    private void lastNameField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameField7KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameField7KeyReleased

    private void lastNameWrapper7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper7MouseExited

    private void lastNameWrapper7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lastNameWrapper7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameWrapper7MouseEntered

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
            java.util.logging.Logger.getLogger(AddEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEmployeeComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel addEmployeePanel;
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
    private javax.swing.JLabel employeeNumberErrorLabel;
    private javax.swing.JTextField employeeNumberField;
    public javax.swing.JLabel employeeNumberLabel;
    private javax.swing.JLabel firstNameErrorLabel;
    private javax.swing.JTextField firstNameField;
    public javax.swing.JLabel firstNameLabel;
    private javax.swing.JPanel firstNameWrapper;
    private javax.swing.JPanel formatter;
    private javax.swing.JPanel formatter1;
    private javax.swing.JPanel formatter10;
    private javax.swing.JPanel formatter15;
    private javax.swing.JPanel formatter16;
    private javax.swing.JPanel formatter17;
    private javax.swing.JPanel formatter18;
    private javax.swing.JPanel formatter19;
    private javax.swing.JPanel formatter2;
    private javax.swing.JPanel formatter20;
    private javax.swing.JPanel formatter21;
    private javax.swing.JPanel formatter22;
    private javax.swing.JPanel formatter23;
    private javax.swing.JPanel formatter24;
    private javax.swing.JPanel formatter3;
    private javax.swing.JPanel formatter4;
    private javax.swing.JPanel formatter45;
    private javax.swing.JPanel formatter46;
    private javax.swing.JPanel formatter5;
    private javax.swing.JPanel formatter6;
    private javax.swing.JPanel formatter7;
    private javax.swing.JPanel formatter8;
    private javax.swing.JPanel formatter9;
    public javax.swing.JLabel genderLabel;
    public javax.swing.JButton ghostSubmit;
    public javax.swing.JLabel hourlyWageLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lastNameErrorLabel;
    private javax.swing.JLabel lastNameErrorLabel19;
    private javax.swing.JLabel lastNameErrorLabel7;
    private javax.swing.JLabel lastNameErrorLabel8;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JTextField lastNameField5;
    private javax.swing.JTextField lastNameField6;
    private javax.swing.JTextField lastNameField7;
    public javax.swing.JLabel lastNameLabel;
    public javax.swing.JLabel lastNameLabel19;
    public javax.swing.JLabel lastNameLabel7;
    private javax.swing.JPanel lastNameWrapper;
    private javax.swing.JPanel lastNameWrapper1;
    private javax.swing.JPanel lastNameWrapper2;
    private javax.swing.JPanel lastNameWrapper3;
    private javax.swing.JPanel lastNameWrapper4;
    private javax.swing.JPanel lastNameWrapper5;
    private javax.swing.JPanel lastNameWrapper6;
    private javax.swing.JPanel lastNameWrapper7;
    public javax.swing.JLabel resetButton;
    private javax.swing.JLabel title;
    private javax.swing.JLabel typeErrorLabel;
    public javax.swing.JLabel typeLabel;
    private javax.swing.JLabel workLocationErrorLabel;
    private javax.swing.JTextField workLocationField;
    public javax.swing.JLabel workLocationLabel;
    private javax.swing.JLabel yearlySalaryErrorLabel;
    private javax.swing.JTextField yearlySalaryField;
    public javax.swing.JLabel yearlySalaryLabel;
    // End of variables declaration//GEN-END:variables
}
