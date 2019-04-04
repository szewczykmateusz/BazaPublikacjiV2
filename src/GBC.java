import java.awt.GridBagConstraints;
import java.awt.Insets;
public class GBC extends GridBagConstraints {
	//tworzy obiekt GBC z podanymi wartosciami gridx i gridy
	//wszystkie pozostale wartosci sa domyslne
	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}
	//tworzy obiekt GBC z podanymi wartosciami gridx, gridy, gridwidth i gridheight
	//wszystkie pozostale wartosci sa domyslne
	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}
	
	//ustawia parametr anchor (polozenie komponentu w dostepnym miejscu)
	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}
	//ustawia parametr fill (kierunek zapelnienia)
	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}
	//ustawia parametry weight komorek
	public GBC setWeight(double weightx, double weighty) {
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}
	//ustawia dodatkowa pusta przestezen w komorce
	public GBC setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}
	//ustawia dopelnienia w komorce
	public GBC setInsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}
	//ustawia dopelnienie wewnetrzne
	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
	

}
