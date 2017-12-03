/*
 * Main.java
 * 
 */
package main;

import java.awt.Color;
import views.ClientView;
/**
 * README
 * 
 * TRABAJO PROGRAMACIÓN ORIENTADO OBJETOS
 * PROYECTO MYCAR
 * 
 * ANALISTA PROGRAMADOR
 * INACAP 
 * SECCIÓN 221 VESPERTINO
 * 2° SEMESTRE 2017 
 * 
 * PROFESOR JAVIER GUTIÉRREZ
 * 
 * Aprendizaje esperado  
 * 4.1.- Integra el diseño de interfaces gráficas de usuario en el desarrollo de aplicaciones de software. 
 * 5.1.- Desarrolla una aplicación de software que interactúe con un motor de base de datos. 
 * 
 * config/Config.java
 *                      Archivo de configuración
 * 
 * config/Conexion.java
 *                      Constantes con la configuración para la conección a base de datos
 * 
 * assets/sql/tallermecanico.sql
 *                      dump de base de datos con datos de prueba, para importar
 * 
 * @author jacobopus
 */
public class Main {
    public static void main(String[] args) {
        ClientView mV = new ClientView();
        mV.setLocationRelativeTo(null);
        mV.setVisible(true);
        mV.pack();
    }
}