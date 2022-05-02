package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    // propiedades agregadas -> ?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true
    public static final String JDBC_URL_MYSQL = "jdbc:mysql://localhost:3306/controlclientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "bola0925";
    
    // realiza la conexión a la base de datos
    public static Connection getConexion() {
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(JDBC_URL_MYSQL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("--> *** Conexión exitosa a la base de datos.");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return cn;
    }
    
    // cierra los objetos al terminar la transacción
    public static void close(Connection cn) {
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(PreparedStatement stm) {
        try {
            stm.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(ResultSet cn) {
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}
