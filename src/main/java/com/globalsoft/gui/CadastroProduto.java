package com.globalsoft.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.globalsoft.business.Facade;
import com.globalsoft.entities.Category;
import com.globalsoft.entities.Product;
import com.globalsoft.entities.SubCategory;
import com.globalsoft.entities.Supplier;
import com.globalsoft.util.Util;

public class CadastroProduto extends JFrame {

	private static final long serialVersionUID = -5862683290614642467L;
	private JPanel contentPane;
	private JComboBox<Category> cbCatego;
	private JComboBox<SubCategory> cbSubcat;
	private JComboBox<Supplier> cmbFornecedor;
	private JTextField txtCod;
	private JTextField txtDescription;
	private JTextField txtValorUnitario;
	private JTextField txtNotaFiscal;
	private JTextField txtEstoqueMinimo;
	private JTextField txtEstoqueMaximo;
	private JTextField txtLocalEstoque;
	private JTextField txtMarca;
	private JTextField txtFabricante;
	private JTextField txtUnidadeMedida;
	private JTextField txtCor;
	private JTextField txtSerie;
	private JTextField txtReferencia;
	private JTextPane txtObservacao;
	private JTextField txtEstoque;

	private void clearScreen() {
		txtCod.setText("");
		txtDescription.setText("");
		txtValorUnitario.setText("");
		txtNotaFiscal.setText("");
		txtEstoqueMinimo.setText("");
		txtEstoqueMaximo.setText("");
		txtLocalEstoque.setText("");
		txtMarca.setText("");
		txtFabricante.setText("");
		txtUnidadeMedida.setText("");
		txtCor.setText("");
		txtSerie.setText("");
		txtReferencia.setText("");
		txtObservacao.setText("");
		txtEstoque.setText("");
	}
	
	private Product getScreenData() {
		Product result = new Product();
		String id = txtCod.getText();
		
		if (!Util.isNullOrEmpty(id)) {
			result.setId(Long.valueOf(txtCod.getText()));
		}
		result.setNome(txtDescription.getText());
		result.setEstoqueMin(txtEstoqueMinimo.getText());
		result.setEstoqueMax(txtEstoqueMaximo.getText());
		result.setFabricante(txtFabricante.getText());
		result.setCor(txtCor.getText());	
		result.setFornecedor((Supplier) cmbFornecedor.getSelectedItem());
		result.setMarca(txtMarca.getText());
		result.setLocalEstoque(txtLocalEstoque.getText());
		result.setNotaFiscal(txtNotaFiscal.getText());
		result.setValorUnit(txtValorUnitario.getText());
		result.setSerie(txtSerie.getText());	
		result.setSubCategory((SubCategory) cbSubcat.getSelectedItem());
		result.setUnidadeMedida(txtUnidadeMedida.getText());
		result.setReferencia(txtReferencia.getText());
		result.setObsComments(txtObservacao.getText());
		result.setEstoque(txtEstoque.getText());
		
		return result;
	}
	
	private void setScreenData(Product product){
		if (product.getId() != null) {
			txtCod.setText(String.valueOf(product.getId()));
		}
		txtCor.setText(product.getCor());
		txtDescription.setText(product.getNome());
		txtEstoqueMaximo.setText(product.getEstoqueMax());
		txtEstoqueMinimo.setText(product.getEstoqueMin());
		txtFabricante.setText(product.getFabricante());
		txtLocalEstoque.setText(product.getLocalEstoque());
		txtMarca.setText(product.getMarca());
		txtNotaFiscal.setText(product.getNotaFiscal());
		txtReferencia.setText(product.getReferencia());
		txtSerie.setText(product.getSerie());
		txtUnidadeMedida.setText(product.getUnidadeMedida());
		txtValorUnitario.setText(product.getValorUnit());
		txtObservacao.setText(product.getObsComments());
		txtEstoque.setText(product.getEstoque()); 
	}

	public CadastroProduto(Product product) {
		this();
		setScreenData(product);
	}
	
