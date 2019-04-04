import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/*
 *Okno dialogowe sluzace, do dodawania publikacji, wywolywane w BaseFrame 
 */
public class PublicationAdder extends JPanel {
	public PublicationAdder() {
		setLayout(new BorderLayout());
		authors = new ArrayList<Author>();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,6));
		panel.add(new JLabel("Nazwa publikacji: "));
		panel.add(name = new JTextField(""));
		
		year = new JComboBox();
		year.setEditable(false);
		for (int i = 2018; i >= 1900; i--) 
			year.addItem(i);
		panel.add(new JLabel("Rok: "));
		panel.add(year);
		month = new JComboBox();
		month.setEditable(false);
		for(int i = 1; i <= 12; i++)
			month.addItem(i);
		panel.add(new JLabel("Miesiac: "));
		panel.add(month);
		day = new JComboBox();
		day.setEditable(false);
		for(int i = 1; i <= 31; i++)
			day.addItem(i);
		panel.add(new JLabel("Dzien: "));
		panel.add(day);
		panel.add(new JLabel("Punkty"));
		panel.add(points = new JTextField(""));
		panel.add(new JLabel("Wstep"));
		intro = new JTextArea(4,20);
		intro.setLineWrap(true);
		panel.add(intro);
		
		add(panel, BorderLayout.CENTER);
		
		//tworzenie przyciskow Ok i Anuluj, ktore zamykaja okno dialogowe
		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			ok = true;
			dialog.setVisible(false);
		}
		});
				
		JButton cancelButton = new JButton("Anuluj");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
					dialog.setVisible(false);
				}
			});
		//tworzenie przycisku dodawania autorow
		addAuthorsButton = new JButton("Dodaj autora");
		addAuthorsButton.addActionListener(new AddAuthorActionListener());
		add(addAuthorsButton, BorderLayout.EAST);
		
		
		//dodawanie przyciskow w poblizu poludniowej krawedzi
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	public Publication getPublication() {
//		Publication publication =  new Publication(name.getText(),((int)year.getSelectedItem()),Integer.parseInt(month.getText()),
//				Integer.parseInt(day.getText()), intro.getText(),Byte.parseByte(points.getText()));
		Publication publication =  new Publication(name.getText(), (int)year.getSelectedItem(), (int)month.getSelectedItem(),
				(int)day.getSelectedItem(), intro.getText(),Byte.parseByte(points.getText()));
		publication.addAuthors(authors);
		publication.setNumAuthors((byte)authors.size());
		authors = new  ArrayList<Author>();
		return publication;
	} 
	
	public boolean showDialog(Component parent, String title) {
		ok = false;
		
		//lokalizacja ramki nadrzednej
		Frame owner = null;
		if (parent instanceof Frame) owner = (Frame) parent;
		else owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
		
		//jesli jest to pierwszy raz lub zmienil sie uzytkownik, utworzenie nowego okna dialogowego
		if(dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
		}
		//ustawienie tytulu i wyswietlenie okna dialogowego
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
	
	private class AddAuthorActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//jesli jest to pierwszy raz, tworzy okno dialogowe
			if(adder == null) adder = new AuthorsAdder();
			
			//wyswietlanie okna dialogowego
			if (adder.showDialog(PublicationAdder.this, "Dodaj autora")) {
				authors.add(adder.getAuthor());
				
			}
		}
		
	} 
	
	private JTextField name;
	private JComboBox year;
	private JComboBox month;
	private JComboBox day;
	private JTextArea intro;
	private JTextField points;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog; 
	private AuthorsAdder adder = null;
	private JButton addAuthorsButton;
	private ArrayList<Author> authors;
}
