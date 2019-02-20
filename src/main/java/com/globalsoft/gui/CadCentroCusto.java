package com.globalsoft.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.globalsoft.business.Facade;
import com.globalsoft.entities.CentroCusto;
import com.globalsoft.util.Util;

public class CadCentroCusto extends JFrame {

	private JPanel contentPane;
	private JTextField tfCentroCusto;
	private JTextField tfContrato;
	private JTextField tfGestorContrato;
	private JTextField txtCodigo;

	private void clearScreen(){
		txtCodigo.setText("");
		tfCentroCusto.setText("");
		tfContrato.setText("");
		tfGestorContrato.setText("");
		
	}
	
	private CentroCusto getScreenData(){
		CentroCusto result = new CentroCusto();
		
		String id = txtCodigo.getText();
		
		if(!Util.isNullOrEmpty(id)){
			result.setId(Long.valueOf(txtCodigo.getText()));
		}
		
		result.setCodigo(tfCentroCusto.getText());
		result.setContrato(tfContrato.getText());
		result.setGestorContrato(tfGestorContrato.getText());
		
		return result;
	}
	
	private void setScreenData(CentroCusto centrocusto){
		if(centrocusto.getId() != null){
			txtCodigo.setText(String.valueOf(centrocusto.getId()));
		}
		
		tfCentroCusto.setText(centrocusto.getCodigo());
		tfContrato.setText(centrocusto.getContrato());
		tfGestorContrato.setText(centrocusto.getGestorContrato());
	}
	
	public CadCentroCusto(CentroCusto centrocusto){
		this();
		setScreenData(centrocusto);
	}
	
	public CadCentroCusto() {
		
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setBounds(100, 100, 302, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Centro de Custo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 115, 275, 14);
		contentPane.add(lblNewLabel);
		
		tfCentroCusto = new JTextField();
		tfCentroCusto.setHorizontalAlignment(SwingConstants.CENTER);
		tfCentroCusto.setBounds(10, 140, 275, 20);
		contentPane.add(tfCentroCusto);
		tfCentroCusto.setColumns(10);
		
		JLabel lblNomeCentroDe = new JLabel("Contrato");
		lblNomeCentroDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCentroDe.setBounds(10, 176, 275, 14);
		contentPane.add(lblNomeCentroDe);
		
		tfContrato = new JTextField();
		tfContrato.setHorizontalAlignment(SwingConstants.CENTER);
		tfContrato.setBounds(10, 201, 275, 20);
		contentPane.add(tfContrato);
		tfContrato.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Gestor Contrato");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 232, 275, 14);
		contentPane.add(lblNewLabel_1);
		
		tfGestorContrato = new JTextField();
		tfGestorContrato.setHorizontalAlignment(SwingConstants.CENTER);
		tfGestorContrato.setBounds(10, 257, 275, 20);
		contentPane.add(tfGestorContrato);
		tfGestorContrato.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 275, 92);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton brnSalvar = new JButton("");
		brnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CentroCusto cc = getScreenData();
					if (cc.getId() != null ) {
						Facade.getInstance().update(cc);
					}else{
						Facade.getInstance().create(cc);
					}
					JOptionPane.showMessageDialog(CadCentroCusto.this, "Registro salvo com sucesso !");					
					clearScreen();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(CadCentroCusto.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		brnSalvar.setBounds(10, 11, 58, 50);
		panel.add(brnSalvar);
		
		JButton btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(205, 11, 58, 50);
		panel.add(btnSair);
		
		JLabel lblSalvar = new JLabel("Salvar");
		lblSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalvar.setBounds(10, 67, 58, 14);
		panel.add(lblSalvar);
		
		JLabel lblSair = new JLabel("Sair");
		lblSair.setHorizontalAlignment(SwingConstants.CENTER);
		lblSair.setBounds(205, 67, 58, 14);
		panel.add(lblSair);
		
		JButton btnNovo = new JButton("");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearScreen();
			}
		});
		btnNovo.setBounds(75, 11, 58, 50);
		panel.add(btnNovo);
		
		JLabel lblNovo = new JLabel("Novo");
		lblNovo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovo.setBounds(75, 67, 58, 14);
		panel.add(lblNovo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(145, 11, 48, 50);
		panel.add(txtCodigo);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
	}
}
