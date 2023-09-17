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

public class FrmMantenerProducto extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JComboBox cboProveedor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMantenerProducto frame = new FrmMantenerProducto();
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
	public FrmMantenerProducto() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(327, 70, 86, 22);
		contentPane.add(cboProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(215, 74, 102, 14);
		contentPane.add(lblProveedor);
		
		llenaCombo1();
		llenaCombo2();
	}

	void llenaCombo1() {
		// llamar a la conexión
		EntityManagerFactory fabrica = 
				Persistence.createEntityManagerFactory("jpa_sesion01");
		// crear un manejador de las entidades
		EntityManager manager = fabrica.createEntityManager();
		String jpql = "select c from Categoria c";
		List<Categoria> lstCategoria  = manager.createQuery(jpql,Categoria.class).getResultList();
		//Mostrar el Listado
		cboCategorias.addItem("Seleccione....");	
		for(Categoria c : lstCategoria) {
			cboCategorias.addItem(c.getDescripcion());		
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
		//Mostrar el Listado
		cboProveedor.addItem("Seleccione....");	
		for(Proveedor p : lstProveedor) {
			cboProveedor.addItem(p.getNombre_rs());		
		}
		manager.close();
	}
	
	void listado() {
		// llamar a la conexión
				EntityManagerFactory fabrica = 
						Persistence.createEntityManagerFactory("jpa_sesion01");
				// crear un manejador de las entidades
				EntityManager manager = fabrica.createEntityManager();
				String jpql = "select p from Producto p";
				List<Producto> lstProducto  = manager.createQuery(jpql,Producto.class).getResultList();
				//Mostrar el Listado
				txtSalida.setText("Listado de Productos"+"\n");
				for(Producto p : lstProducto) {
					imprimir("ID.....: " + p.getId_prod());
					imprimir("Descripcion.....: " + p.getDes_prod());
					imprimir("Stock.....: " + p.getStk_prod());
					imprimir("Precio.....: " + p.getPre_prod());
					imprimir("Categoria.....: " + p.getIdcategoria()+ " - " + 
							p.getObjCategoria().getDescripcion());
					imprimir("Estado.....: " + p.isEst_prod());
					imprimir("Proveedor.....: " + p.getIdproveedor()+ " - " + 
										p.getObjProveedor().getNombre_rs() + " - " +p.getObjProveedor().getTelefono());
					imprimir("--------------------------------------------------------------");
					
					
				}
				
				
				manager.close();
	}
	
	void registrar() {
		// llamar a la conexión
				EntityManagerFactory fabrica = 
						Persistence.createEntityManagerFactory("jpa_sesion01");
				// crear un manejador de las entidades
				EntityManager manager = fabrica.createEntityManager();
				//Entradas
				String id_prod = txtCodigo.getText().toUpperCase(); 
				String des_prod;
				int stk_prod;
				double pre_prod;
				int idcategoria;
				boolean est_prod;
				int idproveedor;
				//validaciones
				if (!id_prod.matches("[P][0-9]{4}")) {
					aviso("Formato de codigo incorrecto");
					return;
				}
				// objeto a grabar
				Producto p = new Producto();
				p.setId_prod(txtCodigo.getText());
				p.setDes_prod(txtDescripcion.getText());
				p.setStk_prod(Integer.parseInt( txtStock.getText()));
				p.setPre_prod(Double.parseDouble( txtPrecio.getText()));
				p.setIdcategoria(cboCategorias.getSelectedIndex());
				p.setEst_prod(true);
				p.setIdproveedor(cboProveedor.getSelectedIndex());
				// si queremos registrar, actualizar o eliminar -> transa..
				manager.getTransaction().begin();
				//INSERT INTO TB_XX VALUES (?,?, ....
				try {
					manager.persist(p); 
					manager.getTransaction().commit();
					aviso("Registro Ok");
				}catch (PersistenceException e) {
					// TODO: handle exception
					aviso("error"+e.getMessage());
				}catch (Exception e) {
					// TODO: handle exception
					aviso("Ocurrio un error"+e.getMessage());
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
