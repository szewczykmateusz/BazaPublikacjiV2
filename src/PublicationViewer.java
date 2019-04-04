import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/*
 * Okno modalne pozwalajace przegladac poszczegolne publikacje
 */
public class PublicationViewer extends JPanel {
	public PublicationViewer(ArrayList<Publication> publications) {
		setLayout(new BorderLayout());
		this.publications = publications;
		publicationsComboBox = new JComboBox();
		for(int i = 0; i < publications.size(); i++) 
			publicationsComboBox.addItem(i+1 + "." + publications.get(i).getName());
		
		
		
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
		
		add(publicationsComboBox, BorderLayout.CENTER);
		//dodawanie przyciskow w poblizu poludniowej krawedzi
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
		
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.EAST);
		
		publicationsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean ifEnd = false;
				int numberOfDigits = 0; //ilosc cyfr, z ktorych sklada numer wybranego elementu
				String selectedItem = publicationsComboBox.getSelectedItem().toString();
				while(!ifEnd) {
					if(selectedItem.substring(numberOfDigits, numberOfDigits+1).equals(".")) ifEnd = true;
					else numberOfDigits++;
				}
				int publicationNumber = Integer.parseInt(selectedItem.substring(0,numberOfDigits));
				textArea.append(publications.get(publicationNumber-1).getPublication());
			}
		});
		
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
	
	
	
	
	private boolean ok;
	private JDialog dialog; 
	private JComboBox publicationsComboBox;
	private JButton okButton;
	private final JTextArea textArea = new JTextArea(8,40);
	ArrayList<Publication> publications;

}
