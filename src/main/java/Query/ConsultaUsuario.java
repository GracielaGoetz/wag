package Query;


import Datos.Usuario;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;

public class ConsultaUsuario {
	
	public void autenticarUsuario(RoutingContext rc, JDBCClient cliente){
		String correo = rc.request().getParam("correo");
		String contrasena = rc.request().getParam("contrasena");
			//invocamos la conexión a la base de datos y el manejador de respuesta conn 
			cliente.getConnection(conn ->{
				//invocamos un cliente SQL para ejecutar querys
				SQLConnection conexion = conn.result();
				consultaAutenticar(correo, contrasena, conexion, resultado->{
					if(resultado.succeeded()) {
						rc.response().setStatusCode(200).putHeader("content-type", "application/json; charset=utf-8").end(Json.encode(resultado.result()));
					}else {
						rc.response().setStatusCode(404).end("<h1>Error Usuariono encontrado</h1>");
					}
				});
			});
	}
	
	public void consultaAutenticar(String correo, String contrasena, SQLConnection conexion,Handler<AsyncResult<Usuario>> resultado) {
		conexion.queryWithParams("select * from usuario where correo=? and contrasena=?", new JsonArray().add(correo).add(contrasena), rb->{
			if(rb.result().getNumRows() >= 1) {
				Usuario user = new Usuario(rb.result().getRows().get(0));
				resultado.handle(Future.succeededFuture(user));
			}else {
				resultado.handle(Future.failedFuture("Usuario no registrado"));
			}
		});
	}
	
	public void ingresarUsuario(RoutingContext rc, JDBCClient cliente) {
		cliente.getConnection(conn->{
			Usuario Usuario =  Json.decodeValue(rc.getBodyAsString(),Usuario.class);
			SQLConnection conexion = conn.result();
		});
	}

	public void insertarUsuario(SQLConnection conexion, Usuario user, Handler<AsyncResult<Usuario>> r){
		conexion.updateWithParams("INSERT INTO public.usuario (id, nombre, apellido, contrasena, rol, correo) VALUES (?, ?, ?, ?, ?, ?); ",
				new JsonArray().add(user.getId()).add(user.getNombre()).add(user.getApellido()).add(user.getContrasena())
				.add(user.getRol()).add(user.getCorreo())
				, rb ->{
					
				});
	}

}