	public CadastroProduto() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 712, 594);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnSave = new JButton("Salvar");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Product p = getScreenData();
					if (p.getId() != null){
						Facade.getInstance().update(p);
					} else {
						Facade.getInstance().create(p);
					}
					JOptionPane.showMessageDialog(CadastroProduto.this, "Registro salvo com sucesso !");					
					clearScreen();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(CadastroProduto.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
				
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setIcon(new ImageIcon("Icones\\page_save.png"));
		btnSave.setBounds(498, 531, 89, 23);
		contentPane.add(btnSave);

		JButton btExit = new JButton("Sair");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btExit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btExit.setIcon(new ImageIcon("Icones\\door_in.png"));
		btExit.setBounds(597, 531, 89, 23);
		contentPane.add(btExit);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 67, 676, 210);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCod = new JLabel("C\u00F3digo");
		lblCod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCod.setBounds(10, 11, 67, 14);
		panel.add(lblCod);

		txtCod = new JTextField();
		txtCod.setEditable(false);
		txtCod.setBounds(10, 26, 67, 20);
		panel.add(txtCod);
		txtCod.setColumns(10);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(82, 26, 381, 20);
		panel.add(txtDescription);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDescrio.setBounds(82, 11, 304, 14);
		panel.add(lblDescrio);

		JLabel lblValorUnd = new JLabel("Valor Unit\u00E1rio");
		lblValorUnd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblValorUnd.setBounds(565, 103, 81, 14);
		panel.add(lblValorUnd);

		txtValorUnitario = new JTextField();
		txtValorUnitario.setColumns(10);
		txtValorUnitario.setBounds(565, 118, 101, 20);
		panel.add(txtValorUnitario);

		JLabel lblMarca = new JLabel("N\u00BA Nota Fiscal");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMarca.setBounds(473, 11, 193, 14);
		panel.add(lblMarca);

		txtNotaFiscal = new JTextField();
		txtNotaFiscal.setColumns(10);
		txtNotaFiscal.setBounds(473, 26, 193, 20);
		panel.add(txtNotaFiscal);

		JLabel lblQuantidade = new JLabel("Estoque Minimo");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblQuantidade.setBounds(230, 103, 100, 14);
		panel.add(lblQuantidade);

		txtEstoqueMinimo = new JTextField();
		txtEstoqueMinimo.setColumns(10);
		txtEstoqueMinimo.setBounds(230, 118, 100, 20);
		panel.add(txtEstoqueMinimo);

		JLabel lblQtdMaxima = new JLabel("Estoque Max");
		lblQtdMaxima.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblQtdMaxima.setBounds(340, 103, 100, 14);
		panel.add(lblQtdMaxima);

		txtEstoqueMaximo = new JTextField();
		txtEstoqueMaximo.setColumns(10);
		txtEstoqueMaximo.setBounds(340, 118, 100, 20);
		panel.add(txtEstoqueMaximo);

		txtLocalEstoque = new JTextField();
		txtLocalEstoque.setColumns(10);
		txtLocalEstoque.setBounds(11, 165, 304, 20);
		panel.add(txtLocalEstoque);

		JLabel lblLocalizao = new JLabel("Localiza\u00E7\u00E3o");
		lblLocalizao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLocalizao.setBounds(10, 150, 100, 14);
		panel.add(lblLocalizao);

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(10, 72, 117, 20);
		panel.add(txtMarca);

		JLabel lblMarca_1 = new JLabel("Marca");
		lblMarca_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMarca_1.setBounds(10, 57, 117, 14);
		panel.add(lblMarca_1);

		txtFabricante = new JTextField();
		txtFabricante.setColumns(10);
		txtFabricante.setBounds(137, 72, 299, 20);
		panel.add(txtFabricante);

		JLabel lblFabricante = new JLabel("Fabricante");
		lblFabricante.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFabricante.setBounds(137, 57, 299, 14);
		panel.add(lblFabricante);

		JLabel lblUnidadeDeMedida = new JLabel("Unidade de Medida");
		lblUnidadeDeMedida.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUnidadeDeMedida.setBounds(446, 57, 109, 14);
		panel.add(lblUnidadeDeMedida);

		txtUnidadeMedida = new JTextField();
		txtUnidadeMedida.setColumns(10);
		txtUnidadeMedida.setBounds(446, 72, 109, 20);
		panel.add(txtUnidadeMedida);

		cmbFornecedor = new JComboBox<Supplier>();
		cmbFornecedor.setBounds(325, 165, 277, 20);
		panel.add(cmbFornecedor);

		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFornecedor.setBounds(325, 149, 277, 14);
		panel.add(lblFornecedor);

		txtCor = new JTextField();
		txtCor.setColumns(10);
		txtCor.setBounds(566, 72, 100, 20);
		panel.add(txtCor);

		JLabel lblCor = new JLabel("Cor");
		lblCor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCor.setBounds(566, 57, 100, 14);
		panel.add(lblCor);

		txtSerie = new JTextField();
		txtSerie.setColumns(10);
		txtSerie.setBounds(10, 118, 100, 20);
		panel.add(txtSerie);

		JLabel lblSrie = new JLabel("S\u00E9rie");
		lblSrie.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSrie.setBounds(10, 103, 100, 14);
		panel.add(lblSrie);

		JLabel lblReferncia = new JLabel("Refer\u00EAncia");
		lblReferncia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblReferncia.setBounds(120, 103, 100, 14);
		panel.add(lblReferncia);

		txtReferencia = new JTextField();
		txtReferencia.setColumns(10);
		txtReferencia.setBounds(120, 118, 100, 20);
		panel.add(txtReferencia);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFornecedor view = new CadastroFornecedor();
				view.setLocationRelativeTo(null);
				view.setVisible(true);
			}
		});
		button.setIcon(new ImageIcon("/Users/elias/eclipse-workspace/cccontrol/Icones/lipinha.png"));
		button.setToolTipText("Cadastrar Fornecedor");
		button.setBounds(618, 157, 28, 28);
		panel.add(button);
		
		txtEstoque = new JTextField();
		txtEstoque.setText("0");
		txtEstoque.setColumns(10);
		txtEstoque.setBounds(450, 118, 100, 20);
		panel.add(txtEstoque);
		
		JLabel lblEmEstoque = new JLabel("Em Estoque");
		lblEmEstoque.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmEstoque.setBounds(450, 103, 100, 14);
		panel.add(lblEmEstoque);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 288, 676, 59);
		contentPane.add(panel_1);

		cbCatego = new JComboBox<Category>();
		cbCatego.setBounds(10, 26, 280, 20);
		panel_1.add(cbCatego);

		cbSubcat = new JComboBox<SubCategory>();
		cbSubcat.setBounds(308, 26, 290, 20);
		panel_1.add(cbSubcat);

		JLabel lblSub = new JLabel("Subcategoria");
		lblSub.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSub.setBounds(308, 11, 290, 14);
		panel_1.add(lblSub);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCategoria.setBounds(10, 11, 280, 14);
		panel_1.add(lblCategoria);

		JButton btnNewSubcat = new JButton("");
		btnNewSubcat.setIcon(new ImageIcon("/Users/elias/eclipse-workspace/cccontrol/Icones/lipinha.png"));
		btnNewSubcat.setToolTipText("Cadastar Subcategoria");
		btnNewSubcat.setBounds(622, 18, 28, 28);
		panel_1.add(btnNewSubcat);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 358, 676, 152);
		contentPane.add(panel_2);

		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblObservaes.setBounds(10, 0, 81, 25);
		panel_2.add(lblObservaes);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 656, 117);
		panel_2.add(scrollPane);

		txtObservacao = new JTextPane();
		scrollPane.setViewportView(txtObservacao);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearScreen();
			}			
		});
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNovo.setIcon(new ImageIcon("Icones\\page_white_horizontal.png"));
		btnNovo.setBounds(10, 531, 89, 23);
		contentPane.add(btnNovo);

		JLabel lblCadastroProduto = new JLabel("Cadastro Produto");
		lblCadastroProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroProduto.setBounds(10, 11, 676, 45);
		contentPane.add(lblCadastroProduto);
	}
}
