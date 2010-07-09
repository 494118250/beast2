package test.beast.integration;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import beast.util.Randomizer;
import beast.util.XMLParser;
import junit.framework.TestCase;

/** check whether all example files parse **/
public class ExampleXmlParsingTest extends TestCase {

	@Test
	public void test_XmlExamples() {
		Randomizer.setSeed(127);
		String sDir = System.getProperty("user.dir") + "/examples";
		System.out.println("Test XML Examples in " + sDir);
		File sExampleDir = new File(sDir);
		String[] sExampleFiles = sExampleDir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		});

		List<String> sFailedFiles = new ArrayList<String>();
		for (String sFileName : sExampleFiles) {
			XMLParser parser = new XMLParser();
			try {
				parser.parseFile(sDir + "/" + sFileName);
			} catch (Exception e) {
				System.out.println("ExampleXmlParsing::Failed for " + sFileName
						+ ": " + e.getMessage());
				sFailedFiles.add(sFileName);
			}
		}
		assertTrue(sFailedFiles.toString(), sFailedFiles.size() == 0);
	}

} // ExampleXmlParsingTest