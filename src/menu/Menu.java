package menu;

import controller.ClienteController;
import controller.CompraController;
import controller.ProductoController;
import controller.TiendaController;

import javax.swing.*;

public class Menu {


    TiendaController objTiendaController = new TiendaController ();
    public void mostrarMenu() {
        String[] opciones = {"Tiendas", "Productos", "Clientes", "Comprar", "Salir del sistema"};
        int seleccion;
        int select;
        do {
            seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Sistema Centro Comercial de Moda\n" +
                            "Seleccione la consulta a realizar dando click sobre su respectivo boton",
                    "Menu de inicio",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            switch (seleccion) {
                case 0:
                    System.out.println("Menu de tiendas");
                    do {
                        select = Integer.parseInt(JOptionPane.showInputDialog(null, "Sistema Centro Comercial de Moda\n" +
                                "1. Ver tiendas\n"+ "9. Salir del sistema"));
                        switch (select){
                            case 1:
                                TiendaController.getAll();
                                break;
                        }
                    } while (select != 9);
                    break;
                case 1:
                    System.out.println("Menu de Productos");
                    do {
                        select = Integer.parseInt(JOptionPane.showInputDialog(null, "Sistema Centro Comercial de Moda\n" +
                                "1. Agregar productos\n"+ "2. Listar todos los productos\n" + "3. Actualizar productos\n" + "4. Eliminar producto\n" +"5. para buscar por nombre\n" + "9. Salir del sistema"));
                        switch (select){
                            case 1:
                                ProductoController.create();
                                break;
                            case 2:
                                ProductoController.getAll();
                                break;
                            case 3:
                                ProductoController.update();
                                break;
                            case 4:
                                ProductoController.delete();
                                break;
                            case 5:
                                ProductoController.findByName();
                                break;
                        }
                    } while (select != 9);
                    break;
                case 2:
                    System.out.println("menu de Clientes");
                    do {
                        select = Integer.parseInt(JOptionPane.showInputDialog(null, "Sistema Centro Comercial de Moda\n" +
                                "1. Agregar clientes\n"+ "2. Listar todos los clientes\n" + "3. Actualizar clientes\n" + "4. Eliminar clientes\n" + "9. Salir del sistema"));
                        switch (select){
                            case 1:
                                ClienteController.create();
                                break;
                            case 2:
                                ClienteController.getAll();
                                break;
                            case 3:
                                ClienteController.update();
                                break;
                            case 4:
                                ClienteController.delete();
                                break;
                        }
                    } while (select != 9);
                    break;
                case 3:
                    System.out.println("Menu de Compras");
                    do {
                        select = Integer.parseInt(JOptionPane.showInputDialog(null, "Sistema Centro Comercial de Moda\n" +
                                "1. Agregar Compras\n"+ "2. Listar todas las compras\n" + "3. Actualizar compras\n" + "4. Eliminar compras\n" + "9. Salir del sistema"));
                        switch (select){
                            case 1:
                                CompraController.create();
                                break;
                            case 2:
                                CompraController.getAll();
                                break;
                            case 3:
                                CompraController.update();
                                break;
                            case 4:
                                CompraController.delete();
                                break;
                        }
                    } while (select != 9);
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Vuelve pronto");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");
                    break;
            }
        } while (seleccion != 5);
    }
}
