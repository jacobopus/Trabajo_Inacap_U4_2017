/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_models;

/**
 *
 * @author jacobopus
 */
public class Maintenance {
    private int id;
    private int idCliente;
    private String fecha;
    private String laborRealizada;
    private String observaciones;

    public Maintenance() {
    }
    
    public Maintenance(int idCliente, String fecha, String laborRealizada, String observaciones) {
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.laborRealizada = laborRealizada;
        this.observaciones = observaciones;
    }

    public Maintenance(int id, int idCliente, String fecha, String laborRealizada, String observaciones) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.laborRealizada = laborRealizada;
        this.observaciones = observaciones;
    }

    
    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getLaborRealizada() {
        return laborRealizada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setLaborRealizada(String laborRealizada) {
        this.laborRealizada = laborRealizada;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String toString(){
        return this.fecha + "     " + 
               this.laborRealizada + "      " +
               this.observaciones + "\n";
               
    }
    
}
