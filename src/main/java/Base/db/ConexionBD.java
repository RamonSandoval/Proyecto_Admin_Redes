package Base.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

import java.sql.*;

public class ConexionBD {


    static String bd="redes";
    static String login="root";
    static String password="";
    static String url="jdbc:mysql://localhost/"+ bd;
    Connection con=null;

    public ConexionBD() {
        try{

            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url, login ,password);

            if(con !=null){
                System.out.println("Connecting database ["+con+"] Conexion establecida exitosamente");
            }

        } catch (SQLException e) { //excepción generada por la conexión
            System.out.println("excepción conexión "+ e.getMessage());

        }catch(ClassNotFoundException e)//excepción generada por no encontrar el driver
        {
            System.out.println("excepción driver "+ e.getMessage());

        }
    }

    public Connection getCon() {
        return con;
    }

    public void disconnect(){
        System.out.println("clocking database: ["+con+"]ok");
        if(con!=null)
        {
            try{
                con.close();


            }catch(SQLException e)
            {
                System.out.println(e);
            }
        }
    }
}