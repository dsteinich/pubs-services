package gov.usgs.cida.pubs.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.usgs.cida.pubs.BaseSpringTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MvcServiceTest extends BaseSpringTest {

	private class TestMvcService extends MvcService<MvcServiceTest> {
		
	}
	
	private TestMvcService testMvcService = new TestMvcService();
	
	@Test
    public void addToFiltersIfNotNullStringTest() {
		Map<String, Object> filters = new HashMap<>();
		String values = null;
		//A null value should not add to the map
		testMvcService.addToFiltersIfNotNull(filters, "x", values);
		assertEquals(0, filters.keySet().size());
		
		//An empty value should not add to the map
		testMvcService.addToFiltersIfNotNull(filters, "x", "");
		assertEquals(0, filters.keySet().size());

		testMvcService.addToFiltersIfNotNull(filters, "x", "y");
		assertEquals(1, filters.keySet().size());
		assertTrue(filters.containsKey("x"));
		assertEquals("y", filters.get("x"));
    }

	@Test
    public void addToFiltersIfNotNullArrayTest() {
		Map<String, Object> filters = new HashMap<>();
		String[] values = null;
		//A null value should not add to the map
		testMvcService.addToFiltersIfNotNull(filters, "x", values);
		assertEquals(0, filters.keySet().size());
		
		//An empty value should not add to the map
		testMvcService.addToFiltersIfNotNull(filters, "x", new String[]{""});
		assertEquals(0, filters.keySet().size());

		testMvcService.addToFiltersIfNotNull(filters, "x", new String[]{"","y"});
		assertEquals(1, filters.keySet().size());
		assertTrue(filters.containsKey("x"));
		assertEquals("y", ((Object[]) filters.get("x"))[0].toString());
    }

	@Test
	public void configureSingleSearchFiltersTest() {
		Map<String, Object> filters = new HashMap<>();

		//A null value should not add to the map
		testMvcService.configureSingleSearchFilters(filters, null);
		assertEquals(0, filters.keySet().size());
		
		//An empty value should not add to the map
		testMvcService.configureSingleSearchFilters(filters, "");
		assertEquals(0, filters.keySet().size());
		testMvcService.configureSingleSearchFilters(filters, "   ,  ");
		assertEquals(0, filters.keySet().size());

		testMvcService.configureSingleSearchFilters(filters, "turtles and, loggerhead,, ");
		assertEquals(2, filters.keySet().size());
		assertTrue(filters.containsKey("searchTerms"));
		Object[] searchTerms = (Object[]) filters.get("searchTerms");
		assertEquals(2, searchTerms.length);
		assertEquals("turtles", searchTerms[0].toString());
		assertEquals("loggerhead", searchTerms[1].toString());
		assertTrue(filters.containsKey("searchTerms"));
		String q = (String) filters.get("q");
		assertEquals("$turtles and $loggerhead", q);
		
		testMvcService.configureSingleSearchFilters(filters, "turtles-loggerhead");
		assertEquals(2, filters.keySet().size());
		assertTrue(filters.containsKey("searchTerms"));
		searchTerms = (Object[]) filters.get("searchTerms");
		assertEquals(2, searchTerms.length);
		assertEquals("turtles", searchTerms[0].toString());
		assertEquals("loggerhead", searchTerms[1].toString());
		assertTrue(filters.containsKey("searchTerms"));
		q = (String) filters.get("q");
		assertEquals("$turtles and $loggerhead", q);
	}
	
	@Test
	public void buildQTest() {
		List<String> list = new LinkedList<>();
		assertNull(testMvcService.buildQ(null));
		assertNull(testMvcService.buildQ(list));
		
		list.add("test");
		assertEquals("$test", testMvcService.buildQ(list));
		
		list.add("turtles");
		list.add(" ");
		list.add("");
		list.add(null);
		list.add("loggerhead");
		assertEquals("$test and $turtles and $loggerhead",  testMvcService.buildQ(list));

	}
	
	@Test
	public void configureContributorFilterTest() {
		Map<String, Object> filters = new HashMap<>();

		//A null value should not add to the map
		filters = testMvcService.configureContributorFilter(null);
		assertEquals(0, filters.keySet().size());
		
		//An empty value should not add to the map
		filters = testMvcService.configureContributorFilter(new String[]{""});
		assertEquals(0, filters.keySet().size());
		filters = testMvcService.configureContributorFilter(new String[]{"   .  "});
		assertTrue(filters.containsKey("text"));
		Object[] searchTerms = (Object[]) filters.get("text");
		assertEquals(1, searchTerms.length);
		assertEquals(".", searchTerms[0].toString());

		filters = testMvcService.configureContributorFilter(new String[]{"Rebecca Carvin"});
		assertTrue(filters.containsKey("text"));
		searchTerms = (Object[]) filters.get("text");
		assertEquals(2, searchTerms.length);
		assertEquals("rebecca", searchTerms[0].toString());
		assertEquals("carvin", searchTerms[1].toString());
		
		filters = testMvcService.configureContributorFilter(new String[]{"Carvin", " Rebecca"});
		assertTrue(filters.containsKey("text"));
		searchTerms = (Object[]) filters.get("text");
		assertEquals(2, searchTerms.length);
		assertEquals("carvin", searchTerms[0].toString());
		assertEquals("rebecca", searchTerms[1].toString());
	}
}
