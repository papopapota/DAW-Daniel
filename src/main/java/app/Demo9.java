package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.mysql.cj.x.protobuf.MysqlxCursor.Open;

import model.Usuario;

public class Demo9 {
	// Listado de todos los  Usuario, según un Filtro o Condicion.
	public static void main(String[] args) {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		String usr_usua = JOptionPane.showInputDialog("ingrese Usuario: ") /*"U003@gmail.com" */; 
		String cla_usua = JOptionPane.showInputDialog("ingrese clave: ")/*"10003"*/ ; 
		String jpql = "select u from Usuario u where u.usr_usua = :xusr_usua and u.cla_usua = :xcla_usua";
		try {
			Usuario u  = manager.createQuery(jpql,Usuario.class).setParameter("xusr_usua", usr_usua).setParameter("xcla_usua", cla_usua).getSingleResult();
			//Mostrar el Listado
			System.out.print("Listado de Usuarios");
				System.out.println("Codigo.....:" + u.getCod_usua());
				System.out.println("Nombre.....:" + u.getNom_usua() + " " + u.getApe_usua());
				System.out.println("Tipo.....:" + u.getIdtipo()+ "-" +
						u.getObjTipo().getDescripcion());
				System.out.println("-------------------------------");
				//mostrar usuario 
				JOptionPane.showMessageDialog(null, "Bienvenido " + u.getNom_usua());
				// abrir ventana principal
				FrmMantenerProducto ventana = new FrmMantenerProducto();
				ventana.setVisible(true);
				//dispose();// cuando se tenga una ventana actual
		}catch(Exception e ) {
			JOptionPane.showMessageDialog(null, "Error : Usuario o Clave icorrecto");
		}
		manager.close();
	}
}
