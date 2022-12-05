package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//import java.sql.Date;

public class Usuario {
	
	private String id;
	private String nombre;
	private String apellido;
	//private Date fechaRegistro;
	private String username;
	private String contrasena;
	private boolean activo;
	
	public Usuario() {}
	
	public Usuario(String id, String nombre, String apellido, String username, String contrasena, boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		//this.fechaRegistro = fechaRegistro
		this.username = username;
		this.contrasena = contrasena;
		this.activo = activo;
	}

	//id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	//nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//apellido
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	//fecha
	//que por hacer
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	//contraseña
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	//activo
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public String toString() {
		return this.id + ":" + this.nombre + ":" + this.apellido + ":" +
	/*this.fechaRegistro + ":" + */ this.username + ":" + this.contrasena + ":" + (this.activo == true ? 1 : 0);
	}
	
	public static ArrayList<Usuario> cargarUsuario(String rutaFichero) throws FileNotFoundException {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		File file = new File(rutaFichero);
		Scanner scFile = new Scanner(file);
		
		for (int i = 0; scFile.hasNextLine(); i++) {
			String linea[] = scFile.nextLine().split(":");
			Usuario usuario = new Usuario(linea[0], linea[1], linea[2], linea[3], linea[4],
					linea[5].equals("1") ? true : false);
			usuarios.add(usuario);
		}
		
		return usuarios;
		
	}
	
	public static void guardarUsuario(ArrayList<Usuario> usuarios, String rutaFicher) throws FileNotFoundException {
		File file = new File(rutaFicher);
		PrintWriter pw = new PrintWriter(file);
		
		for (Usuario usuario : usuarios) {
			pw.println(usuario);
		}
		
		pw.close();
	}
	
	
}
