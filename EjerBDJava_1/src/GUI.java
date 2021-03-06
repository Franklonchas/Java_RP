
import java.sql.*;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fran De La Torre
 */
public class GUI extends javax.swing.JFrame {

    Controlador control;
    String jornadaSeleccionada, codEquipo;
    DefaultTableModel modelo1;
    DefaultTableModel modelo2;

    public GUI() {
        initComponents();
        control = new Controlador();
        rellenarJornadas();
    }

    public void generarTabla1() {
        modelo1 = new DefaultTableModel();
        modelo1.addColumn("Equpo Local");
        modelo1.addColumn("Canastas 1");
        modelo1.addColumn("Canastas 2");
        modelo1.addColumn("Canastas 3");
        tabla1.setModel(modelo1);
    }

    public void generarTabla2() {
        modelo2 = new DefaultTableModel();
        modelo2.addColumn("Equpo Local");
        modelo2.addColumn("Canastas 1");
        modelo2.addColumn("Canastas 2");
        modelo2.addColumn("Canastas 3");
        tabla2.setModel(modelo2);
    }

    public void rellenarJornadas() {
        ResultSet res = control.obtenerJornadas();
        try {
            if (res != null) {
                while (res.next()) {
                    choice.add(res.getString("jornada"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
    }

    public void seleccionarFecha() {
        jornadaSeleccionada = choice.getSelectedItem();
        ResultSet res = control.obtenerFechaJornada(jornadaSeleccionada);
        try {
            if (res != null) {
                while (res.next()) {
                    jFecha.setText(res.getString("fecha"));
                    jFecha.setEditable(false);
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
    }

    public void rellenarTabla() {
        jornadaSeleccionada = choice.getSelectedItem();
        ResultSet res = control.obtenerNombrePartidos(jornadaSeleccionada);
        generarTabla1();
        generarTabla2();
        try {
            if (res != null) {
                while (res.next()) {
                    modelo1.addRow(new Object[]{res.getString("codigoEquipo1")});
                    modelo2.addRow(new Object[]{res.getString("codigoEquipo2")});
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
        rellenarCanastas1();
        rellenarCanastas2();
    }

    public void rellenarCanastas1() {
        jornadaSeleccionada = choice.getSelectedItem();
        for (int i = 0; i < modelo1.getRowCount(); i++) {
            codEquipo = modelo1.getValueAt(i, 0).toString();
            ResultSet res = control.obtenerCanastas(jornadaSeleccionada, codEquipo);
            try {
                if (res != null) {
                    while (res.next()) {
                        modelo1.setValueAt(Integer.parseInt(res.getString("canastas1"))*100/Integer.parseInt(res.getString("intentos1")) + "%", i, 1);
                        modelo1.setValueAt(Integer.parseInt(res.getString("canastas2"))*100/Integer.parseInt(res.getString("intentos2")) + "%", i, 2);
                        modelo1.setValueAt(Integer.parseInt(res.getString("canastas3"))*100/Integer.parseInt(res.getString("intentos3")) + "%", i, 3);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar el choice Jornadas");
            }
        }
    }

    public void rellenarCanastas2() {
        jornadaSeleccionada = choice.getSelectedItem();
        for (int i = 0; i < modelo2.getRowCount(); i++) {
            codEquipo = modelo2.getValueAt(i, 0).toString();
            ResultSet res = control.obtenerCanastas(jornadaSeleccionada, codEquipo);
            try {
                if (res != null) {
                    while (res.next()) {
                        modelo2.setValueAt(Integer.parseInt(res.getString("canastas1"))*100/Integer.parseInt(res.getString("intentos1")) + "%", i, 1);
                        modelo2.setValueAt(Integer.parseInt(res.getString("canastas2"))*100/Integer.parseInt(res.getString("intentos2")) + "%", i, 2);
                        modelo2.setValueAt(Integer.parseInt(res.getString("canastas3"))*100/Integer.parseInt(res.getString("intentos3")) + "%", i, 3);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar el choice Jornadas");
            }
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

        jLabel1 = new javax.swing.JLabel();
        choice = new java.awt.Choice();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jFecha = new javax.swing.JTextField();
        aceptar = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Jornada");

        choice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choiceItemStateChanged(evt);
            }
        });

        jLabel2.setText("Fecha");

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla1);

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla2);

        aceptar.setText("Aceptar");
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(choice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(203, 203, 203)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(aceptar)
                        .addGap(234, 234, 234)
                        .addComponent(salir)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(choice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(salir))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void choiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choiceItemStateChanged
        seleccionarFecha();
    }//GEN-LAST:event_choiceItemStateChanged

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        rellenarTabla();
    }//GEN-LAST:event_aceptarActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar;
    private java.awt.Choice choice;
    private javax.swing.JTextField jFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla1;
    private javax.swing.JTable tabla2;
    // End of variables declaration//GEN-END:variables
}
