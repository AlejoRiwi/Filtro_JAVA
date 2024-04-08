package controller;

import Util.Utils;
import entity.Producto;
import entity.Tienda;
import model.ProductoModel;

import javax.swing.*;
import java.util.List;

public class ProductoController {
    TiendaController objTiendaController = new TiendaController();
    ProductoModel objProductoModel;

    public ProductoController() {
        objProductoModel = new ProductoModel();
    }

    public static ProductoModel instanceModel() {
        return new ProductoModel();
    }

    public static void create() {
        String nombre = JOptionPane.showInputDialog(null, "Escribe el nombre del Producto");
        Double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "Escribe el precio del producto"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Escribe el stock del producto"));

        Object[] opcionesTienda = Utils.lisToArray(TiendaController.instanciaModel().findAll());

        Tienda objTienda = (Tienda) JOptionPane.showInputDialog(null, "Selecciona una Tienda", "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesTienda,
                opcionesTienda[0]);

        instanceModel().insert(new Producto(nombre, precio,stock, objTienda.getId(), objTienda));
    }

    public static void getAll() {
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> listObject) {
        String list = "-- Lista de Productos --\n";
        for (Object obj : listObject) {
            Producto objProducto = (Producto) obj;

            list += objProducto.toString() + "\n";
        }
        return list;
    }

    public static void findByName() {
        String listaProductos = "-- Lista productos --\n";

        String isName = JOptionPane.showInputDialog(null, "Ingresa el nombre del producto");

        String list  = getAll(instanceModel().findByName(isName));

        JOptionPane.showMessageDialog(null,list);
    }

    public static void update() {
        Object[] opciones = Utils.lisToArray(instanceModel().findAll());


        Producto objProducto = (Producto) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        String nombre = JOptionPane.showInputDialog(null, "ingresa el nuevo nombre del producto->", objProducto.getNombre());

        Double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "ingresa el precio nuevo del producto->", objProducto.getPrecio()));
        int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "ingresa el stock nuevo del producto->", objProducto.getStock()));

        objProducto.setNombre(nombre);
        objProducto.setPrecio(precio);
        objProducto.setStock(stock);

        Object[] opcionesTiendas = Utils.lisToArray(TiendaController.instanciaModel().findAll());

        Tienda objTienda = (Tienda) JOptionPane.showInputDialog(null, "Selecciona la tienda del producto agregada", "", JOptionPane.QUESTION_MESSAGE, null, opcionesTiendas, opcionesTiendas[0]);

        objProducto.setId_tienda(objTienda.getId());
        instanceModel().update(objProducto);
    }

    public static void delete() {
        Object [] opciones =Utils.lisToArray(instanceModel().findAll());
        Producto objProducto = (Producto) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto que quiere eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        instanceModel().delete(objProducto);
    }
}
