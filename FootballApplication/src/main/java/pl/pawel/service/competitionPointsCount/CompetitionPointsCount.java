package pl.pawel.service.competitionPointsCount;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphbuilder.struc.LinkedList;

import pl.pawel.model.Competition;
import pl.pawel.model.Match;
import pl.pawel.repository.MatchRepository;

@Service
public class CompetitionPointsCount {

	private List<Match> matches;
	private Competition competition;
	private List<LeagueTableRow> table;

	@Autowired
	private MatchRepository matchRepository;

	public int getMatchesFromCompetition() {
		// int numberOfCompetition = matchRepository.countByCompetition();
		Competition competition = new Competition();
		competition.setId((long) 2);
		long numberOfMatches = matchRepository.countByCompetition(competition);
		matches = matchRepository.findByCompetition(competition);
		return (int) numberOfMatches;
	}

	public LeagueTableRow getTableOfCompetition(Competition competition) {
		return new LeagueTableRow();
	}

	public LeagueTableRow getTableOfCompetition(long id) {
		competition = createCompetitionFromLong(id);
		return getTableOfCompetition(competition);
	}

	private List<Match> getMatchesFromCompetition(Competition competition) {
		matches = matchRepository.findByCompetition(competition);
		return matches;
	}

	public List<LeagueTableRow> getTableByCompetitionId(long id) {
		matches = matchRepository.findByCompetition(createCompetitionFromLong(id));
		table = createTable(matches);
		return table;
	}

	public List<LeagueTableRow> createTable(List<Match> matches) {
		matches = getMatchesFromCompetition(competition);
		table = new ArrayList<>();
		for (Match match : matches) {
			int homeTeamPosition = -1;
			int awayTeamPosition = -1;
			// check if teams form match already exist in table
			int i = 0;
			if (!table.isEmpty()) {
				while (i <= table.size() && (homeTeamPosition < 0 || awayTeamPosition < 0)) {
					if (table.get(i).getTeamNeame().equals(match.getHomeTeam().getClubName()))
						homeTeamPosition = i;
					if (table.get(i).getTeamNeame().equals(match.getAwayTeam().getClubName()))
						awayTeamPosition = i;
					i++;
				}
			}
			if (homeTeamPosition < 0) {
				LeagueTableRow homeTeam = new LeagueTableRow();
				homeTeam.setTeamNeame(match.getHomeTeam().getClubName());
				table.add(homeTeam);
				homeTeamPosition = table.size() - 1;
			}
			if (awayTeamPosition < 0) {
				LeagueTableRow awayTeam = new LeagueTableRow();
				awayTeam.setTeamNeame(match.getAwayTeam().getClubName());
				table.add(awayTeam);
				awayTeamPosition = table.size() - 1;
			}
			LeagueTableRow homeTeamRow = table.get(homeTeamPosition);
			homeTeamRow.setGoalsFor(homeTeamRow.getGoalsFor() + match.getHomeTeamScore());
			homeTeamRow.setGoalsAgainst(homeTeamRow.getGoalsAgainst() + match.getAwayTeamScore());

			LeagueTableRow awayTeamRow = table.get(awayTeamPosition);
			awayTeamRow.setGoalsFor(awayTeamRow.getGoalsFor() + match.getAwayTeamScore());
			awayTeamRow.setGoalsAgainst(awayTeamRow.getGoalsAgainst() + match.getHomeTeamScore());

			if (match.getHomeTeamScore() > match.getAwayTeamScore())
				homeTeamRow.setPoints(homeTeamRow.getPoints() + 3);
			else if (match.getHomeTeamScore() == match.getAwayTeamScore()) {
				homeTeamRow.setPoints(homeTeamRow.getPoints() + 1);
				awayTeamRow.setPoints(awayTeamRow.getPoints() + 1);
			} else {
				awayTeamRow.setPoints(awayTeamRow.getPoints() + 3);
			}
		}
		return table;
	}

	private Competition createCompetitionFromLong(long id) {
		competition = new Competition();
		competition.setId(id);
		return competition;
	}
}
