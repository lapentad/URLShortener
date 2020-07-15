package web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class URLForm {

	@NotNull
	@Size(min=2, max=300)
	private String longUrl;

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	@Override
	public String toString() {
		return "URL: " + this.longUrl;
	}
}
