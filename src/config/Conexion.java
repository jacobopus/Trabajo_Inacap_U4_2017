package config;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion 
{
    private String USERNAME  = Config.USERNAME;
    private String PASSWORD  = Config.PASSWORD; 
    private String HOST      = Config.HOST;
    private String PORT      = Config.PORT;
    private String DATABASE  = Config.DATABASE;
    private String CLASSNAME = Config.CLASSNAME;
    private String URL       = Config.URL;
    
    private Connection con;

    public Conexion() 
    {
        try
        {
            Class.forName(CLASSNAME);
            con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            // JOptionPane.showMessageDialog(null,"Conexión EXITOSA");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"ERROR:\n"+e);
        }
    }
    
    public Connection getConecction()
    {
        return con;
    }
    
    public static void main(String[] args) 
    {
        Conexion c=new Conexion();                
    }
}
