package cc.nnproject.ytapp.localeeditor;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;

public class UI {

	JFrame frame;
	JTextField idField;
	JTextField authorField;
	JTable table;
	private LocalizationTableModel model;

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
		frame = new JFrame();
		frame.setTitle("JTube Locale Editor v1.0.2 (for 1.4.0)");
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
