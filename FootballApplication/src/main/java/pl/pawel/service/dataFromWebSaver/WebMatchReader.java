package pl.pawel.service.dataFromWebSaver;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebMatchReader {

	private String htmlAddress;
	
	//address to be consider as data source
	private String testHtml1 = "https://www.transfermarkt.pl/premier-league/gesamtspielplan/wettbewerb/GB1/saison_id/2018";
	private String testHtml2 = "https://wyniki.interia.pl/rozgrywki-L-anglia-premier-league-sezon-zasadniczy,cid,619";
	private String testHtml3 = "https://www.flashscore.pl/pilka-nozna/francja/ligue-1/wyniki/";
	
	public WebMatchReader(String htmlAddress) {
		this.htmlAddress = htmlAddress;
	}
	
	public Document getDocument() throws IOException {
		Document doc = Jsoup.connect(testHtml1).get();
		return doc;
	}
}
