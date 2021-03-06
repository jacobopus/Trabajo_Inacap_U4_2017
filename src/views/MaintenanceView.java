/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import config.Config;
import controllers.MaintenanceController;
import data_models.Client;
import data_models.Maintenance;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jacobopus
 */
public class MaintenanceView extends javax.swing.JDialog {
    private TableRowSorter trsFiltro;
    private Client cliente;
    
    /**
     * 
     * @param isModal
     * @param cliente 
     */
    public MaintenanceView( boolean isModal, Client cliente ) {
        super(new JDialog(), "Control de Mantenciones", isModal );
        initComponents();
        this.setLocationRelativeTo(this);
        this.cliente = cliente;
        this.myInitComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        maintenanceTable = new javax.swing.JTable();
        volverBoton = new javax.swing.JButton();
        nuevaManten = new javax.swing.JButton();
        eliminaManten = new javax.swing.JButton();
        editaManten = new javax.swing.JButton();
        filtrarText = new javax.swing.JTextField();
        tituloMaintenanceView = new javax.swing.JLabel();
        filtroComboBox = new javax.swing.JComboBox<>();
        verBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(566, 360));

        maintenanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(maintenanceTable);

        volverBoton.setText("Volver");
        volverBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverBotonActionPerformed(evt);
            }
        });

        nuevaManten.setText("Nueva");
        nuevaManten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaMantenActionPerformed(evt);
            }
        });

        eliminaManten.setText("Eliminar");
        eliminaManten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminaMantenActionPerformed(evt);
            }
        });

        editaManten.setText("Editar");
        editaManten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editaMantenActionPerformed(evt);
            }
        });

        filtrarText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filtrarTextKeyTyped(evt);
            }
        });

        tituloMaintenanceView.setText("jLabel2");

        filtroComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "FECHA", "LABOR", "OBS" }));
        filtroComboBox.setSelectedIndex(1);
        filtroComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                filtroComboBoxKeyTyped(evt);
            }
        });

        verBoton.setText("Ver");
        verBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(volverBoton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(tituloMaintenanceView)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filtroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(filtrarText, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(eliminaManten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editaManten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nuevaManten, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(verBoton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(28, 28, 28)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtrarText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloMaintenanceView)
                    .addComponent(filtroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nuevaManten)
                        .addGap(18, 18, 18)
                        .addComponent(editaManten)
                        .addGap(18, 18, 18)
                        .addComponent(eliminaManten)
                        .addGap(18, 18, 18)
                        .addComponent(verBoton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(volverBoton)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminaMantenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminaMantenActionPerformed
        this.invokeDeleteMantencion();
    }//GEN-LAST:event_eliminaMantenActionPerformed

    private void filtroComboBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroComboBoxKeyTyped
         // TODO add your handling code here:

    }//GEN-LAST:event_filtroComboBoxKeyTyped

    private void volverBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverBotonActionPerformed
        this.dispose();
    }//GEN-LAST:event_volverBotonActionPerformed

    private void filtrarTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrarTextKeyTyped
        filtrarText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                String cadena = (filtrarText.getText());
                filtrarText.setText(cadena);
                repaint();
                filtroMantencion();
            }
        });
        trsFiltro = new TableRowSorter(maintenanceTable.getModel());
        maintenanceTable.setRowSorter(trsFiltro);
    }//GEN-LAST:event_filtrarTextKeyTyped

    private void editaMantenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editaMantenActionPerformed
        this.invokeUpdateMantencion();
    }//GEN-LAST:event_editaMantenActionPerformed

    private void nuevaMantenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaMantenActionPerformed
        this.invokeInsertMantencion();
    }//GEN-LAST:event_nuevaMantenActionPerformed

    private void verBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verBotonActionPerformed
        MaintenanceController mc = new MaintenanceController();
        Maintenance m = extraeMantencion();
        
        if( m != null)
        {    
            m.setIdCliente(this.cliente.getId());
            mc.displayMantencion(m, this.cliente);
            this.myInitComponents();
        }
    }//GEN-LAST:event_verBotonActionPerformed

    public final void myInitComponents() {
        DefaultTableModel modelo;
        MaintenanceController mc = new MaintenanceController();
        maintenanceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modelo = this.prepareModel( mc );
        this.setTableModel( modelo );
        this.selectFirstTableRow();  
    }
    
    public void selectFirstTableRow() {
        if(this.maintenanceTable.getModel().getRowCount() > 0)  //Si es que hay al menos una mantención..
        {
            filtrarText.setEnabled(true);
            filtroComboBox.setEnabled(true);
            verBoton.setEnabled(true);
            editaManten.setEnabled(true);
            eliminaManten.setEnabled(true);
            this.maintenanceTable.setRowSelectionInterval(0, 0); //Ubicate en la primera de ellas en la tabla
        }
        else
        {
            filtrarText.setEnabled(false);
            filtroComboBox.setEnabled(false);
            verBoton.setEnabled(false);
            editaManten.setEnabled(false);
            eliminaManten.setEnabled(false);
        }
    }
    
    public DefaultTableModel prepareModel( MaintenanceController mc) {
        return mc.prepareTModel( this.cliente );
    }
    
    public void setTableModel( DefaultTableModel modelo) {
        this.maintenanceTable.setModel( modelo );
    }   
     
    
    public void invokeInsertMantencion() {
        MaintenanceController mc = new MaintenanceController();
        Maintenance m = new Maintenance();
        m.setIdCliente(this.cliente.getId());
        
        mc.newMaintenanceUpdateWindow( Config.MODO_APPEND, m, "Ingrese nueva mantención", this );
        this.myInitComponents();
    }
    
    public void invokeUpdateMantencion() {
        MaintenanceController mc = new MaintenanceController();
        Maintenance m = extraeMantencion();
        
        if( m!= null)
        {    
            m.setIdCliente(this.cliente.getId());
            mc.newMaintenanceUpdateWindow( Config.MODO_EDIT, m, "Actualice mantención", this );
            this.myInitComponents();
        }
    }
    
    public void invokeDeleteMantencion() {
        MaintenanceController mc = new MaintenanceController();
        Maintenance m = extraeMantencion();
        
        if( m!= null)
        { 
            mc.deleteMaintenanceControl( cliente, m ); 
            this.myInitComponents();
        }    
    } 
    
    
    public Maintenance extraeMantencion() {
        Maintenance m = null;
        int row = maintenanceTable.getSelectedRow();
        
        if (row >-1)
        {    
            m = new Maintenance();
            m.setId(
                Integer.parseInt(
                        maintenanceTable.getValueAt(row, Config.FIELD_ID).toString()));
            m.setFecha(
                maintenanceTable.getValueAt(row, 
                                         Config.FIELD_MAINT_FECHA).toString());
            m.setLaborRealizada(
                maintenanceTable.getValueAt(row, 
                                         Config.FIELD_MAINT_LABOR).toString());
            m.setObservaciones(
                maintenanceTable.getValueAt(row, 
                                         Config.FIELD_MAINT_OBS).toString());
            m.setIdCliente( 
                Integer.parseInt(
                      maintenanceTable.getValueAt(row, 
                                         Config.FIELD_MAINT_ID_CLIENT).toString()));
          
                    
        }       
        
       
        return m;
    }
   
    private void filtroMantencion() {
        int columnaABuscar = 0;
        
        if (filtroComboBox.getSelectedItem().equals("ID")) {
            columnaABuscar = 0;
        }
        if (filtroComboBox.getSelectedItem().equals("FECHA")) {
            columnaABuscar = 1;
        }
        if (filtroComboBox.getSelectedItem().equals("LABOR")) {
            columnaABuscar = 2;
        }
        if (filtroComboBox.getSelectedItem().equals("OBS")) {
            columnaABuscar = 3;
        }

        trsFiltro.setRowFilter(RowFilter.regexFilter(filtrarText.getText().toUpperCase(), columnaABuscar));
        this.selectFirstTableRow();
        
    }    
   
   
   
   public void setLabelTitle( String txt ) {
       this.tituloMaintenanceView.setText( txt );
   }
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editaManten;
    private javax.swing.JButton eliminaManten;
    private javax.swing.JTextField filtrarText;
    private javax.swing.JComboBox<String> filtroComboBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable maintenanceTable;
    private javax.swing.JButton nuevaManten;
    private javax.swing.JLabel tituloMaintenanceView;
    private javax.swing.JButton verBoton;
    private javax.swing.JButton volverBoton;
    // End of variables declaration//GEN-END:variables


}
