package  com.cdel.api.callCenter.classes;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 
import com.cdel.advc.classes.domain.Classes;
import com.cdel.api.callCenter.classes.ClassesFacade;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:testConfig/applicationContext.xml")
public class ClassesFacadeTest {
	    
	@Autowired
	@Qualifier("callCenterClassesFacade")
	private ClassesFacade classesFacade;

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

	
//	@Test
//	public final void testFindClasses() {
//		Classes classes = new Classes();
//		this.classesFacade.findClasses(classes );
//	}
	@Test
	public final void testUpdateClasses() {
		Classes classes = new Classes();
		classes.setClassID(9999);
		classes.setCurrCount(Short.valueOf((short) 101));
		this.classesFacade.update(classes);
	 
	}
	@Test
	public final void testADDClasses() {
		Classes classes = new Classes();
		classes.setClassID(9999);
		classes.setTermID(123);
		classes.setStrategyID(574);
		classes.setClassName("123");
		classes.setClassCode("123");
		classes.setCurrCount(Short.valueOf((short) 101));
		classes.setCreateTime(new Date());
		classes.setStatus(Short.valueOf((short) 1));
		// 1
		this.classesFacade.add(classes);
	}
}
