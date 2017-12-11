/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeedatabase;

import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * employee database/management system
 * allows a human resources manager to track employees in a company
 * created for ICS4U0
 * @author alan
 */
public class MainComponent extends javax.swing.JFrame {
    
    // <editor-fold defaultstate="collapsed" desc="dashboard component init">
    /**
     * Creates new form DashboardComponent
     */
    public MainComponent() {
        initComponents();
        initializeDatabase();
        initializeComponents();
        updateDashboard();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="global variables">
    /**
     * initializes global variables
     */
    private AddEmployeeComponent addEmployeeComponent;
    private ColorSelectorService colorSelector = new ColorSelectorService();
    private HashTable employeeDatabase = new HashTable(2);
    private ArrayList<ViewEmployeeComponent> viewEmployeeComponents;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="initialize database">
    /**
     * initialize database with employees
     */
    private void initializeDatabase() {
        // read from file
        employeeDatabase.addEmployee(new FullTimeEmployee("123456", "bob", "the builder", "M", "here", 0.1, 100));
        employeeDatabase.addEmployee(new FullTimeEmployee("123456", "bob", "the builder", "M", "here", 0.1, 100));
        employeeDatabase.addEmployee(new FullTimeEmployee("123456", "bob", "the builder", "M", "here", 0.1, 100));
        employeeDatabase.addEmployee(new FullTimeEmployee("123456", "bob", "the builder", "M", "here", 0.1, 100));
        employeeDatabase.addEmployee(new FullTimeEmployee("123456", "bob", "the builder", "M", "here", 0.1, 100));
        employeeDatabase.addEmployee(new FullTimeEmployee("123456", "bob", "the builder", "M", "here", 0.1, 100));
        
        updateDashboard();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="other init">
    
    /**
     * initializes other components
     */
    private void initializeComponents() {
        addEmployeeComponent = new AddEmployeeComponent();
        addEmployeeComponent.confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEmployeeConfirmButtonMouseClicked(evt);
            }
        });
        addEmployeeComponent.confirmButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmButtonKeyPressed(evt);
            }
        });
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="dashboard view update">
    
    /**
     * updates the dashboard view
     */
    private void updateDashboard() {
        // update the number of employees
        if (employeeDatabase.getNumEmployees() == 1) {
            information.setText("there is currently 1 employee.");
        } else {
            information.setText("there are currently " + employeeDatabase.getNumEmployees() + " employees.");
        }
        
        databaseTable.setModel(new javax.swing.table.DefaultTableModel(
            employeeDatabase.display(),
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        )
        { public boolean isCellEditable(int row, int column){return false;} }
        );
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="check new employee">
    
    /**
     * checks the new employee being added
     */
    private void checkNewEmployee() {
        // add employee and reset fields
        
        boolean errorsExist = addEmployeeComponent.checkErrors();
        EmployeeInfo newEmployee;
        
        if (!errorsExist) {
            if (addEmployeeComponent.type == 0) {
                // full time
                newEmployee = new FullTimeEmployee(
                        addEmployeeComponent.employeeNumber,
                        addEmployeeComponent.firstName,
                        addEmployeeComponent.lastName,
                        addEmployeeComponent.gender,
                        addEmployeeComponent.workLocation,
                        Double.valueOf(addEmployeeComponent.deductionsRate),
                        Double.valueOf(addEmployeeComponent.yearlySalary)
                );
            } else {
                // part time
                newEmployee = new PartTimeEmployee(
                        addEmployeeComponent.employeeNumber,
                        addEmployeeComponent.firstName,
                        addEmployeeComponent.lastName,
                        addEmployeeComponent.gender,
                        addEmployeeComponent.workLocation,
                        Double.valueOf(addEmployeeComponent.deductionsRate),
                        Double.valueOf(addEmployeeComponent.hourlyWage),
                        Double.valueOf(addEmployeeComponent.hoursPerWeek),
                        Double.valueOf(addEmployeeComponent.weeksPerYear)
                );
            }
            
            employeeDatabase.addEmployee(newEmployee);
            addEmployeeComponent.reset();
        }
        
        addEmployeeComponent.updateDisplay();
        updateDashboard();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="open employee info panel">
    
    private void openEmployeeInfo(String employeeID) {
        ViewEmployeeComponent test = new ViewEmployeeComponent(employeeDatabase.search(employeeID));
        test.setVisible(true);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dashboardPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        information = new javax.swing.JLabel();
        addEmployeeButton = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        databaseScrollPane = new javax.swing.JScrollPane();
        databaseScrollPane.getVerticalScrollBar().setPreferredSize (new Dimension(0,0));
        databaseScrollPane.setBorder(null);
        databaseTable = new javax.swing.JTable();
        databaseHeader = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        dashboardPanel.setBackground(new java.awt.Color(250, 250, 250));
        dashboardPanel.setForeground(new java.awt.Color(0, 0, 0));
        dashboardPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dashboardPanel.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        dashboardPanel.setPreferredSize(new java.awt.Dimension(900, 540));
        dashboardPanel.setSize(new java.awt.Dimension(900, 540));

        jPanel2.setOpaque(false);

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setFont(new java.awt.Font("Montserrat", 0, 30)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setText("employee database");

        information.setBackground(new java.awt.Color(255, 255, 255));
        information.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        information.setForeground(new java.awt.Color(0, 0, 0));
        information.setText("there are currently 0 employees.");

        addEmployeeButton.setBackground(new java.awt.Color(230, 230, 230));
        addEmployeeButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        addEmployeeButton.setForeground(new java.awt.Color(0, 0, 0));
        addEmployeeButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addEmployeeButton.setText("add employee");
        addEmployeeButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 2));
        addEmployeeButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addEmployeeButton.setMaximumSize(new java.awt.Dimension(125, 35));
        addEmployeeButton.setMinimumSize(new java.awt.Dimension(125, 35));
        addEmployeeButton.setOpaque(true);
        addEmployeeButton.setPreferredSize(new java.awt.Dimension(125, 35));
        addEmployeeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addEmployeeButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addEmployeeButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEmployeeButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addEmployeeButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addEmployeeButtonMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title)
                    .addComponent(information))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(information)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel10.setOpaque(false);

        databaseScrollPane.setBackground(new java.awt.Color(250, 250, 250));
        databaseScrollPane.setForeground(new java.awt.Color(0, 0, 0));
        databaseScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        databaseScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        databaseTable.setBackground(new java.awt.Color(250, 250, 250));
        databaseTable.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        databaseTable.setForeground(new java.awt.Color(0, 0, 0));
        databaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a1", "b1", "c1", "d1", "e1", "f1"},
                {"a2", "b2", "c2", "d2", "e2", "f2"},
                {"a3", "b3", "c3", "d3", "e3", "f3"},
                {"a4", "b4", "c4", "d4", "f4", "f"}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        )
        {public boolean isCellEditable(int row, int column){return false;}}
    );
    databaseTable.setFillsViewportHeight(true);
    databaseTable.setFocusable(false);
    databaseTable.setGridColor(new java.awt.Color(250, 250, 250));
    databaseTable.setRowHeight(48);
    databaseTable.setRowMargin(12);
    databaseTable.setSelectionBackground(new java.awt.Color(225, 245, 254));
    databaseTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
    databaseTable.setShowGrid(false);
    databaseTable.setTableHeader(null);
    databaseTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        public void mouseMoved(java.awt.event.MouseEvent evt) {
            databaseTableMouseMoved(evt);
        }
    });
    databaseTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            databaseTableMouseClicked(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            databaseTableMouseExited(evt);
        }
    });
    databaseScrollPane.setViewportView(databaseTable);

    databaseHeader.setBackground(new java.awt.Color(250, 250, 250));
    databaseHeader.setForeground(new java.awt.Color(0, 0, 0));

    jPanel3.setBackground(new java.awt.Color(250, 250, 250));
    jPanel3.setForeground(new java.awt.Color(0, 0, 0));
    jPanel3.setOpaque(false);
    jPanel3.setRequestFocusEnabled(false);

    jLabel3.setBackground(new java.awt.Color(250, 250, 250));
    jLabel3.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(0, 0, 0));
    jLabel3.setText("<html><u  style=\"white-space: nowrap\">employee #</u></html>");
    jLabel3.setToolTipText("");
    jLabel3.setAlignmentX(0.5F);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel4.setBackground(new java.awt.Color(250, 250, 250));
    jPanel4.setForeground(new java.awt.Color(0, 0, 0));
    jPanel4.setOpaque(false);
    jPanel4.setRequestFocusEnabled(false);

    jLabel4.setBackground(new java.awt.Color(250, 250, 250));
    jLabel4.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(0, 0, 0));
    jLabel4.setText("<html><u>first</u></html>");
    jLabel4.setAlignmentX(0.5F);

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel5.setBackground(new java.awt.Color(250, 250, 250));
    jPanel5.setForeground(new java.awt.Color(0, 0, 0));
    jPanel5.setOpaque(false);
    jPanel5.setRequestFocusEnabled(false);

    jLabel5.setBackground(new java.awt.Color(250, 250, 250));
    jLabel5.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(0, 0, 0));
    jLabel5.setText("<html><u>last</u></html>");
    jLabel5.setAlignmentX(0.5F);

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel6.setBackground(new java.awt.Color(250, 250, 250));
    jPanel6.setForeground(new java.awt.Color(0, 0, 0));
    jPanel6.setOpaque(false);
    jPanel6.setRequestFocusEnabled(false);

    jLabel6.setBackground(new java.awt.Color(250, 250, 250));
    jLabel6.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(0, 0, 0));
    jLabel6.setText("<html><u>gender</u></html>");
    jLabel6.setAlignmentX(0.5F);

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel7.setBackground(new java.awt.Color(250, 250, 250));
    jPanel7.setForeground(new java.awt.Color(0, 0, 0));
    jPanel7.setOpaque(false);
    jPanel7.setRequestFocusEnabled(false);

    jLabel7.setBackground(new java.awt.Color(250, 250, 250));
    jLabel7.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(0, 0, 0));
    jLabel7.setText("<html><u>location</u></html>");
    jLabel7.setAlignmentX(0.5F);

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel7Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jPanel8.setBackground(new java.awt.Color(250, 250, 250));
    jPanel8.setForeground(new java.awt.Color(0, 0, 0));
    jPanel8.setOpaque(false);
    jPanel8.setRequestFocusEnabled(false);

    jLabel8.setBackground(new java.awt.Color(250, 250, 250));
    jLabel8.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
    jLabel8.setForeground(new java.awt.Color(0, 0, 0));
    jLabel8.setText("<html><u>type</u></html>");
    jLabel8.setAlignmentX(0.5F);

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout databaseHeaderLayout = new javax.swing.GroupLayout(databaseHeader);
    databaseHeader.setLayout(databaseHeaderLayout);
    databaseHeaderLayout.setHorizontalGroup(
        databaseHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(databaseHeaderLayout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(6, 6, 6)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(6, 6, 6)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(6, 6, 6)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(6, 6, 6))
    );
    databaseHeaderLayout.setVerticalGroup(
        databaseHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(databaseHeaderLayout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(databaseHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 0, 0))
    );

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addComponent(databaseScrollPane)
                    .addGap(12, 12, 12))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                    .addComponent(databaseHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(databaseHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(databaseScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
            .addContainerGap())
    );

    javax.swing.GroupLayout dashboardPanelLayout = new javax.swing.GroupLayout(dashboardPanel);
    dashboardPanel.setLayout(dashboardPanelLayout);
    dashboardPanelLayout.setHorizontalGroup(
        dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dashboardPanelLayout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addGroup(dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(40, 40, 40))
    );
    dashboardPanelLayout.setVerticalGroup(
        dashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dashboardPanelLayout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(20, 20, 20))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(dashboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(0, 0, 0))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(dashboardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(0, 0, 0))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="add employee button">
    
    private void addEmployeeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeButtonMouseEntered
        // TODO add your handling code here:
        addEmployeeButton.setBackground(colorSelector.button_hover);
    }//GEN-LAST:event_addEmployeeButtonMouseEntered

    private void addEmployeeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeButtonMouseExited
        // TODO add your handling code here:
        addEmployeeButton.setBackground(colorSelector.button_background);
    }//GEN-LAST:event_addEmployeeButtonMouseExited

    private void addEmployeeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeButtonMouseClicked
        // TODO add your handling code here:
        addEmployeeComponent.reset();
        addEmployeeComponent.updateDisplay();
        addEmployeeComponent.setVisible(true);
    }//GEN-LAST:event_addEmployeeButtonMouseClicked

    private void addEmployeeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeButtonMousePressed
        // TODO add your handling code here:
        addEmployeeButton.setBackground(colorSelector.button_pressed);
    }//GEN-LAST:event_addEmployeeButtonMousePressed

    private void addEmployeeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeButtonMouseReleased
        // TODO add your handling code here:
        addEmployeeButton.setBackground(colorSelector.button_background);
    }//GEN-LAST:event_addEmployeeButtonMouseReleased

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="database table">
    
    private void databaseTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseTableMouseMoved
        // TODO add your handling code here:
        int hoverRow = databaseTable.rowAtPoint(evt.getPoint());
        if (hoverRow > -1) {
            databaseTable.setRowSelectionInterval(hoverRow, hoverRow);
        } else {
            databaseTable.clearSelection();
        }
    }//GEN-LAST:event_databaseTableMouseMoved

    private void databaseTableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseTableMouseExited
        // TODO add your handling code here:
        databaseTable.clearSelection();
    }//GEN-LAST:event_databaseTableMouseExited

    private void databaseTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = databaseTable.rowAtPoint(evt.getPoint());
        if (selectedRow > -1) {
            String id = databaseTable.getValueAt(selectedRow, 0).toString();
            openEmployeeInfo(id);
        }
    }//GEN-LAST:event_databaseTableMouseClicked
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="add employee events">
    
    private void addEmployeeConfirmButtonMouseClicked(java.awt.event.MouseEvent evt) {                                               
        // TODO add your handling code here:
        checkNewEmployee();
    }
    
    private void confirmButtonKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkNewEmployee();
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="other generated things">
    
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
            java.util.logging.Logger.getLogger(MainComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainComponent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>z
        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainComponent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addEmployeeButton;
    private javax.swing.JPanel dashboardPanel;
    private javax.swing.JPanel databaseHeader;
    private javax.swing.JScrollPane databaseScrollPane;
    private javax.swing.JTable databaseTable;
    private javax.swing.JLabel information;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
    
    // </editor-fold>
}
