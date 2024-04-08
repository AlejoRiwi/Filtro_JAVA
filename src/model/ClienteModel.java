package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cliente;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Cliente objCliente =(Cliente) object;

        try{
            String sql = "INSERT INTO cliente(nombre,apellido, email) VALUES(?,?,?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objCliente.getNombre());
            objPrepare.setString(2,objCliente.getApellido());
            objPrepare.setString(3,objCliente.getEmail());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objCliente.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Cliente Creado correctamente");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Cliente " + e.getMessage() + "No se puede añadir el cliente");
        }
        ConfigDB.closeConnection();
        return objCliente;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaClientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente ORDER BY cliente.id ASC;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Cliente objCliente = new Cliente();
                objCliente.setId(objResult.getInt("id"));
                objCliente.setNombre(objResult.getString("nombre"));
                objCliente.setApellido(objResult.getString("apellido"));
                objCliente.setEmail(objResult.getString("email"));

                listaClientes.add(objCliente);
            }
        } catch (SQLException e) {
            System.out.println("Error ->" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaClientes;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Cliente objCliente = (Cliente) object;
        boolean isUpdate = false;
        try{
            String sql = "UPDATE cliente SET nombre=?, apellido=?, email=? WHERE id=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objCliente.getNombre());
            objPrepare.setString(2,objCliente.getApellido());
            objPrepare.setString(3, objCliente.getEmail());
            objPrepare.setInt(4,objCliente.getId());

            int filasAfectadas = objPrepare.executeUpdate();


            if (filasAfectadas > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El cliente, " + objCliente.getNombre() + " Fue actualizado correctamente");

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        Cliente objCliente = (Cliente) object;
        boolean isDeleted = false;

        Connection objconnection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM cliente WHERE id = ?;";
            PreparedStatement objPrepare = objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objCliente.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El cliente, " + objCliente.getNombre() + " se elimino correctamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
