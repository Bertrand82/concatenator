package serializatorJavaSources.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import serializatorJavaSources.MainConcatenator;
import serializatorJavaSources.MainDeconcatenator;

public class SerializatorGUI {
	JFrame frame = new JFrame("Serializator");;
	JFileChooser fileChooserConcatenator = new JFileChooser();
	JFileChooser fileChooserDeConcatenator = new JFileChooser();
	
	JTextArea textArea = new JTextArea(10, 100);
	JTextField textFieldTrace = new JTextField();
	File dirSelected;
	FileFilter fileFilter = new FileFilter() {
		
		@Override
		public String getDescription() {			
			return "Directory";
		}
		
		@Override
		public boolean accept(File f) {
			return  f.isDirectory();
		}
	};

	SerializatorGUI() {
		JScrollPane scrollPaneTextArea = new JScrollPane(textArea);		
		fileChooserConcatenator.setCurrentDirectory(new File("."));
		fileChooserConcatenator.addChoosableFileFilter(fileFilter);
		fileChooserConcatenator.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooserDeConcatenator.setCurrentDirectory(new File("."));
		fileChooserDeConcatenator.addChoosableFileFilter(fileFilter);
		fileChooserDeConcatenator.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		JButton buttonChooseDirAndConcatene = new JButton("Choose dir and concatene");
		buttonChooseDirAndConcatene.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {				
				chooseFileAndConcatene();					
			}
		});
		
		JButton buttonChooseDestinationAndDeconcatene = new JButton("Choose destination and deconcatene");
		buttonChooseDestinationAndDeconcatene.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {				
				chooseDirDestinationAndDeConcatene();					
			}

			
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(buttonChooseDirAndConcatene);
		menuBar.add(buttonChooseDestinationAndDeconcatene);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(menuBar, BorderLayout.NORTH);
		panel.add(scrollPaneTextArea, BorderLayout.CENTER);
		panel.add(textFieldTrace, BorderLayout.SOUTH);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}

	private void chooseFileAndConcatene() {

		int result = fileChooserConcatenator.showOpenDialog(this.frame);
		System.err.println("ChooseFile ---------------start ----- : " + result);
		if (result == JFileChooser.APPROVE_OPTION) {// 0
			dirSelected = fileChooserConcatenator.getSelectedFile();
			System.out.println("Selected file: " + dirSelected.getAbsolutePath());
			textFieldTrace.setText("dirSelected : " + dirSelected.getName());
			SwingUtilities.invokeLater(concatene(dirSelected));
		} else {
			textFieldTrace.setText("--- "+result);
		}

	}
	
	private void chooseDirDestinationAndDeConcatene() {
		int result = fileChooserDeConcatenator.showOpenDialog(this.frame);
		System.err.println("ChooseFile decontanetor ---------------start ----- : " + result);
		if (result == JFileChooser.APPROVE_OPTION) {// 0
			dirSelected = fileChooserConcatenator.getSelectedFile();
			System.out.println("Selected file: " + dirSelected.getAbsolutePath());
			textFieldTrace.setText("dirSelected : " + dirSelected.getName());
			SwingUtilities.invokeLater(deconcatene(dirSelected));
		} else {
			textFieldTrace.setText("--- "+result);
		}

	}

	private Runnable concatene(File dir) {
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {
				String s  = MainConcatenator.getConcatenate(dir);
				textArea.setText(s);
			}
		};		
		return runnable;
	}
	
	private Runnable deconcatene(File dir) {
		Runnable runnable = new Runnable() {			
			@Override
			public void run() {
				try {
					String s = textArea.getText();
					Reader targetReader = new StringReader(s);
					BufferedReader br = new BufferedReader(targetReader);
					MainDeconcatenator.desiaralize(br, dir);
					textFieldTrace.setText("Done dir "+dir.getAbsolutePath());
				} catch (Exception e) {
					e.printStackTrace();
					textFieldTrace.setText("Exception "+e.getMessage());
				}
			}
		};		
		return runnable;
	}
}





