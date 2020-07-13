package main;

import java.util.Scanner;

import database.DBhandler;
import url.UrlShortner;

public class AppMain {

	public static void main(String args[]) {
		DBhandler d = new DBhandler("test.db", "URLS");
		System.out.println("   _____  __                  __   __  __ ____   __ \r\n"
				+ "  / ___/ / /_   ____   _____ / /_ / / / // __ \\ / / \r\n"
				+ "  \\__ \\ / __ \\ / __ \\ / ___// __// / / // /_/ // /  \r\n"
				+ " ___/ // / / // /_/ // /   / /_ / /_/ // _, _// /___\r\n"
				+ "/____//_/ /_/ \\____//_/    \\__/ \\____//_/ |_|/_____/");
		int answer = 0;
		while (answer != 3) {
			UrlShortner u = new UrlShortner();
			System.out.println("\n1.To short URL\n2.To long URL\n3.Exit\n4.Print DB\n5.Print Most Clicked");
			Scanner keyb = new Scanner(System.in);
		    if(keyb.hasNext())
		    	answer = keyb.nextInt();
			switch (answer) {
			case 1:
				f1_toShort(u, d);
				break;
			case 2:
				f2_toLong(u, d);
				break;
			case 3:
				keyb.close();
				break;
			case 4:
				d.selectAll();
				break;
			case 5:
				d.selectMostClicked();
				break;
			case 6:
				d.createNewDatabase();
				d.createNewTable();
				d.insert("1h3d9bM1p", "www.test.com/image.png");
				break;
			default:
				System.out.println("Invalid");
				break;
			}
		}
	}

	public static void f1_toShort(UrlShortner u, DBhandler d) {
		System.out.println("Insert long URL: \n");
		String answer = "";
		@SuppressWarnings("resource")
		Scanner keyb1 = new Scanner(System.in);
		if(keyb1.hasNext())
			answer = keyb1.nextLine();
		String shortUrl = u.shortenURL(answer);
		System.out.println(shortUrl);

	}

	public static void f2_toLong(UrlShortner u, DBhandler d) {
		System.out.println("Insert short URL: \n");
		String answer = "";
		@SuppressWarnings("resource")
		Scanner keyb2 = new Scanner(System.in);
		if(keyb2.hasNext())
			answer = keyb2.nextLine();
		String expandedUrl = u.expandURL(answer);
		System.out.println(expandedUrl);

	}

}
