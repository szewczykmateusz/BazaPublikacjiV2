import java.util.*; 
import java.io.*;
public class Author {
	public Author()
	{
		name = "Jakis";
		surname = "Autor";
		institute = "ins";
	}
	public Author(String n, String s, String i)
	{
		name = n;
		surname = s;
		institute = i;
	}
	public Author(Author author) {
		name = author.getName();
		surname = author.getSurName();
		institute = author.getInstitute();
	}
	public String getName()
	{
		return name;
	}
	public String getSurName()
	{
		return surname;
	}
	public String getInstitute()
	{
		return institute;
	}
	public void getAuthor()
	{
		System.out.println("Autor:");
		System.out.println("Imie: " + name);
		System.out.println("Nazwisko:" + surname);
		System.out.println("Instytut:" + institute);
	} 
	public void readAuthor(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		name = tokens[0];
		surname = tokens[1];
		institute = tokens[2];
		
	}
	public boolean Compare(Author author) { // jezeli porownywany obiekt jest mniejszy alfabetycznie zwraca true
											// jezeli sa takie same zwraca true
		if(surname.compareTo(author.getSurName()) < 1) return true;
		if(surname.compareTo(author.getSurName()) > 1) return false;
		if(name.compareTo(author.getName()) < 1) return true;  
		if(name.compareTo(author.getName()) > 1) return false;
		if(institute.compareTo(author.getInstitute()) < 1) return true;
		if(institute.compareTo(author.getInstitute()) > 1) return false;
		
		return true;
	}
	
	
	
	
	private String name;
	private String surname;
	private String institute;
	
}
