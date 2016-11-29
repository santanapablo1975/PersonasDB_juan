/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pc12
 */
//herencia
public class PersonaAdmin extends Conexion {

    public List<PersonaModelo> Consultar() {

        List<PersonaModelo> lista = new ArrayList<>();

        Conectar();

        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `ConsultarPesona`()");
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                PersonaModelo modelo = new PersonaModelo();
                modelo.setIdpersona(resultado.getInt(1));
                modelo.setNombre(resultado.getString(2));
                modelo.setApeliido(resultado.getString(3));
                lista.add(modelo);
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            DesConectar();

        }
        return lista;

    }

    public void Guardar(PersonaModelo modelo) {
        Conectar();
        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `InsertarPersona`(?,?)");
            sentencia.setString(1, modelo.getNombre());
            sentencia.setString(2, modelo.getApeliido());
            int resultado = sentencia.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Persona Guardada");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Persona NO Guardada");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DesConectar();
        }

    }

    public void Actualizar(PersonaModelo modelo) {
        Conectar();
        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `ActualizarPersona`(?,?,?)");
            sentencia.setInt(1, modelo.getIdpersona());
            sentencia.setString(2, modelo.getNombre());
            sentencia.setString(3, modelo.getApeliido());

            int resultado = sentencia.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Persona Guardada");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Persona NO Guardada");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DesConectar();
        }

    }

    public void Eliminar(PersonaModelo modelo) {
        Conectar();
        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `BorrarPersona`(?)");
            sentencia.setInt(1, modelo.getIdpersona());

            int resultado = sentencia.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Persona Borrada");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Persona NO Borrada");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DesConectar();
        }

    }

}
