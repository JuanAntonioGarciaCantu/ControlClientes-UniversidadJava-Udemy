<!-- Permite utilizar etiquetas para acceder a los Java Beans para poder utilizar estructuras de control(entro otras) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!-- Permite utilizar un formato en fechas, numeros, mensajes, etc.. -->
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- establece la localidad para manejar el formato adecuado -->
<fmt:setLocale value="es_MX"/> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Permite utilizar iconos de FontAwesome -->
        <script src="https://kit.fontawesome.com/9fbd14239f.js" crossorigin="anonymous"></script>        
        <!-- Importa el framework de Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!-- Permite utilizar iconos de Google -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">        
    </head>
    <body>
        <header class="bg-info bg-gradient py-1">
            <div class="container d-flex justify-content-center">
                <div class="row">
                    <div class="col">
                        <h1>Control de clientes</h1>                
                    </div>
                </div>
            </div>
        </header>

        <main>
            <section id="actions" class="py-3">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#agregarClienteModal">
                                <b>+</b> Agregar cliente
                            </button>
                        </div>
                    </div>
                </div>
            </section> 
            <section id="clientes">
                <div class="container">
                    <div class="row">
                        <div class="col-9">
                            <table class="table table-responsive table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <td class="text-center">#</td>
                                        <td class="text-center">Nombre</td>
                                        <td class="text-center">Email</td>
                                        <td class="text-center">Teléfono</td>
                                        <td class="text-center">Saldo</td>
                                        <td class="text-center">Editar</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- Recorre la lista de empleado (jstl/core) -->
                                    <c:forEach items="${clientes}" var="cliente">
                                        <tr>
                                            <td class="text-center">${cliente.id}</td>
                                            <td class="text-center">${cliente.nombre} ${cliente.apellido}</td>
                                            <td class="text-center">${cliente.email}</td>
                                            <td class="text-center">${cliente.telefono}</td>
                                            <!-- Formato de moneda (jstl/fmt) -->
                                            <td class="text-center"><fmt:formatNumber value="${cliente.saldo}" type="currency" /></td>
                                            <td class="text-center">
                                                <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.id}" class="btn btn-secondary"><i class="fas fa-edit"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="col-3">
                            <div class="card text-center bg-danger text-white mb-3">
                                <div class="card-body">
                                    <h3>Saldo total</h3>
                                    <h4 class="display-4">
                                        <fmt:formatNumber value="${saldoTotal}" type="currency" />
                                    </h4>
                                </div>
                            </div>
                            <div class="card text-center bg-success text-white mb-3">
                                <div class="card-body">
                                    <h3>Total clientes</h3>
                                    <h4 class="display-4">
                                        ${totalClientes}
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </div>      
                </div>
            </section>
        </main>

        <div class="modal fade" id="agregarClienteModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Agregar cliente</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar" method="POST" class="was-validated">                        
                        <div class="modal-body">
                            <div class="form-group mb-2">
                                <label for="nombre" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>
                            <div class="form-group mb-2">
                                <label for="apellidos" class="form-label">Apellidos</label>
                                <input type="text" class="form-control" id="apellidos" name="apellidos" required>
                            </div>
                            <div class="form-group mb-2">
                                <label for="email" class="form-label">Correo electronico</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="form-group mb-2">
                                <label for="telefono" class="form-label">Teléfono celular</label>
                                <input type="tel" class="form-control" id="telefono" name="telefono" required>
                            </div>
                            <div class="form-group">
                                <label for="saldo" class="form-label">Saldo</label>
                                <input type="text" class="form-control" id="saldo" name="saldo" value="0.00">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-success">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>



        <!-- Impor el JavaScript para las funciones de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        
    </body>
</html>
