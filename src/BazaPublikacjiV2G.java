import java.util.*;

import javax.swing.JFrame;

import java.awt.EventQueue;
import java.io.*;
public class BazaPublikacjiV2G {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				BaseFrame frame = new BaseFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
/*		Menu menu = new Menu();
		Base base = new Base();
		while(menu.End() == false) {
			menu.ShowOptions(base);
		}
		System.out.println("Program skonczyl dzialanie");  */
		
		
		
	}

}
