package datos;

import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC {

    //constantes con los instrucciones(Query's) para la interaccion con la BD
    private static final String SQL_SELECT = "SELECT idCliente, nombre, apellido, email, telefono, saldo FROM cliente";
    private static final String SQL_SELECT_BY_ID = "SELECT idCliente, nombre, apellido, email, telefono, saldo FROM cliente WHERE idCliente = ?";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ?  WHERE idCliente = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE idCliente = ?";

    //no recibe parametros y regresa una lista de objetos de tipo "Cliente"
    public List<Cliente> getAll() {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            cn = Conexion.getConexion();
            stm = cn.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();

            //mientras se encuntran resultados
            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                //nuevo objeto
                cliente = new Cliente(id, nombre, apellido, email, telefono, saldo);
                //agregar el objeto a la lista
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return clientes;
    }

    // recibe como parametro a un objeto de tipo Cliente, solo con el "id"
    public Cliente getById(Cliente cliente) {
        Connection cn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            stm = cn.prepareStatement(SQL_SELECT_BY_ID);
            stm.setInt(1, cliente.getId());
            rs = stm.executeQuery();

            while (rs.next()) {                
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                //ingresa los datos obtenidos al cliente que se recibe como parametro
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEmail(email);
                cliente.setTelefono(telefono);
                cliente.setSaldo(saldo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return cliente;
    }

    // recibe como parametro a un objeto de tipo Cliente, con todos los datos a excepcion del "id"
    public int insert(Cliente cliente) {
        Connection cn = null;
        PreparedStatement stm = null;
        int rowsAffected = 0; //indica si se inserto un nuevo registro(fila) en la BD

        try {
            cn = Conexion.getConexion();
            stm = cn.prepareStatement(SQL_INSERT);

            //envia los datos correspondientes
            stm.setString(1, cliente.getNombre());
            stm.setString(2, cliente.getApellido());
            stm.setString(3, cliente.getEmail());
            stm.setString(4, cliente.getTelefono());
            stm.setDouble(5, cliente.getSaldo());

            //ejecuta el query
            rowsAffected = stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return rowsAffected;
    }

    // recibe como parametro a un objeto de tipo Cliente, con todos los datos
    public int update(Cliente cliente) {
        Connection cn = null;
        PreparedStatement stm = null;
        int rowsAffected = 0; //indica si se modifico un registro(fila) en la BD

        try {
            cn = Conexion.getConexion();
            stm = cn.prepareStatement(SQL_UPDATE);

            //envia los datos correspondientes
            stm.setString(1, cliente.getNombre());
            stm.setString(2, cliente.getApellido());
            stm.setString(3, cliente.getEmail());
            stm.setString(4, cliente.getTelefono());
            stm.setDouble(5, cliente.getSaldo());
            stm.setInt(6, cliente.getId());

            //ejecuta el query
            rowsAffected = stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return rowsAffected;
    }

    // recibe como parametro a un objeto de tipo Cliente, solo con el "id"
    public int delete(Cliente cliente) {
        Connection cn = null;
        PreparedStatement stm = null;
        int rowsAffected = 0; //indica si se elimino un registro(fila) en la BD

        try {
            cn = Conexion.getConexion();
            stm = cn.prepareStatement(SQL_DELETE);

            //envia los datos correspondientes            
            stm.setInt(1, cliente.getId());

            //ejecuta el query
            rowsAffected = stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return rowsAffected;
    }
}
