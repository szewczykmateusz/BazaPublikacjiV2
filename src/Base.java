import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Base {
	public Base()
	{
		numPublications = 0;
		publications = new ArrayList<Publication>();
		howSort = Enum.valueOf(howToSort.class, "NAME");
	}
	public void addPublication(Publication publication, final JTextArea textArea)
	{
//		if(!ifTheSame(publication)) {
			publications.add(publication);
			numPublications++;
//		}
//		else
//			textArea.append("Publikacja jest juz w bazie");
			
	}
	public void addPublication() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Podaj nazwe publikacji: ");
		String name = in.nextLine();
		System.out.println("Podaj rok publikacji: ");
		int year = in.nextInt();
		System.out.println("Podaj miesiac publikacji: ");
		int month = in.nextInt();
		System.out.println("Podaj dzien publikacji: ");
		int day = in.nextInt();
		in.nextLine();
		System.out.println("Podaj wstep publikacji: ");
		String intro = in.nextLine();
		System.out.println("Podaj ilosc punktow publikacji: ");
		byte points = in.nextByte();
		GregorianCalendar date = new GregorianCalendar(year, month - 1, day);
		Publication newPublication = new Publication(name, date.getTime(), intro, points);
		newPublication.addAuthorsToPublication();
		if(ifTheSame(newPublication) != true) {
			publications.add(newPublication);
			numPublications++;
		}
		else System.out.println("Publikacja jest juz w bazie");
		
		
	}
	
/*	public void addPublication(final JTextArea textArea) {
		textArea.append("Podaj nazwe publikacji: ");
		numPublications++;
		
	} */
