package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Producto;

public class Demo7 {
	// Listado de todos los  Usuario, según su código.
	public static void main(String[] args) {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		String jpql = "select p from Producto p";
		List<Producto> lstProducto  = manager.createQuery(jpql,Producto.class).getResultList();
		//Mostrar el Listado
		System.out.print("Listado de Productos");
		for(Producto p : lstProducto) {
			System.out.println("ID.....: " + p.getId_prod());
			System.out.println("Descripcion.....: " + p.getDes_prod());
			System.out.println("Stock.....: " + p.getStk_prod());
			System.out.println("Precio.....: " + p.getPre_prod());
			System.out.println("Categoria.....: " + p.getIdcategoria()+ " - " + 
					p.getObjCategoria().getDescripcion());
			System.out.println("Estado.....: " + p.isEst_prod());
			System.out.println("Proveedor.....: " + p.getIdproveedor()+ " - " + 
								p.getObjProveedor().getNombre_rs() + " - " +p.getObjProveedor().getTelefono());
			System.out.println("-------------------------------");
			
			
		}
		
		
		manager.close();
		
		
	}
}
