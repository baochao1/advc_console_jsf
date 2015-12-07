package com.cdel.advc.classes.facade;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdel.advc.classes.domain.Classes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:testConfig/applicationContext.xml")
public class ClassesFacadeTest {

	@Autowired
	private ClassesFacade classesFacade;
	//
	private Classes classes ;

	private int first;

	private int pageSize;

	private String sortField;

	private SortOrder sortOrder;

	private Map<String, String> filters;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		this.classes = new Classes();
		this.first = 0;
		this.pageSize = 20;
		this.sortField = "";
		this.sortOrder = SortOrder.ASCENDING;
		this.filters = new HashMap<String, String>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testFindPage() {
		
		LazyDataModel<Classes> page = this.classesFacade.findPage(this.classes );
		List<Classes> list = page.load(first, pageSize, sortField, sortOrder, filters);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		}
	}

	@Test
	public final void testUpdateMsgTime() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassesListByMajorID() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassesListByMajorID2() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFindClassNames() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFindClasss() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetTeacherClasses() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateClassMemberCount() {
		Integer classID = 4097;
		
		this.classesFacade.updateClassMemberCount(classID, -1);
		
		System.out.println(this.classesFacade.findByID(classID).getCurrCount());
	}

	@Test
	public final void testGetCPAClassesCount() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCheckTeaClass() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetCountClassesByTermID() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassesByTerm() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateClassesStatusInTerm() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetCountClassByStrategyID() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClassByStrategyID() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetNotFullClasses() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCheckClass() {
		fail("Not yet implemented"); // TODO
	}

}
