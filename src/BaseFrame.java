import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class BaseFrame extends JFrame {
	public BaseFrame() {
		setTitle("PublicationsBase");
//		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		base = new Base();
		
		//sprawdzenie wymiarow ekranu
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		//ustawienie szerokosci i wysokosci ramki oraz polecenie systemowi, aby ustalil jej polozenie
		setSize(screenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		//przyciski akcji wraz z etykietami
		JButton closeButton = new JButton("Koniec");
		JLabel closeLabel = new JLabel("Wylacz program");
		JButton addButton = new JButton("Dodaj");
		JLabel addLabel = new JLabel("Dodaj publikacje");
		JButton showButton = new JButton("Wyswietl");
		JLabel showLabel = new JLabel("Wyswietl baze");
		JButton saveButton = new JButton("Zapisz");
		JLabel saveLabel = new JLabel("Zapisz baze");
		JButton readButton = new JButton("Odczytaj");
		JLabel readLabel = new JLabel("Odczytaj baze z pliku");
		JButton showPublications = new JButton("Wyszukaj");
		JLabel showPublicationsLabel = new JLabel("Wyszukaj publikacje");
		
		
		//pole tekstowe do wyswietlania informacji uzytkownikowi
		textArea = new JTextArea(10,40);
		textArea.setEditable(false);
		textArea.setLineWrap(true); //zawijanie tekstu
		textArea.setBorder(BorderFactory.createEtchedBorder());
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		//dodanie sluchaczy do przyciskow
		closeButton.addActionListener(new CloseActionListener());
		addButton.addActionListener(new AddActionListener());
		showButton.addActionListener(new ShowActionListener());
		saveButton.addActionListener(new SaveActionListener());
		readButton.addActionListener(new ReadActionListener());
		showPublications.addActionListener(new ShowPublicationsListener());
		
		add(closeLabel, new GBC(0,0));
		add(closeButton, new GBC(1,0));
		add(addLabel, new GBC(0,1));
		add(addButton, new GBC(1,1));
		add(showLabel, new GBC(0,2));
		add(showButton, new GBC(1,2));
		add(saveLabel, new GBC(0,3));
		add(saveButton, new GBC(1,3));
		add(readLabel, new GBC(0,4));
		add(readButton, new GBC(1,4));
		add(showPublicationsLabel, new GBC(0,5));
		add(showPublications, new GBC(1,5));
		
		add(scrollPane, new GBC(2,0,2,10));
		
		
	}
	
	private class CloseActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(base.getNumPublications() != 0)
				base.saveBase(); 
/*			else
				textArea.append("Baza pusta"); */
			System.exit(0);
		}
	}
	private class AddActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//jesli jest to pierwszy raz, tworzy okno dialogowe
			if(adder == null) adder = new PublicationAdder();
			
			//wyswietlanie okna dialogowego
			if (adder.showDialog(BaseFrame.this, "Nowa publikacja")) {
				//pobranie danych uzytkownika w przypadku zatwierdzenia
				base.addPublication(adder.getPublication(), textArea);
			}
		}
	}
	private class ShowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			textArea.append(base.getBase());
			textArea.append("\n\n");
		}
	}
	private class SaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(base.getNumPublications() != 0) 
				base.saveBase();
			else
				textArea.append("Baza pusta!\n");
		}
	}
	private class ReadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//jesli jest to pierwszy raz, tworzy okno dialogowe
			if(reader == null) reader = new BaseReader();
			
			//wyswietlanie okna dialogowego
			if(reader.showDialog(BaseFrame.this, "Odczytaj baze")) {
				//pobranie danych w przypadku zatwierdzenia
				if(base.readBase(reader.getName()))
					textArea.append(base.getBase());
				else
					textArea.append("Nazwa nieprawidlowa!\n");
			}
			
		}
	}
	private class ShowPublicationsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			if (base.getNumPublications() == 0) textArea.append("Baza pusta!");
			else { 
				publicationViewer = new PublicationViewer(base.getPublications());
				publicationViewer.showDialog(BaseFrame.this, "Lista publikacji");
			}
			}
			
/*			//wyswietlanie okna dialogowego
			if (publicationViewer.showDialog(BaseFrame.this, "Lista publikacji")) {
				
				
			} */
		}
		
		
	
	
	
	
	
	
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 500;
	private final JTextArea textArea;
	private Base base;
	private PublicationAdder adder = null;
	private BaseReader reader = null;
	private PublicationViewer publicationViewer = null;

}
