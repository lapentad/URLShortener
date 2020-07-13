package url;

import java.util.Random;

import database.DBhandler;

public class UrlShortner {

	DBhandler d = new DBhandler("test.db", "URLS");

	private String domain; // A domain to put before the genereted key
	private char myChars[]; // This array is used for character to number mapping
	private Random myRand; // Random object used to generate random integers
	private int keyLength; // the key length in URL defaults to 8

	public UrlShortner() {
		myRand = new Random();
		keyLength = 8;
		myChars = new char[62];
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 10) {
				j = i + 48;
			} else if (i > 9 && i <= 35) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			myChars[i] = (char) j;
		}
		domain = "www.short.com";
	}

	public String shortenURL(String longURL) {
		String shortURL = "";
		shortURL = domain + "/" + getKey(longURL);
		return shortURL;
	}

	public String expandURL(String shortURL) {
		if (shortURL.contains(domain))
			shortURL = shortURL.replace(domain+"/", "");
		String longURL = "";
		longURL = d.selectLongUrl(shortURL);
		d.updateClick(shortURL);
		return longURL;
	}

	private String getKey(String longURL) {
		String key;
		key = generateKey();
		d.insert(key, longURL);
		return key;
	}

	private String generateKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += myChars[myRand.nextInt(62)];
			}
			// System.out.println(d.checkShortUrl("test.db", key));
			if (!d.checkShortUrl(key)) {
				flag = false;
			}
		}
		return key;
	}
}