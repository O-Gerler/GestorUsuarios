package principal;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Function;

import clases.Usuario;

public class GestionDeUsuarios {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		final String RUTA_ARCHIVO = "recursos/usuarios.txt";
		ArrayList<Usuario> usuarios = Usuario.cargarUsuario(RUTA_ARCHIVO);
		Scanner sc = new Scanner(System.in);
		
		final int OPCION_MOSTRAR_USUARIOS = 1;
		final int OPCION_ELIMINAR_USUARIO = 2;
		final int OPCION_CREAR_USUARIO = 3;
		final int OPCION_MODIFICAR_USUARIO = 4;
		final int SALIR = 0;

		int opcion_menu;

		do {
			opcion_menu = mostrarMainMenu(sc, OPCION_MOSTRAR_USUARIOS, OPCION_ELIMINAR_USUARIO, OPCION_CREAR_USUARIO,
					OPCION_MODIFICAR_USUARIO, SALIR);

			switch (opcion_menu) {
			case OPCION_MOSTRAR_USUARIOS:
				mostrarDatos(usuarios);
				break;
			case OPCION_ELIMINAR_USUARIO:
				eliminarUsuario(usuarios, sc);
				break;
			case OPCION_CREAR_USUARIO:
				agregarUsuario(usuarios);
				break;
			case OPCION_MODIFICAR_USUARIO:
				modificarUsuario(usuarios, sc);
				break;
			case SALIR:
				System.out.println("ADIOS");
				break;
			default:
				System.out.println("Opcion incorrecta!");
			}

		} while (opcion_menu != SALIR);
		sc.close();
		Usuario.guardarUsuario(usuarios, RUTA_ARCHIVO);
	}

	private static int mostrarMainMenu(Scanner sc, final int OPCION_MOSTRAR_USUARIOS, final int OPCION_ELIMINAR_USUARIO,
			final int OPCION_CREAR_USUARIO, final int OPCION_MODIFICAR_USUARIO, final int SALIR) {
		int opcion_menu;
		System.out.println("------MENU-------");
		System.out.println(OPCION_MOSTRAR_USUARIOS + ". Mostrar usuarios");
		System.out.println(OPCION_ELIMINAR_USUARIO + ". Eliminar usuario");
		System.out.println(OPCION_CREAR_USUARIO + ". Crear Usuario");
		System.out.println(OPCION_MODIFICAR_USUARIO + ". Modificar usuario");
		System.out.println(SALIR + ". Salir");
		System.out.print("Elije una de las opciones: ");
		try {
			opcion_menu = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			// TODO: handle exception
			opcion_menu = -1;
		}
		
		return opcion_menu;
	}
	
	public static void agregarUsuario(ArrayList<Usuario> usuarios) {
		Usuario usuario = new Usuario();
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("Introduce un id: ");
		usuario.setId(sc.nextLine());
		
		System.out.print("Introduce un nombre: ");
		usuario.setNombre(sc.nextLine());
		
		System.out.print("Introduce un apellido: ");
		usuario.setApellido(sc.nextLine());
		
		//System.out.println("Introduce fecha de inicio");
		//usuario.setApellido(sc.nextLine());
		
		System.out.print("Introduce un username: ");
		usuario.setUsername(sc.nextLine());
		
		System.out.print("Introduce un contrasena: ");
		usuario.setContrasena(sc.nextLine());
		
		usuario.setActivo(true);
		
		usuarios.add(usuario);
	}
	
	public static String eliminarUsuario(ArrayList<Usuario> usuarios, Scanner sc) {
		System.out.print("Introduce el ID del usuario a eliminar: ");
		String usuarioAEliminar = sc.nextLine();
		Iterator<Usuario> it = usuarios.iterator();
		
		while(it.hasNext()) {
			if(it.next().getId().equals(usuarioAEliminar)) {
				it.remove();
				return "Usuario eliminado";
			}
				
		}
		
		return "Usuario no encontrado";
	}
	
	public static void mostrarDatos(ArrayList<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	public static void modificarUsuario(ArrayList<Usuario> usuarios, Scanner sc){
		String opcion;
		
		System.out.print("Introduce el username: ");
		String usuarioModifcar = sc.nextLine();
		System.out.print("Introduce la contrasena: ");
		String contrasenaModifcar = sc.nextLine();
		
		for (Usuario usuario : usuarios) {
			if (usuario.getUsername().equals(usuarioModifcar) && usuario.getContrasena().equals(contrasenaModifcar)) {
				cambiarAtributosUsuario(sc, usuario, "nombre");
				cambiarAtributosUsuario(sc, usuario, "apellido");
				cambiarAtributosUsuario(sc, usuario, "username");
				cambiarAtributosUsuario(sc, usuario, "contrasena");
				cambiarAtributosUsuario(sc, usuario, "activo");
			}
		}
	}

	private static void cambiarAtributosUsuario(Scanner sc, Usuario usuario, String atributoCambiar){
		// TODO Auto-generated method stub
		char opcionAfirmativa;
		System.out.print(atributoCambiar + ": " + elegirAtributosMostrar(usuario, atributoCambiar) + ", desea cambiarlo(N/s): ");
		try {
			opcionAfirmativa = sc.nextLine().toLowerCase().charAt(0);
		} catch (Exception e) {
			// TODO: handle exception
			opcionAfirmativa = 'a';
		}
		if(opcionAfirmativa == 's') {
			if(!atributoCambiar.equals("activo"))
				System.out.print("Escriba el nuevo: ");
			elegirAtributosCambiar(usuario, atributoCambiar, sc);
		}
	}

	private static void elegirAtributosCambiar(Usuario usuario, String cambiar, Scanner sc) {
		switch(cambiar) {
		case "nombre": usuario.setNombre(sc.nextLine());
		break;
		case "apellido": usuario.setApellido(sc.nextLine());
		break;
		case "username": usuario.setUsername(sc.nextLine());
		break;
		case "contrasena": usuario.setContrasena(sc.nextLine());
		break;
		case "activo":
			System.out.print("Escriba 'true' para activar o 'false' para"
					+ " desactivar: ");
			boolean opcion = sc.nextLine().toLowerCase().equals("true") ? true: false;
			usuario.setActivo(opcion);
		break;
		}
	}

	private static String elegirAtributosMostrar(Usuario usuario, String cambiar) {
		switch(cambiar) {
		case "nombre":return usuario.getNombre();
		case "apellido":return usuario.getApellido();
		case "username":return usuario.getUsername();
		case "contrasena":return usuario.getContrasena();
		case "activo":return usuario.isActivo() ? "1" : "0";
		}
		return cambiar;
	}

}





