package model;

import database.CRUD;
import database.ConfigDB;
import entity.Producto;
import entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoModel implements CRUD {

    @Override
    public Object insert(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Producto objProducto =(Producto) object;
        Tienda objEspecialidad = new Tienda();

        try{
            String sql = "INSERT INTO producto(nombre,precio, id_tienda, stock) VALUES(?,?,?,?);";

            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objProducto.getNombre());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while(objResult.next()){
                objProducto.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Producto Creado correctamente");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding producto " + e.getMessage() + "No se puede añadir el producto");
        }
        ConfigDB.closeConnection();
        return objProducto;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaProductos = new ArrayList<>();

        try{
            String sql = "SELECT * FROM producto INNER JOIN tienda ON tienda.id = producto.id_tienda ORDER BY producto.id ASC;";
            PreparedStatement objPrepareStatement = (PreparedStatement) objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepareStatement.executeQuery();

            while (objResult.next()){
                Producto objProducto = new Producto();
                Tienda objTienda = new Tienda();

                objProducto.setId(objResult.getInt("id"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setStock(objResult.getInt("stock"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));

                objTienda.setId(objResult.getInt("tienda.id"));
                objTienda.setNombre(objResult.getString("tienda.nombre"));
                objTienda.setUbicacion(objResult.getString("tienda.ubicacion"));

                objProducto.setObjTienda(objTienda);
                listaProductos.add(objProducto);

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaProductos;
    }


    public List<Object> findByName(String name) {
        List<Object> listaProducto = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        Producto objProducto = null;


        try{
            String sql = "SELECT * FROM producto WHERE producto.nombre LIKE ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1,"%"+name+"%");

            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()) {
                objProducto = new Producto();
                Tienda objTienda = new Tienda();
                objProducto.setId(objResult.getInt("id"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));

                objProducto.setObjTienda(objTienda);
                listaProducto.add(objProducto);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listaProducto;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Producto objProducto = (Producto) object;
        boolean isUpdate = false;
        try{
            String sql = "UPDATE producto SET nombre=?, precio=?, id_tienda=?, stock=? WHERE id=?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1,objProducto.getNombre());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3, objProducto.getId_tienda());
            objPrepare.setInt(4, objProducto.getStock());
            objPrepare.setInt(5,objProducto.getId());

            int filasAfectadas = objPrepare.executeUpdate();


            if (filasAfectadas > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El producto, " + objProducto.getNombre() + " Fue actualizado correctamente");

            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error > " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object object) {
        Producto objProducto = (Producto) object;
        boolean isDeleted = false;

        Connection objconnection = ConfigDB.openConnection();

        try{
            String sql = "DELETE FROM producto WHERE id = ?;";
            PreparedStatement objPrepare = objconnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setInt(1, objProducto.getId());

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "El producto " + objProducto.getNombre() + " se elimino Correctamente");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }
}

// Falta buscar por nombre o tienda

