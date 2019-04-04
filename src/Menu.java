import java.util.*;
public class Menu {
	public Menu() {
		ifEnd = false;
	}
	public boolean End() {
		return ifEnd;
	}
	public Base ShowOptions(Base base) {
		System.out.println("Wybierz opcje, ktora chcesz wybrac wpisujac odpowiednie slowo kluczowe");
		System.out.println("DODAJ - dodawanie publikacji do bazy");
		System.out.println("WYSWIETL - wyswietlanie bazy");
	    System.out.println("ZAPISZ - zapisanie bazy do pliku");
	    System.out.println("ODCZYTAJ - odczytanie bazy z pliku");
	    System.out.println("JAKSORTOWAC - wybor parametru, po ktorym maja byc sortowane publikacje (nazwa lub data)"); 
	    System.out.println("KONIEC - zakonczenie dzialania programu");
	    Scanner in = new Scanner(System.in);
	    String buffer = in.next().toUpperCase(); //bufor pobierajacy slowo od uzytkownika
	    
	    if(buffer.equals("KONIEC")) {
	    	ifEnd = true;
	    	base.saveBase();
	    	return base;
	    }
	    if(buffer.equals("DODAJ")) {
	    	base.addPublication();
	    	return base;
	    }
	    if(buffer.equals("WYSWIETL")) {
	    	base.getBase();
	    	return base;
	    }
	    if(buffer.equals("ZAPISZ")) {
	    	base.saveBase();
	    	return base;
	    }
	    if(buffer.equals("ODCZYTAJ")) {
//	    	base.readBase();
	    	return base;
	    }
	    if(buffer.equals("JAKSORTOWAC")) {
	    	base.changeSortType();
	    	return base;
	    }


	    
		
		
		return base;
	}
	
	private boolean ifEnd;

}
