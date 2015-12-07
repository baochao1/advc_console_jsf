1、
BuyCourse bc = new BuyCourse();
			bc.setCourseCode("acc1032480");
			bc.setCourseName("会计基础[实验班]-北京2013");
			bc.setCourseType(1);
			bcList.add(bc);
			bc = new BuyCourse();
			bc.setCourseCode("acc1032490");
			bc.setCourseName("初级会计电算化[实验班]-北京2013");
			bc.setCourseType(1);
			bcList.add(bc);
			bc = new BuyCourse();
			bc.setCourseCode("acc1032470");
			bc.setCourseName("财经法规与会计职业道德[实验班]-北京2013");
			bc.setCourseType(1);
			bcList.add(bc);
			bc = new BuyCourse();
			bc.setCourseCode("acc1032460");
			bc.setCourseName("初级会计电算化[精品班]-北京2013");
			bc.setCourseType(1);
			bcList.add(bc);
			bc = new BuyCourse();
			bc.setCourseCode("acc1032440");
			bc.setCourseName("财经法规与会计职业道德[精品班]-北京2013");
			bc.setCourseType(1);
			bcList.add(bc);
			bc = new BuyCourse();
			bc.setCourseCode("b08200190r");
			bc.setCourseName("财务管理[实验班]2013");
			bc.setCourseType(1);
			bcList.add(bc);
			bc = new BuyCourse();
			bc.setCourseCode("b07200190r");
			bc.setCourseName("经济法[实验班]2013");
			bc.setCourseType(1);
			bcList.add(bc);
			bc = new BuyCourse();
			bc.setCourseCode("b08200200r");
			bc.setCourseName("财务管理[精品班]2013");
			bc.setCourseType(1);
			bcList.add(bc);
			
			
			BuyCourse bc = new BuyCourse();
		bc.setCourseCode("c07200200r");
		bc.setCourseName("审计[精品班]");
		bc.setCourseType(1);
		bcList.add(bc);
		
		BuyCourse bc = new BuyCourse();
		bc.setCourseCode("d03200200r");
		bc.setCourseName("经济法基础[精品班]2013");
		bc.setCourseType(1);
		bcList.add(bc);
		
				bc = new BuyCourse();
		bc.setCourseCode("d04200200r");
		bc.setCourseName("初级会计实务[精品班]2013");
		bc.setCourseType(1);
		bcList.add(bc);
			
2、
接口：
老：http://qz.chinaacc.com/remoteDivide/view.do?op=view&userName=15622251096&websiteID=1&operator=zxw
新：http://qz.test.com/remoteDivide/view.do?op=view&userName=zhang19770306&siteID=1&operator=zhangsulei
	http://localhost/remoteDivide/view.do?op=view&userName=cailiqiang&websiteID=1&operator=zhangsulei&userid=1
老：http://qz.chinaacc.com/remoteDivide/operate.do?op=autoDivide&userName=xcdszs0004&websiteID=1&operator=system
新：http://qz.test.com/remoteDivide/operate.do?op=autoDivide&userName=15639088450&websiteID=1&operator=zhangsulei
	http://localhost/remoteDivide/operate.do?op=autoDivide&userName=cailiqiang&websiteID=1&operator=zhangsulei&userid=1
呼叫中心系统接口
会计网										
	班级：http://qz.chinaacc.com/api/callCenter/classes.do?op=findClasses
	学员：http://qz.chinaacc.com/api/callCenter/member.do?op=findMembersByClassID
	
	变更：2014-01-13	
	班级：http://qz.chinaacc.com/api/callCenter/classes/findClasses
	学员：http://localhost/api/callCenter/member/findMembersByClassID?classID=5516

3、
执行字符串表达式：
引入jar：bsh-core-2.0b4.jar

import bsh.EvalError;
import bsh.Interpreter;

public class start {
	public static void main(String[] args) throws EvalError {
		Interpreter interpreter = new Interpreter();
		interpreter.set("a1",new Integer(1));
		interpreter.set("a2",new Integer(2));
		interpreter.eval("a3=a1+a2");
		System.out.println(interpreter.get("a3"));
	}
}

4、外呼接口：
插入回访记录接口：
http://localhost/advc/api/callCenter/member/saveMemberCall?siteID=1&jsonData=[{"callStatus":0,"callTime":"2014-05-20 17:28:54.0","classID":4072,"outboundID":88,"studyStatus":"1","updateTime":"2014-05-19 17:28:54.74","userID":9732683}]