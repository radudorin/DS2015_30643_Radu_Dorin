/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4_client.gui;

import com.a4.javawebservice.client.Client;
import com.a4.javawebservice.client.ClientOperationsWebService;
import com.a4.javawebservice.client.User;
import com.a4.javawebservice.client.Package;
import com.a4.javawebservice.client.Route;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author radud
 */
public class ClientForm extends javax.swing.JFrame {

    /**
     * Creates new form ClientForm
     */
    public ClientForm(User user) {
        currentUser = user;
        initTheme();
        initComponents();
        myInit();
        strtUpdateTask();
    }
    
    private void strtUpdateTask() {
        Timer timer = new Timer();
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateRoutes();
            }
        }, 0, SCHEDULE_DELAY);
    }
    
    public void updateRoutes() {
        routes = clientProxy.getRoutes(currentUser.getId());
        updateRoutesTable(routes);
        System.out.println("updating routes");
    }
    
    public void initTheme() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private void myInit() {
        initTheme();
        routesTableModel = (DefaultTableModel) routesTable.getModel();
        tableModel = (DefaultTableModel) packagesTable.getModel();
        
        routesFrame.setLocationRelativeTo(this);
        routesFrame.setResizable(false);
        routesFrame.setVisible(false);
        routesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        clientProxy = (new Client()).getClientOperationsWebServicePort();
        
        updatePackages();
        
        packagesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                errorJLabel.setVisible(false);

                int index = packagesTable.getSelectedRow();
                
                if (index == -1) {
                    selectedId = -1;
                    return;
                }
                
                selectedId = (int) tableModel.getValueAt(packagesTable.getSelectedRow(), 0);
                
                System.out.println("id = " + selectedId);
            }
        });
    }
    
    private void updatePackages() {
        packages = clientProxy.getPackages(currentUser.getId());
        users = clientProxy.getUsers();
        updateTable(packages);
    }
    
    private void updateTable(List<Package> packages) {
        
        if (tableModel.getRowCount() > 0) {
            for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
                tableModel.removeRow(i);
            }
        }
        
        for (Package p : packages) {
            
            int id = p.getId();
            String name = p.getName();
            String description = p.getDescription();
            String sender = "";
            String receiver = "";
            for (User user : users) {
                if (user.getId() == p.getReceiverId()) {
                    receiver = user.getFullName();
                }
                
                if (user.getId() == p.getSenderId()) {
                    sender = user.getFullName();
                }
            }
            
            String destinationCity = p.getDestinationCity();
            String senderCity = p.getSenderCity();
            boolean isTracking = p.isTracking();
            
            Object[] row = {id, sender, receiver, name, description, senderCity, destinationCity, isTracking};
            
            tableModel.addRow(row);
        }
    }
    
    private void updateRoutesTable(List<Route> routes) {
        
        if (routesTableModel.getRowCount() > 0) {
            for (int i = routesTableModel.getRowCount() - 1; i > -1; i--) {
                routesTableModel.removeRow(i);
            }
        }
        
        for (Route route : routes) {
            Object[] row = {route.getId(), route.getArrivalTime(), route.getCity(), route.getStatus()};
            routesTableModel.addRow(row);
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

        routesFrame = new javax.swing.JFrame();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        routesTable = new javax.swing.JTable();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        packagesTable = new javax.swing.JTable();
        showRoutesButton = new javax.swing.JButton();
        beginTrackingButton = new javax.swing.JButton();

        routesFrame.setMaximumSize(new java.awt.Dimension(730, 400));
        routesFrame.setMinimumSize(new java.awt.Dimension(730, 400));
        routesFrame.setPreferredSize(new java.awt.Dimension(730, 400));

        routesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Route ID", "Current City", "Arrival Time", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(routesTable);

        jDesktopPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout routesFrameLayout = new javax.swing.GroupLayout(routesFrame.getContentPane());
        routesFrame.getContentPane().setLayout(routesFrameLayout);
        routesFrameLayout.setHorizontalGroup(
            routesFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );
        routesFrameLayout.setVerticalGroup(
            routesFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(963, 429));
        setMinimumSize(new java.awt.Dimension(963, 429));
        setPreferredSize(new java.awt.Dimension(963, 429));
        setSize(new java.awt.Dimension(963, 429));

        packagesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Sender", "Receiver", "Name", "Description", "Sender City", "Destination City", "Tracking"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(packagesTable);

        showRoutesButton.setText("Show routes");
        showRoutesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showRoutesButtonActionPerformed(evt);
            }
        });

        beginTrackingButton.setText("Begin tracking");
        beginTrackingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beginTrackingButtonActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(showRoutesButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(beginTrackingButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(beginTrackingButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(showRoutesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(showRoutesButton)
                        .addGap(18, 18, 18)
                        .addComponent(beginTrackingButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void beginTrackingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beginTrackingButtonActionPerformed
        if (selectedId != -1) {
            clientProxy.beginTracking(selectedId);
            System.out.println("selectedId = " + selectedId);
            updatePackages();
        }
    }//GEN-LAST:event_beginTrackingButtonActionPerformed

    private void showRoutesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showRoutesButtonActionPerformed
        routesFrame.setVisible(true);
    }//GEN-LAST:event_showRoutesButtonActionPerformed
    
    private final int SCHEDULE_DELAY = 3 * 1000;
    private List<Route> routes;
    private User currentUser;
    private List<User> users;
    private List<Package> packages;
    private int selectedId;
    private DefaultTableModel routesTableModel;
    private DefaultTableModel tableModel;
    private ClientOperationsWebService clientProxy;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton beginTrackingButton;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable packagesTable;
    private javax.swing.JFrame routesFrame;
    private javax.swing.JTable routesTable;
    private javax.swing.JButton showRoutesButton;
    // End of variables declaration//GEN-END:variables
}
