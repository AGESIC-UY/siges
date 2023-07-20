package com.sofis.generico.utils.generalutils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class HTMLSanitizer {

	public static String removeStyle(String html) {

		Document doc = Jsoup.parse(html);

		// transforma style bold, italic a tag
		doc.select("[style*=font-weight: bold]").wrap("<b></b>");
		doc.select("[style*=font-style: italic]").wrap("<i></i>");

		return clean(doc.body().html());
	}

	public static String clean(String html) {

		if (html == null) {
			return null;
		}

		Whitelist wl = Whitelist.relaxed();

		return Jsoup.clean(html, wl);
	}
}
