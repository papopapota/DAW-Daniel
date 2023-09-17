package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	// Listado de todos los  Usuario, según su código.
	public static void main(String[] args) {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		String jpql = "select u from Usuario u";
		List<Usuario> lstUsuario  = manager.createQuery(jpql,Usuario.class).getResultList();
		//Mostrar el Listado
		System.out.print("Listado de Usuarios");
		for(Usuario u : lstUsuario) {
			System.out.println("Codigo.....:" + u.getCod_usua());
			System.out.println("Nombre.....:" + u.getCod_usua() + " " + u.getApe_usua());
			System.out.println("Tipo.....:" + u.getCod_usua());
			System.out.println("-------------------------------");
			
			
		}
		
		
		manager.close();
		
		
	}
}
