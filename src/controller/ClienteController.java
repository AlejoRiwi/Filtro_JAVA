package controller;

import Util.Utils;
import entity.Cliente;
import entity.Producto;
import model.ClienteModel;

import javax.swing.*;
import java.util.List;

public class ClienteController {
    ClienteModel objClienteModel;

    public ClienteController() {
        objClienteModel = new ClienteModel();
    }

    public static ClienteModel instanceModel() {
        return new ClienteModel();
    }

    public static void create() {
        String nombre = JOptionPane.showInputDialog(null, "Escribe el nombre del cliente");
        String apellido = JOptionPane.showInputDialog(null, "Escribe el apellido del cliente");
        String email = JOptionPane.showInputDialog(null, "Escribe el email del cliente");


        instanceModel().insert(new Cliente(nombre, apellido, email));
    }

    public static void getAll() {
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> listObject) {
        String list = "-- Lista de Clientes --\n";
        for (Object obj : listObject) {
            Cliente objCliente = (Cliente) obj;

            list += objCliente.toString() + "\n";
        }
        return list;
    }

    public static  void update () {
        Object[] opciones = Utils.lisToArray(instanceModel().findAll());


        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente que quiere actualizar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        String nombre = JOptionPane.showInputDialog(null, "ingresa el nuevo nombre del cliente->", objCliente.getNombre());

        String apellido = JOptionPane.showInputDialog(null, "ingresa el nuevo apellido del cliente->", objCliente.getApellido());

        String email = JOptionPane.showInputDialog(null, "ingresa el nuevo email del cliente->", objCliente.getEmail());

        objCliente.setNombre(nombre);
        objCliente.setApellido(apellido);
        objCliente.setEmail(email);

        instanceModel().update(objCliente);
    }

    public static void delete() {
        Object [] opciones =Utils.lisToArray(instanceModel().findAll());
        Cliente objCliente = (Cliente) JOptionPane.showInputDialog(
                null,
                "Seleccione el cliente que quiere eliminar",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );
        instanceModel().delete(objCliente);
    }

}
