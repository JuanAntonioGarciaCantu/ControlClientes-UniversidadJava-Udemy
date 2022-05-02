package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// nombre del servlet para ser llamado
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet { 
    
    //procesa las peticiones de tipo GET(parametros enviados por URL)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtener la acción que se va realizar
        String accion = request.getParameter("accion");
        
        //comprueba que la accion no sea vacia ni nula
        if (accion != null) {
            switch(accion) {
                case "editar":
                    editar(request, response);
                    break;
                case "eliminar":
                    eliminar(request, response);
                    break;
                default:
                    accionDefault(request, response);
            }
        } else {            
            accionDefault(request, response);
        }
    }
    
    //procesa las peticiones de tipo GET(envio de formularios)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //obtener la acción que se va realizar
        String accion = request.getParameter("accion");
        
        switch(accion) {
            case "insertar":
                insertar(request, response);
                break;
            case "modificar":
                modificar(request, response);
                break;
            default:
                accionDefault(request, response);
        }
    }
    
    //muestra la lista de los clientes(JSP principal) 
    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //obtiene todos los clientes 
        List<Cliente> clientes = new ClienteDaoJDBC().getAll(); 
        
        //imprime la lista en consola
        System.out.println("clientes = " + clientes);
        
        //Asigna las variables en el alcance de Sesión
        HttpSession sesion = request.getSession();  //creación de un objeto Session
        sesion.setAttribute("clientes", clientes);  //asigna la lista de clientes
        sesion.setAttribute("saldoTotal", calcularSaldoClientes(clientes)); //asigna el saldo total de todos los clientes
        sesion.setAttribute("totalClientes", clientes.size());  //asigna el total de clientes que hay
        
        //respuesta de la petición
        response.sendRedirect("clientes.jsp");
    }
    
    //inserta un nuevo cliente en la BD
    private void insertar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //obtener los valores que se enviaron del formulario
        String nombres = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");        
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }
        
        //creación de un objeto Cliente
        Cliente cliente = new Cliente(nombres, apellidos, email, telefono, saldo);
        
        //insertar el objeto (cliente) en la base de datos
        int registrosAgregados = new ClienteDaoJDBC().insert(cliente);
        System.out.println("--> *** Registros agregados = " + registrosAgregados);
        
        //redireccionar a la pagina(JSP) principal
        accionDefault(request, response);
        
    }
    
    //permite editar los datos de un cliente ya existente en la BD
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //obtener el id del cliente
        int id = Integer.parseInt(request.getParameter("idCliente"));
        
        //Obtener cliente deacuerdo al "id"
        Cliente cliente = new ClienteDaoJDBC().getById(new Cliente(id));
        
        //establecer la variable cliente en un alcance
        request.setAttribute("cliente", cliente);
        
        //redirigir al JSP para editar el cliente
        String jspEditar = "editarCliente.jsp";       
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    //Realiza la actualizacion de los datos del cliente
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //obtener los datos que se reciben del formulario
        int id = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = Double.parseDouble(request.getParameter("saldo"));
        
        //crear objeto de tipo cliente
        Cliente cliente = new Cliente(id, nombre, apellidos, email, telefono, saldo);
        
        //actualizar el registro en la BD, se envia el cliente 
        int registrosModificados = new ClienteDaoJDBC().update(cliente);
        System.out.println("--> *** Registros modificados: " + registrosModificados);
        
        //redireccionar a la pagina principal
        accionDefault(request, response);
    }
    
    //elimina el cliente
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //obtener el id del cliente
        int id = Integer.parseInt(request.getParameter("idCliente"));
        
        //Crear objeto cliente solo con el ID
        Cliente cliente = new Cliente(id);
        
        //eliminar registro de la BD
        int registrosEliminados = new ClienteDaoJDBC().delete(cliente);
        System.out.println("--> *** Registros eliminados: " + registrosEliminados );
        
        //redireccionar a la pagina principal
        accionDefault(request, response);
        
    }
    
    //calcula el saldo total de todos los clientes
    private double calcularSaldoClientes(List<Cliente> clientes) {
        double saldoTotal = 0;
        
        for(Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        
        return saldoTotal;
    }
}
