package controller;

import Util.Utils;
import entity.Cliente;
import entity.Compra;
import entity.Producto;
import model.ClienteModel;
import model.CompraModel;
import model.ProductoModel;

import javax.swing.*;
import java.util.List;

public class CompraController {
    ClienteController objClienteController = new ClienteController();
    Cliente objCliente = new Cliente();
    ClienteModel objClienteModel = new ClienteModel();
    ProductoController objProductoController = new ProductoController();
    Producto objProducto = new Producto();
    ProductoModel objProductoModel = new ProductoModel();

    CompraModel objCompraModel;

    public CompraController() {
        objCompraModel = new CompraModel();
    }
    public static CompraModel instanceModel() {
        return new CompraModel();
    }

    public static void create() {

        Object[] opcionesCliente = Utils.lisToArray(ClienteController.instanceModel().findAll());

        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(null, "Selecciona un cliente", "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesCliente,
                opcionesCliente[0]);

        Object[] opcionesProductos = Utils.lisToArray(ProductoController.instanceModel().findAll());

        Producto objProducto = (Producto) JOptionPane.showInputDialog(null, "Selecciona un producto", "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionesProductos,
                opcionesProductos[0]);

        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la cantidad del producto "));

        instanceModel().insert(new Compra(objCliente.getId(),objCliente,objProducto.getId(),objProducto, cantidad));
    }


    public static void getAll() {
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll (List<Object> listObject){
        String list = "-- Lista de Compras --\n";
        for (Object obj : listObject) {
            Compra objCompra = (Compra) obj;

            list += objCompra.toString() + "\n";
        }
        return list;
    }

    public static void update () {
        Compra objCompra = new Compra();
        Object[] opcionCliente = Utils.lisToArray(ClienteController.instanceModel().findAll());

        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionCliente,
                opcionCliente[0]
        );

        Object[] opcionProducto = Utils.lisToArray(ProductoController.instanceModel().findAll());

        Producto objProducto = (Producto) JOptionPane.showInputDialog(
                null,
                "Seleccione el producto que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcionProducto,
                opcionProducto[0]
        );

        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "ingresa cantidad del producto ->", objCompra.getCantidad()));

        objCompra.setId_cliente(objCliente.getId());
        objCompra.setId_producto((objProducto.getId()));
        objCompra.setCantidad(cantidad);

        instanceModel().update(objCompra);
    }

    public static void delete() {
        Object [] opciones =Utils.lisToArray(instanceModel().findAll());
        Compra objCompra = (Compra) JOptionPane.showInputDialog(
                null,
                "Seleccione la compra que quiere eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        instanceModel().delete(objCompra);
    }

}
