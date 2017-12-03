/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_models;

import config.Conexion;
import config.Config;
import data_models.Client;
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
public class DAO_Client extends DAO_Base implements DAO_Operations{
    Conexion connect = new Conexion();
    
    @Override
    public boolean insert(Object mappedObj) {
       PreparedStatement prpStm = null;
       Client cliente = (Client) mappedObj;
       String sql = this.sqlClientInsertFields();  
                     
       return this.grabarCliente(cliente, prpStm, sql, Config.MODO_APPEND);
    }

    @Override
    public boolean update(Object mappedObj) {
       PreparedStatement prpStm = null;
       Client cliente = (Client) mappedObj;
       String sql = this.sqlClientUpdateFields();  
       
       return this.grabarCliente(cliente, prpStm, sql, Config.MODO_EDIT);  
    }

    @Override
    public boolean delete(Object mappedObj) {
       boolean success = false;
       Client cliente = (Client) mappedObj;
       
       PreparedStatement prpStm = null;
       String sql = "delete from " + 
                    Config.CLIENTS + 
                    " where " + Config.CLIENTS_FIELDS[ Config.FIELD_ID ] + 
                    "=?";
         
        try {
            prpStm = this.connect.getConecction().prepareStatement(sql);
            
            prpStm.setInt( 1, cliente.getId());
            
            if (prpStm.executeUpdate()==1){
                success = true;
            }          
            prpStm.close();
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
        }
     
       return success;
    }

    @Override
    public ArrayList<Client> fetch() {
        ArrayList<Client> clientList = new ArrayList<>();        
        Client cliente;
        PreparedStatement prpStm = null; 
        ResultSet rs;
        
        String sql = "select * from " + Config.CLIENTS;
        try 
        {
           prpStm=this.connect.getConecction().prepareStatement(sql);
           
           rs = prpStm.executeQuery();
           while (rs.next())
           {  
               cliente = new Client(rs.getInt   ( Config.CLIENTS_FIELDS[0] ),
                                    rs.getString( Config.CLIENTS_FIELDS[1] ),
                                    rs.getString( Config.CLIENTS_FIELDS[2] ),
                                    rs.getString( Config.CLIENTS_FIELDS[3] ),
                                    rs.getString( Config.CLIENTS_FIELDS[4] ),
                                    rs.getString( Config.CLIENTS_FIELDS[5] ),
                                    0);
                cliente.setTipoVehiculoFromString(
                                    rs.getString( Config.CLIENTS_FIELDS[6] ));             
                clientList.add(cliente);
           }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
        }
        return clientList;
    }
    
    
  
