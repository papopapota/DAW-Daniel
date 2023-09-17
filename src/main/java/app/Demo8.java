package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo8 {
	// Listado de todos los  Usuario, según un Filtro o Condicion.
	public static void main(String[] args) {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		int tipo = 1 ; // tipo oingresado por la GUI
		String jpql = "select u from Usuario u where u.idtipo = :xtipo";
		List<Usuario> lstUsuario  = manager.createQuery(jpql,Usuario.class).setParameter("xtipo", tipo).getResultList();
		//Mostrar el Listado
		System.out.print("Listado de Usuarios");
		for(Usuario u : lstUsuario) {
			System.out.println("Codigo.....:" + u.getCod_usua());
			System.out.println("Nombre.....:" + u.getNom_usua() + " " + u.getApe_usua());
			System.out.println("Tipo.....:" + u.getIdtipo()+ "-" +
					u.getObjTipo().getDescripcion());
			System.out.println("-------------------------------");
			
			
		}
		
		
		manager.close();
		
		
	}
}
