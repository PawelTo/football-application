package pl.pawel.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;

public class MatchCompareTest {

	private Match m1;
	private Match m2;
	private MatchCompare mCompare;
	
	@Before
	public void setUp() {
		m1 = new Match(LocalDate.of(2017, 3, 21), null, null, 4, 1, new Competition(), 1324);
		m2 = new Match(LocalDate.of(2017, 3, 20), null, null, 4, 1, new Competition(), 2324);
		mCompare = new MatchCompare();
	}
	
	@Test
	public void testIntDifference() {
		assertEquals(1000, mCompare.compareMatchesAttendance(m1, m2));
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullMatch() {
		mCompare.compareMatchesAttendance(m1, null);
	}
}
