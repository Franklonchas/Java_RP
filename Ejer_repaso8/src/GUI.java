/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fran De La Torre
 */
public class GUI extends javax.swing.JFrame implements Runnable {

    Thread tiradaManual;
    Thread tiradaAutomatica;

    int cont1 = 0;
    int cont2 = 0;
    int cont3 = 0;
    int cont4 = 0;
    int cont5 = 0;
    int cont6 = 0;

    public GUI() {
        initComponents();
        setResizable(false);
        limpiar();
        tiradaManual = null;
        tiradaAutomatica = null;
    }

    public void run() {
        try {
            simularHilo();
            simularAutomatico();
        } catch (InterruptedException e) {
        }
    }

    public void nuevoHilo() {
        if (tiradaManual == null) {
            tiradaManual = new Thread(this);
            tiradaManual.start();
        }
    }

    public void nuevoHilo2() {
        if (tiradaAutomatica == null) {
            tiradaAutomatica = new Thread(this);
            tiradaAutomatica.start();
        }
    }

    public void reasignar() {
        cont1 = 0;
        cont2 = 0;
        cont3 = 0;
        cont4 = 0;
        cont5 = 0;
        cont6 = 0;
    }

//    public void comprobarSeleccionado() {
//        if (manual.isSelected() && automatico.isSelected() == false) {
//            automatico.setSelected(false);
//        }
//        if (manual.isSelected() == false && automatico.isSelected()) {
//            manual.setSelected(false);
//        }
//    }
    public void simularHilo() throws InterruptedException {

        if (!tTiradas.getText().equals("")) {
            parar.setEnabled(true);
            continuar.setEnabled(true);
            reasignar();
            int i = Integer.parseInt(tTiradas.getText());
            while (i > 0 && Thread.currentThread() == tiradaManual) {
                int estaTirada = (int) (Math.random() * 6 + 1);
                if (estaTirada == 1) {
                    cont1++;
                    n1.setText("Nº veces 1: " + cont1);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 2) {
                    cont2++;
                    n2.setText("Nº veces 3: " + cont2);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 3) {
                    cont3++;
                    n3.setText("Nº veces 3: " + cont3);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 4) {
                    cont4++;
                    n4.setText("Nº veces 4: " + cont4);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 5) {
                    cont5++;
                    n5.setText("Nº veces 5: " + cont5);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 6) {
                    cont6++;
                    n6.setText("Nº veces 6: " + cont6);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                }
                Thread.sleep(1000);

            }
        }
    }

    public void parar() {
        tiradaManual.suspend();
    }

    public void reanudar() {
        tiradaManual.resume();
    }

    public void simularAutomatico() throws InterruptedException {

        if (!tTiradas.getText().equals("")) {
            parar.setEnabled(false);
            continuar.setEnabled(false);
            reasignar();
            int i = Integer.parseInt(tTiradas.getText());
            while (i > 0 && Thread.currentThread() == tiradaAutomatica) {
                int estaTirada = (int) (Math.random() * 6 + 1);
                if (estaTirada == 1) {
                    cont1++;
                    n1.setText("Nº veces 1: " + cont1);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 2) {
                    cont2++;
                    n2.setText("Nº veces 3: " + cont2);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 3) {
                    cont3++;
                    n3.setText("Nº veces 3: " + cont3);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 4) {
                    cont4++;
                    n4.setText("Nº veces 4: " + cont4);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 5) {
                    cont5++;
                    n5.setText("Nº veces 5: " + cont5);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                } else if (estaTirada == 6) {
                    cont6++;
                    n6.setText("Nº veces 6: " + cont6);
                    tArea.append("" + estaTirada + '\n');
                    i--;
                }
                Thread.sleep(500);

            }
        }
    }

    public void limpiar() {
        n1.setText("Nº veces: 0 ");
        n2.setText("Nº veces: 0 ");
        n3.setText("Nº veces: 0 ");
        n4.setText("Nº veces: 0 ");
        n5.setText("Nº veces: 0 ");
        n6.setText("Nº veces: 0 ");

        tTiradas.setText("");
        tArea.removeAll();
        manual.setSelected(true);
        tTiradas.requestFocus();

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
        tTiradas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        manual = new javax.swing.JRadioButton();
        automatico = new javax.swing.JRadioButton();
        n1 = new javax.swing.JTextField();
        n2 = new javax.swing.JTextField();
        n3 = new javax.swing.JTextField();
        n4 = new javax.swing.JTextField();
        n5 = new javax.swing.JTextField();
        n6 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tArea = new javax.swing.JTextArea();
        simular = new javax.swing.JButton();
        parar = new javax.swing.JButton();
        continuar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero tiradas");

        jLabel2.setText("Modo");

        manual.setText("Manual");
        manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualActionPerformed(evt);
            }
        });

        automatico.setText("Automatico");
        automatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                automaticoActionPerformed(evt);
            }
        });

        tArea.setColumns(20);
        tArea.setRows(5);
        jScrollPane1.setViewportView(tArea);

        simular.setText("Simular");
        simular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simularActionPerformed(evt);
            }
        });

        parar.setText("Parar");
        parar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pararActionPerformed(evt);
            }
        });

        continuar.setText("Continuar");
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
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
                        .addContainerGap()
                        .addComponent(simular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(continuar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(120, 120, 120)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tTiradas, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(n3)
                                        .addComponent(n2)
                                        .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(32, 32, 32)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(automatico)
                                        .addComponent(manual)
                                        .addComponent(n4)
                                        .addComponent(n5)
                                        .addComponent(n6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tTiradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(automatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simular)
                    .addComponent(parar)
                    .addComponent(continuar)
                    .addComponent(limpiar)
                    .addComponent(salir))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void automaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_automaticoActionPerformed
        manual.setSelected(false);
    }//GEN-LAST:event_automaticoActionPerformed

    private void manualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualActionPerformed
        automatico.setSelected(false);
    }//GEN-LAST:event_manualActionPerformed

    private void simularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simularActionPerformed
        if (manual.isSelected()) {
            nuevoHilo();
        } else {
            nuevoHilo2();
        }
    }//GEN-LAST:event_simularActionPerformed

    private void pararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pararActionPerformed
        try {
            parar();
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_pararActionPerformed

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed
        try {
            reanudar();
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_continuarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_limpiarActionPerformed

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
    private javax.swing.JRadioButton automatico;
    private javax.swing.JButton continuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    private javax.swing.JRadioButton manual;
    private javax.swing.JTextField n1;
    private javax.swing.JTextField n2;
    private javax.swing.JTextField n3;
    private javax.swing.JTextField n4;
    private javax.swing.JTextField n5;
    private javax.swing.JTextField n6;
    private javax.swing.JButton parar;
    private javax.swing.JButton salir;
    private javax.swing.JButton simular;
    private javax.swing.JTextArea tArea;
    private javax.swing.JTextField tTiradas;
    // End of variables declaration//GEN-END:variables
}
