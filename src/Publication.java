import java.util.*;
import java.io.*;
public class Publication {
	
	public Publication()
	{
		name = "Publikacja";
		GregorianCalendar d = new GregorianCalendar();
		date = d.getTime();
		intro = "Jakis wstep...";
		numAuthors = 0;
		points = 0;
		authors = new ArrayList<Author>();
	}
	
	public Publication(String name, Date date, String intro, byte points)
	{
		this.name = name;
		this.date = date;
		this.intro = intro;
		this.points = points;
		authors = new ArrayList<Author>();
		numAuthors = 0;
	}
	public Publication(String name, int year, int month, int day, String intro, byte points)
	{
		this.name = name;
		GregorianCalendar d = new GregorianCalendar(year, month - 1, day);
		date = d.getTime();
		this.intro = intro;
		this.points = points;
		authors = new ArrayList<Author>();
		numAuthors = 0;
	}
	public Publication(Publication publication) {
		name = publication.getName();
		date = publication.getDate();
		intro = publication.getIntro();
		points =  publication.getPoints();
		authors = publication.getAuthors();
		numAuthors = publication.getNumAuthors();
	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}
	public String getName()
	{
		return name;
	}
	public Date getDate()
	{
		return date;
	}
	public String getIntro()
	{
		return intro;
	}
	public byte getNumAuthors()
	{
		return numAuthors;
	}
	public byte getPoints()
	{
		return points;
	}
	public void setNumAuthors(byte numAuthors) {
		this.numAuthors = numAuthors;
	}
/*	public void getPublication()
	{
		System.out.println("Publikacja");
		System.out.println("Nazwa publikacji: " + getName());
		System.out.println("Data publikacji: " + getDate());
		System.out.println("Wstep publikacji: " + getIntro());
		System.out.println("Punkty publikacji: " + getPoints());
	//	System.out.println("Ilosc autorow" + getNumAuthors());
		sortAuthors();
		showAuthors();
	} */
	public String getPublication() {
		StringBuilder publicationText = new StringBuilder();
		publicationText.append("Publikacja\n");
		publicationText.append("Nazwa publikacji: ");
		publicationText.append(getName());
		publicationText.append("\n");
		publicationText.append("Data publikacji: ");
		publicationText.append(getDate());
		publicationText.append("\n");
		publicationText.append("Wstep publikacji: ");
		publicationText.append(getIntro());
		publicationText.append("\n");
		publicationText.append("Punkty publikacji: ");
		publicationText.append(getPoints());
		publicationText.append("\n");
		
		//wyswietlanie autorow
		publicationText.append("\nAutorzy:\n");
		for(Author a : authors) {
			publicationText.append("Autor\n");
			publicationText.append("Imie: ");
			publicationText.append(a.getName());
			publicationText.append("\n");
			publicationText.append("Nazwisko: ");
			publicationText.append(a.getSurName());
			publicationText.append("\n");
			publicationText.append("Instytut: ");
			publicationText.append(a.getInstitute());
			publicationText.append("\n");
		}
		publicationText.append("\n\n");
		
		
		return publicationText.toString();
	}
	public void addAuthor(Author author) 
	{
		authors.add(author);
		numAuthors++;
	}
	public void addAuthorWithoutCounting(Author author) //dodawanie autora po pobraniu z pliku, kiedy znamy juz ilosc autorow i nie musimy jej zwiekszac
	{
		authors.add(author);
	}
	public void addAuthors(ArrayList<Author> authors) { //dodawanie listy autorow
		this.authors = authors;
	}
	public void showAuthors()
	{
		if(numAuthors != 0)
		for(Author a : authors)
			a.getAuthor();
		else
			System.out.println("Publikacja nie ma autorow!");
	}
	public void writePublication(PrintWriter out) {
		GregorianCalendar d = new GregorianCalendar();
		d.setTime(date);
		out.println(name + "|" + d.get(Calendar.YEAR) + "|" + (d.get(Calendar.MONTH) + 1) + "|"
		+ d.get(Calendar.DAY_OF_MONTH) + "|" + intro + "|" + points + "|" + numAuthors);
	//	out.println(numAuthors);
	//	for(int i = 0; i < numAuthors; i++)
	//		out.println(authors.get(i).getName() + "|" + authors.get(i).getSurName() + "|" + authors.get(i).getInstitute());
		
	}
	
