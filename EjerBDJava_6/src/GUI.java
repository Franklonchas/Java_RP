
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    DefaultTableModel modelo;
    Controlador control;
    int nuevoNumero, nLinea;
    String diaQueEs, clientesOrdenados, nombreCliente, dniCliente, telefonoCliente, nombreSeleccionado, codigoCliente, articulo, unidades, pvp, importe, diaBD;
    int importeTotal = 0;
    int facturaTotal = 0;
    int ivaTotal = 0;

    public GUI() {
        initComponents();
        control = new Controlador();
        generarTabla();
    }

    public void generarTabla() {
        modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        modelo.addColumn("nº Linea");
        modelo.addColumn("Articulo");
        modelo.addColumn("P.V.P");
        modelo.addColumn("Unidades");
        modelo.addColumn("Importe");
    }

    public void nueva() {
        generarTabla();
        ResultSet res = control.obtenerNumeroFactura();
        try {
            if (res != null) {
                while (res.next()) {
                    nuevoNumero = res.getInt("nfactura") + 1;
                }
                nFactura.setText(nuevoNumero + "");
                nFactura.setEditable(false);
                getDay();
                meterClientesOrdenados();
                limpiarTodo();
                jFecha.requestFocus();
                add.setEnabled(true);
                delete.setEnabled(true);
                grabar.setEnabled(true);
            }
        } catch (SQLException e) {
            System.out.println("Fallo al generar nueva factura");
        }
    }

    public void meterClientesOrdenados() {
        choice.removeAll();
        ResultSet res = control.obtenerClientesOrdenados();
        try {
            if (res != null) {
                while (res.next()) {
                    clientesOrdenados = res.getString("cliente");
                    choice.add(clientesOrdenados);
                }
            }
        } catch (SQLException e) {
            System.out.println("Fallo al meter los clientes ordenados");
        }
    }

    public void limpiarTodo() {
        tNombre.setText("");
        tTelefono.setText("");
        tNif.setText("");
        tImporte.setText("");
        tFactura.setText("");
        tIva.setText("");
    }

    public void getDay() {
        Calendar c = new GregorianCalendar();

        String dia, mes, annio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH));
        annio = Integer.toString(c.get(Calendar.YEAR));

        if (dia.length() == 1 && mes.length() == 1) {
            diaQueEs = "0" + dia + "/" + "0" + mes + "/" + annio;
            diaBD = annio + "/" + "0" + mes + "/" + "0" + dia;
        } else {
            diaQueEs = dia + "/" + mes + "/" + annio;
            diaBD = annio + "/" + mes + "/" + dia;
        }

        System.out.println("Hoy es día: " + diaBD);
        jFecha.setEditable(true);
        jFecha.setText(diaQueEs);
    }

    public void datosCliente() {
        nombreSeleccionado = choice.getSelectedItem();
        ResultSet res = control.obtenerDatosClientes(nombreSeleccionado);
        try {
            if (res != null) {
                while (res.next()) {
                    nombreCliente = res.getString("cliente");
                    dniCliente = res.getString("nif");
                    telefonoCliente = res.getString("telefono");
                    tNombre.setText(nombreCliente);
                    tNif.setText(dniCliente);
                    tTelefono.setText(telefonoCliente);
                    codigoCliente = res.getString("codCliente");
                    jCliente.setText(codigoCliente);
                }
            }
        } catch (SQLException e) {
            System.out.println("Fallo al meter los clientes ordenados");
        }
    }

    public void addLinea() {
        int aux = 0;
        if (modelo.getRowCount() == 0) {
            nLinea = 1;
            modelo.addRow(new Object[]{nLinea});
        } else {
            nLinea = nLinea + 1;
            modelo.addRow(new Object[]{nLinea});
        }
        importeTotal = 0;
        facturaTotal = 0;
        ivaTotal = 0;
        for (int i = 0; i < modelo.getRowCount() - 1; i++) {
            try {
                aux = Integer.parseInt(modelo.getValueAt(i, 4) + "");
                importeTotal += aux;
                aux = 0;
            } catch (NumberFormatException evt) {
            }
        }
        ivaTotal = (int) (importeTotal * 0.21);
        facturaTotal = ivaTotal + importeTotal;
        tImporte.setText(importeTotal + "");
        tFactura.setText(facturaTotal + "");
        tIva.setText(ivaTotal + "");
        System.out.println("El importe hasta ahora es ----> " + importeTotal);
    }

    public void borrarLinea() {
        int row = tabla.getSelectedRow();
        modelo.removeRow(row);
        nLinea--;
    }

    public void grabarFactura() {
        ResultSet res = control.grabarDatosFacturas(nuevoNumero, diaBD, codigoCliente, importeTotal, ivaTotal, facturaTotal);
        try {
            if (res != null) {
                while (res.next()) {
                }
            }
        } catch (SQLException e) {
            System.out.println("Fallo al meter la factura en la BD");
        }
    }

    public void grabarLineaFacturas() {
        String nLineaBD = "";
        for (int i = 0; i < modelo.getRowCount(); i++) {
            articulo = modelo.getValueAt(i, 1).toString();
            unidades = modelo.getValueAt(i, 3) + "";
            pvp = modelo.getValueAt(i, 2) + "";
            importe = modelo.getValueAt(i, 4) + "";
            nLineaBD = modelo.getValueAt(i, 0) + "";
            ResultSet res = control.grabarDatosLineasFacturas(nuevoNumero, nLineaBD, articulo, unidades, pvp, importe);
        }
        nueva();
    }

    public void consultar() {
        generarTabla();
        int nFacturaIntro = Integer.parseInt(nFactura.getText());
        ResultSet res = control.consultarFactura(nFacturaIntro);
        try {
            if (res != null) {
                while (res.next()) {
                    jFecha.setText(res.getString("fecha"));
                    jCliente.setText(res.getString("codCliente"));
                    tImporte.setText(res.getString("importe"));
                    tIva.setText(res.getString("iva"));
                    tFactura.setText(res.getString("total"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Fallo al comprobar la factura");
        }
        consultarRellenarDatosCliente();
        consultarRellenarLineas();
    }

    public void consultarRellenarDatosCliente() {
        String codClienteDatos = jCliente.getText();
        ResultSet res = control.consultaFacturaClientes(codClienteDatos);

        try {
            if (res != null) {
                while (res.next()) {
                    tNombre.setText(res.getString("cliente"));
                    tTelefono.setText(res.getString("telefono"));
                    tNif.setText(res.getString("nif"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Fallo al comprobar la factura");
        }
    }

    public void consultarRellenarLineas() {
        String nFacturaBD = nFactura.getText();
        ResultSet res = control.consultaLineas(nFacturaBD);
        nLinea = 1;
        try {
            if (res != null) {
                while (res.next()) {
                    modelo.addRow(new Object[]{nLinea, res.getString("articulo"), res.getString("pvp"), res.getString("unidades"), res.getString("importe")});
                    nLinea++;
                }
            }
        } catch (SQLException e) {
            System.out.println("Fallo al comprobar la factura");
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
        jLabel2 = new javax.swing.JLabel();
        nFactura = new javax.swing.JTextField();
        jCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFecha = new javax.swing.JTextField();
        choice = new java.awt.Choice();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tNombre = new javax.swing.JTextField();
        tTelefono = new javax.swing.JTextField();
        tNif = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tImporte = new javax.swing.JTextField();
        tFactura = new javax.swing.JTextField();
        tIva = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        grabar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Factura Nº");

        jLabel2.setText("Código cliente");

        jCliente.setEditable(false);

        jLabel3.setText("Fecha");

        choice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choiceItemStateChanged(evt);
            }
        });

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nueva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos cliente"));

        jLabel4.setText("Nombre");

        jLabel5.setText("Telefono");

        jLabel6.setText("NIF");

        tNombre.setEditable(false);

        tTelefono.setEditable(false);

        tNif.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(tTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(tNif, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

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

        jLabel7.setText("Total Importe");

        jLabel8.setText("Total Factura");

        jLabel9.setText("Total IVA");

        tImporte.setEditable(false);

        tFactura.setEditable(false);

        tIva.setEditable(false);

        add.setText("Añadir Linea");
        add.setEnabled(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setText("Borrar Linea");
        delete.setEnabled(false);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        grabar.setText("Grabar Factura");
        grabar.setEnabled(false);
        grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(nFactura))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(33, 33, 33)
                                        .addComponent(jFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(choice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add)
                                .addGap(135, 135, 135)
                                .addComponent(delete)
                                .addGap(137, 137, 137)
                                .addComponent(grabar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(tIva, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(choice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(tImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(tFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(delete)
                    .addComponent(grabar))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        nueva();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void choiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choiceItemStateChanged
        datosCliente();
    }//GEN-LAST:event_choiceItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        addLinea();
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        borrarLinea();
    }//GEN-LAST:event_deleteActionPerformed

    private void grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarActionPerformed
        grabarFactura();
        grabarLineaFacturas();
    }//GEN-LAST:event_grabarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        consultar();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton add;
    private java.awt.Choice choice;
    private javax.swing.JButton delete;
    private javax.swing.JButton grabar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JTextField jCliente;
    private javax.swing.JTextField jFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nFactura;
    private javax.swing.JTextField tFactura;
    private javax.swing.JTextField tImporte;
    private javax.swing.JTextField tIva;
    private javax.swing.JTextField tNif;
    private javax.swing.JTextField tNombre;
    private javax.swing.JTextField tTelefono;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
