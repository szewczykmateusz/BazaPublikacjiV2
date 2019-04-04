import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * okno dialogowe sluzace do pobrania nazwy bazy, ktora uzytkownik chce odczytac
 * wywolywana z BaseFrame
 */
public class BaseReader extends JPanel {
	public BaseReader() {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(new JLabel("Nazwa bazy: "));
		panel.add(name = new JTextField(""));
		
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
		//dodawanie przyciskow w poblizu poludniowej krawedzi
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	public String getName() {
		return name.getText();
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
	
	
	
	private JTextField name;
	private boolean ok;
	private JDialog dialog; 
	private JButton okButton;

}
