package vista;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ItemSelectable;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controlador.Controlador;
import dominio.Direccion;
import dominio.Marca;
import dominio.Producto;
import dominio.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;

public class AltaPrecioPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfNombreProducto;
	private JComboBox<String> cbTipoProducto;
	private JComboBox<Marca> cbMarcaProducto;
	private JComboBox<String> cbTienda;
	private JComboBox<String> cbCalle;
	private JTextField tfAltura;
	private JTextField tfEntreCalles1;
	private JTextField tfEntreCalles2;
	private JTextField tfPrecioProducto;
	private JButton btnBuscarProducto;
	private JButton btnBuscarTienda;
	private JButton btnRegistrarPrecio;
	private JFrame frame;
	private DefaultComboBoxModel<String> tiposComboModel;
	private DefaultComboBoxModel<Marca> marcasComboModel;
	private DefaultComboBoxModel<String> tiendasComboModel;
	private DefaultComboBoxModel<String> callesComboModel;
	private BuscadorProductosDialog buscadorProductos;
	private BuscadorTiendasDialog buscadorTiendas;
	private Producto productoElegido;
	private String tipoElegido;
	private Marca marcaElegida;
	private Tienda tiendaElegida;
	
	private Controlador controlador;

	/**
	 * Create the panel.
	 */
	public AltaPrecioPanel() {
		setLayout(null);
		
		frame = (JFrame) SwingUtilities.getWindowAncestor(this);
		
		JLabel lblDatosDelProducto = new JLabel("Datos del Producto");
		lblDatosDelProducto.setBounds(10, 10, 136, 13);
		lblDatosDelProducto.setForeground(Color.BLUE);
		add(lblDatosDelProducto);
		
		btnBuscarProducto = new JButton("...");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarBuscadorProductos();
			}
		});
		btnBuscarProducto.setBounds(236, 6, 44, 21);
		add(btnBuscarProducto);
		
		JLabel lblNombreProducto = new JLabel("Nombre");
		lblNombreProducto.setBounds(10, 30, 101, 14);
		lblNombreProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombreProducto.setForeground(Color.BLACK);
		add(lblNombreProducto);
		
		tfNombreProducto = new JTextField();
		tfNombreProducto.setBounds(81, 29, 199, 19);
		add(tfNombreProducto);
		tfNombreProducto.setColumns(10);
		
		JLabel lblTipoProducto = new JLabel("Tipo");
		lblTipoProducto.setBounds(10, 56, 31, 13);
		lblTipoProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTipoProducto.setForeground(Color.BLACK);
		add(lblTipoProducto);
		
		cbTipoProducto = new JComboBox<String>();
		cbTipoProducto.setEditable(true);
		cbTipoProducto.setBounds(81, 50, 199, 19);
		tiposComboModel = new DefaultComboBoxModel<String>();
		cbTipoProducto.setModel(tiposComboModel);
		cbTipoProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox) e.getSource();
				try {
				setTipoElegido(cb.getSelectedItem().toString());
				} catch(Exception except) {}
			}
		});
		add(cbTipoProducto);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(10, 83, 61, 13);
		lblMarca.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMarca.setForeground(Color.BLACK);
		add(lblMarca);
		
		cbMarcaProducto = new JComboBox<Marca>();
		cbMarcaProducto.setEditable(true);
		cbMarcaProducto.setBounds(81, 75, 199, 21);
		marcasComboModel = new DefaultComboBoxModel<Marca>();
		cbMarcaProducto.setModel(marcasComboModel);
		cbMarcaProducto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<Marca> cb = (JComboBox) e.getSource();
				try {
					setMarcaElegida((Marca) cb.getSelectedItem());
				} catch (Exception except) {}
			}
		});
		add(cbMarcaProducto);
		
		JLabel lblDatosTienda = new JLabel("Tienda");
		lblDatosTienda.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDatosTienda.setBounds(10, 107, 136, 22);
		lblDatosTienda.setForeground(Color.BLUE);
		add(lblDatosTienda);
		
		btnBuscarTienda = new JButton("...");
		btnBuscarTienda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarBuscadorTiendas();
			}
		});
		btnBuscarTienda.setBounds(236, 110, 43, 21);
		add(btnBuscarTienda);
		
		JLabel lblNombreTienda = new JLabel("Nombre");
		lblNombreTienda.setBounds(10, 138, 61, 16);
		lblNombreTienda.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNombreTienda.setForeground(Color.BLACK);
		add(lblNombreTienda);
		
		cbTienda = new JComboBox<String>();
		cbTienda.setEditable(true);
		tiendasComboModel = new DefaultComboBoxModel<String>();
		cbTienda.setModel(tiendasComboModel);
		cbTienda.setBounds(81, 133, 199, 21);
		add(cbTienda);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 168, 71, 13);
		lblProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvincia.setVerticalAlignment(SwingConstants.BOTTOM);
		lblProvincia.setForeground(Color.BLACK);
		add(lblProvincia);
		
		JComboBox<String> cbProvincia = new JComboBox<String>();
		cbProvincia.setModel(new DefaultComboBoxModel<String>(new String[] {"CABA"}));
		cbProvincia.setBounds(81, 160, 199, 21);
		add(cbProvincia);
		
		cbCalle = new JComboBox<String>();
		cbCalle.setEditable(true);
		callesComboModel = new DefaultComboBoxModel<String>();
		cbCalle.setModel(callesComboModel);
		cbCalle.setBounds(81, 187, 199, 21);
		add(cbCalle);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 191, 61, 17);
		lblCalle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCalle.setHorizontalAlignment(SwingConstants.LEFT);
		lblCalle.setForeground(Color.BLACK);
		add(lblCalle);
		
		tfAltura = new JTextField();
		tfAltura.setBounds(81, 213, 199, 19);
		tfAltura.setColumns(10);
		add(tfAltura);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 213, 56, -3);
		lblAltura.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAltura.setForeground(Color.BLACK);
		add(lblAltura);
		
		tfEntreCalles1 = new JTextField();
		tfEntreCalles1.setBounds(81, 234, 98, 19);
		tfEntreCalles1.setColumns(10);
		add(tfEntreCalles1);
		
		tfEntreCalles2 = new JTextField();
		tfEntreCalles2.setBounds(189, 234, 91, 19);
		tfEntreCalles2.setColumns(10);
		add(tfEntreCalles2);
		
		JLabel lblEntreCalles = new JLabel("Entre calles");
		lblEntreCalles.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEntreCalles.setBounds(10, 234, 91, 19);
		add(lblEntreCalles);
		
		tfPrecioProducto = new JTextField();
		tfPrecioProducto.setBounds(128, 257, 152, 19);
		add(tfPrecioProducto);
		tfPrecioProducto.setColumns(10);
		
		JLabel lblPrecioProducto = new JLabel("Precio del Producto");
		lblPrecioProducto.setBounds(10, 263, 120, 13);
		lblPrecioProducto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrecioProducto.setForeground(Color.BLACK);
		add(lblPrecioProducto);
		
		btnRegistrarPrecio = new JButton("Registrar Precio");
		btnRegistrarPrecio.setActionCommand(InterfazVistaAltaPrecio.AGUARDAR_DATOS_ALTAPRECIO);
		btnRegistrarPrecio.setBounds(10, 282, 270, 40);
		btnRegistrarPrecio.setBackground(Color.BLUE);
		btnRegistrarPrecio.setForeground(Color.WHITE);
		add(btnRegistrarPrecio);
		
		JLabel lblAltura_1 = new JLabel("Altura");
		lblAltura_1.setBounds(10, 219, 92, 13);
		lblAltura_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAltura_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAltura_1.setForeground(Color.BLACK);
		add(lblAltura_1);

	}

	private void mostrarBuscadorProductos() {
		buscadorProductos = new BuscadorProductosDialog(frame, this);
		buscadorProductos.addListenerBuscarProducto(this.controlador);
		buscadorProductos.setPreferredSize(new Dimension(310,300));
		buscadorProductos.pack();
		buscadorProductos.setModal(true);
		buscadorProductos.setVisible(true);
	}
	
	private void mostrarBuscadorTiendas() {
		buscadorTiendas = new BuscadorTiendasDialog(frame, this);
		buscadorTiendas.addListenerBuscarTienda(this.controlador);
		buscadorTiendas.setPreferredSize(new Dimension(310,300));
		buscadorTiendas.pack();
		buscadorTiendas.setModal(true);
		buscadorTiendas.setVisible(true);
	}
	
	public String getPalabraBuscadorProductos() {
		return buscadorProductos.getPalabraBusqueda();
	}
	
	public String getPalabraBuscadorTiendas() {
		return buscadorTiendas.getPalabraBusqueda();
	}
 	
	public void agregarResultadosBuscadorProductos(Collection<Producto> items) {
		buscadorProductos.agregarResultadosBusqueda(items);
	}
	
	public void agregarResultadosBuscadorTiendas(Collection<Tienda> items) {
		buscadorTiendas.agregarResultadosBusqueda(items);
	}
	
	public void agregarItemsSelectorTipos(Collection<String> items) {
		tiposComboModel.removeAllElements();
		tiposComboModel.addAll(items);
	}
	
	public void agregarItemsSelectorMarcas(Collection<Marca> items) {
		marcasComboModel.removeAllElements();
		marcasComboModel.addAll(items);
	}
	
	public void agregarItemsSelectorTiendas(Collection<String> items) {
		tiendasComboModel.removeAllElements();
		tiendasComboModel.addAll(items);
	}
	
	public void agregarItemsSelectorCalles(Collection<String> items) {
		callesComboModel.removeAllElements();
		callesComboModel.addAll(items);
	}
	
	public void addListenerRegistrarPrecio(Controlador c) {
		btnRegistrarPrecio.addActionListener(c);
	}
	
	public void setControlador(Controlador c) {
		this.controlador = c;
	}
	
	public void limpiar(String evento) {
		switch (evento) {
			case InterfazVistaAltaPrecio.ABUSCAR_PRODUCTOS_DESDE_ALTAPRECIO: 
				buscadorProductos.limpiar();
				break;
			case InterfazVistaAltaPrecio.ABUSCAR_TIENDAS_DESDE_ALTAPRECIO:
				buscadorTiendas.limpiar();
				break;
			case InterfazVistaAltaPrecio.AGUARDAR_DATOS_ALTAPRECIO:
				limpiarVentana();
				break;
		}
	}
	
	private void limpiarVentana() {
		tfNombreProducto.setText("");
		cbTipoProducto.setSelectedIndex(0);
		cbMarcaProducto.setSelectedIndex(0);
		cbTienda.setSelectedIndex(0);
		cbCalle.setSelectedIndex(0);
		tfAltura.setText("");
		tfEntreCalles1.setText("");
		tfEntreCalles2.setText("");
		tfPrecioProducto.setText("");
		productoElegido = null;
		tipoElegido = null;
		marcaElegida = null;
		tiendaElegida = null;
		
	}
	
	public void setProductoElegido(Producto producto) {
		productoElegido = producto;
		tfNombreProducto.setText(producto.obtenerNombre());
		tiposComboModel.setSelectedItem(producto.obtenerTipo());
		marcasComboModel.setSelectedItem(producto.obtenerMarca());
	}
	
	public void setTipoElegido(String tipo) {
		tipoElegido = tipo;
	}
	
	public void setMarcaElegida(Marca marca) {
		marcaElegida = marca;
	}
	
	public void setTiendaElegida(Tienda tienda) {
		Direccion direccion = tienda.obtenerDireccion();
		tiendaElegida = tienda;
		tiendasComboModel.setSelectedItem(tienda.obtenerNombre());
		callesComboModel.setSelectedItem(direccion.obtenerCalle());
		tfAltura.setText(String.valueOf(direccion.obtenerAltura()));
		tfEntreCalles1.setText(direccion.obtenerEntreCalle1());
		tfEntreCalles2.setText(direccion.obtenerEntreCalle2());		
	}
	
	public HashMap<String, String> obtenerDatos() {
		HashMap<String, String> datos = new HashMap<>();
		
		datos.put("producto-nombre", tfNombreProducto.getText());
		datos.put("producto-tipo", tipoElegido);
		if (cbTipoProducto.getSelectedItem() instanceof String) {
			datos.put("producto-tipo", cbTipoProducto.getSelectedItem().toString());
		}
		if (marcaElegida != null) {
			datos.put("producto-marca", marcaElegida.obtenerNombre());
		} 
		if (cbMarcaProducto.getSelectedItem() instanceof String ) {
			datos.put("producto-marca", cbMarcaProducto.getSelectedItem().toString());
		}
		if (tiendasComboModel.getSelectedItem() != null) {
			datos.put("tienda-nombre", tiendasComboModel.getSelectedItem().toString());
		} 
		if (cbTienda.getSelectedItem() instanceof String){
			datos.put("tienda-nombre", cbTienda.getSelectedItem().toString());
		}
		if (callesComboModel.getSelectedItem() != null) {
			datos.put("tienda-calle", callesComboModel.getSelectedItem().toString());
		} else {
			datos.put("tienda-calle", null);
		}
		datos.put("tienda-altura", tfAltura.getText());
		datos.put("tienda-entre-calles1", tfEntreCalles1.getText());
		datos.put("tienda-entre-calles2", tfEntreCalles2.getText());
		datos.put("precio", tfPrecioProducto.getText());
		return datos;
	}
}