/*	public void getBase()
	{
		if(numPublications != 0) {
			sortPublications();
			for(Publication a : publications) {
				a.getPublication();
				System.out.println("");
			}
		}
		else
			System.out.println("Baza pusta!");
	} */
	public String getBase()
	{
		StringBuilder baseText = new StringBuilder();
		if(numPublications != 0) {
//			sortPublications();
			for(Publication a : publications) {
				baseText.append(a.getPublication());
				
			}
		}
		else
			baseText.append("Baza pusta!");
		
		return baseText.toString();
	} 
	public ArrayList<Publication> getPublications() {
		return publications;
	}
	
	public int getNumPublications() {
		return numPublications;
	}
	public void saveBase() {
		String fileName = "baza.txt";
		if(numPublications == 0) {
			System.out.println("Baza pusta");
			return;
		}
		savePublications(fileName);
		saveAuthors(fileName);
	}
	
	public void savePublications(String fileName) 
	{
		try {
		PrintWriter out = new PrintWriter(fileName);	
//		sortPublications();
		out.println(publications.size());
		for(Publication p : publications)
			p.writePublication(out);
		out.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		
		
	}
	public void saveAuthors(String baseName) {
		StringBuilder builder = new StringBuilder("Authors");
		builder.append(baseName);
		String authorsBaseName = builder.toString();
		try {
			PrintWriter out = new PrintWriter(authorsBaseName);
		
		for(int i = 0; i < numPublications; i++) {
				publications.get(i).writeAuthors(out);
		}
		
		out.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		
	}
	
	public boolean readBase(String baseName) {
//		String baseName = getBaseName();
		if(!readPublications(baseName)) return false;
		if(!readAuthorsBase(baseName)) return false;
		
		return true;
	}
	
	public boolean readPublications(String baseName) {
		
		boolean ifGood = true;
		try {
		Scanner in = new Scanner(new FileReader(baseName));
		numPublications = in.nextInt();
		in.nextLine();
		publications = new ArrayList<Publication>(numPublications);
		for (int i = 0; i < numPublications; i++) {
		//	in.nextLine();
			Publication p = new Publication();
			p.readPublication(in);
		/*	for(int j = 0; j < p.getNumAuthors(); j++) {
				in.nextLine();
				Author a = new Author();
				a.readAuthor(in);
				p.addAuthor2(a);
				
			}*/
				
			publications.add(p);
		}
		in.close();
		} catch(IOException exception) {
			exception.printStackTrace();
			ifGood = false;
		}
		return ifGood;
		
	}
	
	public boolean readAuthorsBase(String baseName) {
		String authorsBaseName = "Authors" + baseName;
		boolean ifGood = true;
		try {
			Scanner in = new Scanner(new FileReader(authorsBaseName));
			for(int i = 0; i < numPublications; i++) {
				for(int j = 0; j < publications.get(i).getNumAuthors(); j++) {
					Author author = new Author();
					author.readAuthor(in);
					publications.get(i).addAuthorWithoutCounting(author);
				}
			}
			
			in.close();
		} catch(IOException exception) {
			exception.printStackTrace();
			ifGood = false;
		}
		return ifGood;
	}
	public  String getBaseName() {
		System.out.println("Podaj nazwe pliku, ktory chcesz odczytac");
		Scanner in = new Scanner(System.in);
	//	String baseName = in.next();
	//	in.close();
	//	return baseName;
		return in.next();
	}
	
	public boolean ifTheSame(Publication searched) { // sprawdza czy w bazie nie ma identycznej publikacji
		for(Publication publication : publications) {
			if(checkTheSame(searched, publication)) return true;
		}
		return false;
	}
	
	public boolean checkTheSame(Publication searched, Publication publication) {
		if(!searched.getName().equals(publication.getName())) return false;
		if(!searched.getIntro().equals(publication.getIntro())) return false;
		if(!searched.getName().equals(publication.getName())) return false;
		
		return true;
		
	}
	
	public void sortPublications() { // sprawdza wartosc howSort i w zaleznosci od niej wywoluje odpowiednia metoda sortujaca
		if(numPublications == 0) return;
		
		if(howSort == howToSort.DATE) sortPublicationsByName();
		else sortPublicationsByDate();
	}
	
	public void sortPublicationsByName() { //sortowanie A-Z
		boolean guard = true;
		while(guard) {
			for(int i = 0; i < numPublications - 1; i++) {
				guard = false;
				for(int j = i + 1; j < numPublications; j++) {
					if(!publications.get(i).compareByNames(publications.get(j))) {
						spare(i, j);
						guard = true;
					}
				}
			}
		}
	}
	public void sortPublicationsByDate() { //sortowanie data chronologicznie
		boolean guard = true;
		while(guard) {
			for(int i = 0; i < numPublications - 1; i++) {
				guard = false;
				for(int j = i + 1; j < numPublications; j++) {
					if(!publications.get(i).compareByDate(publications.get(j))) {
						spare(i, j);
						guard = true;
					}
				}
			}
		}
	}
	public void spare(int first,int second) { //zamienia dwie publikacje miejscami
		Publication newPublication = new Publication(publications.get(first));
		publications.set(first,publications.get(second));
		publications.set(second, newPublication);
		
	}
	public void changeSortType() { //uzytkownik ustawia rodzaj sortowania
		System.out.println(getSortType());
		boolean ifEnd = false;
		String buffer = "";
		Scanner in = new Scanner(System.in);
		while(!ifEnd) {
			System.out.println("Jesli chcesz sortowac po nazwie - NAZWA\nJesli chcesz sortowac po dacie - DATA");
			System.out.println("Jesli chcesz wrocic do menu - MENU");
			buffer = in.next().toUpperCase();
			if(buffer.equals("NAZWA")) {
				howSort = Enum.valueOf(howToSort.class, "NAME");
				return;
			}
			else if(buffer.equals("DATA")) {
				howSort = Enum.valueOf(howToSort.class, "DATE");
				return;
			}
			else if(buffer.equals("MENU")) {
				return;
			}
			System.out.println("Nieprawidlowe dane");
		}
	}
	public String getSortType() { // zwraca aktualny rodzaj sortowania 
		if(howSort == howToSort.NAME) return "Sortowanie po nazwie";
		else return "Sortowanie po dacie";
	}
	
	
	private    ArrayList<Publication> publications;
	private   int numPublications = 0; // liczba publikacji w bazie
	private howToSort howSort;

}
