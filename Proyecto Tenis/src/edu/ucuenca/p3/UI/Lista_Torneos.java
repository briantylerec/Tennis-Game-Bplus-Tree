/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucuenca.p3.UI;

import edu.ucuenca.p3.Modulos.Torneo;
import edu.ucuenca.p3.SRV.TorneoSRV;
import java.awt.ScrollPane;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class Lista_Torneos extends javax.swing.JPanel {

    public JScrollPane jspContenedor;

    /**
     * Creates new form Lista_Torneos
     */
    public Lista_Torneos(boolean activar, JScrollPane jspContenedor2) {
        initComponents();
        jspContenedor = jspContenedor2;
        jspContenedor.setLayout(null);//centra el jPanel
        limpiar_Tabla(jTable_Lista_Torneos);
        cargarTorneos_Tabla();
        if (activar) {
            jButton_Comenzar_torneo.setEnabled(true);
            jButton_Crear_Torneo.setEnabled(false);
        } else {
            jButton_Crear_Torneo.setEnabled(true);
            jButton_Comenzar_torneo.setEnabled(false);
        }
        TorneoSRV torneosrv = new TorneoSRV();
        torneosrv.cargarDatosArchivo();
        clear_Table(jTable_Lista_Torneos);
        tabla_torneos_Archivo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Crear_Torneo = new javax.swing.JButton();
        jButton_Comenzar_torneo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Lista_Torneos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(627, 492));
        setMinimumSize(new java.awt.Dimension(627, 492));
        setLayout(null);

        jButton_Crear_Torneo.setText("Inscribir participantes");
        jButton_Crear_Torneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Crear_TorneoActionPerformed(evt);
            }
        });
        add(jButton_Crear_Torneo);
        jButton_Crear_Torneo.setBounds(530, 520, 154, 37);

        jButton_Comenzar_torneo.setText("Comenzar torneo");
        jButton_Comenzar_torneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Comenzar_torneoActionPerformed(evt);
            }
        });
        add(jButton_Comenzar_torneo);
        jButton_Comenzar_torneo.setBounds(900, 520, 150, 37);

        jLabel1.setFont(new java.awt.Font("Impact", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccionar torneo");
        add(jLabel1);
        jLabel1.setBounds(650, 40, 260, 33);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Torneos"));

        jTable_Lista_Torneos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Código", "Tipo", "Modalidad", "Categoría"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable_Lista_Torneos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1);
        jPanel1.setBounds(530, 130, 518, 350);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/ucuenca/p3/Iconos/fondo6.jpg"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(0, 0, 1190, 660);
    }// </editor-fold>//GEN-END:initComponents

    private void limpiar_Tabla(JTable jtableTorneos) {
        DefaultTableModel modeloTorneo = (DefaultTableModel) jtableTorneos.getModel();
        for (int i = 0; i < jtableTorneos.getRowCount(); i++) {
            modeloTorneo.removeRow(i);
        }
    }

    public void obtenerTorneo(String codigo_torneo) {

        Torneo torneo;
        TorneoSRV torneoSrv = new TorneoSRV();
        try {
            torneo = torneoSrv.obtenerTorneo(codigo_torneo);
            int seleccion = jTable_Lista_Torneos.getSelectedRow();

            String codigo = (String) jTable_Lista_Torneos.getValueAt(seleccion, 0);
            InscripcionGUI inscripcionGUI = new InscripcionGUI(codigo);

            this.hide();
            jspContenedor.setViewportView(inscripcionGUI);
            jspContenedor.setLocation(300, -2);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Elija un registro por favor !");
        }
    }
    
    private void jButton_Crear_TorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Crear_TorneoActionPerformed

        try {
            TorneoSRV torneoSrv = new TorneoSRV();
            int seleccion_fila = jTable_Lista_Torneos.getSelectedRow();
            if (seleccion_fila > -1) {
                String codigo_torneo = (String) jTable_Lista_Torneos.getValueAt(jTable_Lista_Torneos.getSelectedRow(), 0);
                torneoSrv.validarCodigo(codigo_torneo);
                
                System.out.println("cod torneo: " + codigo_torneo);
                
                InscripcionGUI inscripcionGUI = new InscripcionGUI(codigo_torneo);
                
                this.hide();
                jspContenedor.setViewportView(inscripcionGUI);
                jspContenedor.setLocation(0, 0);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un registro por favor !");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton_Crear_TorneoActionPerformed

    private void jButton_Comenzar_torneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Comenzar_torneoActionPerformed

        try {
            TorneoSRV torneoSrv = new TorneoSRV();
            int seleccion_fila = jTable_Lista_Torneos.getSelectedRow();
            if (seleccion_fila > -1) {
                String codigo_torneo = (String) jTable_Lista_Torneos.getValueAt(jTable_Lista_Torneos.getSelectedRow(), 0);
                torneoSrv.validarCodigo(codigo_torneo);
                EncuentroGUI encuentroGUI = new EncuentroGUI(codigo_torneo, jspContenedor);//

                jspContenedor.setViewportView(encuentroGUI);//
                jspContenedor.setLocation(0, 0);//
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
//        } catch (NullPointerException ex) {
//            JOptionPane.showMessageDialog(null, "Elija un registro por favor !");
//        }
    }//GEN-LAST:event_jButton_Comenzar_torneoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Comenzar_torneo;
    private javax.swing.JButton jButton_Crear_Torneo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Lista_Torneos;
    // End of variables declaration//GEN-END:variables

    private void clear_Table(JTable jTableVigilante) {
        DefaultTableModel modeloVigia = (DefaultTableModel) jTableVigilante.getModel();
        for (int i = 0; i < jTableVigilante.getRowCount(); i++) {
            modeloVigia.removeRow(i);
            i -= 1;
        }
    }
    
    private void cargarTorneos_Tabla() {
        DefaultTableModel modeloTorneo = (DefaultTableModel) jTable_Lista_Torneos.getModel();
        TorneoSRV torneoSrv = new TorneoSRV();
        List<Torneo> lista_torneos = torneoSrv.getListaTorneo();

        Object[] fila = new Object[modeloTorneo.getColumnCount()];
        for (int i = 0; i < lista_torneos.size(); i++) {
            fila[0] = lista_torneos.get(i).getCodigo();
            fila[1] = lista_torneos.get(i).getTipo().toUpperCase();
            fila[2] = lista_torneos.get(i).getTipo_juego().toUpperCase();
            fila[3] = lista_torneos.get(i).getTorneo_categorías().getCategoria().getNombre().toUpperCase();

            modeloTorneo.addRow(fila);
        }

    }
    
    private void tabla_torneos_Archivo(){
        DefaultTableModel modeloVigia = (DefaultTableModel) jTable_Lista_Torneos.getModel();
        TorneoSRV torneosrv = new TorneoSRV();
//        List<Participante> listaParticipantes = participantesrv.listaParticipantes();

        ArrayList<Torneo> listaTorneos = torneosrv.listarTorneosArchivos();
        Object[] fila = new Object[modeloVigia.getColumnCount()];
        for (int i = 0; i < listaTorneos.size(); i++) {
            Torneo torneo = listaTorneos.get(i);
            
            fila[0] = torneo.getCodigo();
            fila[1] = torneo.getTipo();
            fila[2] = torneo.getTipo_juego();
            fila[3] = torneo.getTorneo_categorías().getCategoria().getNombre();
                            
            modeloVigia.addRow(fila);
        }
    }
}
