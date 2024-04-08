package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URLConnection;

public class ConfigDB {
    static Connection objConnection = null;
    public static Connection openConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/_CentroComercial";
            String user = "root";
            String password = "Rlwl2023.";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Conexion establecida sin errores!! ");
        }catch(ClassNotFoundException e){
            System.out.println("Error, Driver no instalado");
        }catch (SQLException e){
            System.out.println("Error >> No se pudo establecer conexion con la base de datos" + e.getMessage());
        }

        return objConnection;
    }

    public static void closeConnection () {
        try{
            if(objConnection != null) {
                objConnection.close();
            }
        }catch(SQLException e){
            System.out.println("Error > " + e.getMessage());
        }
    }

}
