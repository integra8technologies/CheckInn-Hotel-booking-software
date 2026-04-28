package lk.checkinn.setting;

import com.formdev.flatlaf.FlatClientProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RoomNamesPanel extends javax.swing.JPanel {

    public RoomNamesPanel() {
        initComponents();
        loadRoomType();

        loadRooms();
        jPanel1.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:20");
    }

    private void loadRooms() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/checkinn_db", "root", "@Jonathan123");
            Statement s2 = con2.createStatement();
            ResultSet rs2 = s2.executeQuery("SELECT * FROM `room_name` INNER JOIN `room_type` ON `room_name`.`room_type_id` = `room_type`.`id`");

            Vector<Vector> vectorBascket = new Vector();

            while (rs2.next()) {
                Vector<String> roomTableRow = new Vector();
                roomTableRow.add(rs2.getString("name"));
                roomTableRow.add(rs2.getString("room_type.type"));

                vectorBascket.add(roomTableRow);
            }

            Vector<String> roomTableCol = new Vector();
            roomTableCol.add("Room Name");
            roomTableCol.add("Room Type");

            DefaultTableModel dtm = new DefaultTableModel(vectorBascket, roomTableCol);
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRoomType() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/checkinn_db", "root", "@Jonathan123");
            Statement s = con.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM `room_type`");

            Vector<String> v = new Vector();
            v.add("Select Room Type");

            while (r.next()) {
                String type = r.getString("type");
//                System.out.println(type);
                v.add(type);
            }

            DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel(v);
            roomTypeComboBoxModel.setModel(dcm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        roomNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        roomTypeComboBoxModel = new javax.swing.JComboBox<>();
        deleteBtnRoomName = new javax.swing.JButton();
        insertBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(84, 84, 84));

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add / Remove Room Names");

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Add or Remove the room names used by the hotel, motel or guest house");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel3.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("New Room");

        jLabel16.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("New Room Name");

        roomNameField.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Room Type");

        roomTypeComboBoxModel.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        roomTypeComboBoxModel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        deleteBtnRoomName.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        deleteBtnRoomName.setText("Delete");
        deleteBtnRoomName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnRoomNameActionPerformed(evt);
            }
        });

        insertBtn.setBackground(new java.awt.Color(51, 102, 255));
        insertBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 12)); // NOI18N
        insertBtn.setForeground(new java.awt.Color(255, 255, 255));
        insertBtn.setText("Insert");
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel16)
                    .addComponent(roomNameField)
                    .addComponent(jLabel3)
                    .addComponent(roomTypeComboBoxModel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(deleteBtnRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(insertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomTypeComboBoxModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtnRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jTable1.setBackground(new java.awt.Color(84, 84, 84));
        jTable1.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Room Name", "Room Type"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed
        // TODO add your handling code here:
        String roomName = roomNameField.getText();
        String selectedRoomType = (String) roomTypeComboBoxModel.getSelectedItem();

        if (selectedRoomType.equals("Select Room Type")) {
            JOptionPane.showMessageDialog(this, "Please select a room type before inserting.",
                    "Room Type Validation",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (roomName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a room name before inserting.",
                    "Room Name Validation",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/checkinn_db", "root", "@Jonathan123");
            Statement s1 = con1.createStatement();
            ResultSet rs1 = s1.executeQuery("SELECT `id` FROM `room_type` WHERE `type` = '" + selectedRoomType + "'");

            if (rs1.next()) {
                int roomTypeId = rs1.getInt("id");

                ResultSet rsCheck = s1.executeQuery("SELECT * FROM `room_name` WHERE `name` = '" + roomName + "' AND `room_type_id` = '" + roomTypeId + "'");
                if (rsCheck.next()) {
                    JOptionPane.showMessageDialog(this, "Room name already exists for this room type.",
                            "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                s1.executeUpdate("INSERT INTO `room_name` (`name`, `room_type_id`)"
                        + "VALUES ('" + roomName + "', '" + roomTypeId + "')");

                JOptionPane.showMessageDialog(this, "Room name inserted successfully.",
                        "Insert Successful", JOptionPane.INFORMATION_MESSAGE);

                roomNameField.setText("");
                roomTypeComboBoxModel.setSelectedIndex(0);
            }

            loadRooms();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_insertBtnActionPerformed

    private void deleteBtnRoomNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnRoomNameActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room name to delete.",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String roomName = (String) jTable1.getValueAt(selectedRow, 0);
        String roomType = (String) jTable1.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete room '" + roomName + "' of type '" + roomType + "'?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            try {

                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/checkinn_db", "root", "@Jonathan123");
                Statement s = con.createStatement();

                ResultSet rs = s.executeQuery("SELECT `id` FROM `room_type` WHERE `type` = '" + roomType + "'");

                if (rs.next()) {
                    int roomTypeId = rs.getInt("id");

                    int rowDeleted = s.executeUpdate("DELETE FROM `room_name` WHERE `name` = '" + roomName + "' AND `room_type_id` = '" + roomTypeId + "'");

                    if (rowDeleted > 0) {
                        JOptionPane.showMessageDialog(this, "Room name deleted successfully.",
                                "Delete Success", JOptionPane.INFORMATION_MESSAGE);
                        loadRooms();
                    } else {
                        JOptionPane.showMessageDialog(this, "No room name found to delete.",
                                "Delete Failed", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Room type not found in database.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteBtnRoomNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBtnRoomName;
    private javax.swing.JButton insertBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField roomNameField;
    private javax.swing.JComboBox<String> roomTypeComboBoxModel;
    // End of variables declaration//GEN-END:variables
}
