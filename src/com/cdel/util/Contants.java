package com.cdel.util;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Contants {
	public static String VIEW = "/view";
	public static String OPT = "/operate";
	public static final String CODE = "UTF-8";
	public static final String code = "utf-8";
	// 日志路径
	public static String logPath = File.separator + "web" + File.separator
			+ "logs" + File.separator + "advcAccConsole";
	// public static String logPath = "C:/zsl/logs";
	// 答疑板地址
	public static String faqConsoleUrl = "http://faq.chinaacc.com/BUS";
	// 综合题库地址
	public static String zhqzConsoleUrl = "http://faq.cdeledu.com";
	// 综合答疑板地址
	public static String zhfaqConsoleUrl = "http://faq.cdeledu.com/BUS";
	// 系统管理地址
	public static String authConsoleUrl = "http://qz.chinaacc.com/AUTH/login/login.action";
	// 题库地址
	public static String qzConsoleUrl = "http://qz.chinaacc.com/QUIZ";
	public static String qzConsoleOpenPaper = qzConsoleUrl
			+ "/qzPoint/operate/buildPaper4NewWin.action";
	public static String qzConsolePointPaper = qzConsoleUrl
			+ "/qzUnitScore/view/buildPaper4NewWin.action";// 查看知识点测试结果
	public static String qzConsoleSavePaper = qzConsoleUrl
			+ "/advcinterface/operate/addGdbPaper.action";
	public static String qzConsoleViewPaper = qzConsoleUrl
			+ "/qzPaperView/view/buildPaper4NewWin.action";// 高端班调整题目
	public static String qzConsoleEditPaper = qzConsoleUrl
			+ "/qzPaperView/operate/addPaperInit4NewWin.action";// 高端班修改试卷
	public static String qzConsoleAddPaper = qzConsoleUrl
			+ "/qzPaper/operate/addQuesInit.action";// 高端班加题
	public static String qzConsolePaperScore = qzConsoleUrl
			+ "/qzPaperScore/operate/buildPaper4NewWin.action";// 试卷评分
	// 验证码
	public static final String YZ = "loginValidate_save_number";
	// 客服分班角色
	public static final String DIVIDE_ROLENAME = "客服分班角色";
	// session名
	public static final String ADMIN_USER_ID = "com.chnedu.plat.grant.adminID";
	public static final String ADMIN_USER_NAME = "com_chnedu_plat_grant_adminUser_name";
	public static final String ADMIN_REAL_NAME = "com_chnedu_plat_grant_admin_real_name";
	public static final String ADMIN_PASSWORD = "com_chnedu_plat_grant_admin_password";
	public static final String ADMIN_USER_IS_SUPER = "com_chnedu_plat_grant_adminUser_super";
	public static final String ADMIN_USER_ROLES = "com.chnedu.plat.grant.role";
	public static final String ADMIN_USER_AUTH = "com.chnedu.plat.grant.auth";
	public static final String ADVC_CCSTRATEGY_WEBSITE = "ADVC_CCSTRATEGY_WEBSITE"; // 当前网站
	public static final String QZ_CCSTRATEGY_BUSINESS = "QZ_CCSTRATEGY_BUSINESS"; // 当前专业
	public static final String QZ_CCSTRATEGY_MAJOR = "QZ_CCSTRATEGY_MAJOR"; // 当前辅导
	public static final String QZ_CCSTRATEGY_COURSE = "QZ_CCSTRATEGY_COURSE"; // 当前课程
	public static final String CLASS_TEACHER = "class_teacher"; // 当前用户--班级管理员

	// 实验班systemType
	public final static int defSystemType = 5;
	// cookie名
	public static final String COOKIENAME_WEBSITE = "ADVCDefaultWebSite";
	public static final String COOKIENAME_COURSE = "QZDefaultCourse";

	public static final Integer USER_DEFINED = -2; // 自定义班级标识

	/** 高端班 */
	public static final String ADVC_ADVANCE_COURSE = "'acc1014660','acc1014670','acc1014680'";
	/** 高端班serverType */
	public final static int gdbServerType = 5;

	// 数据库标示
	public static final String DATA_KEY = "dataKey";
	public static final String JIAO_WU = "jiaowu";
	public static final String COURSE_WARE = "courseWare";
	public static final String ACC_MAIN = "accMain";

	// 上传文件地址
	public static String fileServerUrl = "http://img.cdeledu.com/BUS/uploadFile.jsp";
	public static int BUFFER_SIZE = 10 * 1024;
	public static int FILE_SIZE = 1024 * 1024 * 10;

	// 课程类别
	public static Map<Short, String> courseTypeMap = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("1"), "实验班");
			put(Short.parseShort("2"), "精品班");
			put(Short.parseShort("5"), "高端班");
		}
	};
	// 考期类别
	public static Map<Short, String> termTypeMap = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("1"), "实验班");
			put(Short.parseShort("2"), "精品班");
			put(Short.parseShort("3"), "混合班");
			put(Short.parseShort("4"), "面授班");
		}
	};
	// 策略类别
	public static Map<Short, String> strategyTypeMap = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("1"), "实验班");
			put(Short.parseShort("2"), "精品班");
			put(Short.parseShort("3"), "混合班");
			put(Short.parseShort("4"), "面授班");
		}
	};
	// 是否预习课程
	public static Map<Integer, String> isPreMap = new LinkedHashMap<Integer, String>() {
		{
			put(1, "预习课程");
			put(2, "分班课程");
		}
	};
	// 有效性
	public static Map<Short, String> status = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "无效");
			put(Short.parseShort("1"), "有效");
		}
	};

	// 是否
	public static Map<Short, String> yesorno = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "否");
			put(Short.parseShort("1"), "是");
		}
	};
	// 开通状态
	public static Map<Short, String> openStatus = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "未开通");
			put(Short.parseShort("1"), "已开通");
			put(Short.parseShort("2"), "已关闭");
		}
	};
	// 排序
	public static Map<Short, String> orderType = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("1"), "升序");
			put(Short.parseShort("2"), "降序");
		}
	};
	// 年份
	public static Integer[] years;
	static {
		years = new Integer[20];
		for (int i = 0; i < years.length; i++) {
			years[i] = 2010 + i;
		}
	}
	// 时间
	public static String[] times;
	static {
		times = new String[24 * 6];
		for (int i = 0; i <= 23; i++) {
			for (int j = 0; j <= 5; j++) {
				if (i < 10)
					times[(i * 5) + i + j] = "0" + i + ":" + j + "0";
				else
					times[(i * 5) + i + j] = i + ":" + j + "0";
			}
		}
	}

	// 网站类型
	public static Map<Short, String> siteType = new LinkedHashMap<Short, String>() {
		{
			put(Short.parseShort("0"), "会计网");
			put(Short.parseShort("1"), "医学网");
			put(Short.parseShort("2"), "人事网");
			put(Short.parseShort("3"), "外语教育网");
			put(Short.parseShort("4"), "中小学教育网");
			put(Short.parseShort("5"), "建设工程网");
			put(Short.parseShort("6"), "法律网");
			put(Short.parseShort("7"), "自考成考网");
		}
	};

	// 网站类型
	public static Map<Integer, String> siteIDs = new LinkedHashMap<Integer, String>() {
		{
			put(1, "@chinaacc.com");
			put(2, "@chinalawedu.com");
			put(3, "@zikao365.com");
			put(4, "@jianshe99.com");
			put(5, "@med66.com");
			put(7, "@for68.com");
			put(9, "@chinatat.com");
		}
	};

	// 网站选课路径 按顺序 会计网-医学网-人事网-外语-中小学-建设-法律
	public static String[] aspServers = {
			"http://class.chinaacc.com/login/senddata/LoginCheck_UserInfo.asp",
			"http://class.med66.com/asp/login/senddata/LoginCheck_UserInfo.asp",
			"http://class.chinatat.com/login/senddata/LoginCheck_UserInfo.asp",
			"http://class.for68.com/asp/login/SendData/LoginCheck_UserInfo.asp",
			"http://class.g12e.com/login/SendData/LoginCheck_UserInfo.asp",
			"http://class.jianshe99.com/asp/login/SendData/LoginCheck_UserInfo.asp",
			"http://class.chinalawedu.com/login/SendData/LoginCheck_UserInfo.asp",
			"http://class.zikao365.com/asp/login/SendData/LoginCheck_UserInfo.asp" };
	// 密匙
	public static String[] keys = { "abc123", "cba123", "yup123", "for6688",
			"ge21", "jsstt99", "ttt123", "zkzk987" };

	/**
	 * 解决状态--互助答疑，
	 */
	public static Map<Short, String> solveStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未解决");
			put((short) 1, "已解决");

		}
	};

	/**
	 * 回复状态--互助答疑，
	 */
	public static Map<Short, String> answerStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未回复");
			put((short) 1, "已回复");
		}
	};

	/**
	 * 阅读状态--学员间短信，
	 */
	public static Map<Short, String> readStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未读");
			put((short) 1, "已读");
		}
	};

	/**
	 * 邮箱类型，
	 */
	public static Map<Short, String> mailType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "收信箱");
			put((short) 2, "发信箱");
		}
	};

	/**
	 * 班级留言消息（学员端）类别--
	 */
	public static Map<Short, String> membernoteType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "留言本");
			put((short) 2, "学习心得");
		}
	};

	/**
	 * 班级留言（学员端）是否解决常量控制
	 */
	public static Map<Short, String> membernoteIsSolve = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "暂未解决");
			put((short) 1, "已解决");
		}
	};

	/**
	 * 班级留言（学员端）是否指定常量控制
	 */
	public static Map<Short, String> membernoteIsTop = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未置顶");
			put((short) 1, "已置顶");
		}
	};

	/**
	 * 班级留言（学员端）显示范围常量
	 */
	public static Map<Short, String> membernoteViewType = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "自己");
			put((short) 1, "所有人");
			put((short) 2, "好友");
		}
	};

	/**
	 * 班级动态（新鲜事，新闻）类型
	 */
	public static Map<Short, String> classnewsType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "学习");
			put((short) 2, "活动");
		}
	};

	/**
	 * 网站公告类型
	 */
	public static Map<Short, String> sitenoticeType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "黑板报");
			put((short) 2, "功能更新");
		}
	};

	/**
	 * 电话回访记录--提醒帮助状态
	 */
	public static Map<Short, String> memberCallNotifyType = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "预");
			put((short) 1, "提醒");
			put((short) 2, "已过期");
		}
	};

	/**
	 * 电话回访记录--状态
	 */
	public static Map<Short, String> memberCallStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "电话打不通");
			put((short) 1, "成功回访");
		}
	};

	/**
	 * 计划--是否新
	 */
	public static Map<Short, String> planIsNew = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "旧");
			put((short) 1, "新");
		}
	};

	/**
	 * 消息类型: 正常0、技术1、教师发送的消息3
	 */
	public static Map<Short, String> msgType = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "正常");
			put((short) 1, "技术");
			put((short) 3, "教师");
		}
	};
	/**
	 * 班级消息类型
	 */
	public static Map<Short, String> classMsgType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "班级公告");
			put((short) 3, "班级活动");
		}
	};

	/**
	 * 群发短信类型
	 */
	public static Map<Short, String> memberMsgType = new LinkedHashMap<Short, String>() {
		{
			put((short) 3, "普通消息");
			put((short) 4, "备考提醒");
		}
	};
	/**
	 * 电话回访状态
	 */
	public static Map<Short, String> callStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "回访失败");
			put((short) 1, "回访成功");
			put((short) 2, "预约成功");
			put((short) 3, "首次回访失败");
		}
	};

	/**
	 * 预约回访时间段
	 */
	public static Map<Short, String> reservedTimeMap = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "全天");
			put((short) 2, "上午");
			put((short) 3, "中午");
			put((short) 4, "下午");
			put((short) 5, "晚上");
		}
	};

	/**
	 * 预约回访时间段
	 */
	public static Map<Short, String> smsStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "可发送");
			put((short) 0, "已退订");
		}
	};

	/**
	 * 班级是否满员状态显示值
	 */
	public static Map<Short, String> hasFull = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未标记");
			put((short) 1, "已标记");
		}
	};

	/**
	 * 教师职位
	 */
	public static Map<Short, String> teacherHeader = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "超级管理员");
			put((short) 1, "经理");
			put((short) 2, "组长");
			put((short) 3, "组员");
			put((short) 4, "督导");
		}
	};

	/**
	 * 学员工作状态
	 */
	public static Map<Short, String> stuJobStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "比较忙");
			put((short) 2, "正常");
			put((short) 3, "比较闲");
		}
	};

	/**
	 * 学员学习方法
	 */
	public static Map<Short, String> stuStudyWay = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "在线收听为主");
			put((short) 2, "下载收听为主");
			put((short) 3, "随身携带收听为主");

		}
	};

	/**
	 * 学员学习方式
	 */
	public static Map<Short, String> studyHabitMap = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "先看教材，然后听课，最后做题");
			put((short) 1, "先听课，然后结合教材做题");
			put((short) 2, "先做题，然后再看教材，最后听课");
			put((short) 3, "其他");

		}
	};

	/**
	 * 学员学历
	 */
	public static Map<Short, String> educationMap = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "大专及以下");
			put((short) 2, "本科");
			put((short) 3, "硕士及以上");

		}
	};

	/**
	 * 学员记忆能力
	 */
	public static Map<Short, String> stuMemory = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "较强 ");
			put((short) 2, "一般 ");
			put((short) 3, "较弱 ");
		}
	};

	/**
	 * 电话预约回访类型
	 */
	public static Map<Short, String> stuCallType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "专业问题");
			put((short) 2, "客服问题");
		}
	};

	/**
	 * 生成学习计划 错误信息
	 */
	public static Map<Integer, String> generatePlanError = new LinkedHashMap<Integer, String>() {
		{
			put(-5, "生成计划发生错误：学员未报课 ！");
			put(-6, "生成计划发生错误：教师设定的学习时间大于两条 ！");
			put(-9, "生成计划发生错误：没有设置学习计划项！");
			put(-11, "生成计划发生错误：学习时间有错误 学员反馈大于两条！");
			put(-13, "生成计划发生错误：章节都舍弃掉了，没有章节可以学习了！");
			put(null, "生成计划发生错误：未知错误，请联系技术人员！");
		}
	};

	/**
	 * 计划类型
	 */
	public static Map<Short, String> planType = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "学习计划");
			put((short) 1, "预习计划");
		}
	};

	/**
	 * 学习计划--状态
	 */
	public static Map<Short, String> planStatus0 = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未设置");
			put((short) 1, "学习计划");
			put((short) 2, "需重新生成");
			put((short) 3, "需重新生成");
			put((short) 4, "需重新生成");
			put((short) 5, "需重新生成");
			put((short) 6, "需重新生成");
			put((short) 7, "需重新生成");
			put((short) 8, "需重新生成");
			put((short) 9, "需重新生成");
			put((short) 10, "需重新生成");
			put((short) 11, "需重新生成");
			put((short) 12, "需重新生成");
			put((short) 13, "需重新生成");
			put((short) 14, "需重新生成");
		}
	};

	/**
	 * 预习计划--状态
	 */
	public static Map<Short, String> prePlanStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未设置");
			put((short) 1, "预习计划");
			put((short) 2, "需重新生成");
			put((short) 3, "需重新生成");
			put((short) 4, "需重新生成");
			put((short) 5, "需重新生成");
			put((short) 6, "需重新生成");
			put((short) 7, "需重新生成");
			put((short) 8, "需重新生成");
			put((short) 9, "需重新生成");
			put((short) 10, "需重新生成");
			put((short) 11, "需重新生成");
			put((short) 12, "需重新生成");
			put((short) 13, "需重新生成");
			put((short) 14, "需重新生成");
		}
	};

	/**
	 * 学习计划状态
	 */
	public static Map<Short, String> planStatus1 = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未生成学习计划");
			put((short) 1, "学习计划正常");
			put((short) 2, "章节被更改,需重新生成");
			put((short) 3, "学员反馈信息被更改,需重新生成");
			put((short) 4, "计划项(章节)开始时间滞后,需重新生");
			put((short) 5, "学员开始时间被更改,需重新生成");//
			put((short) 6, "学员特殊时间被更改,需重新生成");//
			put((short) 7, "学员学习时间被更改,需重新生成");//
			put((short) 8, "学员结束时间被更改,需重新生成");//
			put((short) 9, "学员预习课程被更改,需重新生成");
			put((short) 10, "学员学习时长被更改,需重新生成");//
			put((short) 11, "学员学习章节取舍状态被更改,需重新生成");//
			put((short) 12, "学员章节顺序被更改,需重新生成");
			put((short) 13, "学员换班,计划需重新生成");
			put((short) 14, "章节学习状态设为已完成,需重新生成");

		}
	};

	/**
	 * 发短信返回信息
	 */
	public static Map<Integer, String> smsInfo = new LinkedHashMap<Integer, String>() {
		{
			put(-1, "短信内容参数有误");
			put(-2, "接收手机号有误");
			put(-3, "短信内容超过200个字符");
			put(-4, "接收手机号非11位");
			put(-5, "接收手机号不正确");
			put(-6, "业务说明为空");
			put(0, "发送失败");
		}
	};

	/**
	 * 课程章节计开通状态值
	 */
	public static Map<Short, String> planChapterOpenStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未开通");
			put((short) 1, "已开通");
			put((short) 2, "已关闭");
		}
	};

	/**
	 * 课程章节计完成状态值
	 */
	public static Map<Short, String> planChapterFinishStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未完成");
			put((short) 1, "进行中");
			put((short) 2, "已完成");
		}
	};

	/**
	 * 学员辅助信息操作类型
	 */
	public static Map<Short, String> logTypeMap = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "修改辅助信息");
			put((short) 2, "清除反馈信息");
		}
	};

	/**
	 * 性别
	 */
	public static Map<Boolean, String> generMap = new LinkedHashMap<Boolean, String>() {
		{
			put(true, "男");
			put(false, "女");
		}
	};

	/**
	 * 学员（对学习报告）申请状态
	 */
	public static Map<Short, String> applyStatus = new LinkedHashMap<Short, String>() {
		{
			put((short) 0, "未申请");
			put((short) 1, "已申请");
			put((short) 2, "已处理");
		}
	};

	/**
	 * 高端班试卷类型
	 */
	public static Map<Short, String> gdbPaperType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "周考");
			put((short) 2, "月考");
			put((short) 3, "综合");
		}
	};

	/**
	 * 与或类型
	 */
	public static Map<Byte, String> andOrs = new LinkedHashMap<Byte, String>() {
		{
			put((byte) 1, "并且");
			put((byte) 0, "或者");
		}
	};

	/**
	 * 匹配情况
	 */
	public static Map<Byte, String> piPeis = new LinkedHashMap<Byte, String>() {
		{
			put((byte) 1, "全部匹配");
			put((byte) 2, "任意匹配");
		}
	};

	/**
	 * 发送规则
	 */
	public static Map<Byte, String> sendType = new LinkedHashMap<Byte, String>() {
		{
			put((byte) 1, "非固定日期单次");
			put((byte) 2, "循环");
		}
	};

	/**
	 * 开始或结束
	 */
	public static Map<Byte, String> startOrEnd = new LinkedHashMap<Byte, String>() {
		{
			put((byte) 1, "开始");
			put((byte) 2, "结束");
		}
	};

	/**
	 * 星期
	 */
	public static Map<Byte, String> week = new LinkedHashMap<Byte, String>() {
		{
			put((byte) 1, "星期一");
			put((byte) 2, "星期二");
			put((byte) 3, "星期三");
			put((byte) 4, "星期四");
			put((byte) 5, "星期五");
			put((byte) 6, "星期六");
			put((byte) 0, "星期日");
		}
	};

	/**
	 * 引导语版本
	 */
	public static Map<Short, String> guideTextType = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "简略版");
			put((short) 2, "详细版");
		}
	};

	/**
	 * 提示类型
	 * 
	 * @return
	 */
	public static Map<Short, String> promptKeyMap = new LinkedHashMap<Short, String>() {
		{
			put((short) 1, "阶段开始前");
			put((short) 2, "阶段中");
			put((short) 3, "阶段结束后");
			put((short) 4, "任意时间点前");
			put((short) 5, "任意时间点后");
			put((short) 6, "任意2个时间点间");
		}
	};

}
