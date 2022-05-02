<!-- Permite utilizar etiquetas para acceder a los Java Beans para poder utilizar estructuras de control(entro otras) -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

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
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar" method="POST">
                <section id="actions" class="py-3">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-2">
                                <a href="index.jsp" class="btn btn-ligth">
                                    <i class="fas fa-arrow-left"></i> Regresar al inicio
                                </a>
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-success">
                                    <i class="fas fa-check"></i> Guardar cliente
                                </button>
                            </div>
                            <div class="col-md-2">
                                <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.id}" class="btn btn-danger">
                                    <i class="fas fa-trash"></i> Eliminar cliente
                                </a>
                            </div>
                        </div>
                    </div>
                </section> 

                <section id="details">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>Editar cliente</h4>
                                    </div>
                                    <div class="card-body">                                        
                                        <div class="form-group mb-2">
                                            <label for="idCliente" class="form-label">Id</label>
                                            <input type="text" class="form-control" id="idCliente" name="idCliente" value="${cliente.id}">
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="nombre" class="form-label">Nombre</label>
                                            <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}">
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="apellidos" class="form-label">Apellidos</label>
                                            <input type="text" class="form-control" id="apellidos" name="apellidos" value="${cliente.apellido}">
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="email" class="form-label">Correo electronico</label>
                                            <input type="email" class="form-control" id="email" name="email" value="${cliente.email}">
                                        </div>
                                        <div class="form-group mb-2">
                                            <label for="telefono" class="form-label">Teléfono celular</label>
                                            <input type="tel" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}">
                                        </div>
                                        <div class="form-group">
                                            <label for="saldo" class="form-label">Saldo</label>
                                            <input type="text" class="form-control" id="saldo" name="saldo" value="${cliente.saldo}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </form>
        </main>        

        <!-- Impor el JavaScript para las funciones de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    </body>
</html>
