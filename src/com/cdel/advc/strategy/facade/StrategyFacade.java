package com.cdel.advc.strategy.facade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdel.advc.course.domain.Course;
import com.cdel.advc.major.domain.MainMajor;
import com.cdel.advc.major.facade.MainMajorFacade;
import com.cdel.advc.strategy.domain.CourseReg;
import com.cdel.advc.strategy.domain.Strategy;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.InterpreterUtil;

@SuppressWarnings("serial")
@Service
public class StrategyFacade extends BaseFacadeImpl<Strategy, Integer> implements
		Serializable {
	

	@Autowired
	private MainMajorFacade mainMajorFacade;
	
	/**
	 * 班级策略查询，关联主库辅导处理
	 */
	public LazyDataModel<Strategy> findPage(final Strategy strategy, final Integer siteID) {
		
		LazyDataModel<Strategy> lazyModel = new LazyDataModel<Strategy>() {
			@Override
			public void setRowIndex(int rowIndex) {
				if (rowIndex == -1 || getPageSize() == 0) {
					super.setRowIndex(-1);
				} else
					super.setRowIndex(rowIndex % getPageSize());
			}

			@Override
			public List<Strategy> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {
				
				List<Strategy> strategies = null;
				if (pageSize <= 0) {
					pageSize = 10;
				}
				this.setPageSize(pageSize);
				this.setRowIndex(first);
				int count = (Integer) baseDao.get(strategy, "strategy.countStrategy");
				this.setRowCount(count);

				if (first <= count) {
					strategy.setSortField(sortField);
					strategy.setSortOrder(sortOrder.name());
					strategy.setRowNumStart(first + 1);
					strategy.setRowNumEnd(first + pageSize);

					strategies = baseDao.findListByEntity(strategy, "strategy.findPageStrategy");
				} 
				
				setMainMajors(strategies, siteID);
				return strategies;
			}
		};
		return lazyModel;
	}
	
	
	/**
	 * 设置主库辅导，方便显示主库辅导名称
	 */
	private void setMainMajors(List<Strategy> strategies, Integer siteID){
		
		if(strategies != null && strategies.size() > 0){
			List<MainMajor> mainMajors = this.mainMajorFacade.findMajorsBySiteID(siteID);
			
			for (int i = 0, s = strategies.size(); i < s; i++) {
				strategies.get(i).setMainMajors(mainMajors);
			}
		}
	}

	/**
	 * 模糊匹配策略描述
	 * 
	 * @param desc
	 * @return
	 */
	public List<String> getStrategyDesc(String desc) {
		return baseDao.findSomeList(desc, "getStrategyDesc");
	}

	/**
	 * 验证
	 * 
	 * @param flag
	 *            =3，生成策略描述时
	 * @param msg
	 * @param context
	 * @param updateStrategy
	 * @param courseNum
	 * @param courseIDs
	 * @return
	 */
	public boolean checkStrategy(byte flag, Strategy updateStrategy,
			List<CourseReg> courseRegs) {
		if (flag == 0) {
			if (updateStrategy.getMajorID() == null) {
				return addWarnMessage("请选择辅导！");
			}
			if (updateStrategy.getTermID() == null) {
				return addWarnMessage("请选择考期！");
			}
			if (updateStrategy.getMainMajorID() == null
					|| updateStrategy.getMainMajorID() < 0) {
				return addWarnMessage("请选择主库辅导！");
			}
		}
		if (updateStrategy.getLimitNum() == null
				|| updateStrategy.getLimitNum() < 0) {
			return addWarnMessage("班级人数不能为空，且要大于0！");
		}
		if (updateStrategy.getPriorityLevel() == null
				|| updateStrategy.getPriorityLevel() < 0) {
			return addWarnMessage("优先级别不能为空，且要大于0！");
		}
		if (flag != 3 && StringUtils.isBlank(updateStrategy.getSetDesc())) {
			return addWarnMessage("策略描述不能为空！");
		}
		if (flag != 3 && StringUtils.isBlank(updateStrategy.getRegSeq())) {
			return addWarnMessage("执行顺序不能为空！");
		}
		InterpreterUtil iu = new InterpreterUtil();
		boolean f = iu.checkExp(updateStrategy.getRegSeq());
		if (!f) {
			return addWarnMessage("执行顺序表达式错误！");
		}
		if (courseRegs != null && courseRegs.size() > 0) {
			for (CourseReg creg : courseRegs) {
				byte courseNum = creg.getCourseNum();
				List<Course> clist = creg.getCourseReg().getTarget();
				if (clist == null || clist.size() == 0) {
					return addWarnMessage("请选择课程！");
				}
				if (courseNum < 0) {
					return addWarnMessage("任意匹配的课程中，任选的课程数要大于0！");
				}
			}
		} else {
			return addWarnMessage("请至少添加一条规则！");
		}
		return true;
	}

	/**
	 * 拼正则表达式
	 * 
	 * @param courses
	 * @param courseNum
	 * @return
	 */
	public String[] makeCourseReg(List<CourseReg> courseRegs) {
		String[] arr = new String[3];
		StringBuffer sb = new StringBuffer("");
		StringBuffer sb2 = new StringBuffer(",");
		StringBuffer sb3 = new StringBuffer("");
		for (int i = 0; i < courseRegs.size(); i++) {
			CourseReg cReg = courseRegs.get(i);
			byte piPei = cReg.getPiPei();
			List<Course> clist = cReg.getCourseReg().getTarget();
			byte andOr = cReg.getAndOr();
			byte courseNum = 0;
			if (piPei == 1) {
				// 全匹配
				courseNum = Byte.parseByte(clist.size() + "");
			} else {
				// 任意匹配
				courseNum = cReg.getCourseNum();
			}
			if (i > 0) {
				sb3.append(andOr).append(",");
			}
			if (clist != null && clist.size() > 0) {
				if (sb.toString().equals("")) {
					sb.append("((");
				} else {
					sb.append("_((");
				}
				for (int j = 0; j < clist.size(); j++) {
					int courseID = clist.get(j).getCourseID();
					if (j == 0) {
						sb.append(",").append(courseID).append(",");
					} else {
						sb.append("|").append(",").append(courseID).append(",");
					}
					sb2.append(courseID).append(",");
				}
				sb.append(").*){" + courseNum + ",}");
			}
		}
		arr[0] = sb.toString();
		arr[1] = sb2.toString();
		arr[2] = sb3.toString();
		return arr;
	}

	/**
	 * 判断是否有相同的策略设置
	 * 
	 * @param strategy
	 * @return
	 */
	public boolean judgeRegexHasExistByRegex(Strategy strategy) {
		boolean f = false;
		if (this.baseDao.count(strategy, "judgeRegexHasExistByRegex") > 0) {
			f = true;
		}
		return f;
	}

	/**
	 * 根据辅导IDs获取相关的策略
	 * 
	 * @param majorList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Strategy> getStrategyAndTerm(Integer[] ids, Short isAuto) {
		Map map = new HashMap();
		map.put("majorIDs", ids);
		map.put("isAuto", isAuto);
		return this.baseDao.findList(map, "getStrategyAndTerm");
	}

	/**
	 * 根据ID获取策略(分班时用)
	 * 
	 * @param strategyID
	 * @return
	 */
	public Strategy getStrategy4Class(Integer strategyID) {
		return this.baseDao.getByID(strategyID, "getStrategy4Class");
	}

	/**
	 * 初始化修改操作的课程规则列表里的课程
	 * 
	 * @param courseList
	 * @return
	 */
	public List<List<Course>> getUpdateCourseList(List<Course> courseList,
			String courseIDs) {
		List<List<Course>> result = new ArrayList<List<Course>>();
		List<Course> targetList = new ArrayList<Course>();
		courseIDs = StringUtils.replace(courseIDs, ",", "");
		String[] courseArr = StringUtils.split(courseIDs, "|");
		Course c;
		if (courseArr != null && courseArr.length > 0) {
			for (int i = 0; i < courseArr.length; i++) {
				Integer courseID = Integer.parseInt(courseArr[i]);
				c = new Course();
				c.setCourseID(courseID);
				if (courseList.contains(c)) {
					targetList.add(courseList.get(courseList.indexOf(c)));
					courseList.remove(c);
				}
			}
		}
		result.add(courseList);
		result.add(targetList);
		return result;
	}

	/**
	 * 根据输入的正则表达式，返回课程数
	 * 
	 * @param regStr
	 * @return
	 */
	public byte getCourseCourse(String regStr) {
		String s = StringUtils.substringAfter(regStr, "){");
		if (StringUtils.isNotBlank(s)) {
			s = s.substring(0, s.length() - 2);
			if (StringUtils.isNotBlank(s)) {
				return new Byte(s);
			} else {
				return (byte) 0;
			}
		} else {
			return (byte) StringUtils.split(regStr, ",").length;
		}
	}

	/**
	 * 根据输入的正则表达式，返回课程数组
	 * 
	 * @param regStr
	 * @return
	 */
	public String[] getCourseIDsArr(String regStr) {
		String s = StringUtils.substringBefore(regStr, ",).");
		String start = StringUtils.substringAfter(s, "((,");
		if (StringUtils.isNotBlank(start)) {
			start = StringUtils.replace(start, ",", "");
			String[] tmp = StringUtils.split(start, "|");
			Arrays.sort(tmp);
			return tmp;
		} else {
			if (StringUtils.isNotBlank(s)) {
				String[] tmp = StringUtils.split(s, ",");
				Arrays.sort(tmp);
				return tmp;
			} else {
				return null;
			}
		}
	}


	public void setMainMajorFacade(MainMajorFacade mainMajorFacade) {
		this.mainMajorFacade = mainMajorFacade;
	}
}
