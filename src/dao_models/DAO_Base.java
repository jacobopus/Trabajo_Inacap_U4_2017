package dao_models;

import javax.swing.table.DefaultTableModel;

/**
 * Contiene utilidades para ser usadas por las clases hijas 
 * que implementan el patron DAO
 * 
 * @author jacobopus
 */
public class DAO_Base {
    
    /**
     * Devuelve un modelo básico para ser usado con la JTable que corresponda.
     * 
     * @param isEditable
     * @return 
     */
    public DefaultTableModel getDefaultModel( boolean isEditable) {
        DefaultTableModel modelo;
                      
        modelo = new DefaultTableModel() 
                {
                    @Override
                    public boolean isCellEditable(int fila, int columna) 
                    {
                        return isEditable; 
                    }
                };
    return modelo;
    }   
    
    /**
     * Agrega las columnas de la tabla que corresponda al modelo
     * 
     * @param modelo
     * @param fields
     * @param howFields
     * @return 
     */
    public DefaultTableModel modelAddColumn(DefaultTableModel modelo, String[] fields, int howFields) {
        int i;
        for(i=0;i<howFields;i++)
        {
            modelo.addColumn( fields[ i ]);
        }
        return modelo;
    }  
    
}
