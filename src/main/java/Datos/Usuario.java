package Datos;

import io.vertx.core.json.JsonObject;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellido;
	private String contrasena;
	private int rol;
	private String correo;
	
	public Usuario(int id, String nombre, String apellido, String contrasena, int rol, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		//this.rol = rol;
		this.correo = correo;
	}
	//problema linea 28 el rol
	public Usuario(JsonObject cuerpo) {
		this.id = cuerpo.getInteger("id");
		this.nombre = cuerpo.getString("nombre");
		this.apellido = cuerpo.getString("apellido");
		this.contrasena = cuerpo.getString("contrasena");
		this.rol = cuerpo.getInteger("rol");
		this.correo = cuerpo.getString("correo");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}
