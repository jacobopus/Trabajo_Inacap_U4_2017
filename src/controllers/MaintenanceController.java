/**
 * MaintenanceControler.java
 * extends Base_Controller
 * 
 * @Author Andrés Reyes
 * 02-12-2017
 * 
 * Esta clase es la controladora de las operaciones que afectan a Mantenciones.
 * La idea es que cualquier operación que afecte a estas entidades
 * sea manejada solo desde esta clase.
 */
package controllers;

import config.Config;
import dao_models.DAO_Maintenance; 
import data_models.Client;
import data_models.Maintenance;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.MaintenanceView;
import views.UpdateMaintenanceView;

/**
 * 
 * @author jacobopus
 */
public class MaintenanceController extends Base_Controller{
    private MaintenanceView mV;
    
    public MaintenanceController() {
    }
    
    public void maintenanceControl_showWindow( Client client ) {
        mV = new MaintenanceView( Config.DIALOG_MODAL, client);       
        mV.setLabelTitle("Mantenciones de cliente " + client.getNombre());
        mV.setVisible( true );
        mV.pack();
    }
    
    public DefaultTableModel prepareTModel( Client cliente ) {
        DAO_Maintenance dM = new DAO_Maintenance();        
        return dM.prepareTableModel( cliente );
    }
    
    public void deleteAllMaintClient( Client cliente) {
        DAO_Maintenance dM = new DAO_Maintenance();
        dM.deleteAllMaintenancesClient( cliente );
    }
    
    public void deleteMaintenanceControl( Client cliente, Maintenance m) {
        DAO_Maintenance dMan = new DAO_Maintenance();
        int respuesta;
        
        
        respuesta = JOptionPane.showConfirmDialog(null, 
                                                  "¿Realmente desea eliminar la mantención seleccionada? \n" +
                                                  "ID " + m.getId() + " de fecha " + m.getFecha() + "\n " + 
                                                  m.getLaborRealizada() + "\n",
                                                  "Confirme eliminación de mantención",
                                                  JOptionPane.YES_NO_OPTION, 
                                                  JOptionPane.QUESTION_MESSAGE);        
        if (respuesta == Config.DIALOG_YES)
        {            
            dMan.delete( m );            
        }
    }
    
   public void newMaintenanceUpdateWindow( int modo, Maintenance m, String title,  MaintenanceView mv ) {
       UpdateMaintenanceView upv;
       int clientIdBuffer;
               
       if (Config.MODO_EDIT == modo)
       {    
           upv = new UpdateMaintenanceView(Config.DIALOG_MODAL, m,  title, mv);
           upv.setManten( m );   
       }
       else
       {
           upv = new UpdateMaintenanceView(Config.DIALOG_MODAL, title, mv);
           clientIdBuffer = m.getIdCliente();
           m = new Maintenance();
           m.setIdCliente(clientIdBuffer);
           upv.setManten(m);
           upv.resetText();
       }    
       upv.setModo( modo );
       upv.setMaintenanceController(this);           
             
       upv.setVisible(true);
       upv.pack();  
   }
    
    public boolean newMaintenanceControl_insert( Maintenance m ) {
        DAO_Maintenance dm = new DAO_Maintenance();
        
        return dm.insert( m );
    }
    
    public boolean newMaintenanceControl_edit( Maintenance m ) {
        DAO_Maintenance dm = new DAO_Maintenance();
        
        return dm.update( m );
    }

    public void displayMantencion(Maintenance m, Client cliente) {
        JOptionPane.showMessageDialog(null,"Mantencion cliente " + cliente.getNombre() + "\n\n" +
                                            m.toString());
    }
}
