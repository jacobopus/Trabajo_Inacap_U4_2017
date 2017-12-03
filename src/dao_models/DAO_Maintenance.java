/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_models;

import config.Conexion;
import config.Config;
import data_models.Client;
import data_models.Maintenance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jacobopus
 */
public class DAO_Maintenance extends DAO_Base implements DAO_Operations{
    private Conexion connect = new Conexion();
    private Client cliente;

    public DAO_Maintenance() {
    }

    public DAO_Maintenance(Client cliente) {
        this.cliente = cliente;
    }

    
    
    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }
            
            
    @Override
    public boolean insert(Object mappedObj) {
       PreparedStatement prpStm = null;
       Maintenance manten = (Maintenance) mappedObj;
       String sql = this.sqlMaintInsertFields();  
                     
       return this.grabarMantencion(manten, prpStm, sql, Config.MODO_APPEND);
    }

    @Override
    public boolean update(Object mappedObj) {
       PreparedStatement prpStm = null;
       Maintenance manten = (Maintenance) mappedObj;
       String sql = this.sqlMaintUpdateFields();  
       
       return this.grabarMantencion(manten, prpStm, sql, Config.MODO_EDIT);  
    }

    @Override
    public boolean delete(Object mappedObj) {
       boolean success = false;
       Maintenance manten = (Maintenance) mappedObj;
       
       PreparedStatement prpStm = null;
       String sql = "delete from " + 
                    Config.MAINTENANCE +
                    " where " + 
                    Config.MAINTENANCE_FIELDS[ Config.FIELD_ID ] + 
                    "=?";
         
       try {
            prpStm = this.connect.getConecction().prepareStatement(sql);
            
            prpStm.setInt( 1, manten.getId());
            
            if (prpStm.executeUpdate() == 1){
                success = true;
       }
          
       prpStm.close();
       } catch (SQLException e) 
       {
           JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
       }
       
       return success;
    }
      
    public ArrayList<Maintenance> fetch(Client cliente) {
        this.setCliente(cliente);
        return this.fetch();
    }
    
    @Override
    public ArrayList<Maintenance> fetch() {
        ArrayList<Maintenance> mantenList = new ArrayList<>();        
        Maintenance mantencion;
        PreparedStatement prpStm = null; 
        ResultSet rs;
        
        String sql = "select * from " + 
                     Config.MAINTENANCE + 
                     " where " + 
                     Config.MAINTENANCE_FIELDS[ Config.FIELD_MAINT_ID_CLIENT ] + 
                     "=?" ;
        try 
        {
           prpStm=this.connect.getConecction().prepareStatement(sql);
           prpStm.setInt(1, this.cliente.getId());
           rs = prpStm.executeQuery();
           while (rs.next())
           {  
               mantencion = new Maintenance(rs.getInt   ( Config.MAINTENANCE_FIELDS[ Config.FIELD_ID ] ),
                                            rs.getInt   ( Config.MAINTENANCE_FIELDS[ Config.FIELD_MAINT_ID_CLIENT ] ),
                                            rs.getString( Config.MAINTENANCE_FIELDS[ Config.FIELD_MAINT_FECHA ] ),
                                            rs.getString( Config.MAINTENANCE_FIELDS[ Config.FIELD_MAINT_LABOR ] ),
                                            rs.getString( Config.MAINTENANCE_FIELDS[ Config.FIELD_MAINT_OBS ] ));
                          
                mantenList.add(mantencion);
           }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
        }
        return mantenList;
    }
    
    @Override
    public boolean search(Object mappedObj  ) {return true;}
    
    public Maintenance get(Object mappedObj) {
       Maintenance manten = (Maintenance) mappedObj;
       PreparedStatement prpStm = null; 
       ResultSet rs;
       String sql ="select * from " + Config.MAINTENANCE + " where " + Config.MAINTENANCE_FIELDS[ 0 ] + " =?";  
         
       try 
       {                      
           prpStm=this.connect.getConecction().prepareStatement(sql);
           prpStm.setInt(1, manten.getId());
           
           rs = prpStm.executeQuery();
           
           if (rs.next())
           {
               manten.setId(
                       rs.getInt( Config.MAINTENANCE_FIELDS[ 0 ] ));
               manten.setFecha(
                       rs.getString( 
                           Config.MAINTENANCE_FIELDS[ 1 ] ));
               manten.setLaborRealizada(
                       rs.getString( 
                           Config.MAINTENANCE_FIELDS[ 2 ] ));
               manten.setObservaciones(
                       rs.getString( 
                           Config.MAINTENANCE_FIELDS[ 3 ] ));
              
           }
           else
           {
               manten.setId(Config.NO_ENCONTRADO);
           }
        } catch (Exception e) {
            manten.setId(Config.NO_ENCONTRADO);
            JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
        }       
        return manten;
    }    
    
    private boolean grabarMantencion(Maintenance manten, PreparedStatement prpStm, String sql, int modo)
    {                
        try
        {
            
            prpStm = this.connect.getConecction().prepareStatement(sql);
            prpStm.setString(1, manten.getFecha());
            prpStm.setString(2, manten.getLaborRealizada().toUpperCase());
            prpStm.setString(3, manten.getObservaciones().toUpperCase());
            prpStm.setInt(4, manten.getIdCliente());
            
            if (modo == Config.MODO_EDIT)
            {
                prpStm.setInt(5, manten.getId());
                
            }    
            if (prpStm.executeUpdate() == Config.OPERATION_SUCCESS)
            {
                return true;
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
        }       
        return false;
    }
    
    
    // DEBO REESCRIBIR ESTE METODO Y USAR EL MISMO METODO DELETE
    public void deleteAllMaintenancesClient( Client cliente ) {
        PreparedStatement prpStm = null;
        String sql = "delete from " + 
                     Config.MAINTENANCE +
                     " where " + 
                     Config.MAINTENANCE_FIELDS[ Config.FIELD_MAINT_ID_CLIENT ] + 
                     "=?";
        
         try {
            prpStm = this.connect.getConecction().prepareStatement(sql);
            
            prpStm.setInt(1,cliente.getId());
            
            if (prpStm.executeUpdate() == 1){
              
            }
          
       prpStm.close();
       } catch (SQLException e) 
       {
           JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
       }
       
    }
    
    private String sqlMaintInsertFields() {
        int i, largo = Config.NUMBER_OF_FIELDS_MAINT;
        String sql = "insert into " + 
                     Config.MAINTENANCE + 
                     " (" +
                     this.addFieldsToSql("") +
                     ") values (?,?,?,?)";
        return sql;
    }
    
    private String sqlMaintUpdateFields() {
        String sql = "update " + 
                     Config.MAINTENANCE + 
                     " set " +
                     this.addFieldsToSql("=?");
        
        sql += " where " + Config.MAINTENANCE_FIELDS[0] + "=?"; // where id_maintenance=?  
        return sql;
    }
    
    
    private String addFieldsToSql( String agregado ) {
        String sql = "";
        int i, largo = Config.NUMBER_OF_FIELDS_MAINTENANCE;
        
        for(i=1;i<largo;i++)
        {
            sql += Config.MAINTENANCE_FIELDS[ i ] + agregado + ",";
        }  
        return sql.substring(0, sql.length() -1);
    }
    
    
    
    public DefaultTableModel prepareTableModel() {
        return this.prepareTableModel(this.cliente);
    }
    
    
    public DefaultTableModel prepareTableModel( Client cliente ) {
        DefaultTableModel modelo;
        DAO_Maintenance daoMaint = new DAO_Maintenance();
        ArrayList<Maintenance> mList;
                
        modelo = getDefaultModel( false );
        
        mList = daoMaint.fetch( cliente );
        modelo = this.modelAddColumn( modelo );
        
        modelo = this.fillTableRows( modelo, mList );
        
        return modelo;
    }
    
    public DefaultTableModel fillTableRows( DefaultTableModel modelo, ArrayList<Maintenance> datos ) {
        String[] fila = new String[ Config.NUMBER_OF_FIELDS_MAINT ]; 
        int i, cuantos = datos.size();    
    
        for(i=0;i<cuantos;i++)
        {       
            fila[ Config.FIELD_ID          ] = ""+datos.get(i).getId();
            fila[ Config.FIELD_MAINT_FECHA ] = datos.get(i).getFecha();
            fila[ Config.FIELD_MAINT_LABOR ] = datos.get(i).getLaborRealizada();
            fila[ Config.FIELD_MAINT_OBS   ] = datos.get(i).getObservaciones();
            fila[ Config.FIELD_MAINT_ID_CLIENT ] = "" + datos.get(i).getIdCliente();
        
            modelo.addRow(fila); // Añade una fila al final del modelo de la tabla
        } 
        return modelo;
    }
    
    public DefaultTableModel modelAddColumn(DefaultTableModel modelo) {
        int i, largo = Config.NUMBER_OF_FIELDS_MAINT;
        
        modelo = super.modelAddColumn(modelo, 
                                      Config.MAINTENANCE_COLUMNS, 
                                      Config.NUMBER_OF_FIELDS_MAINT);
        return modelo;
    }    
    
    
}