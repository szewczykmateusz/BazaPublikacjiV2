
public enum howToSort {
	NAME("N"), DATE("D");
	private howToSort(String abbrevation) {
		abbrevation_ = abbrevation;
	}
	public String getAbbrevation() {
		return abbrevation_;
	}
	
	
	private String abbrevation_;
	
}
