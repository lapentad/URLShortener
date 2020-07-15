package com.lapenta.main;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lapenta.url.UrlShortner;

@Controller
public class WebController implements WebMvcConfigurer {
	UrlShortner u = new UrlShortner();
	private String shortUrl;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(URLForm urlForm) {
		return "form";
	}

	@PostMapping("/")
	public @ResponseBody String checkPersonInfo(@Valid URLForm urlForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "Error in form";
		}
		String s = u.shortenURL(urlForm.getLongUrl());
		// String sub = s.substring(s.lastIndexOf("/")+1);

		setShortUrl(s);
		// return "redirect:/results";
		return "<a href='" + shortUrl + "'><h1>" +"www.short.com/"+shortUrl + "</h1></a>";
	}

	@GetMapping(value = "/show_all", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String showAllEntries() {
		String x = u.getAllEntries();
	       return "<html>\n" + "<header><title>Most Clicked</title></header>\n" +
	          "<body>\n <table style=\"width:50%\"><tr> "
	          + "<th>ID</th> <th>ShortURL</th> <th>LongURL</th> <th>Clicks</th> </tr>"
	          + x + "</body>\n" + "</html>";
    }

	@GetMapping(value = "/most_clicked", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String showMostClicked() {
		String x = u.getMostClicked();
        return "<html>\n" + "<header><title>Most Clicked</title></header>\n" +
          "<body>\n <table style=\"width:50%\"><tr> "
          + "<th>ID</th> <th>ShortURL</th> <th>LongURL</th> <th>Clicks</th> </tr>"
          + x + "</body>\n" + "</html>";

    }



	@GetMapping(value = "/{shortU}")
	public ResponseEntity<Void> handleRedirect(@PathVariable String shortU ,HttpServletResponse response) throws IOException {
		setShortUrl(shortU);
		String longurl = u.expandURL(shortUrl);
		if (!longurl.contains("https"))
			longurl = "https://" + u.expandURL(shortUrl);

		// response.sendRedirect(url);
		ResponseEntity<Void> x = ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(longurl))
				.build();
		System.out.println(x);
		return x;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}
