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
		frame.setTitle("JTube Locale Editor v1.0 (for 1.4.0)");
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		panel.add(new JLabel("Locale identificator:"));
		
		final JTextField idField = new JTextField("en");
		panel.add(idField);
		idField.setColumns(10);
		
		panel.add(new JLabel("Author:"));
		
		final JTextField authorField = new JTextField("me");
		panel.add(authorField);
		authorField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LangBuilder.write(idField.getText(), LocalizationTableModel.map, authorField.getText());
			}
		});
		panel.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTable table = new JTable(new LocalizationTableModel());
		scrollPane.setViewportView(table);
	}

}
