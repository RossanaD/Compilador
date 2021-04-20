import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.ScrollPaneConstants;

public class Interface {

	private JFrame frame;
	private JTextField txtPastanomearquivo;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private File file;

	public void salvar() {
		if(file == null) {
			String filename = File.separator+"tmp";
		    JFileChooser fc = new JFileChooser(new File(filename));
		    fc.showSaveDialog(frame);
		    File selFile = fc.getSelectedFile();
		    FileWriter saida;
			try {
				saida = new FileWriter(selFile);
				BufferedWriter fw = new BufferedWriter(saida);
				fw.write(textArea.getText());
				fw.close();
				txtPastanomearquivo.setText(selFile.getAbsolutePath());
				textArea_1.setText(null);	
				file = fc.getSelectedFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	

		}else {
			 try {
				FileWriter saida = new FileWriter(file);	
				BufferedWriter fw = new BufferedWriter(saida);
				fw.write(textArea.getText());
				fw.close();
				textArea_1.setText(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void Abrir() {
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
		textArea.setBorder(new NumberedBorder());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[146px][10px][1234.00px,grow,right]", "[392px,grow][8px][13.00px,grow][20px]"));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "cell 0 0,growx,aligny center");
		
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
		InputMap novo = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        novo.put(KeyStroke.getKeyStroke("ctrl pressed N"), "new");
        panel.getActionMap().put("new", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	textArea.setText(null);
				textArea_1.setText(null);
				txtPastanomearquivo.setText("pasta\\nome do arquivo");
             }
        });
		
		panel.setLayout(new MigLayout("", "[146px]", "[65px][65px][65px][65px][65px,baseline][65px][65px][65px]"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(Color.WHITE);
		panel.add(btnNewButton, "cell 0 0,grow");
		
		JButton btnNewButton_1 = new JButton("abrir [ctrl-o]");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\pasta.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Abrir();				 
			}
		});
		InputMap open = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        open.put(KeyStroke.getKeyStroke("ctrl pressed O"), "open");
        panel.getActionMap().put("open", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {          	
            	Abrir();          	
             }
        });
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBackground(Color.WHITE);
		panel.add(btnNewButton_1, "cell 0 1,grow");
		
		JButton btnNewButton_2 = new JButton("salvar [ctrl-s]");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\floppy-disk.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();				
			}
		});
		InputMap save = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        save.put(KeyStroke.getKeyStroke("ctrl pressed S"), "save");
        panel.getActionMap().put("save", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	salvar();
             }
        });
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBackground(Color.WHITE);
		panel.add(btnNewButton_2, "cell 0 2,grow");
		
		JButton btnNewButton_3 = new JButton("copiar [ctrl-c]");
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\copie-o-simbolo-de-interface-de-duas-folhas-de-papel.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.copy();
			}
		});
		InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put(KeyStroke.getKeyStroke("ctrl pressed C"), "copy");
        panel.getActionMap().put("copy", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
             }
        });
		
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBackground(Color.WHITE);
		panel.add(btnNewButton_3, "cell 0 3,grow");
		
		JButton btnNewButton_4 = new JButton("colar [ctrl-v]");
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\colar.png"));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.paste();
			}
		});
		InputMap paste = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        paste.put(KeyStroke.getKeyStroke("ctrl pressed V"), "paste");
        panel.getActionMap().put("paste", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
             }
        });
		
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBackground(Color.WHITE);
		panel.add(btnNewButton_4, "cell 0 4,grow");
		
		JButton btnNewButton_5 = new JButton("recortar[ctrl-x]");
		btnNewButton_5.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_5.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\tesouras.png"));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.cut();
			}
		});
		InputMap cut = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        cut.put(KeyStroke.getKeyStroke("ctrl pressed X"), "cut");
        panel.getActionMap().put("cut", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
             }
        });
		
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.setBackground(Color.WHITE);
		panel.add(btnNewButton_5, "cell 0 5,grow");
		
		JButton btnNewButton_6 = new JButton("compilar [F7]");
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\compilar.png"));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(null);
				boolean erro = false;
				Lexico lexico = new Lexico();
				//...
				lexico.setInput(textArea.getText());
				//...			
				try
				{
					Token t = null;
				    while ( (t = lexico.nextToken()) != null )
				    {
				    	textArea_1.append(t.toString()+"\n");				    	
				    }
				}
				catch ( LexicalError e1 )
				{
					textArea_1.setText(null);
					erro = true;
					textArea_1.append("Erro linha "+e1.getPosition()+" - "+ e1.getMessage()); 
					//converter para linha
					//message olhar ScannerConstants
				}
				if(!erro) {
					textArea_1.append("programa compilado com sucesso");
				}
			}
		});
		InputMap comp = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        comp.put(KeyStroke.getKeyStroke("F7"), "comp");
        panel.getActionMap().put("comp", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	textArea_1.setText(null);
				Lexico lexico = new Lexico();
				//...
				lexico.setInput(textArea.getText());
				//...			
				try
				{
					Token t = null;
				    while ( (t = lexico.nextToken()) != null )
				    {
				    	textArea_1.append(t.toString()+"\n");
				    }
				}
				catch ( LexicalError e1 )
				{
					textArea_1.setText(null);
					textArea_1.append("Erro na linha "+e1.getPosition()+" - "+ e1.getMessage()); 
					//converter para linha
					//message olhar ScannerConstants
				}
             }
        });
		
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_6.setBackground(Color.WHITE);
		panel.add(btnNewButton_6, "cell 0 6,grow");
		
		JButton btnNewButton_7 = new JButton("equipe [F1]");
		btnNewButton_7.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_7.setIcon(new ImageIcon("G:\\Workspaceeclipse\\Compilador\\imagens\\trabalho-em-equipe.png"));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("Christian Trisotto Alegri, Eduardo Rebelo Degan, Rossana Ariadna Schumann Dullius");
			}
		});
		InputMap equipe = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        equipe.put(KeyStroke.getKeyStroke("F1"), "team");
        panel.getActionMap().put("team", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
            	textArea_1.setText("Christian Trisotto Alegri, Eduardo Rebelo Degan, Rossana Ariadna Schumann Dullius");
             }
        });
		
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_7.setBackground(Color.WHITE);
		panel.add(btnNewButton_7, "cell 0 7,grow");
		
		txtPastanomearquivo = new JTextField();
		txtPastanomearquivo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPastanomearquivo.setText("pasta\\nomeArquivo");
		txtPastanomearquivo.setBackground(new Color(240, 240, 240));
		txtPastanomearquivo.setEditable(false);
		frame.getContentPane().add(txtPastanomearquivo, "cell 0 3 3 1,growx,aligny top");
		txtPastanomearquivo.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane_1, "cell 2 0,grow");
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane, "cell 2 2,grow");
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		scrollPane.setViewportView(textArea_1);
	}
}
