/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4_client.gui;

import com.a4.javawebservice.admin.Admin;
import com.a4.javawebservice.admin.Package;
import com.a4.javawebservice.admin.AdminOperationsWebService;
import com.a4.javawebservice.admin.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author radud
 */
public class AdminForm extends javax.swing.JFrame {

    /**
     * Creates new form AdminForm
     */
    public AdminForm(int userId) {
        this.userId = userId;
        initComponents();
        myInit();
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
        tableModel = (DefaultTableModel) packagesTable.getModel();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addPackageFrame.setLocationRelativeTo(this);
        addPackageFrame.setResizable(false);
        addPackageFrame.setVisible(false);
        addPackageFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        errorLabel.setVisible(false);

        adminProxy = (new Admin()).getAdminOperationsWebServicePort();
        packages = adminProxy.getPackages();
        users = adminProxy.getUsers();
        updateTable(packages);

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

    private void setUpComboBoxes() {

        if (users.isEmpty()) {
            return;
        }

        String[] strings = new String[users.size()];

        for (int i = 0; i < users.size(); i++) {
            strings[i] = users.get(i).getId() + " - " + users.get(i).getFullName();
        }

        senderComboBoxModel = new DefaultComboBoxModel(strings);
        receiverComboBoxModel = new DefaultComboBoxModel(strings);

        receiverComboBox.setModel(receiverComboBoxModel);
        receiverComboBox.setSelectedIndex(0);
        senderComboBox.setModel(senderComboBoxModel);
        senderComboBox.setSelectedIndex(0);

    }

    private void setUpAddPackageContent() {
        nameField.setText("");
        descriptionField.setText("");
        destinationCityField.setText("");
        senderCityField.setText("");
        setUpComboBoxes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addPackageFrame = new javax.swing.JFrame();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        doneButton = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        nameLabl = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        senderCityLabel = new javax.swing.JLabel();
        senderCityField = new javax.swing.JTextField();
        destinationCityLabel = new javax.swing.JLabel();
        destinationCityField = new javax.swing.JTextField();
        senderLabel = new javax.swing.JLabel();
        receiverLabel = new javax.swing.JLabel();
        senderComboBox = new javax.swing.JComboBox<>();
        receiverComboBox = new javax.swing.JComboBox<>();
        errorLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        packagesTable = new javax.swing.JTable();
        removeButton = new javax.swing.JButton();

        addPackageFrame.setMinimumSize(new java.awt.Dimension(550, 450));

        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        nameLabl.setText("Name : ");

        descriptionLabel.setText("Description :");

        senderCityLabel.setText("Sender City :");

        destinationCityLabel.setText("Destination City :");

        senderLabel.setText("Sender");

        receiverLabel.setText("Receiver");

        senderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        receiverComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        errorLabel.setForeground(new java.awt.Color(204, 0, 51));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel.setText("Error");

        jDesktopPane2.setLayer(doneButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(nameField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(nameLabl, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(descriptionField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(descriptionLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(senderCityLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(senderCityField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(destinationCityLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(destinationCityField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(senderLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(receiverLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(senderComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(receiverComboBox, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(errorLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(destinationCityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(senderCityLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(nameLabl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(senderLabel)
                                    .addComponent(receiverLabel))
                                .addGap(45, 45, 45)
                                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(descriptionField)
                                    .addComponent(senderCityField)
                                    .addComponent(destinationCityField)
                                    .addComponent(nameField)
                                    .addComponent(senderComboBox, 0, 352, Short.MAX_VALUE)
                                    .addComponent(receiverComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabl))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionLabel))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senderCityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senderCityLabel))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinationCityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinationCityLabel))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senderLabel)
                    .addComponent(senderComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(receiverComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(receiverLabel))
                .addGap(44, 44, 44)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(doneButton)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout addPackageFrameLayout = new javax.swing.GroupLayout(addPackageFrame.getContentPane());
        addPackageFrame.getContentPane().setLayout(addPackageFrameLayout);
        addPackageFrameLayout.setHorizontalGroup(
            addPackageFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );
        addPackageFrameLayout.setVerticalGroup(
            addPackageFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );

        jLabel4.setText("jLabel2");

        jTextField4.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(963, 429));

        addButton.setText("Add Package");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update Packages");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

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

        removeButton.setText("Remove Package");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(addButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(updateButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(removeButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(updateButton)
                        .addGap(18, 18, 18)
                        .addComponent(removeButton))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addPackageFrame.setVisible(true);
        setUpAddPackageContent();
    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        updatePackages();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        System.out.println("selected id = " + selectedId);
        if (selectedId != -1) {
            adminProxy.deletePackage(selectedId);
            updatePackages();
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed

        String name = nameField.getText();
        String description = descriptionField.getText();
        String destinationCity = destinationCityField.getText();
        String senderCity = senderCityField.getText();
        int senderIndex = senderComboBox.getSelectedIndex();
        int receiverIndex = receiverComboBox.getSelectedIndex();
        boolean trackin = false;

        if (name.equals("")) {
            errorLabel.setText("Name field cannot be empty");
            errorLabel.setVisible(true);
            return;
        }

        if (destinationCity.equals("")) {
            errorLabel.setText("Destination city field cannot be empty");
            errorLabel.setVisible(true);
            return;
        }

        if (senderCity.equals("")) {
            errorLabel.setText("Sender city field cannot be empty");
            errorLabel.setVisible(true);
            return;
        }

        if (senderIndex == -1) {
            errorLabel.setText("Please choose a sender");
            errorLabel.setVisible(true);
            return;
        }

        if (receiverIndex == -1) {
            errorLabel.setText("Please Choose a receiver");
            errorLabel.setVisible(true);
            return;
        }

        if (receiverIndex == senderIndex) {
            errorLabel.setText("Sender cannot be receiver at the same time");
            errorLabel.setVisible(true);
            return;
        }

        int senderId = users.get(senderIndex).getId();
        int receiverId = users.get(receiverIndex).getId();

        errorLabel.setVisible(false);
        Package p = new Package();
        p.setName(name);
        p.setDescription(description);
        p.setDestinationCity(destinationCity);
        p.setSenderCity(senderCity);
        p.setSenderId(senderId);
        p.setReceiverId(receiverId);

        adminProxy.addPackage(p);
        addPackageFrame.setVisible(false);
        updatePackages();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void updatePackages() {
        packages = adminProxy.getPackages();
        updateTable(packages);
    }

    DefaultComboBoxModel senderComboBoxModel = new DefaultComboBoxModel();
    DefaultComboBoxModel receiverComboBoxModel = new DefaultComboBoxModel();
    private int selectedId = -1;
    List<User> users;
    List<Package> packages;
    private int userId;
    private DefaultTableModel tableModel;
    AdminOperationsWebService adminProxy;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JFrame addPackageFrame;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField destinationCityField;
    private javax.swing.JLabel destinationCityLabel;
    private javax.swing.JButton doneButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabl;
    private javax.swing.JTable packagesTable;
    private javax.swing.JComboBox<String> receiverComboBox;
    private javax.swing.JLabel receiverLabel;
    private javax.swing.JButton removeButton;
    private javax.swing.JTextField senderCityField;
    private javax.swing.JLabel senderCityLabel;
    private javax.swing.JComboBox<String> senderComboBox;
    private javax.swing.JLabel senderLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
