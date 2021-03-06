package com.globalsoft.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.globalsoft.business.Facade;
import javax.swing.JTabbedPane;
import java.awt.Dialog.ModalExclusionType;

public class Menu extends JFrame {

	private static final long serialVersionUID = 8076074023643336659L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Facade.getInstance();
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1471, 537);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mntmFuncionrios = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionrios.setIcon(new ImageIcon("Icones\\group.png"));
		mntmFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrudUsers tela = new CrudUsers();
				tela.setLocationRelativeTo(null);
				tela.setVisible(true);

			}
		});
		mnArquivo.add(mntmFuncionrios);

		JMenu mnMovimentao = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnMovimentao);
		
		JMenuItem mntmCategoria = new JMenuItem("Categorias");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CategoryView view = new CategoryView(false);
				view.setLocationRelativeTo(Menu.this);
				view.setVisible(true);
			}
		});
		mnMovimentao.add(mntmCategoria);
		
		JMenuItem mntmSubcategoria = new JMenuItem("Sub Categorias");
		mntmSubcategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubCategoryView view = new SubCategoryView(false);
				view.setLocationRelativeTo(Menu.this);
				view.setVisible(true);
			}
		});
		mnMovimentao.add(mntmSubcategoria);
		
		JMenuItem mntmCentroDeCusto = new JMenuItem("Centro De Custo");
		mntmCentroDeCusto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CentroDeCusto view = new CentroDeCusto(false);
				view.setLocationRelativeTo(Menu.this);
				view.setVisible(true);
			}
		});
		mnMovimentao.add(mntmCentroDeCusto);

		JMenu mnNewMenu = new JMenu("Ajuda");
		menuBar.add(mnNewMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 1427, 80);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btProduto = new JButton("");
		btProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Produtos r = new Produtos(false);
				r.setLocationRelativeTo(null);
				r.setVisible(true);
			}

		});
		btProduto.setIcon(new ImageIcon("Icones\\1448763544_deliverables.png"));
		btProduto.setBounds(10, 11, 56, 48);
		panel.add(btProduto);

		JButton btRelatorio = new JButton("");
		btRelatorio.setIcon(new ImageIcon("Icones\\280_32x32.png"));
		btRelatorio.setBounds(1282, 11, 56, 48);
		panel.add(btRelatorio);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_3.setIcon(new ImageIcon(
				"Icones\\1448763830_xfce-system-exit.png"));
		button_3.setBounds(1350, 11, 65, 48);
		panel.add(button_3);

		JLabel lblNovoCadastro = new JLabel("Produtos");
		lblNovoCadastro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNovoCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoCadastro.setBounds(10, 56, 56, 25);
		panel.add(lblNovoCadastro);

		JLabel lblRelatrio = new JLabel("Relat\u00F3rio");
		lblRelatrio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRelatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatrio.setBounds(1282, 56, 56, 25);
		panel.add(lblRelatrio);

		JLabel lblSair = new JLabel("Sair");
		lblSair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSair.setHorizontalAlignment(SwingConstants.CENTER);
		lblSair.setBounds(1350, 56, 65, 25);
		panel.add(lblSair);

		JLabel lblClientes = new JLabel("Solicitantes");
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblClientes.setBounds(167, 56, 71, 25);
		panel.add(lblClientes);

		JButton btCliente = new JButton("");
		btCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Solicitantes view = new Solicitantes(false);
				view.setLocationRelativeTo(null);
				view.setVisible(true);

			}
		});
		btCliente.setIcon(new ImageIcon("Icones\\User group.png"));
		btCliente.setBounds(177, 11, 56, 48);
		panel.add(btCliente);

		JLabel lblFornecedores = new JLabel("Fornecedores");
		lblFornecedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornecedores.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFornecedores.setBounds(76, 56, 91, 25);
		panel.add(lblFornecedores);

		JButton btfornecedor = new JButton("");
		btfornecedor
				.setIcon(new ImageIcon("Icones\\1448763387_Supervisor.png"));
		btfornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores view = new Fornecedores(false);
				view.setLocationRelativeTo(null);
				view.setVisible(true);
			}
		});
		btfornecedor.setBounds(93, 11, 56, 48);
		panel.add(btfornecedor);

		JLabel lblEntrada = new JLabel("Entrada");
		lblEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrada.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEntrada.setBounds(260, 56, 56, 25);
		panel.add(lblEntrada);

		JButton btEntrada = new JButton("");
		btEntrada.setToolTipText("Entrada de Materiais");
		btEntrada.setIcon(new ImageIcon("Icones\\Down.png"));
		btEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Input view = new Input();
				view.setLocationRelativeTo(null);
				view.setVisible(true);
			}
		});
		btEntrada.setBounds(260, 11, 56, 48);
		panel.add(btEntrada);

		JLabel lblSaida = new JLabel("Saida");
		lblSaida.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaida.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSaida.setBounds(341, 56, 56, 25);
		panel.add(lblSaida);

		JButton btSaida = new JButton("");
		btSaida.setToolTipText("Saida de Materiais");
		btSaida.setIcon(new ImageIcon("Icones\\Raise.png"));
		btSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Output view = new Output();
				view.setLocationRelativeTo(null);
				view.setVisible(true);

			}
		});
		btSaida.setBounds(341, 11, 56, 48);
		panel.add(btSaida);

		JLabel lblOcompra = new JLabel("Ordem De Compra");
		lblOcompra.setHorizontalAlignment(SwingConstants.CENTER);
		lblOcompra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblOcompra.setBounds(407, 56, 109, 25);
		panel.add(lblOcompra);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuyOrder view = new BuyOrder();
				view.setLocationRelativeTo(null);
				view.setVisible(true);
			}
		});
		button.setBounds(433, 11, 56, 48);
		panel.add(button);
	}
}
