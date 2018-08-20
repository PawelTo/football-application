package pl.pawel.service;

import pl.pawel.model.Match;

/** Interface to compare matches
 * @author Pawel
 *
 */
@FunctionalInterface
public interface MatchComparator {
	public int compare(Match m1, Match m2);
}
