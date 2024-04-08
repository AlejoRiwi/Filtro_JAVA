package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cliente;
import entity.Compra;
import entity.Producto;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraModel implements CRUD {
    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Compra objCompra =(Compra) object;
        // Especialidad objEspecialidad = new Especialidad();

        try{
            String sql = "INSERT INTO compra (id_cliente, id_producto, cantidad) VALUES(?,?,?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objCompra.getId_cliente());
            objPrepare.setInt(2,objCompra.getId_producto());
            objPrepare.setInt(3,objCompra.getCantidad());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objCompra.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Medico Creado correctamente");
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding Medico " + e.getMessage() + "No se puede añadir el medico");
        }
        ConfigDB.closeConnection();
        return objCompra;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listCompras = new ArrayList<>();

        try{
            String sql = "SELECT * FROM compra INNER JOIN cliente ON cliente.id = compra.id_cliente" +
                    " INNER JOIN producto ON producto.id = compra.id_producto ORDER BY compra.id ASC;";
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepareStatement.executeQuery();

            while (objResult.next()){
                Compra objCompra = new Compra();
                Cliente objCliente = new Cliente();
                Producto objProducto = new Producto();

                objCompra.setId(objResult.getInt("id"));
                objCompra.setFecha_compra(objResult.getString("fecha_compra"));
                objCompra.setId_cliente(objResult.getInt("id_cliente"));
                objCompra.setId_producto(objResult.getInt("id_producto"));
                objCompra.setCantidad(objResult.getInt("cantidad"));

                objCliente.setId(objResult.getInt("cliente.id"));
                objCliente.setNombre(objResult.getString("cliente.nombre"));
                objCliente.setApellido(objResult.getString("cliente.apellido"));
                objCliente.setEmail(objResult.getString("cliente.email"));

                objProducto.setId(objResult.getInt("producto.id"));
                objProducto.setNombre(objResult.getString("producto.nombre"));
                objProducto.setPrecio(objResult.getDouble("producto.precio"));
                objProducto.setId_tienda(objResult.getInt("producto.id_tienda"));

                objCompra.setObjCliente(objCliente);
                objCompra.setObjProducto(objProducto);
                listCompras.add(objCompra);

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCompras;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Compra objCompra = (Compra) object;
        boolean isUpdate = false;
        try{
            String sql = "UPDATE compra SET id_cliente=?, id_producto=?, cantidad=? WHERE id=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1,objCompra.getId_cliente());
            objPrepare.setInt(2,objCompra.getId_producto());
            objPrepare.setInt(3, objCompra.getCantidad());
            objPrepare.setInt(4,objCompra.getId());

            int filasAfectadas = objPrepare.executeUpdate();


            if (filasAfectadas > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "Fue actualizado, " + objCompra.getObjProducto().getNombre() + " en el menu de Compras");

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        Compra objCompra = (Compra) object;
        boolean isDeleted = false;

        Connection objconnection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM compra WHERE id = ?;";
            PreparedStatement objPrepare = objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objCompra.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Fue eliminado el producto " + objCompra.getObjProducto().getNombre() + " desde compras");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
}
