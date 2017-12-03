/**
 * ClienteControler.java
 * extends Base_Controller
 * 
 * @Author Andrés Reyes
 * 02-12-2017
 * 
 * Esta clase es la controladora de las operaciones que afectan a Clientes.
 * La idea es que cualquier operación que afecte a estas entidades
 * sea manejada solo desde esta clase.
 * 
 * Provee con métodos para acceder a las funcionalidades de la clase DAO_Clientes.
 * 
 * También provee un método para obtener la lista de cliente en formato
 * compatible con DefaultTableModel, para poder llenar la Jtable de clientes
 * ubicada en ClientView.java
 * 
 */
package controllers;

import config.Config;
import dao_models.DAO_Client;
import data_models.Client;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import views.ClientView;
import views.UpdateClienteView;
import views.displayClient;

/**
 *
 * @author jacobopus
 */
public class ClienteController extends Base_Controller{
    private displayClient dCv;
    private UpdateClienteView uCv;
    private ClientView vP;

    public ClienteController(ClientView vP) {
        this.vP = vP;
    }  
    
    
    /**
     * Llama a la vista UpdateClientView.java
     * configurada para realizar un ingreso de un nuevo cliente.
     * @return void
     */
    public void newClientControl_showWindow() {
        Client cliente = new Client();
               
        
        uCv = new UpdateClienteView(this.vP, 
                                    "Nuevo Cliente", 
                                    Config.DIALOG_MODAL,
                                    cliente,
                                    Config.MODO_APPEND);
        uCv.setTitulo("Ingrese un nuevo Cliente");
        uCv.setControllerWhoCalls( this );
        uCv.setVisible(true);
        uCv.pack();            
    }
    
    
    /**
     * Se comunica con DAO_Client para hacer la inserción del nuevo cliente.
     * retorna el verdadero si la inserción tiene exito, y falso en caso contrario.
     * 
     * @param cliente
     * @return boolean
     */
    public boolean newClientControl_insert(Client cliente) {
        DAO_Client dCli = new DAO_Client();
        
        return dCli.insert(cliente);
    }
    
    public void editClientControl_showWindow( Client cliente ) {

        uCv = new UpdateClienteView(this.vP, 
                                    "Editar Cliente", 
                                    Config.DIALOG_MODAL,
                                    cliente,
                                    Config.MODO_EDIT);
        uCv.setTitulo("Edite los datos del Cliente");
        uCv.setControllerWhoCalls( this );
        uCv.setVisible(true);
        uCv.pack();            
    }
    
    
    public boolean editClientControl_update(Client cliente) {
        DAO_Client dCli = new DAO_Client();
        
        return dCli.update(cliente);
    }
    
    /**
    * Obtiene los datos de cliente de la clase DAO_Client,
    * y las mantenciones de este cliente a travez del controlador de mantenciones
    * MaintenanceController.java.
    * Luego llama a la vista displayClienteView.java para desplegar esos datos.
    * 
    * @param Client cliente
    * @return void
    */
    public void showClientControl( Client cliente ) {
        Client c = new Client();
        MaintenanceController dM = new MaintenanceController(); //creo una instancia del controlador
        DefaultTableModel modelo;
        String toDisplay="";
        JTable tM;
        
        c.setId( cliente.getId());
        c.setCi(cliente.getCi());
        c.setFono(cliente.getFono());
        c.setMail(cliente.getMail());
        c.setNombre(cliente.getNombre());
        c.setPpu(cliente.getPpu());
        c.setTipoVehiculo(cliente.getTipoVehiculo());
        
        modelo = dM.prepareTModel( c );      
        toDisplay = cliente.toStringWithoutMantainances();
        
        this.dCv = new displayClient( this.vP, "Información de Cliente", Config.DIALOG_MODAL);        
        this.dCv.setLabels(cliente);
        tM = this.dCv.getJTableMantenciones();  //obtengo una referencia a JTable de mantenciones
       
        tM.setModel( modelo );
        this.dCv.setVisible(true);
        this.dCv.pack();        
    }
    
    public void deleteClientControl( Client cliente ) {
        DAO_Client dCli;
        MaintenanceController dM;
        int respuesta;
        
        respuesta = JOptionPane.showConfirmDialog(null, 
                                                  "Realmente desea eliminar al cliente " +
                                                  cliente.getNombre() +".\n" +
                                                  "Tenga presente que al hacerlo eliminara también \n" +
                                                  "las mantenciones que tenga asociadas", 
                                                  "Confirmar eliminación",
                                                  JOptionPane.YES_NO_OPTION, 
                                                  JOptionPane.QUESTION_MESSAGE);
        
        if (respuesta == Config.DIALOG_YES)
        {
            dM = new MaintenanceController(); //creo una instancia del controlador
            dM.deleteAllMaintClient(cliente);
            
            dCli = new DAO_Client();
            dCli.delete(cliente);            
        }
    }
    
    public DefaultTableModel prepareTModel() {
        DAO_Client dCli = new DAO_Client();
        
        return dCli.prepareTableModel();
    }
}
