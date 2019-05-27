package Server;



import org.postgresql.util.LruCache.CreateAction;

import Query.ConsultaUsuario;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;



public class Server extends AbstractVerticle{
	
	ConsultaUsuario user = new ConsultaUsuario();
	JDBCClient cliente;
	
	@Override
	public void start() {
		//Configuracion de la conexion a base de datos
		cliente = JDBCClient.createShared(vertx, new JsonObject()
				.put("url", "jdbc:postgresql://localhost:5433/wag")//3007
				.put("driver_class", "org.postgresql.Driver")
				.put("max_pool_size", 30)
				.put("user", "postgres")
				.put("password", "1151")//Impe98rio
				);
		//Crea un router que guiara las peticiones a las URL
		Router router = Router.router(vertx);
		
		//Define la url de los componentes estáticos como HTML, CSS, JS
		router.route("/assests/*").handler(StaticHandler.create("assets"));
		
		//Define un conjunto de rutas para diferentes peticiones http sobre /api/usuario
		router.route("/api/usuarios*").handler(BodyHandler.create());
		
		//Autenticar Usuario
		router.get("/api/usuario/:correo/:contrasena").handler(r->{
			user.autenticarUsuario(r, cliente);
		});
		
		//Agregar Usuario
		router.post("/api/usuario").handler(r->{
			
		});
		
		//Actualizar Usuario
		router.put("/api/usuario/:id").handler(r->{
			
		});
		//Borrar Usuario
		router.delete("/api/usuario/:id").handler(r->{
			
		});
	
		//Creamos el Servidor http
		//vertx.createHttpServer().requestHandler(router).listen(8080);
	}
	
}
