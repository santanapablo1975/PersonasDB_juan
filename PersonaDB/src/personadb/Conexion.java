package personadb;


import java.sql.Connection;
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pc12
 */
public class Conexion {
    
    //herencia para llmar la base de datos
    
    protected Connection cnn;
    
    protected void Conectar(){
       try {
            Class.forName("com.mysql.jdbc.Driver");
          
               cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mascotas", "root", "");
  
          
           
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }   
    
    protected void DesConectar(){
        
        try {
            cnn.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }   
    
    
}