    public Client get(Object mappedObj) {
       Client cliente = (Client) mappedObj;
       PreparedStatement prpStm = null; 
       ResultSet rs;
       String sql =""; //"select * from clientes where ppuVehiculoCliente=?";
       boolean searchCi = false, searchPpu = false, searchName =false;
       boolean resultado=false;
       int i=1;
        
       // Construye la cadena sql para permitir buscar por los campos especificados si es que estan presentes.
       if (!cliente.getNombre().isEmpty())
        {
            sql += Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_NAME ] + "=?";
        }
        if (!cliente.getCi().isEmpty())
        {
            if (!sql.isEmpty())
            {
                sql+=" and";
            }
            sql+=" " + Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_CI ] + "=?";
            searchCi = true;
        }
        if (!cliente.getPpu().isEmpty())
        {
            if (!sql.isEmpty())
            {
                sql+=" and";
            }
            sql+=" " + Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_PPU ] + "=?";
            searchPpu = true;
        }
        
        try 
        {
           sql = "select * from " + Config.CLIENTS + " where " + sql;
            
           prpStm=this.connect.getConecction().prepareStatement(sql);
           
           //Si los campos estaban presentes, 
           //los agrega al estamento sql para buscar.
           if (searchName)
           {
                prpStm.setString(i,cliente.getNombre());
                i++;
           }
           if (searchCi)
           {
                prpStm.setString(i,cliente.getCi());
                i++;
           }
           if (searchPpu)
           {
                prpStm.setString(i,cliente.getPpu());
                i++;
           }
           
           rs = prpStm.executeQuery();
           
           if (rs.next())
           {
               cliente.setId(
                       rs.getInt( Config.CLIENTS_FIELDS[ 0 ] ));
               cliente.setNombre(
                       rs.getString( 
                           Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_NAME ] ));
               cliente.setMail(
                       rs.getString( 
                           Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_MAIL ] ));
               cliente.setCi(
                       rs.getString( 
                           Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_CI ] ));
               cliente.setPpu(
                       rs.getString( 
                           Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_PPU ] ));
               cliente.setFono(rs.getString( 
                           Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_FONO ] ));
               cliente.setTipoVehiculoFromString(
                       rs.getString( 
                         Config.CLIENTS_FIELDS[ Config.FIELD_CLIENT_TIPO_V ] ));
               resultado = true;
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
        }       
        return cliente;
    }    
        
    
    
    private boolean grabarCliente(Client cliente, PreparedStatement prpStm, String sql, int modo)
    {                
        try
        {
            prpStm = this.connect.getConecction().prepareStatement(sql);
            prpStm.setString(1, cliente.getNombre().toUpperCase());
            prpStm.setString(2, cliente.getMail());
            prpStm.setString(3, cliente.getFono());
            prpStm.setString(4, cliente.getCi());
            prpStm.setString(5, cliente.getPpu().toUpperCase());
            prpStm.setString(6, cliente.tipoVehiculoToString().toUpperCase());
            
            if (modo == Config.MODO_EDIT)
            {
                prpStm.setInt(7, cliente.getId());
                
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
    
    private String sqlClientInsertFields() {
        int i, largo = Config.NUMBER_OF_FIELDS_CLIENT;
        String sql = "insert into " + 
                     Config.CLIENTS + 
                     " (" +
                     this.addFieldsToSql("") +
                     ") values (?,?,?,?,?,?)";
        return sql;
    }
    
    private String sqlClientUpdateFields() {
        String sql = "update " + 
                     Config.CLIENTS + 
                     " set " +
                     this.addFieldsToSql("=?");
        
        sql += " where " + Config.CLIENTS_FIELDS[0] + "=?";       // where id_clientes=  
        return sql;
    }
    
    
    private String addFieldsToSql(String agregado) {
        String sql = "";
        int i, largo = Config.NUMBER_OF_FIELDS_CLIENT;
        
        for(i=1;i<largo;i++)
        {
            sql += Config.CLIENTS_FIELDS[i] + agregado + ",";
        }  
        return sql.substring(0, sql.length()-1);
    }

    @Override
    public boolean search(Object mappedObj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public DefaultTableModel prepareTableModel() {
        DefaultTableModel modelo;
        DAO_Client daoClientes = new DAO_Client();
        ArrayList<Client> clientesList;
                
        modelo = getDefaultModel( false );
        
        clientesList = daoClientes.fetch();
        modelo = this.modelAddColumn( modelo );
        
        modelo = this.fillTableRows( modelo, clientesList );
        
        return modelo;
    }
    
    public DefaultTableModel fillTableRows( DefaultTableModel modelo, ArrayList<Client> datos ) {
        String[] fila = new String[ Config.NUMBER_OF_FIELDS_CLIENT ]; 
        int i, cuantos = datos.size();    
    
        for(i=0;i<cuantos;i++)
        {       
            fila[0] = ""+datos.get(i).getId();
            fila[1] = datos.get(i).getNombre();
            fila[2] = datos.get(i).getCi();
            fila[3] = datos.get(i).getMail();
            fila[4] = datos.get(i).getFono();
            fila[5] = datos.get(i).getPpu();
            fila[6] = datos.get(i).tipoVehiculoToString();
        
            modelo.addRow(fila); // Añade una fila al final del modelo de la tabla
        } 
        return modelo;
    }
    
    public DefaultTableModel modelAddColumn(DefaultTableModel modelo) {
        int i, largo = Config.NUMBER_OF_FIELDS_CLIENT;
        
        modelo = super.modelAddColumn(modelo, 
                                      Config.CLIENTS_COLUMNS, 
                                      Config.NUMBER_OF_FIELDS_CLIENT);
        return modelo;
    }
        
    
}
