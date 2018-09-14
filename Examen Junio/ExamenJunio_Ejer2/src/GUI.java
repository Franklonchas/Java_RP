
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
    DefaultTableModel modelo;
    String jornadaSeleccionada, partidoSeleccionado, jornadaTabla, codigoTabla;
    int canastas1, canastas2, canastas3, puntosTotales;

    public GUI() {
        initComponents();
        control = new Controlador();
        rellenarChoiceJornada();
        rellenarChoicePartido();
    }

    public void generarTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Jornada");
        modelo.addColumn("Partido");
        modelo.addColumn("Equipo Local");
        modelo.addColumn("Ptos equipo local");
        modelo.addColumn("Equipo visitante");
        modelo.addColumn("Ptos equipo visitante");
        modelo.addColumn("Equipo Ganador");
        tabla.setModel(modelo);
    }

    public void rellenarChoiceJornada() {
        ResultSet res = control.rellenarChoiceJornada();
        try {
            if (res != null) {
                while (res.next()) {
                    cJornada.add(res.getString("jornada"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas.");
        }
    }

    public void rellenarChoicePartido() {
        cPartido.removeAll();
        jornadaSeleccionada = cJornada.getSelectedItem();
        ResultSet res = control.rellenarChoicePartido(jornadaSeleccionada);
        try {
            if (res != null) {
                while (res.next()) {
                    cPartido.add(res.getString("codigoPartido"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Partido.");
        }
    }

    public void datosPartido() {
        jornadaSeleccionada = cJornada.getSelectedItem();
        partidoSeleccionado = cPartido.getSelectedItem();

        ResultSet res = control.rellenarDatosPartido(jornadaSeleccionada, partidoSeleccionado);
        try {
            if (res != null) {
                while (res.next()) {
                    jFecha.setText(res.getString("fecha"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar los datos del PARTIDO.");
        }
    }

    public void unaSeleccionada() {
        todas.setSelected(false);
        generarTabla();
        jornadaSeleccionada = cJornada.getSelectedItem();
        ResultSet res = control.unaSeleccionadaBD(jornadaSeleccionada);

        if (una.isSelected() && !todas.isSelected()) {
            try {
                if (res != null) {
                    while (res.next()) {
                        modelo.addRow(new Object[]{res.getString("jornada"), res.getString("codigoPartido")});
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar los datos del PARTIDO.");
            }
        }
    }

    public void todasSeleccionada() {
        una.setSelected(false);
        generarTabla();
        ResultSet res = control.todasSeleccionadaBD();

        if (todas.isSelected() && !una.isSelected()) {
            try {
                if (res != null) {
                    while (res.next()) {
                        modelo.addRow(new Object[]{res.getString("jornada"), res.getString("codigoPartido")});
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar los datos del PARTIDO.");
            }
        }
    }

    public void consultar() {

        if (una.isSelected() && !todas.isSelected()) {
            jornadaSeleccionada = cJornada.getSelectedItem();
            ResultSet res = control.consultarUna(jornadaSeleccionada);
            try {
                if (res != null) {
                    while (res.next()) {

                        obtenerDatosPartido1();
                        obtenerDatosPartido2();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar los datos del PARTIDO.");
            }
        } else if (todas.isSelected() && !una.isSelected()) {
            ResultSet res = control.consultarTodas();
            try {
                if (res != null) {
                    while (res.next()) {

                        obtenerDatosPartido1();
                        obtenerDatosPartido2();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar los datos del PARTIDO.");
            }
        }
    }

    public void obtenerDatosPartido1() {
        puntosTotales = 0;
        ResultSet res;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            jornadaTabla = modelo.getValueAt(i, 0) + "";
            codigoTabla = modelo.getValueAt(i, 1) + "";
            res = control.consultarDatosPartido(jornadaTabla, codigoTabla);
            canastas1 = 0;
            canastas2 = 0;
            canastas3 = 0;
            puntosTotales = 0;
            try {
                if (res != null) {
                    while (res.next()) {
                        canastas1 = res.getInt("canastas1");
                        canastas2 = res.getInt("canastas2") * 2;
                        canastas3 = res.getInt("canastas3") * 3;
                        puntosTotales = canastas1 + canastas2 + canastas3;
                        modelo.setValueAt(puntosTotales, i, 3);
                        modelo.setValueAt(res.getString("codigoEquipo"), i, 2);
                        canastas1 = 0;
                        canastas2 = 0;
                        canastas3 = 0;
                        puntosTotales = 0;
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar los datos del PARTIDO.");
            }
        }
    }

    public void obtenerDatosPartido2() {
        puntosTotales = 0;
        ResultSet res;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            jornadaTabla = modelo.getValueAt(i, 0) + "";
            codigoTabla = modelo.getValueAt(i, 1) + "";
            res = control.consultarDatosPartido(jornadaTabla, codigoTabla);
            canastas1 = 0;
            canastas2 = 0;
            canastas3 = 0;
            puntosTotales = 0;
            try {
                if (res != null) {
                    while (res.next()) {
                        canastas1 = res.getInt("canastas1");
                        canastas2 = res.getInt("canastas2") * 2;
                        canastas3 = res.getInt("canastas3") * 3;
                        puntosTotales = canastas1 + canastas2 + canastas3;
                        modelo.setValueAt(puntosTotales, i, 5);
                        modelo.setValueAt(res.getString("codigoEquipo"), i, 4);
                        canastas1 = 0;
                        canastas2 = 0;
                        canastas3 = 0;
                        puntosTotales = 0;
                    }
                }
            } catch (SQLException e) {
                System.err.println("Fallo al rellenar los datos del PARTIDO.");
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
        cJornada = new java.awt.Choice();
        jLabel2 = new javax.swing.JLabel();
        cPartido = new java.awt.Choice();
        jLabel3 = new javax.swing.JLabel();
        jFecha = new javax.swing.JTextField();
        una = new javax.swing.JRadioButton();
        todas = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        consultar = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Jornada");

        cJornada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cJornadaItemStateChanged(evt);
            }
        });

        jLabel2.setText("Partido");

        jLabel3.setText("Fecha");

        una.setText("Una Jornada");
        una.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unaActionPerformed(evt);
            }
        });

        todas.setText("Todas");
        todas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todasActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        consultar.setText("Consultar");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
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
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cJornada, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(cPartido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(una)
                                .addGap(48, 48, 48)
                                .addComponent(todas))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 912, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(consultar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir)
                .addGap(291, 291, 291))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(cPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(una)
                        .addComponent(todas)))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultar)
                    .addComponent(salir))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cJornadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cJornadaItemStateChanged
        rellenarChoicePartido();
        datosPartido();
    }//GEN-LAST:event_cJornadaItemStateChanged

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void unaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unaActionPerformed
        todas.setSelected(false);
        unaSeleccionada();
    }//GEN-LAST:event_unaActionPerformed

    private void todasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todasActionPerformed
        una.setSelected(false);
        todasSeleccionada();
    }//GEN-LAST:event_todasActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        consultar();
    }//GEN-LAST:event_consultarActionPerformed

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
    private java.awt.Choice cJornada;
    private java.awt.Choice cPartido;
    private javax.swing.JButton consultar;
    private javax.swing.JTextField jFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton salir;
    private javax.swing.JTable tabla;
    private javax.swing.JRadioButton todas;
    private javax.swing.JRadioButton una;
    // End of variables declaration//GEN-END:variables
}
