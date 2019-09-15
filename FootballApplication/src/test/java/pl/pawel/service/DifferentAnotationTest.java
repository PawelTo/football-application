package pl.pawel.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import pl.pawel.model.Club;
import pl.pawel.model.Competition;
import pl.pawel.model.Match;

/**
 * Class for learning different annotation for testing
 * 
 * @RunWith(SpringRunner.class) - can be used if we need spring context for
 * test. Its enable some annotations like: MockBean, which is used to add bean
 * to spring ApplicationContext, in traditional unit test we should use @Mock
 * 
 * @author Pawel
 */
@RunWith(MockitoJUnitRunner.class)
public class DifferentAnotationTest {

	@Mock
	private MatchCompare mockMatchCompare;

	@Spy
	private MatchCompare spyMatchCompare;

	@Captor
	private ArgumentCaptor<List<Match>> argCaptor;

	@InjectMocks
	private MatchCompare injectMocksMatchCompare;

	@Mock
	private Competition competitionMockForMatchCreating;
	@Mock
	private List<String> mockList;

	private List<Match> createMatchesList() {
		Match m1 = new Match(LocalDate.of(2019, 9, 11), new Club("C1W", "s1", 3141), new Club("C2P", "s2", 1311), 3, 1,
				competitionMockForMatchCreating, 12305);
		Match m2 = new Match(LocalDate.of(2019, 9, 11), new Club("C3R", "s3", 3111), new Club("C4R", "s4", 6311), 1, 1,
				competitionMockForMatchCreating, 12235);
		Match m3 = new Match(LocalDate.of(2019, 9, 11), new Club("C5P", "s5", 3511), new Club("C6W", "s6", 4311), 0, 2,
				competitionMockForMatchCreating, 12135);
		return new LinkedList<>(Arrays.asList(m1, m2, m3));
	}

	private List<Match> createReturnedList() {
		Match m1 = new Match(LocalDate.of(2019, 9, 11), new Club("RC1W", "Rs1", 13141), new Club("RC2P", "Rs2", 11311),
				13, 11, competitionMockForMatchCreating, 123051);
		Match m2 = new Match(LocalDate.of(2019, 9, 11), new Club("RC3R", "Rs3", 13111), new Club("RC4R", "Rs4", 16311),
				11, 11, competitionMockForMatchCreating, 122351);
		return new LinkedList<>(Arrays.asList(m1, m2));
	}

	/**
	 * Mock Annotation create, which can't be modify - methods aren't in real call,
	 * but we can set response
	 */
	@Test
	public void testMockAnnotation() {
		List<Match> listMatches = createMatchesList();
		List<Match> returnedListMatches = createReturnedList();

		List<Match> homeWinnerMatches = mockMatchCompare.getHomeWinnerMatches(listMatches);
		List<Match> callInnerMethod = mockMatchCompare.callInnerMethod(listMatches);

		Mockito.when(mockMatchCompare.callInnerMethod(listMatches)).thenReturn(returnedListMatches);

		Mockito.verify(mockMatchCompare).getHomeWinnerMatches(listMatches);
		assertEquals(0, homeWinnerMatches.size());
		assertEquals(0, callInnerMethod.size());
		assertEquals(returnedListMatches, mockMatchCompare.callInnerMethod(listMatches));
	}

	/**
	 * Spy Annotation create real object, which can be modify - methods are call in
	 * real, but we can also set the response
	 */
	@Test
	public void testSpyAnnotation() {
		List<Match> listMatches = createMatchesList();
		List<Match> returnedListMatches = createReturnedList();

		List<Match> homeWinnerMatches = spyMatchCompare.getHomeWinnerMatches(listMatches);
		List<Match> callInnerMethod = spyMatchCompare.callInnerMethod(listMatches);

		Mockito.when(spyMatchCompare.callInnerMethod(listMatches)).thenReturn(returnedListMatches);

		Mockito.verify(spyMatchCompare).getHomeWinnerMatches(listMatches);
		assertEquals(1, homeWinnerMatches.size());
		assertEquals(3, callInnerMethod.size());
		assertEquals(returnedListMatches, spyMatchCompare.callInnerMethod(listMatches));
	}

	/**
	 * Captor annotations can be use to check parameters in methods which you call
	 * 
	 */
	@Test
	public void testCaptorAnnotation() {
		List<Match> listMatches = createMatchesList();

		mockMatchCompare.callInnerMethod(listMatches);
		Mockito.verify(mockMatchCompare).callInnerMethod(argCaptor.capture());
		assertEquals(listMatches, argCaptor.getValue());
	}

	/**
	 * InjectMocks is used to create object for test, Mock is used to create
	 * dependency, which is used in that class. Methods in InjectMocks object are
	 * called in real, and methods from mock are only assumed
	 */
	@Test
	public void testInjectMockAnnotation() {
		List<Match> listMatches = createMatchesList();
		assertEquals(3, injectMocksMatchCompare.callInnerMethod(listMatches).size());
		when(mockList.get(0)).thenReturn("testPublic");
		assertEquals("testPublic", injectMocksMatchCompare.get0Match());
	}
}