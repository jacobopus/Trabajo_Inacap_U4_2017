package dao_models;

import java.util.ArrayList;

/**
 * Establece las operaciones básicas del patron DAO
 * 
 * @author jacobopus
 */
public interface DAO_Operations {
    
    public boolean insert(Object mappedObj);
    public boolean update(Object mappedObj);
    public boolean delete(Object mappedObj);
    public boolean search(Object mappedObj);
    
    /**
     * Devuelve una lista de objetos de la tabla que corresponda.
     * 
     * @param <T>
     * @return
     */
    public <T> ArrayList<T> fetch();
    
}
