package pl.pawel.service.dataFileSaver;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pl.pawel.model.Match;

public class XMLFileSaver implements IFileSaver {

	private String path = "C:\\Users\\Pawel\\Desktop\\DirToLearn\\Saved_files\\";
	private String fileName = "CreatedFile.XML";
	
	@Override
	public void saveMatch(Match game) {
		
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			Element root = document.createElement("games");
			document.appendChild(root);
			
			Element match = createSingleMatchElement(document, game);			
			root.appendChild(match);
			
            //transform the DOM Object to a XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(path+fileName));
			
			transformer.transform(domSource, streamResult);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveListOfMatches(List<Match> listOfGames) {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			Element root = document.createElement("games");
			document.appendChild(root);
			
			for (Match game : listOfGames) {
				Element match = createSingleMatchElement(document, game);			
				root.appendChild(match);
			}
			
            //transform the DOM Object to a XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(path+fileName));
			
			transformer.transform(domSource, streamResult);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Element createSingleMatchElement(Document document, Match game) {
		Element match = document.createElement("match");
		
		Attr attr = document.createAttribute("id");
		attr.setValue(game.getId().toString());
		match.setAttributeNode(attr);
		
		Element dateOfGame = document.createElement("Date");
		LocalDate dateOfGame2 = game.getDateOfGame();
		dateOfGame.appendChild(document.createTextNode(
				(dateOfGame2 == null) ?  "Brak daty" : dateOfGame2.toString())
				);
		match.appendChild(dateOfGame);
		
		Element homeTeam = document.createElement("Home_Team");
		homeTeam.appendChild(document.createTextNode(game.getHomeTeam().getClubName()));
		match.appendChild(homeTeam);
		
		Element awayTeam = document.createElement("Away_Team");
		awayTeam.appendChild(document.createTextNode(game.getAwayTeam().getClubName()));
		match.appendChild(awayTeam);
		
		Element homeTeamScore = document.createElement("Home_Team_Score");
		homeTeamScore.appendChild(document.createTextNode(String.valueOf(game.getHomeTeamScore())));
		match.appendChild(homeTeamScore);
		
		Element awayTeamScore = document.createElement("Away_Team_Score");
		awayTeamScore.appendChild(document.createTextNode(String.valueOf(game.getAwayTeamScore())));
		match.appendChild(awayTeamScore);
		
		Element competition = document.createElement("Competition");
		competition.appendChild(document.createTextNode(game.getCompetition().getCompetitionName()));
		match.appendChild(competition);
		
		Element attendance = document.createElement("Attendance");
		attendance.appendChild(document.createTextNode(String.valueOf(game.getAttendance())));
		match.appendChild(attendance);
		
		return match;
	}
}
