package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmLoginProfe extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLoginProfe frame = new FrmLoginProfe();
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
	public FrmLoginProfe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 146);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(10, 64, 102, 14);
		contentPane.add(lblClave);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(122, 61, 161, 20);
		contentPane.add(txtClave);
		
	}

	
	private JTextField txtClave;
	
	
	void registrar() {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		String usr_usua = leerUsuario() /*"U003@gmail.com" */; 
		String cla_usua = txtClave.getText()/*"10003"*/ ; 
		if (usr_usua == null || cla_usua == null) {
			return ;
		}
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
				dispose();// cuando se tenga una ventana actual
		}catch(Exception e ) {
			JOptionPane.showMessageDialog(null, "Error : Usuario o Clave icorrecto");
		}
		manager.close();
	}

	private String leerUsuario() {
		// TODO Auto-generated method stub
		// si es diferente de 
		if(!txtUsuario.getText().matches("[A-Za-z0-9]+[@]+[a-z0-9+]+[.][a-z]{2,3}")) {
			JOptionPane.showMessageDialog(null, "Usuario debe ser un correo ");
			return null;
		}
		return txtUsuario.getText();
	}
}
