package com.globalsoft.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.globalsoft.business.Facade;
import com.globalsoft.entities.Category;
import com.globalsoft.entities.CentroCusto;
import com.globalsoft.entities.Product;
import com.globalsoft.util.Util;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

public class CentroDeCusto extends JFrame {

	private static final long serialVersionUID = 7167351565071980283L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtContrato;
	private JTextField txtGestorContrato;
	private JTable table;
	private CentroCusto selecionado;
	
	public CentroCusto getSelecionado() {
		return selecionado;
	}
	
	private void createTableModel(CentroCusto[] values) {
		String[] columns = { "Código", "Nome" };
		DefaultTableModel model = new DefaultTableModel(columns, 1) {
			private static final long serialVersionUID = 8997062589770807215L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] line = null;
		if (values != null && values.length > 0) {
			for (CentroCusto c : values) {
				line = new String[columns.length];
				line[0] = String.valueOf(c.getCodigo());
				line[1] = String.valueOf(c.getContrato());
				model.addRow(line);
			}
		}
		table.setModel(model);
		table.createDefaultColumnsFromModel();
	}

	public CentroDeCusto(boolean isSelectFrame) {
		addWindowFocusListener(new WindowFocusListener() {
			
			public void windowLostFocus(WindowEvent e) {
			
				try {
					createTableModel(Facade.getInstance().findAllCentroCusto());
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
			
			public void windowGainedFocus(WindowEvent e) {	
			}
		});
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 631, 493);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(10, 120, 166, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtContrato = new JTextField();
		txtContrato.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrato.setColumns(10);
		txtContrato.setBounds(221, 120, 179, 20);
		contentPane.add(txtContrato);
		
		JLabel lblCentroDeCusto = new JLabel("N\u00BA Centro de Custo");
		lblCentroDeCusto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentroDeCusto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCentroDeCusto.setBounds(10, 102, 166, 20);
		contentPane.add(lblCentroDeCusto);
		
		JLabel lblContrato = new JLabel("Contrato");
		lblContrato.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrato.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblContrato.setBounds(221, 102, 179, 20);
		contentPane.add(lblContrato);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 605, 281);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtGestorContrato = new JTextField();
		txtGestorContrato.setHorizontalAlignment(SwingConstants.CENTER);
		txtGestorContrato.setColumns(10);
		txtGestorContrato.setBounds(449, 120, 166, 20);
		contentPane.add(txtGestorContrato);
		
		JLabel lblGestorContrato = new JLabel("Gestor do Contrato");
		lblGestorContrato.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestorContrato.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGestorContrato.setBounds(449, 99, 166, 20);
		contentPane.add(lblGestorContrato);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(8, 11, 607, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CentroCusto cat = Util.getScreenData(CentroDeCusto.this, CentroCusto.class);
				try {
					if (cat.getCodigo() == null) {
						Facade.getInstance().create(cat);
					} else {
						Facade.getInstance().update(cat);
					}
					JOptionPane.showMessageDialog(CentroDeCusto.this, "Registro salvo com sucesso.");
					Util.clearScreen(CentroDeCusto.this);
					createTableModel(Facade.getInstance().findAllCentroCusto());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(CentroDeCusto.this, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon("/Users/elias/eclipse-workspace/cccontrol/Icones/8439_32x32.png"));
		btnSalvar.setBounds(10, 11, 56, 48);
		panel.add(btnSalvar);
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = -1;
				index = table.getSelectedRow();
				if (index > -1) {
					String id = (String) table.getValueAt(index, 0);
					try {
						CentroCusto p = Facade.getInstance().findCentroCusto(
								Long.valueOf(id));
						if (p != null) {
						
						
						
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnAtualizar.setIcon(new ImageIcon("/Users/elias/eclipse-workspace/cccontrol/Icones/Button_Refresh_Icon_32.png"));
		btnAtualizar.setBounds(76, 11, 56, 48);
		panel.add(btnAtualizar);
		
		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon("/Users/elias/eclipse-workspace/cccontrol/Icones/exit.png"));
		btnSair.setBounds(541, 11, 56, 48);
		panel.add(btnSair);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = -1;
				index = table.getSelectedRow();
					try {
						if (index > -1) {
							String value = String.valueOf(table.getValueAt(index, 0));
							CentroCusto cat = new CentroCusto();
							cat.setCodigo(value);
							Optional<CentroCusto> opt = Facade.getInstance().filter(cat).stream().findFirst();
							if (opt.isPresent()) {
								Facade.getInstance().removeCentroCusto(opt.get().getId());
								JOptionPane.showMessageDialog(CentroDeCusto.this, "Registro removido com sucesso!");
								createTableModel(Facade.getInstance().findAllCentroCusto());
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(CentroDeCusto.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
			
			}
		});
		btnExcluir.setIcon(new ImageIcon("/Users/elias/eclipse-workspace/cccontrol/Icones/Delete.png"));
		btnExcluir.setBounds(142, 11, 56, 48);
		//btnExcluir.setVisible(isSelectFrame);
		panel.add(btnExcluir);
		
		JLabel lblAtualizar = new JLabel("Atualizar");
		lblAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAtualizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtualizar.setBounds(76, 55, 56, 25);
		panel.add(lblAtualizar);
		
		JLabel lblExcluir = new JLabel("Excluir");
		lblExcluir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setBounds(142, 55, 56, 25);
		panel.add(lblExcluir);
			
		JLabel lblSair = new JLabel("Sair");
		lblSair.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSair.setHorizontalAlignment(SwingConstants.CENTER);
		lblSair.setBounds(541, 55, 56, 25);
		panel.add(lblSair);
		
		if(isSelectFrame) {
			
			JButton btnSelecionar = new JButton("");
			btnSelecionar.setIcon(new ImageIcon("Icones\\Yes.png"));
			btnSelecionar.setBounds(208, 11, 56, 48);
			panel.add(btnSelecionar);
			
			JLabel lblSelecionar = new JLabel("Selecionar");
			lblSelecionar.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblSelecionar.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelecionar.setBounds(208, 55, 56, 25);
			lblSelecionar.setVisible(isSelectFrame);
			panel.add(lblSelecionar);
		}	
		
		JLabel lblSalvar = new JLabel("Salvar");
		lblSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSalvar.setBounds(10, 55, 56, 25);
		panel.add(lblSalvar);
		
		JLabel lblCentroDeCusto_1 = new JLabel("Centro De Custo");
		lblCentroDeCusto_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentroDeCusto_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCentroDeCusto_1.setBounds(273, 0, 258, 80);
		panel.add(lblCentroDeCusto_1);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
