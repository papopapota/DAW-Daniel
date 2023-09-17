package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.ParseConversionEvent;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtUsuario;
	private JTextField txtContra;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Ingresar");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		JLabel lblStock = new JLabel("Usuario");
		lblStock.setBounds(10, 33, 102, 14);
		contentPane.add(lblStock);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(122, 30, 144, 20);
		contentPane.add(txtUsuario);
		
		JLabel lblPrecio = new JLabel("Contraseña");
		lblPrecio.setBounds(10, 64, 102, 14);
		contentPane.add(lblPrecio);
		
		txtContra = new JTextField();
		txtContra.setColumns(10);
		txtContra.setBounds(122, 61, 144, 20);
		contentPane.add(txtContra);
		
		///llenaCombo1();
		//llenaCombo2();
	}

	void llenaCombo1() {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		String jpql = "select c from Categoria c";
		List<Categoria> lstCategoria  = manager.createQuery(jpql,Categoria.class).getResultList();
		for(Categoria c : lstCategoria) {
		//	cboCategorias.addItem(c.getDescripcion());		
		}
		manager.close();
	}
	
	void llenaCombo2() {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		String jpql = "select p from Proveedor p";
		List<Proveedor> lstProveedor  = manager.createQuery(jpql,Proveedor.class).getResultList();
		for(Proveedor p : lstProveedor) {
			//cboProveedor.addItem(p.getNombre_rs());		
		}
		manager.close();
	}
	
	void Login() {
		// llamar a la conexión
				EntityManagerFactory fabrica = 
						Persistence.createEntityManagerFactory("jpa_sesion01");
				// crear un manejador de las entidades
				EntityManager manager = fabrica.createEntityManager();
				String usr_usua = "U003@gmail.com" ; 
				String cla_usua = "10003" ; 
				String jpql = "select u from Usuario u where u.usr_usua = :xusr_usua and u.cla_usua = :xcla_usua";
				List<Usuario> lstUsuario  = manager.createQuery(jpql,Usuario.class).setParameter("xusr_usua", usr_usua).setParameter("xcla_usua", cla_usua).getResultList();
				//Mostrar el Listado
				for(Usuario u : lstUsuario) {
					imprimir("Codigo.....:" + u.getCod_usua());
					imprimir("Nombre.....:" + u.getNom_usua() + " " + u.getApe_usua());
					imprimir("Tipo.....:" + u.getIdtipo()+ "-" +
							u.getObjTipo().getDescripcion());
					imprimir("-------------------------------");
					
					
				}
				
				manager.close();
				
	}
	private void aviso(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, msg , "Aviso" , JOptionPane.INFORMATION_MESSAGE);
	}

	void imprimir(String texto) {
		txtSalida.append(texto+"\n");
	}
}
