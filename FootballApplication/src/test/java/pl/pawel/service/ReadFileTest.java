package pl.pawel.service;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ReadFileTest {

	
	@Test
	public void testGetDataFromFile() {
		ReadFile rFile = new ReadFile();
		List<String[]> dataFromFile = rFile.getDataFromFile();
		
		for (String[] row : dataFromFile) {
			for (String str : row) {
				System.out.println(str);
			}
		}
	}

}
