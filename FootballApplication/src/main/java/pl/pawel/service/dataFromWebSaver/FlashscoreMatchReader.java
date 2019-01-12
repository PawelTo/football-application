package pl.pawel.service.dataFromWebSaver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FlashscoreMatchReader implements WebMatchReader {

	private String path = "https://www.flashscore.pl/";
	/**Group of supported tournament.
	 * If you want to get data from next competition, it's necessary to add it to array.
	 */
	private String supportedCompetition[][] = { { "ANGLIA", "Premier League" }, { "HISZPANIA", "LaLiga" }, { "WŁOCHY", "Serie A" }, {"AUSTRALIA","A-League"}, {"PORTUGALIA","Primeira Liga"} };

	/**Method to get loaded content of flashscore.com
	 * @return loaded content of flashscore
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public HtmlPage getFlashscorePage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_60);
		HtmlPage fsPage = webClient.getPage(path);
		webClient.waitForBackgroundJavaScript(40 * 1000);
		webClient.close();
		return fsPage;
	}
	
	/**NOT READY YET !!
	 * Method to get loaded content of yestrday's results
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void getYesterdayFlashscorePage() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_60);
		HtmlPage fsPage = webClient.getPage(path);
		webClient.waitForBackgroundJavaScript(30 * 1000);
	}
	
	/**Method to select elements of supported tournaments
	 * @param fsPage
	 * @return 
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public List<Element> getCompetitionsElements(HtmlPage fsPage) throws FailingHttpStatusCodeException, MalformedURLException, IOException {	
		Document doc = Jsoup.parse(fsPage.asXml());
		Element resultsContent = doc.getElementById("fs");
		Elements resultsTables = resultsContent.getElementsByTag("table");
		List<Element> processingCompetitions = new LinkedList<>();

		for (Element element : resultsTables) {
			Elements eCountry = element.getElementsByAttributeValue("class", "country_part");
			Elements eTournament = element.getElementsByAttributeValue("class", "tournament_part");
			String countryName = eCountry.get(0).ownText().trim().replace(":", "");
			String tournamentName = eTournament.get(0).ownText();

			for (String[] sta : supportedCompetition) {
				if (sta[0].equals(countryName) && sta[1].equals(tournamentName)) {
					processingCompetitions.add(element);
					break;
				}
			}
		}
		return processingCompetitions;
	}

	/**
	 * @param processingCompetitions
	 * @return List<Match> from flashscore
	 */
	public List<FlashscoreData> getFlashscoreData(List<Element> processingCompetitions) {
		List<FlashscoreData> returnedData = new ArrayList<>();
		for (Element leagueData : processingCompetitions) {
			Elements matchRow = leagueData.getElementsByTag("tr");
			String leagueCountry = matchRow.get(0).getElementsByAttributeValue("class", "country_part").text().replace(":", "");
			String leagueName = matchRow.get(0).getElementsByAttributeValue("class", "tournament_part").text();
			for (int i = 1; i < matchRow.size(); i++) {
				Element match = matchRow.get(i);
				String finishedStatus = match.getElementsByTag("td").get(2).text();
				if (finishedStatus.equals("Koniec")) {
					Elements matchRows = match.getElementsByTag("td");
					String homeTeam = matchRows.get(3).text();
					String awayTeam = matchRows.get(5).text();
					String score = matchRows.get(4).text();
					int dashPosition = score.indexOf("-");
					int homeTeamScore = Integer.parseInt(score.substring(0, dashPosition).trim());
					int awayTeamScore = Integer.parseInt(score.substring(dashPosition+1).trim());
					FlashscoreData readedMatch = new FlashscoreData(homeTeam, awayTeam, leagueCountry+" - "+leagueName, homeTeamScore, awayTeamScore);
					returnedData.add(readedMatch);
				}			
			}
		}
		return returnedData;
	}
}
