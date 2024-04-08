package controller;


import entity.Tienda;
import model.TiendaModel;

import javax.swing.*;
import java.util.List;

public class TiendaController {
    TiendaModel objTiendaModel;
    public TiendaController(){
        this.objTiendaModel = new TiendaModel();
    }
    public static TiendaModel instanciaModel() {
        return new TiendaModel();
    }

    public static void getAll() {
        String list = getAll(instanciaModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> lisObject) {
        String list = " --- Lista de aviones ---\n";
        for (Object obj : lisObject) {
            Tienda objTienda = (Tienda) obj;
            list += objTienda.toString() + "\n";
        }
        return list;
    }
}
