
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
    String cursoSeleccionado, idCurso, evaluacionBD, asignaturaBD, idAsignatura;
    int evaluacionSeleccionada, sobres, notas, bien, sufis, insu, totalA;

    public GUI() {
        initComponents();
        control = new Controlador();
        rellenarCursos();
    }

    public void rellenarCursos() {
        ResultSet res = control.obtenerCursos();
        try {
            if (res != null) {
                while (res.next()) {
                    curso.add(res.getString("curso"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
    }

    public void rellenarAsignaturas() {
        asigna.removeAll();
        obtenerIdCurso();
        ResultSet res = control.obtenerAsignaturas(idCurso);
        try {
            if (res != null) {
                while (res.next()) {
                    asigna.add(res.getString("asignatura"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
    }

    public void obtenerIdCurso() {
        cursoSeleccionado = curso.getSelectedItem();
        ResultSet res = control.obtenerIdCursoBD(cursoSeleccionado);
        try {
            if (res != null) {
                while (res.next()) {
                    idCurso = res.getString("idCurso");
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
    }

    public void todasSeleccionado() {
        modelo = new DefaultTableModel();
        modelo.addColumn("             ");
        modelo.addColumn("1º Evaluación");
        modelo.addColumn("2º Evaluación");
        modelo.addColumn("3º Evaluación");
        modelo.addColumn("Evaluación Final");
        tabla.setModel(modelo);
    }

    public void unaSeleccionado() {
        modelo = new DefaultTableModel();
        modelo.addColumn("             ");
        modelo.addColumn("1º Evaluación");
        tabla.setModel(modelo);
    }

    public void evaluacionesSeleccionada() {
        evaluacionSeleccionada = evaluacion.getSelectedIndex();
        if (evaluacionSeleccionada == 0 && todas.isSelected() == false) {
            modelo = new DefaultTableModel();
            modelo.addColumn("             ");
            modelo.addColumn("1º Evaluación");
            tabla.setModel(modelo);
        } else if (evaluacionSeleccionada == 1 && todas.isSelected() == false) {
            modelo = new DefaultTableModel();
            modelo.addColumn("             ");
            modelo.addColumn("2º Evaluación");
            tabla.setModel(modelo);
        } else if (evaluacionSeleccionada == 2 && todas.isSelected() == false) {
            modelo = new DefaultTableModel();
            modelo.addColumn("             ");
            modelo.addColumn("3º Evaluación");
            tabla.setModel(modelo);
        } else if (evaluacionSeleccionada == 3 && todas.isSelected() == false) {
            modelo = new DefaultTableModel();
            modelo.addColumn("             ");
            modelo.addColumn("Evaluación Final");
            tabla.setModel(modelo);

        }
    }

    public void obtenerIdAsignatura() {
        asignaturaBD = asigna.getSelectedItem();
        ResultSet res = control.obtenerIDAsignaturaBD(asignaturaBD);
        try {
            if (res != null) {
                while (res.next()) {
                    idAsignatura = res.getString("idAsignatura");
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
    }

    public void deUna(String evaluacionBD, String idAsignatura) {
        ResultSet res = control.consultarUnaEvaluacion(idAsignatura, evaluacionBD);
        try {
            if (res != null) {
                modelo.addRow(new Object[]{"Sobresalientes",});
                modelo.addRow(new Object[]{"Notables"});
                modelo.addRow(new Object[]{"Bienes"});
                modelo.addRow(new Object[]{"Suficientes"});
                modelo.addRow(new Object[]{"Insuficientes"});
                modelo.addRow(new Object[]{"Total Alumnos"});
                while (res.next()) {
                    if (res.getInt("nota") >= 9) {
                        sobres++;
                        totalA++;
                    } else if (res.getInt("nota") >= 7) {
                        notas++;
                        totalA++;
                    } else if (res.getInt("nota") == 6) {
                        bien++;
                        totalA++;
                    } else if (res.getInt("nota") == 5) {
                        sufis++;
                        totalA++;
                    } else if (res.getInt("nota") < 5) {
                        insu++;
                        totalA++;
                    }

                    modelo.setValueAt(sobres * 100 / totalA + "%", 0, 1);
                    modelo.setValueAt(notas * 100 / totalA + "%", 1, 1);
                    modelo.setValueAt(bien * 100 / totalA + "%", 2, 1);
                    modelo.setValueAt(sufis * 100 / totalA + "%", 3, 1);
                    modelo.setValueAt(insu * 100 / totalA + "%", 4, 1);
                    modelo.setValueAt(totalA, 5, 1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Fallo al rellenar el choice Jornadas");
        }
    }

    public void deTodas(String idAsignatura) {

        modelo.addRow(new Object[]{"Sobresalientes",});
        modelo.addRow(new Object[]{"Notables"});
        modelo.addRow(new Object[]{"Bienes"});
        modelo.addRow(new Object[]{"Suficientes"});
        modelo.addRow(new Object[]{"Insuficientes"});
        modelo.addRow(new Object[]{"Total Alumnos"});

        for (int i = 0; i < 5; i++) {
            sobres = notas = bien = sufis = insu = totalA = 0;
            if (i == 1) {
                evaluacionBD = "Evaluacion 1";
            } else if (i == 2) {
                evaluacionBD = "Evaluacion 2";
            } else if (i == 3) {
                evaluacionBD = "Evaluacion 3";
            } else if (i == 4) {
                evaluacionBD = "Evaluacion Final";
            }
            ResultSet res = control.consultarUnaEvaluacion(idAsignatura, evaluacionBD);

            try {
                if (res != null) {

                    while (res.next()) {
                        if (res.getInt("nota") >= 9) {
                            sobres++;
                            totalA++;
                        } else if (res.getInt("nota") >= 7) {
                            notas++;
                            totalA++;
                        } else if (res.getInt("nota") == 6) {
                            bien++;
                            totalA++;
                        } else if (res.getInt("nota") == 5) {
                            sufis++;
                            totalA++;
                        } else if (res.getInt("nota") < 5) {
                            insu++;
                            totalA++;
                        }

                        modelo.setValueAt(sobres * 100 / totalA + "%", 0, i);
                        modelo.setValueAt(notas * 100 / totalA + "%", 1, i);
                        modelo.setValueAt(bien * 100 / totalA + "%", 2, i);
                        modelo.setValueAt(sufis * 100 / totalA + "%", 3, i);
                        modelo.setValueAt(insu * 100 / totalA + "%", 4, i);
                        modelo.setValueAt(totalA, 5, i);
                    }

                }

            } catch (SQLException e) {
                System.err.println("Fallo al rellenar el choice Jornadas");
            }
        }
    }

    public void consultarDatos() {

        sobres = notas = bien = sufis = insu = totalA = 0;
        modelo.setRowCount(0);

        if (evaluacion.getSelectedIndex() == 0) {
            evaluacionBD = "Evaluacion 1";
            obtenerIdAsignatura();
            deUna(evaluacionBD, idAsignatura);

        } else if (evaluacion.getSelectedIndex() == 1) {
            evaluacionBD = "Evaluacion 2";
            obtenerIdAsignatura();
            deUna(evaluacionBD, idAsignatura);

        } else if (evaluacion.getSelectedIndex() == 2) {
            evaluacionBD = "Evaluacion 3";
            obtenerIdAsignatura();
            deUna(evaluacionBD, idAsignatura);

        } else if (evaluacion.getSelectedIndex() == 3 && !todas.isSelected()) {
            evaluacionBD = "Evaluacion Final";
            obtenerIdAsignatura();
            deUna(evaluacionBD, idAsignatura);
        } else if (todas.isSelected() && evaluacion.getSelectedIndex() == 3) {
            obtenerIdAsignatura();
            deTodas(idAsignatura);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        curso = new java.awt.Choice();
        evaluacion = new java.awt.Choice();
        asigna = new java.awt.Choice();
        todas = new javax.swing.JRadioButton();
        una = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección Asignatura/Evaluación"));

        jLabel1.setText("Curso");

        jLabel2.setText("Evaluacion");

        jLabel3.setText("Asignaturas");

        jLabel4.setText("Evaluaciones");

        curso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cursoItemStateChanged(evt);
            }
        });

        evaluacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                evaluacionItemStateChanged(evt);
            }
        });

        todas.setText("Todas");
        todas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todasActionPerformed(evt);
            }
        });

        una.setText("Una");
        una.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(evaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(asigna, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(una)
                            .addComponent(todas))))
                .addGap(148, 148, 148))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3))
                    .addComponent(asigna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(todas))
                    .addComponent(evaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(una)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        evaluacion.add("Evaluacion 1");
        evaluacion.add("Evaluacion 2");
        evaluacion.add("Evaluacion 3");
        evaluacion.add("Evaluacion Final");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Estadística Notas"));

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

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(31, 31, 31)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cursoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cursoItemStateChanged
        rellenarAsignaturas();
    }//GEN-LAST:event_cursoItemStateChanged

    private void todasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todasActionPerformed
        todasSeleccionado();
        una.setSelected(false);
        evaluacion.select(3);
    }//GEN-LAST:event_todasActionPerformed

    private void unaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unaActionPerformed
        unaSeleccionado();
        evaluacion.select(0);
        todas.setSelected(false);
    }//GEN-LAST:event_unaActionPerformed

    private void evaluacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_evaluacionItemStateChanged
        evaluacionesSeleccionada();
    }//GEN-LAST:event_evaluacionItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        consultarDatos();
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
    private java.awt.Choice asigna;
    private java.awt.Choice curso;
    private java.awt.Choice evaluacion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JRadioButton todas;
    private javax.swing.JRadioButton una;
    // End of variables declaration//GEN-END:variables
}
