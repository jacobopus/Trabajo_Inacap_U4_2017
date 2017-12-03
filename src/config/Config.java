/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author jacobopus
 */
public interface Config {
    public final String   CLIENTS  = "cliente";
    public final String[] CLIENTS_FIELDS = {"id_cliente", 
                                           "nombre_cliente", 
                                           "mail_cliente", 
                                           "fono_cliente", 
                                           "ci_cliente",
                                           "ppu_cliente",
                                           "tipo_vehiculo"};
    public final String[] CLIENTS_COLUMNS = {"ID", 
                                             "NOMBRE", 
                                             "MAIL",
                                             "FONO",
                                             "RUT",
                                             "PPU",
                                             "VEHICULO"};
    public final int FIELD_ID = 0;
    public final int FIELD_CLIENT_NAME   = 1;
    public final int FIELD_CLIENT_MAIL   = 2;
    public final int FIELD_CLIENT_FONO   = 3;
    public final int FIELD_CLIENT_CI     = 4;
    public final int FIELD_CLIENT_PPU    = 5;
    public final int FIELD_CLIENT_TIPO_V = 6;
    public final int NUMBER_OF_FIELDS_CLIENT = 7;
    
    public final String   MAINTENANCE  = "mantencion";
    public final String[] MAINTENANCE_FIELDS = {"id_mantencion",
                                                "fecha",
                                                "labor_realizada",
                                                "observaciones",
                                                "cliente_idcliente"};
    public final String[] MAINTENANCE_COLUMNS = {"ID",
                                                 "FECHA",
                                                 "LABOR",
                                                 "OBS.",
                                                 "CLIENTE"};
    public final int FIELD_MAINT_FECHA   = 1;
    public final int FIELD_MAINT_LABOR   = 2;
    public final int FIELD_MAINT_OBS   = 3;
    public final int FIELD_MAINT_ID_CLIENT     = 4; 
    public final int NUMBER_OF_FIELDS_MAINT = 5;
    
    public final String[] TIPOS_VEHICULO = {"AUTOMÓVIL", 
                                           "CAMIONETA", 
                                           "FURGÓN", 
                                           "CAMIÓN"};
    public final int NUMBER_OF_FIELDS_MAINTENANCE = 5;
    
    public final String USERNAME ="root";
    public final String PASSWORD ="mariafeliz"; 
    public final String HOST     ="localhost";
    public final String PORT     ="3306";
    public final String DATABASE ="TallerMecanico";
    public final String CLASSNAME="com.mysql.jdbc.Driver";
    public final String URL      ="jdbc:mysql://"+HOST+"/"+DATABASE;
    
    public final int MODO_APPEND = 0;
    public final int MODO_EDIT   = 1;
    public final int MODO_DELETE = 2;
    public final int MODO_SHOW   = 3;
    
    public final int OPERATION_SUCCESS = 1;
    
    /**
     *
     */
    public final int NO_ENCONTRADO = -15000;
    public final boolean DISPLAY_INFO = true;
    public final boolean NO_DISPLAY_INFO = false;
    
    public final boolean DIALOG_MODAL = true;
    
    public final int DIALOG_YES = 0;
}
