package com.cdel.api.callCenter.member;

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

import com.cdel.advc.memberclass.domain.MemberClass;
import com.cdel.advc.memberclass.facade.MemberClassFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:testConfig/applicationContext.xml")
public class MemberFacadeTest {

	@Autowired
	@Qualifier("callCenterMemberFacade")
	private MemberFacade memberFacade;
	
	@Autowired
	@Qualifier("memberClassFacade")
	private MemberClassFacade  memberClassFacade;
	
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
	public final void testFindMembersByClassID() {
		
		this.memberFacade.findMembersByClassID(5463);
	}
	
	@Test
	public final void testADDMembersClass() {
		MemberClass memberClass = new  MemberClass();
		memberClass.setClassID(4681);
		memberClass.setUserID(32328893);
		memberClass.setStudentNo("1310078606205") ; 
		memberClass.setCreateTime(new Date());
		memberClass.setStudyCourse("20");
		memberClass.setStatus(Short.valueOf((short) 1));
		memberClass.setSequence(99);
		memberClass.setFirstTime(new Date());
		this.memberClassFacade.add(memberClass);
	}
	@Test
	public final void testUpdateMembersClass() {
		MemberClass memberClass = new  MemberClass();
		memberClass.setClassID(4681);
		memberClass.setUserID(32328893);
		
		memberClass.setStudentNo("99999999999") ; 
		memberClass.setCreateTime(new Date());
		memberClass.setStudyCourse("9999999");
		memberClass.setStatus(Short.valueOf((short) 9)); 
		memberClass.setSequence(1);
		this.memberClassFacade.update(memberClass);
	}
}
