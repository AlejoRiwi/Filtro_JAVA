package model;

import database.CRUD;
import database.ConfigDB;
import entity.Tienda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TiendaModel implements CRUD {


    @Override
    public Object insert(Object object) {
        return null;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listaTiendas = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tienda ORDER BY tienda.id ASC;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Tienda objTienda = new Tienda();
                objTienda.setId(objResult.getInt("id"));
                objTienda.setNombre(objResult.getString("nombre"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));

                listaTiendas.add(objTienda);
            }
        } catch (SQLException e) {
            System.out.println("Error ->" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaTiendas;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }
}
