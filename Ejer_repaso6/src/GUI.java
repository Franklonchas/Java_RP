
import java.util.ArrayList;

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

    ArrayList<paciente> Pacientes;
    ArrayList<String> PacientesAtendidos;
    paciente patient;
    String nombre;
    String telefono;

    public GUI() {
        initComponents();
        Pacientes = new ArrayList<paciente>();
        PacientesAtendidos = new ArrayList<String>();
    }

    public void limpiarTF() {
        jPaciente.setText("");
        jTelefono.setText("");
        jPaciente.requestFocus();
    }

    public void addPacientes() {
        nombre = jPaciente.getText();
        telefono = jTelefono.getText();

        if (!"".equals(nombre) && !"".equals(telefono)) {
            patient = new paciente(nombre, telefono);
            Pacientes.add(patient);
            System.out.println(Pacientes);
            limpiarTF();
        } else {
            System.err.println("No se pueden introducir campos vacios!");
        }

    }

    public void comenzarConsulta() {
        lista1.removeAll();
        lista2.removeAll();
        for (int i = 0; i < Pacientes.size(); i++) {
            lista1.add(Pacientes.get(i).nombre);
        }
        System.out.println("Pacientes añadidos a la lista de espera");
    }

    public void siguientePaciente() {
        if (Pacientes.size() != 0 && paConsulta.getText().equals("")) {
            String nombrePaciente = lista1.getItem(WIDTH - 1);
            lista1.remove(WIDTH - 1);
            Pacientes.remove(WIDTH - 1);
            paConsulta.setText(nombrePaciente);

            System.out.println(Pacientes);
        }
    }

    public void finalizarPaciente() {
        if (!paConsulta.getText().equals("")) {
            String finPaciente = paConsulta.getText();
            paConsulta.setText("");
            PacientesAtendidos.add(finPaciente);
            lista2.add(finPaciente);
        } else {
            System.err.println("No hay nadie en consulta.");
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

        dialogo = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jPaciente = new javax.swing.JTextField();
        jTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lista1 = new java.awt.List();
        lista2 = new java.awt.List();
        jLabel3 = new javax.swing.JLabel();
        paConsulta = new javax.swing.JTextField();
        solicitar = new javax.swing.JButton();
        comenzar = new javax.swing.JButton();
        finalizar = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();

        dialogo.setMinimumSize(new java.awt.Dimension(400, 300));

        jLabel4.setText("Paciente");

        jLabel5.setText("Telefono");

        add.setText("Añadir");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogoLayout = new javax.swing.GroupLayout(dialogo.getContentPane());
        dialogo.getContentPane().setLayout(dialogoLayout);
        dialogoLayout.setHorizontalGroup(
            dialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogoLayout.createSequentialGroup()
                .addGroup(dialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(dialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTelefono)
                            .addComponent(jPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                    .addGroup(dialogoLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(add)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        dialogoLayout.setVerticalGroup(
            dialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(dialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(dialogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(add)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(500, 500));

        jLabel1.setText("Lista de espera");

        jLabel2.setText("Pacientes Atendidos");

        jLabel3.setText("Pacientes en consulta");

        paConsulta.setEditable(false);

        solicitar.setText("Solicitar cita");
        solicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitarActionPerformed(evt);
            }
        });

        comenzar.setText("Comenzar consula");
        comenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzarActionPerformed(evt);
            }
        });

        finalizar.setText("Finalizar consulta");
        finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarActionPerformed(evt);
            }
        });

        siguiente.setText("Siguiente paciente");
        siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(solicitar)
                        .addGap(41, 41, 41)
                        .addComponent(comenzar)
                        .addGap(43, 43, 43)
                        .addComponent(siguiente))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(paConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(finalizar))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lista1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addGap(97, 97, 97)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(lista2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lista1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(lista2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(paConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finalizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(solicitar)
                    .addComponent(comenzar)
                    .addComponent(siguiente))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitarActionPerformed
        dialogo.setVisible(true);
    }//GEN-LAST:event_solicitarActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        addPacientes();
    }//GEN-LAST:event_addActionPerformed

    private void comenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comenzarActionPerformed
        comenzarConsulta();
    }//GEN-LAST:event_comenzarActionPerformed

    private void siguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteActionPerformed
        siguientePaciente();
    }//GEN-LAST:event_siguienteActionPerformed

    private void finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarActionPerformed
        finalizarPaciente();
    }//GEN-LAST:event_finalizarActionPerformed

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
    private javax.swing.JButton comenzar;
    private javax.swing.JDialog dialogo;
    private javax.swing.JButton finalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jPaciente;
    private javax.swing.JTextField jTelefono;
    private java.awt.List lista1;
    private java.awt.List lista2;
    private javax.swing.JTextField paConsulta;
    private javax.swing.JButton siguiente;
    private javax.swing.JButton solicitar;
    // End of variables declaration//GEN-END:variables
}
