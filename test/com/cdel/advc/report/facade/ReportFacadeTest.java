package com.cdel.advc.report.facade;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:testConfig/applicationContext.xml")
public class ReportFacadeTest {
	
	@Autowired
	private ReportFacade reportFacade;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public final void testGetPageByClassIDAndUserID() {
		
		
	}

	@Test
	public final void testDeleteReportByID() {
		
		
	}

	@Test
	public final void testUpdateStatus() {
		
		
	}

	@Test
	public final void testUpdateReportToNewClass() {
		
		
	}

	@Test
	public final void testGetCourseTimeByReportID() {
		
//		 List<RptCourseTime> list = this.reportFacade.getCourseTimeByReportID(0);
//		 System.out.println(list);
//		if (list != null) {
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i));
//			}
//		}
		
	}

}
