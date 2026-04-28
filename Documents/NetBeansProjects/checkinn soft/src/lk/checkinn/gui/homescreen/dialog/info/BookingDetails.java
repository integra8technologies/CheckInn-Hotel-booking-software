/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.checkinn.gui.homescreen.dialog.info;

import com.formdev.flatlaf.FlatClientProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BookingDetails extends javax.swing.JPanel {

    private Map<String, Integer> roomTypeMap = new HashMap<>();
    private Map<String, Integer> roomNameMap = new HashMap<>();

    public java.util.Date getCheckinDate() {
        return checkInDate.getDate();
    }

    public java.util.Date getCheckOutDate() {
        return checkOutDate.getDate();
    }

    public int getAdultsCount() {
        return (Integer) adultCount.getValue();
    }

    public int getKidsCount() {
        return (Integer) kidCount.getValue();
    }

    public String getSelectedRoomType() {
        return (String) roomTypeComboBox.getSelectedItem();
    }

    public String getSelectedRoomName() {
        return (String) roomNameComboBox.getSelectedItem();
    }

    /**
     * Creates new form BokkingDetails
     */
    public BookingDetails() {
        initComponents();

        // Setup spinner min values
        adultCount.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1)); // min 1 adult
        kidCount.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));   // min 0 kids

        loadRoomTypes();

        // Add listener for room type selection change to load corresponding room names
        roomTypeComboBox.addActionListener(e -> {
            String selectedType = (String) roomTypeComboBox.getSelectedItem();
            if (selectedType == null || selectedType.equals("Select Room Type")) {
                roomNameComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select Room Name"}));
                roomNameMap.clear();
            } else {
                Integer typeId = roomTypeMap.get(selectedType);
                if (typeId != null) {
                    loadRoomNamesForSelectedType(typeId);
                }
            }
        });
    }

    private void init() {
        bookingPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }

    private void loadRoomTypes() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/checkinn_db", "root", "@Jonathan123"); Statement s = con.createStatement(); ResultSet rs = s.executeQuery("SELECT id, type FROM room_type")) {

            Vector<String> roomTypes = new Vector<>();
            roomTypes.add("Select Room Type");
            roomTypeMap.clear();

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                roomTypes.add(type);
                roomTypeMap.put(type, id);
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(roomTypes);
            roomTypeComboBox.setModel(model);

            // Reset room name combo box
            roomNameComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select Room Name"}));
            roomNameMap.clear();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading room types: " + e.getMessage());
        }
    }

    private void loadRoomNamesForSelectedType(int roomTypeId) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/checkinn_db", "root", "@Jonathan123"); Statement s = con.createStatement(); ResultSet rs = s.executeQuery(
                "SELECT id, name FROM room_name "
                + "WHERE room_type_id = " + roomTypeId + " AND booking_status_id = 2"
        )) {

            Vector<String> roomNames = new Vector<>();
            roomNames.add("Select Room Name");
            roomNameMap.clear();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                roomNames.add(name);
                roomNameMap.put(name, id);
            }

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(roomNames);
            roomNameComboBox.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading room names: " + e.getMessage());
        }
    }

//    private void loadRoomNames() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.01:3306/checkinn_db", "root", "@Jonathan123");
//            Statement s1 = con1.createStatement();
//            ResultSet rs1 = s1.executeQuery("SELECT * FROM `room_name`");
//
//            Vector<String> v1 = new Vector();
//            v1.add("Select Room Name");
//
//            while (rs1.next()) {
//                String roomType = rs1.getString("name");
//                v1.add(roomType);
//            }
//
//            DefaultComboBoxModel dcm1 = new DefaultComboBoxModel(v1);
//            roomNameComboBox.setModel(dcm1);
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
    public boolean validateBookingDetails() {
        // Check dates
        if (getCheckinDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select a check-in date.");
            return false;
        }
        if (getCheckOutDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select a check-out date.");
            return false;
        }
        if (!getCheckOutDate().after(getCheckinDate())) {
            JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.");
            return false;
        }

        // Validate adults count
        int adults = getAdultsCount();
        if (adults < 1) {
            JOptionPane.showMessageDialog(this, "Number of adults must be at least 1.");
            return false;
        }

        // Validate kids count
        int kids = getKidsCount();
        if (kids < 0) {
            JOptionPane.showMessageDialog(this, "Number of kids cannot be negative.");
            return false;
        }

        // Validate room type selection
        String selectedRoomType = getSelectedRoomType();
        if (selectedRoomType == null || selectedRoomType.equals("Select Room Type")) {
            JOptionPane.showMessageDialog(this, "Please select a room type.");
            return false;
        }

        // Validate room name selection
        String selectedRoomName = getSelectedRoomName();
        if (selectedRoomName == null || selectedRoomName.equals("Select Room Name")) {
            JOptionPane.showMessageDialog(this, "Please select a room name.");
            return false;
        }

        return true; // all validations passed
    }

    // New getters to return IDs:
    public Integer getSelectedRoomTypeId() {
        String type = (String) roomTypeComboBox.getSelectedItem();
        return roomTypeMap.getOrDefault(type, null);
    }

    public Integer getSelectedRoomNameId() {
        String name = (String) roomNameComboBox.getSelectedItem();
        return roomNameMap.getOrDefault(name, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookingPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        checkInDate = new com.toedter.calendar.JDateChooser();
        checkOutDate = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        adultCount = new javax.swing.JSpinner();
        kidCount = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        roomTypeComboBox = new javax.swing.JComboBox<>();
        roomNameComboBox = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        bookingPanel.setBackground(new java.awt.Color(51, 51, 51));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new java.awt.GridLayout(2, 2, 5, 0));

        jLabel11.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("CHECK - IN");
        jPanel4.add(jLabel11);

        jLabel12.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("CHECK - OUT");
        jPanel4.add(jLabel12);
        jPanel4.add(checkInDate);
        jPanel4.add(checkOutDate);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new java.awt.GridLayout(2, 2, 5, 0));

        jLabel13.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("ADULTS");
        jPanel5.add(jLabel13);

        jLabel14.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("KIDS");
        jPanel5.add(jLabel14);
        jPanel5.add(adultCount);
        jPanel5.add(kidCount);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new java.awt.GridLayout(2, 2, 5, 0));

        jLabel15.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("ROOM TYPE");
        jPanel6.add(jLabel15);

        jLabel16.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ROOM NAME");
        jPanel6.add(jLabel16);

        roomTypeComboBox.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        roomTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel6.add(roomTypeComboBox);

        roomNameComboBox.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jPanel6.add(roomNameComboBox);

        javax.swing.GroupLayout bookingPanelLayout = new javax.swing.GroupLayout(bookingPanel);
        bookingPanel.setLayout(bookingPanelLayout);
        bookingPanelLayout.setHorizontalGroup(
            bookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(bookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        bookingPanelLayout.setVerticalGroup(
            bookingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner adultCount;
    private javax.swing.JPanel bookingPanel;
    private com.toedter.calendar.JDateChooser checkInDate;
    private com.toedter.calendar.JDateChooser checkOutDate;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSpinner kidCount;
    private javax.swing.JComboBox<String> roomNameComboBox;
    private javax.swing.JComboBox<String> roomTypeComboBox;
    // End of variables declaration//GEN-END:variables
}
