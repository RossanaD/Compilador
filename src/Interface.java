import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Interface {

	private JFrame frame;
	private JTextField txtPastanomearquivo;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 150, 530);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("novo [ctrl-n]");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\novo-arquivo.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textArea_1.setText(null);
				txtPastanomearquivo.setText("pasta\\nome do arquivo");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(0, 0, 150, 50);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("abrir [ctrl-o]");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\pasta.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser jfc = new JFileChooser();
			        int returnVal = jfc.showOpenDialog(null);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	file = jfc.getSelectedFile();
			        	txtPastanomearquivo.setText(file.getAbsolutePath());
		                try {
		                    FileReader entrada = new FileReader(file);
		                    textArea_1.setText(null);
		                    int lido = entrada.read();
		                    while (lido != -1) {
								textArea.append(String.valueOf((char)lido));
								lido = entrada.read();	
							}
		                    entrada.close();
						} catch (FileNotFoundException fnfe) {
							JOptionPane.showMessageDialog(frame, "Arquivo de entrada não encontrado");
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(frame, "Erro na leitura do arquivo "+file.getAbsolutePath());
						}
			        }
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(0, 47, 150, 50);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("salvar [ctrl-s]");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\floppy-disk.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file == null) {
					
				}else {
					 try {
						FileWriter saida = new FileWriter(file);	
						BufferedWriter fw = new BufferedWriter(saida);
						fw.write(textArea.getText());
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(0, 94, 150, 50);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("copiar [ctrl-c]");
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\copie-o-simbolo-de-interface-de-duas-folhas-de-papel.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(0, 141, 150, 50);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("colar [ctrl-v]");
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\colar.png"));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBounds(0, 188, 150, 50);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("recortar[ctrl-x]");
		btnNewButton_5.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_5.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\tesouras.png"));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setBounds(0, 236, 150, 50);
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("compilar [F7]");
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\compilar.png"));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("compilação de programas ainda não foi implementada");
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.setBounds(0, 284, 150, 50);
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("equipe [F1]");
		btnNewButton_7.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_7.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\trabalho-em-equipe.png"));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("Christian Trisotto Alegri, Eduardo Rebelo Degan, Rossana Ariadna Schumann Dullius");
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_7.setBackground(Color.WHITE);
		btnNewButton_7.setBounds(0, 331, 150, 50);
		panel.add(btnNewButton_7);
		
		txtPastanomearquivo = new JTextField();
		txtPastanomearquivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPastanomearquivo.setText("pasta\\nomeArquivo");
		txtPastanomearquivo.setBackground(new Color(240, 240, 240));
		txtPastanomearquivo.setEditable(false);
		txtPastanomearquivo.setBounds(0, 534, 874, 27);
		frame.getContentPane().add(txtPastanomearquivo);
		txtPastanomearquivo.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(156, 10, 718, 398);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 414, 718, 116);
		frame.getContentPane().add(scrollPane);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		scrollPane.setViewportView(textArea_1);
	}
}
