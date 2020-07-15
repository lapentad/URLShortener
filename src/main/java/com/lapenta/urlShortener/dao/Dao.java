package com.lapenta.urlShortener.dao;

public interface Dao {
	void createNewDatabase();
	void createNewTable();

	void insert(String shortUrl, String longUrl);
	void selectAll();
	void selectMostClicked();
	String getMostClicked();
	String getAll();

	void updateClick(String shortUrl);
	String selectLongUrl(String shortUrlQuery);
	boolean checkShortUrl(String shortUrlQuery);

}