	public void writeAuthors(PrintWriter out) {
//		sortAuthors();
		for(byte i = 0; i < numAuthors; i++)
			out.println(authors.get(i).getName() + "|" + authors.get(i).getSurName() + "|" + authors.get(i).getInstitute());
				
	}
	
	public void readPublication(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		name = tokens[0];
		int year = Integer.parseInt(tokens[1]); 
		int month = Integer.parseInt(tokens[2]); 
		int day = Integer.parseInt(tokens[3]); 
		intro = tokens[4];
		points = Byte.parseByte(tokens[5]);
		GregorianCalendar d = new GregorianCalendar(year, month - 1, day);
		date = d.getTime();
		numAuthors = Byte.parseByte(tokens[6]);
		
	}
	public void addAuthorsToPublication() {
		boolean addition = true;
		while(addition) {
			System.out.println("Jesli chcesz dodac autora - DODAJ\nW innym wypadku zakonczysz dodawanie autorow");
			Scanner in = new Scanner(System.in);
			String buffer = in.next().toUpperCase();
			if(buffer.equals("DODAJ")) addAuthor();
			else addition = false;
		}
		
	}
	
	public void addAuthor() {
		Scanner in = new Scanner(System.in);
		System.out.println("Podaj imiona autora: ");
		String name = in.nextLine();
		System.out.println("Podaj nazwisko publikacji: ");
		String surname = in.next();
		in.nextLine();
		System.out.println("Podaj nazwe instytutu: ");
		String institute = in.nextLine();
		Author author = new Author(name, surname, institute);
		authors.add(author);
		numAuthors++;
	}
	
	public void sortAuthors() {
		if(numAuthors == 0) return;
		boolean guard = true;
		while(guard) {
			for(int i = 0; i < numAuthors - 1; i++) {
				guard = false;
				for(int j = i + 1; j < numAuthors; j++) {
					if(!authors.get(i).Compare(authors.get(j))) {
						spare(i, j);
						guard = true;
					}
				}
			}
		} 
	}
	public void spare(int first,int second) {
		Author newAuthor = new Author(authors.get(first));
		authors.set(first,authors.get(second));
		authors.set(second, newAuthor);
		
	}
	public boolean compareByNames(Publication publication) { // jezeli porownywany obiekt jest mniejszy alfabetycznie zwraca true
											// jezeli sa takie same zwraca true
		if(name.compareTo(publication.getName()) < 1) return true;
		if(name.compareTo(publication.getName()) > 1) return false;
		if(date.getTime() < publication.getDate().getTime()) return true;  
		if(date.getTime() > publication.getDate().getTime()) return false;

		return true;
	}
	
	public boolean compareByDate(Publication publication) { // jezeli porownywany obiekt jest wczesniejszy chronologicznie zwraca true
															// jezeli sa takie same zwraca true
		if(date.getTime() < publication.getDate().getTime()) return true;  
		if(date.getTime() > publication.getDate().getTime()) return false;
		if(name.compareTo(publication.getName()) < 1) return true;
		if(name.compareTo(publication.getName()) > 1) return false;

		return true;
	}
	
/*	public String toString() {
		return getClass().getName() + "[name_=" + name_ + ",date_" + date_.getTime() + ",intro_" + intro_ + "," + getAuthors(); 
	} */
	
	
	
	private String name; 
	private Date date; // data publikacji
	private String intro; 
	private ArrayList<Author> authors; // lista autorow publikacji
	private byte numAuthors = 0; // liczba autorow
	private byte points; 
}
