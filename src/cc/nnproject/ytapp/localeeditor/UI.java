package cc.nnproject.ytapp.localeeditor;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class UI {

	JFrame frame;
	JTextField idField;
	JTextField authorField;
	JTable table;
	private LocalizationTableModel model;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		try {
			for(LookAndFeelInfo i: UIManager.getInstalledLookAndFeels()) {
				if(i.getClassName().contains("Nimbus")) {
					UIManager.setLookAndFeel(i.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}
		*/
		frame = new JFrame();
		frame.setTitle("JTube Locale Editor v1.0.6 (for 2.1.1)");
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		panel.add(new JLabel("Locale identificator:"));
		
		idField = new JTextField("en");
		panel.add(idField);
		idField.setColumns(10);
		
		panel.add(new JLabel("Author:"));
		
		authorField = new JTextField("me");
		panel.add(authorField);
		authorField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LangBuilder.write(idField.getText(), LocalizationTableModel.map, authorField.getText(), frame);
			}
		});
		panel.add(btnNewButton);
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.load(UI.this, false);
			}
		});
		panel.add(btnLoad);
		
		lblNewLabel = new JLabel("Preset:");
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"en", "ru"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(new JFrame(), "You may lose your changes, proceed?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION)
					return;
				LocalizationTableModel.localei = comboBox.getSelectedIndex();
				model.init(UI.this);
			}
		});
		panel.add(comboBox);
		JButton btnLoadv1 = new JButton("Load v1");
		btnLoadv1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.load(UI.this, true);
			}
		});
		//panel.add(btnLoadv1);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(model = new LocalizationTableModel());
		scrollPane.setViewportView(table);
	}

}
