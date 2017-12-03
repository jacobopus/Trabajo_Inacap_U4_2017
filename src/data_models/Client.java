package data_models;

import config.Config;
import java.util.ArrayList;

/**
 * Client.java
 * Modelo de datos de la entidad Cliente.
 * 
 * Provee funcionalidad básica para el manejo de entidades cliente.
 * Para una futura revisión, o en el caso de un proyecto del "mundo real",
 * debería considerarse la posibilidad de crear una clase rut que aporte con funcionalidades 
 * de validación, e integrarla en esta clase vía colaboración.
 * 
 * @author jacobopus
 */
public class Client {
    private String[] tipos = Config.TIPOS_VEHICULO;
    private int id;
    private String nombre;
    private String ci;
    private String mail;
    private String fono;
    private String ppu;
    private int tipoVehiculo;
    
    private ArrayList<Maintenance> mL;

    public Client() {
    }

    public Client(String nombre, String ci, String mail, String fono, String ppu, int tipoVehi) {
        this.nombre = nombre;
        this.ci = ci;
        this.mail = mail;
        this.fono = fono;
        this.ppu = ppu;
        this.tipoVehiculo = tipoVehi;
    }

    public Client(int id, String nombre, String ci, String mail, String fono, String ppu, int tipoVehi) {
        this.id = id;
        this.nombre = nombre;
        this.ci = ci;
        this.mail = mail;
        this.fono = fono;
        this.ppu = ppu;
        this.tipoVehiculo = tipoVehi;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCi() {
        return ci;
    }

    public String getMail() {
        return mail;
    }

    public String getFono() {
        return fono;
    }

    public String getPpu() {
        return ppu;
    }
    
    public String tipoVehiculoToString()
    {
        return this.tipos[this.tipoVehiculo];
    }    

    public void setTipoVehiculoFromString(String tipo)
    {
        int i;
        for(i=0;i<this.tipos.length;i++)
        {
            if(tipos[i].trim().toUpperCase().equals(tipo.toUpperCase()))
            {
                this.tipoVehiculo =i;
                break;
            }
        }    
    }
    public int getTipoVehiculo() {
        return tipoVehiculo;
    }
    
    /**
     *
     * @return MaintenanceList
     */
    public ArrayList<Maintenance> getMaintenanceList(){
        return this.mL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public void setPpu(String ppu) {
        this.ppu = ppu;
    }

    public void setTipoVehiculo(int tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public void setMaintenanceList(ArrayList<Maintenance> mL) {
        this.mL = mL;
    }
    
    
    public void addMaintenance(Maintenance m){
        this.mL.add(m);
    }
    
    public void removeMaintenance(Maintenance m){
        this.mL.remove(m);
    }
    
    public Maintenance searchMaintenance(int id){
        int i, largo = this.mL.size();
        Maintenance m;
        
        for(i=0;i<largo;i++)
        {
            m = this.mL.get(i);
            if(m.getId() == i)
            {
                return m;
            }    
        }
        m = new Maintenance(Config.NO_ENCONTRADO, Config.NO_ENCONTRADO, "", "", "");
        return m;
    }
     
    
    @Override
    public String toString()
    {
        return "ID          " + this.id + "\n" +
               "NOMBRE      " + this.nombre + "\n" +
               "CI          " + this.ci + "\n" +
               "MAIL        " + this.mail + "\n" +
               "FONO        " + this.fono + "\n" +
               "PPU         " + this.ppu + "\n" +
               "VEHICULO    " + this.tipoVehiculoToString() + "\n\n" +
               this.maintenancesToString(); 
    }
    
    public String toStringWithoutMantainances() {
        return "ID          " + this.id + "\n" +
               "NOMBRE      " + this.nombre + "\n" +
               "CI          " + this.ci + "\n" +
               "MAIL        " + this.mail + "\n" +
               "FONO        " + this.fono + "\n" +
               "PPU         " + this.ppu + "\n" +
               "VEHICULO    " + this.tipoVehiculoToString() + "\n\n";
    }
    
    public String maintenancesToString(){
        int i, largo = this.mL.size();
        String retorno = "";
        
        for(i=0;i<largo;i++)
        {
            retorno += this.mL.get(i).toString() + "\n";
        }
        
        if (!retorno.isEmpty())
        {
            retorno = "LISTA DE MANTENCIONES \n\n" + retorno;
        }    
        return retorno;
    } 
    
    
}
