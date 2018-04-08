import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * File: SeaPortProgram.java 
 * Author: Maria Tkacheva 
 * Date: April 8, 2018
 * Assignment:Project 2 
 * UMUC CMSC 335 
 * SeaPortProgram class reads selected file,
 * creates data based on the read file, constructs GUI and provides and
 * ActionPerformed method for the buttons
 */
public class SeaPortProgram extends JFrame {

	private static JFrame mainFrame;
	private static JPanel panel, panel2, panel3;
	private static JTextArea textArea;
	private static JScrollPane scrollPane;
	private static World world = null;
	private static boolean firstLine = true;
	private static JTextField search;
	private static JButton searchButton, clear, sortButton;
	private static JRadioButton index, name, skill;
	private static JLabel label;
	private static ButtonGroup group, group2;
	private static JComboBox sortOptions, searchOptions;
	private static String[] sortOptionsList = { "Name", "Index", "Skill", "Length", "Width", "Weight", "Draft" };
	private static String[] searchOptionsList = { "Port", "Dock", "Ship", "Person" };

	/**
	 * Main method to start the application Read selected file and process data
	 * using object of a world class Display GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		readFile();

		displayGUI();
		
	}// end method main

	/**
	 * readFile method will provide an option to choose ports .txt user and 
	 * create an object of a world class that will be constructed based on the selected file
	 */
	private static void readFile() {
		try {
			Scanner scanner = new Scanner(new File(selectFile()));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				line = line.replaceAll("^\\s+", "");
				if (line.length() > 0 && line.charAt(0) != '/') {
					if (line.contains("port") && firstLine) {
						String fLine = line.substring(line.indexOf(" "));
						firstLine = false;
						world = new World(new Scanner(fLine));
						world.process(line);

					} else {
						world.process(line);
					}
				}
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}// end method readFile

	/**
	 * Method to create GUI and define actions for buttons
	 */
	private static void displayGUI() {
		// initialize variables
		mainFrame = new JFrame();
		panel = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		textArea = new JTextArea();
		search = new JTextField(15);
		searchButton = new JButton("search");
		sortButton = new JButton("sort");
		index = new JRadioButton("index");
		name = new JRadioButton("name");
		skill = new JRadioButton("skill");
	    label = new JLabel("Select sort by oprtion: ");
		searchOptions = new JComboBox(searchOptionsList);

		group = new ButtonGroup();
		group2 = new ButtonGroup();
		group2.add(searchButton);
		group2.add(clear);
		group.add(index);
		group.add(name);
		group.add(skill);
		clear = new JButton("clear");
		sortOptions = new JComboBox(sortOptionsList);
		panel.add(label);
		panel.add(sortOptions);
		panel.add(sortButton);
		textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
		panel.setBackground(Color.ORANGE);
		mainFrame.pack();
		mainFrame.setSize(700, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setTitle("Sea Port Program");

		// add components to panel
		panel2.add(searchOptions);
		panel2.setBackground(Color.ORANGE);
		panel2.add(index);
		panel2.add(name);
		panel2.add(skill);
		panel2.add(search);
		panel2.add(searchButton);
		panel2.add(clear);
		mainFrame.add(panel2, BorderLayout.PAGE_END);

		// put scroll bars around the text area
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainFrame.add(scrollPane, BorderLayout.CENTER);
		mainFrame.add(panel, BorderLayout.NORTH);
		mainFrame.add(panel2, BorderLayout.SOUTH);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textArea.setText(world.toString());

		/**
		 * clear searched text and display original list when clear button is clicked
		 */
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(world.toString());
				search.setText("");

			}
		});

		/**
		 * perform search based on the button selection when search button is clicked
		 */
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (search.getText() != null) {
						String searchTerm = (String) searchOptions.getSelectedItem();
						if (index.isSelected() && searchTerm.equals("Port")) {
							textArea.setText("Found Port with index " + search.getText() + "\n"
									+ (world.getPortByIndex(Integer.parseInt(search.getText()))).toString());
						} else if (index.isSelected() && searchTerm.equals("Dock")) {
							textArea.setText("Found Dock with index " + search.getText() + "\n"
									+ (world.getDockByIndex(Integer.parseInt(search.getText()))).toString());
						} else if (index.isSelected() && searchTerm.equals("Ship")) {
							textArea.setText("Found Ship with index " + search.getText() + "\n"
									+ (world.getShipByIndex(Integer.parseInt(search.getText()))).toString());
						} else if (index.isSelected() && searchTerm.equals("Person")) {
							textArea.setText("Found Person with index " + search.getText() + "\n"
									+ (world.getPersonByIndex(Integer.parseInt(search.getText()))).toString());
						} else if (name.isSelected()) {
							textArea.setText(world.searchByName(search.getText()));
						} else if (skill.isSelected()) {
							textArea.setText(world.searchBySkill(search.getText()));
						}
					}
				} catch (NumberFormatException n) {
					textArea.setText("Search returned 0 results. \nPlease enter valid numeric value for index.");
				}
			}
		});

		/**
		 * perform sort based on the sort drop down selection when sort button is
		 * clicked
		 */
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sortTerm = (String) sortOptions.getSelectedItem();
				if(sortTerm.equals("Weight")) {
					world.sortByWeight();
					textArea.setText(world.toString());
				}else if(sortTerm.equals("Length")) {
					world.sortByLength();
					textArea.setText(world.toString());
				}else if(sortTerm.equals("Draft")) {
					world.sortByDraft();
					textArea.setText(world.toString());
				}else if(sortTerm.equals("Width")) {
					world.sortByWidth();
					textArea.setText(world.toString());
				}else if(sortTerm.equals("Name")) {
					world.sortByName();
					textArea.setText(world.toString());
				}else if(sortTerm.equals("Skill")) {
					world.sortBySkill();
					textArea.setText(world.toString());
				}else if(sortTerm.equals("Index")) {
					world.sortByIndex();
					textArea.setText(world.toString());
				}
			}
		});
	}// end method displayGUI

	/**
	 * Selects the file using FileChooser
	 * 
	 * @return File
	 */
	private static String selectFile() {
		JFileChooser jfc = new JFileChooser(".");
		jfc.setDialogTitle("Please select Sea Port data file.");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String file = jfc.getSelectedFile().toString();
			return file;
		} else {
			System.exit(0);
		}
		return null;
	}// end method selectFile

}// end class SeaPortProgram
