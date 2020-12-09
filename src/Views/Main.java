/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.*;
import Interface.*;
import Models.*;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.Map;

/**
 *
 * @author 1styrGroupC
 */
public class Main extends javax.swing.JFrame {

    SettingsInterface settingControllers = new SettingsControllers();
    AdminInterface adminControllers = new AdminController();
    HouseholdInterface houseControllers = new HouseholdController();
    BillInterface billControllers = new BillController();
    TransactionRecordInterface transactionControllers = new TransactionRecordsController();

    Bill bill;
    Admin admin;
    int adminID;
    int billID;
    int householdID;

    public int userID;
    public int userLevel;
    public String fName;
    public String lName;
    public String mName;

    /**
     * Creates new form Main
     */
    public Main(int id) {
        initComponents();
        userID = id;
        icon();
        initialDisplay();
        loadAdmins();
        loadHousehold();
        loadBills();
        chart();
        display();
    }

    private void icon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
    }

    public void initialDisplay() {
        try {
            Admin admin = adminControllers.getAdmin(userID);
            fName = admin.getfName();
            lName = admin.getlName();
            mName = admin.getmName();
            userLevel = admin.getLevel();
            
            
            userNamelbl.setText(fName+" "+lName);
            if (userLevel == 2) {
                adminsbtn.setVisible(false);
                printBillsBtn.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        this.setExtendedState(Main.MAXIMIZED_BOTH);
        tab1.setVisible(true);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
    }

    public void loadAdmins() {
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
        model.setRowCount(0);
        try {
            for (Admin admin : adminControllers.getAllAdmins()) {
                int lvl = admin.getLevel();
                String level = "";
                switch (lvl) {
                    case 1:
                        level = "Administrator";
                        break;
                    case 2:
                        level = "Manager";
                        break;
                    default:
                        level = "Teller";
                        break;
                }
                model.addRow(new Object[]{admin.getId(), admin.getlName().toUpperCase() + ", " + admin.getfName().toUpperCase() + " " + admin.getmName().toUpperCase().charAt(0) + ".", admin.getUserName(), "***********", admin.getContactNumber(), level});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void loadHousehold() {
        DefaultTableModel model = (DefaultTableModel) householdTable.getModel();
        model.setRowCount(0);
        try {
            for (Household house : houseControllers.getAllHousehold()) {
                model.addRow(new Object[]{house.getElectricityLineNo(), house.getlName().toUpperCase() + ", " + house.getfName().toUpperCase() + " " + house.getmName().toUpperCase().charAt(0) + ".", house.getContactNumber(), house.getAddress(), house.getStreetNumber()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void loadBills() {
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        model.setRowCount(0);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        try {
            for (Bill bill : billControllers.getAllBill()) {
                String status;
                if (bill.getStatus() == 1) {
                    status = "PAID";
                } else if (bill.getStatus() == 2) {
                    status = "UNPAID";
                } else {
                    status = "OVERDUE";
                }
                model.addRow(new Object[]{bill.getId(), bill.getElectricityLineNo(), bill.getReading(), formatter.format(bill.getAmountDue()), status, bill.getRecordedDate(), bill.getDueDate(), adminControllers.getAdmin(bill.getRecordedBy()).getlName() + ", " + adminControllers.getAdmin(bill.getRecordedBy()).getfName()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void chart() {
        DefaultCategoryDataset barchartData = new DefaultCategoryDataset();
        try {
            for (Map.Entry data : transactionControllers.getMonthlyIncome().entrySet()) {
                double value = (double) data.getValue();
                String year = Integer.toString((int) data.getKey());
                barchartData.setValue(value, "Amount", year);
            }

            JFreeChart barchart = ChartFactory.createBarChart3D("YEARLY PROFIT REPORT", "YEARS", "AMOUNT", barchartData, PlotOrientation.VERTICAL, false, true, false);
            barchart.setBackgroundPaint(Color.CYAN);
            barchart.getTitle().setPaint(Color.RED);
            CategoryPlot barchrt = barchart.getCategoryPlot();
            barchrt.setRangeGridlinePaint(Color.BLUE);
            ChartPanel barPanel = new ChartPanel(barchart);
            chart.removeAll();
            chart.add(barPanel, BorderLayout.CENTER);
            chart.validate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void display() {
        try {
            String householdCount = Integer.toString(houseControllers.getAllHousehold().size());
            String employeeCount = Integer.toString(adminControllers.getAllAdmins().size());
            String unpaidBill = Integer.toString(billControllers.getAllUnpaidBill().size());
            workersCountlbl.setText(employeeCount);
            householdCountlbl.setText(householdCount);
            unpaidBillslbl.setText(unpaidBill);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

        sideNavBar = new javax.swing.JPanel();
        profileIcon = new javax.swing.JLabel();
        dashBoard = new javax.swing.JButton();
        transaction = new javax.swing.JButton();
        acounts = new javax.swing.JButton();
        adminsbtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        userNamelbl = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        close = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        settings = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        systemTitle = new javax.swing.JLabel();
        container = new javax.swing.JPanel();
        tab1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        unpaidBillslbl = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        workersCountlbl = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        householdCountlbl = new javax.swing.JLabel();
        chart = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        tab2 = new javax.swing.JPanel();
        billsTableContainer = new javax.swing.JScrollPane();
        billsTable = new javax.swing.JTable();
        printBillsBtn = new javax.swing.JButton();
        tfElectricityLineNo = new javax.swing.JTextField();
        tfMeterReading = new javax.swing.JTextField();
        saveBillBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        refreshBills = new javax.swing.JButton();
        tab3 = new javax.swing.JPanel();
        deleteHouseholdBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        refresh1 = new javax.swing.JButton();
        householdTableContainer = new javax.swing.JScrollPane();
        householdTable = new javax.swing.JTable();
        tfSearchHousehold = new javax.swing.JTextField();
        searchHouseholdBtn = new javax.swing.JButton();
        addHouseholdBtn = new javax.swing.JButton();
        modifyHouseholdBtn = new javax.swing.JButton();
        tab4 = new javax.swing.JPanel();
        adminTableContainer = new javax.swing.JScrollPane();
        adminTable = new javax.swing.JTable();
        searchAdmin = new javax.swing.JTextField();
        searchAdminBtn = new javax.swing.JButton();
        addAdminBtn = new javax.swing.JButton();
        modifyAdminBtn = new javax.swing.JButton();
        deleteAdminBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ELECTRICITY BILLING SYSTEM");
        setBackground(new java.awt.Color(204, 204, 204));

        sideNavBar.setBackground(new java.awt.Color(0, 0, 204));

        profileIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profile.png"))); // NOI18N

        dashBoard.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dashBoard.setText("Dashboard");
        dashBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashBoardActionPerformed(evt);
            }
        });

        transaction.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        transaction.setText("Billing");
        transaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionActionPerformed(evt);
            }
        });

        acounts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acounts.setText("Accounts");
        acounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acountsActionPerformed(evt);
            }
        });

        adminsbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adminsbtn.setText("Admins");
        adminsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminsbtnActionPerformed(evt);
            }
        });

        userNamelbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userNamelbl.setForeground(new java.awt.Color(255, 255, 255));
        userNamelbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userNamelbl.setText("..........................");

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/poweroff (1).png"))); // NOI18N
        close.setToolTipText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        logOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        logOut.setToolTipText("Log Out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cog.png"))); // NOI18N
        settings.setToolTipText("Settings");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sideNavBarLayout = new javax.swing.GroupLayout(sideNavBar);
        sideNavBar.setLayout(sideNavBarLayout);
        sideNavBarLayout.setHorizontalGroup(
            sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(sideNavBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(dashBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(transaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userNamelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adminsbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideNavBarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sideNavBarLayout.createSequentialGroup()
                                .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(profileIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        sideNavBarLayout.setVerticalGroup(
            sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideNavBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(profileIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userNamelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(acounts, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(adminsbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sideNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settings, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        header.setBackground(new java.awt.Color(51, 51, 255));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        systemTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        systemTitle.setForeground(new java.awt.Color(255, 255, 255));
        systemTitle.setText("ELECTRICITY BILLING MANAGEMENT SYSTEM");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(systemTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(systemTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        container.setBackground(new java.awt.Color(204, 204, 204));

        tab1.setBackground(new java.awt.Color(102, 204, 255));

        jPanel3.setBackground(new java.awt.Color(0, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/upaidbills.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("UNPAID BILLS");

        unpaidBillslbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        unpaidBillslbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        unpaidBillslbl.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addContainerGap(95, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(unpaidBillslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unpaidBillslbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel6.setBackground(new java.awt.Color(0, 255, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setPreferredSize(new java.awt.Dimension(293, 104));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/employees.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("WORKERS");

        workersCountlbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        workersCountlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        workersCountlbl.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(workersCountlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(workersCountlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 0));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setPreferredSize(new java.awt.Dimension(293, 104));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/household_1.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("HOUSEHOLDS");

        householdCountlbl.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        householdCountlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        householdCountlbl.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(householdCountlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(householdCountlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        chart.setBackground(new java.awt.Color(51, 123, 249));
        chart.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        chart.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab2.setBackground(new java.awt.Color(102, 204, 255));

        billsTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        billsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "HOUSELINE NO.", "METER READING(kwH)", "AMOUNT DUE(P)", "STATUS", "RECORDED DATE", "DUE DATE", "RECORDED BY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billsTableMouseClicked(evt);
            }
        });
        billsTableContainer.setViewportView(billsTable);
        if (billsTable.getColumnModel().getColumnCount() > 0) {
            billsTable.getColumnModel().getColumn(0).setMinWidth(0);
            billsTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            billsTable.getColumnModel().getColumn(0).setMaxWidth(0);
            billsTable.getColumnModel().getColumn(1).setResizable(false);
            billsTable.getColumnModel().getColumn(2).setResizable(false);
            billsTable.getColumnModel().getColumn(3).setResizable(false);
            billsTable.getColumnModel().getColumn(4).setResizable(false);
            billsTable.getColumnModel().getColumn(5).setResizable(false);
            billsTable.getColumnModel().getColumn(6).setResizable(false);
            billsTable.getColumnModel().getColumn(7).setResizable(false);
        }

        printBillsBtn.setText("Print Bills");
        printBillsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBillsBtnActionPerformed(evt);
            }
        });

        tfElectricityLineNo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        tfMeterReading.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        saveBillBtn.setText("Save");
        saveBillBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBillBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("ELectric Line No:");

        jLabel4.setText("Meter Reading(kwH)");

        refreshBills.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        refreshBills.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBillsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(billsTableContainer)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(tfElectricityLineNo, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addComponent(tfMeterReading, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveBillBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 324, Short.MAX_VALUE)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(printBillsBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshBills, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(5, 5, 5)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfElectricityLineNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMeterReading, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveBillBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(printBillsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshBills, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(billsTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab3.setBackground(new java.awt.Color(102, 204, 255));

        deleteHouseholdBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash.png"))); // NOI18N
        deleteHouseholdBtn.setText("Delete");
        deleteHouseholdBtn.setEnabled(false);
        deleteHouseholdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHouseholdBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Household Records");

        refresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh1ActionPerformed(evt);
            }
        });

        householdTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        householdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HOUSELINE NO.", "FULL NAME", "CONTACT NO.", "ADDRESS", "STREET NO."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        householdTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                householdTableMouseClicked(evt);
            }
        });
        householdTableContainer.setViewportView(householdTable);
        if (householdTable.getColumnModel().getColumnCount() > 0) {
            householdTable.getColumnModel().getColumn(0).setResizable(false);
            householdTable.getColumnModel().getColumn(1).setResizable(false);
            householdTable.getColumnModel().getColumn(2).setResizable(false);
            householdTable.getColumnModel().getColumn(3).setResizable(false);
        }

        tfSearchHousehold.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                tfSearchHouseholdCaretUpdate(evt);
            }
        });

        searchHouseholdBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N

        addHouseholdBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/household.png"))); // NOI18N
        addHouseholdBtn.setText("Add Household");
        addHouseholdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHouseholdBtnActionPerformed(evt);
            }
        });

        modifyHouseholdBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        modifyHouseholdBtn.setText("Modify");
        modifyHouseholdBtn.setEnabled(false);
        modifyHouseholdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyHouseholdBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tab3Layout.createSequentialGroup()
                                .addComponent(tfSearchHousehold, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchHouseholdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(householdTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(modifyHouseholdBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteHouseholdBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addHouseholdBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchHouseholdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tfSearchHousehold, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addComponent(refresh1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(householdTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(tab3Layout.createSequentialGroup()
                        .addComponent(addHouseholdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(modifyHouseholdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(deleteHouseholdBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tab4.setBackground(new java.awt.Color(102, 204, 255));

        adminTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        adminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FULL NAME", "USERNAME", "PASSWORD", "CONTACT NO.", "LEVEL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        adminTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adminTableMouseClicked(evt);
            }
        });
        adminTableContainer.setViewportView(adminTable);
        if (adminTable.getColumnModel().getColumnCount() > 0) {
            adminTable.getColumnModel().getColumn(0).setResizable(false);
            adminTable.getColumnModel().getColumn(0).setPreferredWidth(8);
            adminTable.getColumnModel().getColumn(0).setHeaderValue("HOUSELINE NO.");
            adminTable.getColumnModel().getColumn(1).setResizable(false);
            adminTable.getColumnModel().getColumn(1).setHeaderValue("FULL NAME");
            adminTable.getColumnModel().getColumn(2).setResizable(false);
            adminTable.getColumnModel().getColumn(2).setPreferredWidth(18);
            adminTable.getColumnModel().getColumn(2).setHeaderValue("USERNAME");
            adminTable.getColumnModel().getColumn(3).setResizable(false);
            adminTable.getColumnModel().getColumn(3).setPreferredWidth(18);
            adminTable.getColumnModel().getColumn(3).setHeaderValue("PASSWORD");
            adminTable.getColumnModel().getColumn(4).setResizable(false);
            adminTable.getColumnModel().getColumn(4).setHeaderValue("CONTACT NO.");
            adminTable.getColumnModel().getColumn(5).setResizable(false);
            adminTable.getColumnModel().getColumn(5).setPreferredWidth(10);
            adminTable.getColumnModel().getColumn(5).setHeaderValue("LEVEL");
        }

        searchAdmin.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchAdminCaretUpdate(evt);
            }
        });

        searchAdminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N

        addAdminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add.png"))); // NOI18N
        addAdminBtn.setText("Add Employee");
        addAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdminBtnActionPerformed(evt);
            }
        });

        modifyAdminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        modifyAdminBtn.setText("Modify");
        modifyAdminBtn.setEnabled(false);
        modifyAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyAdminBtnActionPerformed(evt);
            }
        });

        deleteAdminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trash.png"))); // NOI18N
        deleteAdminBtn.setText("Delete");
        deleteAdminBtn.setEnabled(false);
        deleteAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAdminBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Administration Records");

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tab4Layout.createSequentialGroup()
                                .addComponent(searchAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(adminTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(modifyAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteAdminBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(searchAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(searchAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addComponent(refresh, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(tab4Layout.createSequentialGroup()
                        .addComponent(addAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(modifyAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(deleteAdminBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(sideNavBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(sideNavBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashBoardActionPerformed
        tab1.setVisible(true);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
    }//GEN-LAST:event_dashBoardActionPerformed

    private void transactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionActionPerformed
        tab1.setVisible(false);
        tab2.setVisible(true);
        tab3.setVisible(false);
        tab4.setVisible(false);
    }//GEN-LAST:event_transactionActionPerformed

    private void acountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acountsActionPerformed
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(true);
        tab4.setVisible(false);
    }//GEN-LAST:event_acountsActionPerformed

    private void adminsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminsbtnActionPerformed
        tab1.setVisible(false);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(true);
    }//GEN-LAST:event_adminsbtnActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        int close = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Close Application", JOptionPane.OK_CANCEL_OPTION);
        if (close == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_closeActionPerformed

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsActionPerformed
        SettingsForm show = new SettingsForm(this, true);
        show.setVisible(true);
    }//GEN-LAST:event_settingsActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        int close = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Sign Out", JOptionPane.OK_CANCEL_OPTION);
        if (close == 0) {
            this.dispose();
            Main_Run run = new Main_Run();
            run.main(null);
        }
    }//GEN-LAST:event_logOutActionPerformed

    private void searchAdminCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchAdminCaretUpdate
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
        model.setRowCount(0);
        try {
            for (Admin admin : adminControllers.getAdmin(searchAdmin.getText())) {
                int lvl = admin.getLevel();
                String level = "";
                switch (lvl) {
                    case 1:
                        level = "Administrator";
                        break;
                    case 2:
                        level = "Manager";
                        break;
                    default:
                        level = "Teller";
                        break;
                }
                model.addRow(new Object[]{admin.getId(), admin.getlName().toUpperCase() + ", " + admin.getfName().toUpperCase() + " " + admin.getmName().toUpperCase().charAt(0) + ".", admin.getUserName(), "***********", admin.getContactNumber(), level});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_searchAdminCaretUpdate

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        searchAdmin.setText("");
        addAdminBtn.setEnabled(true);
        deleteAdminBtn.setEnabled(false);
        modifyAdminBtn.setEnabled(false);
        loadAdmins();
        chart();
        display();
    }//GEN-LAST:event_refreshActionPerformed

    private void addAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminBtnActionPerformed
        Admin_Form show = new Admin_Form(this, true);
        show.setVisible(true);
        loadAdmins();
        chart();
        display();
    }//GEN-LAST:event_addAdminBtnActionPerformed

    private void adminTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminTableMouseClicked
        addAdminBtn.setEnabled(false);
        deleteAdminBtn.setEnabled(true);
        modifyAdminBtn.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();
        adminID = Integer.parseInt(model.getValueAt(adminTable.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_adminTableMouseClicked

    private void deleteAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAdminBtnActionPerformed
        try {
            boolean check = false;
            int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?", "MESSAGE", JOptionPane.OK_CANCEL_OPTION);
            if (action == 0) {
                adminControllers.deleteAdmin(adminID);
                loadAdmins();
                JOptionPane.showMessageDialog(null, "Deleted!");
            }
            addAdminBtn.setEnabled(true);
            deleteAdminBtn.setEnabled(false);
            modifyAdminBtn.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_deleteAdminBtnActionPerformed

    private void modifyAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyAdminBtnActionPerformed
        Modify_Admin_Form show = new Modify_Admin_Form(this, true, adminID);
        show.setVisible(true);
        addAdminBtn.setEnabled(true);
        deleteAdminBtn.setEnabled(false);
        modifyAdminBtn.setEnabled(false);
        loadAdmins();
        chart();
        display();
    }//GEN-LAST:event_modifyAdminBtnActionPerformed

    private void deleteHouseholdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHouseholdBtnActionPerformed
        try {
            boolean check = false;
            int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this household?", "MESSAGE", JOptionPane.OK_CANCEL_OPTION);
            if (action == 0) {
                houseControllers.deleteHousehold(householdID);
                loadAdmins();
                JOptionPane.showMessageDialog(null, "Deleted!");
            }
            addHouseholdBtn.setEnabled(true);
            deleteHouseholdBtn.setEnabled(false);
            modifyHouseholdBtn.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        loadHousehold();
        chart();
        display();
    }//GEN-LAST:event_deleteHouseholdBtnActionPerformed

    private void refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh1ActionPerformed
        tfSearchHousehold.setText("");
        addHouseholdBtn.setEnabled(true);
        deleteHouseholdBtn.setEnabled(false);
        modifyHouseholdBtn.setEnabled(false);
        loadHousehold();
        chart();
        display();
    }//GEN-LAST:event_refresh1ActionPerformed

    private void householdTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_householdTableMouseClicked
        addHouseholdBtn.setEnabled(false);
        deleteHouseholdBtn.setEnabled(true);
        modifyHouseholdBtn.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) householdTable.getModel();
        householdID = Integer.parseInt(model.getValueAt(householdTable.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_householdTableMouseClicked

    private void tfSearchHouseholdCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_tfSearchHouseholdCaretUpdate
        DefaultTableModel model = (DefaultTableModel) householdTable.getModel();
        model.setRowCount(0);
        try {
            for (Household house : houseControllers.getHouseHold(tfSearchHousehold.getText())) {
                model.addRow(new Object[]{house.getElectricityLineNo(), house.getlName().toUpperCase() + ", " + house.getfName().toUpperCase() + " " + house.getmName().toUpperCase().charAt(0) + ".", house.getContactNumber(), house.getAddress(), house.getStreetNumber()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_tfSearchHouseholdCaretUpdate

    private void addHouseholdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHouseholdBtnActionPerformed
        Household_Form show = new Household_Form(this, true);
        show.setVisible(true);
        loadHousehold();
        chart();
        display();
    }//GEN-LAST:event_addHouseholdBtnActionPerformed

    private void modifyHouseholdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyHouseholdBtnActionPerformed
        Modify_Household_Form show = new Modify_Household_Form(this, true, householdID);
        show.setVisible(true);
        addHouseholdBtn.setEnabled(true);
        deleteHouseholdBtn.setEnabled(false);
        modifyHouseholdBtn.setEnabled(false);
        loadHousehold();
        chart();
        display();
    }//GEN-LAST:event_modifyHouseholdBtnActionPerformed

    private void saveBillBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBillBtnActionPerformed
        if ("Save".equals(saveBillBtn.getText())) {
            try {
                String electricityLineNo = tfElectricityLineNo.getText();
                String meterReading = tfMeterReading.getText();
                double amountDue = Double.parseDouble(meterReading) * settingControllers.getElectricityRate().getValue();
                String recordeddate = java.time.LocalDate.now().toString();
                String dueDate = java.time.LocalDate.now().plusMonths(1).toString();
                bill = new Bill(0, Integer.parseInt(electricityLineNo), Integer.parseInt(meterReading), amountDue, 2, recordeddate, dueDate, userID);
                billControllers.addBill(bill);
                JOptionPane.showMessageDialog(null, "Saved");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        } else {
            int modify = JOptionPane.showConfirmDialog(null, "Save Changes?", "Modify Record", JOptionPane.OK_CANCEL_OPTION);
            if (modify == 0) {
                try {
                    String electricityLineNo = tfElectricityLineNo.getText();
                    String meterReading = tfMeterReading.getText();
                    double amountDue = Double.parseDouble(meterReading) * settingControllers.getElectricityRate().getValue();
                    String recordeddate = java.time.LocalDate.now().toString();
                    String dueDate = java.time.LocalDate.now().plusMonths(1).toString();
                    bill = new Bill(billID, Integer.parseInt(electricityLineNo), Integer.parseInt(meterReading), amountDue, 2, recordeddate, dueDate, userID);
                    billControllers.updateBill(bill);
                    JOptionPane.showMessageDialog(null, "Updated!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }

        tfElectricityLineNo.setText("");
        tfMeterReading.setText("");
        saveBillBtn.setText("Save");
        loadBills();
        chart();
        display();

    }//GEN-LAST:event_saveBillBtnActionPerformed

    private void billsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billsTableMouseClicked

        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        billID = Integer.parseInt(model.getValueAt(billsTable.getSelectedRow(), 0).toString());
        int electricityLineNo = Integer.parseInt(model.getValueAt(billsTable.getSelectedRow(), 1).toString());
        int reading = Integer.parseInt(model.getValueAt(billsTable.getSelectedRow(), 2).toString());
        tfElectricityLineNo.setText(Integer.toString(electricityLineNo));
        tfMeterReading.setText(Integer.toString(reading));
        saveBillBtn.setText("Update");
    }//GEN-LAST:event_billsTableMouseClicked

    private void refreshBillsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBillsActionPerformed
        tfElectricityLineNo.setText("");
        tfMeterReading.setText("");
        saveBillBtn.setText("Save");
        loadBills();
        chart();
        display();
    }//GEN-LAST:event_refreshBillsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        chart();
        display();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void printBillsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBillsBtnActionPerformed
        Bill_Printer print = new Bill_Printer(this,true);
        print.setVisible(true);
    }//GEN-LAST:event_printBillsBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acounts;
    private javax.swing.JButton addAdminBtn;
    private javax.swing.JButton addHouseholdBtn;
    private javax.swing.JTable adminTable;
    private javax.swing.JScrollPane adminTableContainer;
    private javax.swing.JButton adminsbtn;
    private javax.swing.JTable billsTable;
    private javax.swing.JScrollPane billsTableContainer;
    private javax.swing.JPanel chart;
    private javax.swing.JButton close;
    private javax.swing.JPanel container;
    private javax.swing.JButton dashBoard;
    private javax.swing.JButton deleteAdminBtn;
    private javax.swing.JButton deleteHouseholdBtn;
    private javax.swing.JPanel header;
    private javax.swing.JLabel householdCountlbl;
    private javax.swing.JTable householdTable;
    private javax.swing.JScrollPane householdTableContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton logOut;
    private javax.swing.JLabel logo;
    private javax.swing.JButton modifyAdminBtn;
    private javax.swing.JButton modifyHouseholdBtn;
    private javax.swing.JButton printBillsBtn;
    private javax.swing.JLabel profileIcon;
    private javax.swing.JButton refresh;
    private javax.swing.JButton refresh1;
    private javax.swing.JButton refreshBills;
    private javax.swing.JButton saveBillBtn;
    private javax.swing.JTextField searchAdmin;
    private javax.swing.JButton searchAdminBtn;
    private javax.swing.JButton searchHouseholdBtn;
    private javax.swing.JButton settings;
    private javax.swing.JPanel sideNavBar;
    private javax.swing.JLabel systemTitle;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JTextField tfElectricityLineNo;
    private javax.swing.JTextField tfMeterReading;
    private javax.swing.JTextField tfSearchHousehold;
    private javax.swing.JButton transaction;
    private javax.swing.JLabel unpaidBillslbl;
    private javax.swing.JLabel userNamelbl;
    private javax.swing.JLabel workersCountlbl;
    // End of variables declaration//GEN-END:variables
}
