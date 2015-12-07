package com.cdel.advc.memberdefine.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cdel.util.CodeUtil;
import com.cdel.util.Contants;
import com.cdel.util.DateUtil;
import com.chnedu.plat.rad.domain.BaseDomain;

@SuppressWarnings("serial")
public class Memberdefine extends BaseDomain implements Serializable {
	private Integer defineID;
	private Integer userID;
	private String userName;
	/** 工作状态 */
	private Short jobStatus;
	private String addiJobStatus;
	/** 记忆能力 */
	private Short memory;
	private String addiMemory;
	/** 考试经历 */
	private String examKill;
	private String addiExamKill;
	/** 学习方式 */
	private String studyHabit;
	private String addiStudyHabit;
	/** 学习方法 */
	private Short studyWay;
	private String addiStudyWay;
	private String specialty;
	private Short education;
	private String occupation;
	/** 周次学习时间字符串 */
	private String studyTimeLong;
	private Integer occupationID;// 职业ID
	private String studyTime;
	private String llikeTeacher;
	private String description;
	private Integer specialtyID;
	private Date updateDate;

	private String week1;
	private String week2;
	private String week3;
	private String week4;
	private String week5;
	private String week6;
	private String week7;
	/** 考试时间 */
	private Date examDate;

	public Short getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Short jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getAddiJobStatus() {
		return addiJobStatus;
	}

	public void setAddiJobStatus(String addiJobStatus) {
		this.addiJobStatus = addiJobStatus;
	}

	public String getJobStatusStr() {
		if (StringUtils.isBlank(addiJobStatus)) {
			if (jobStatus == null) {
				return "";
			} else {
				return Contants.stuJobStatus.get(jobStatus);
			}
		} else {
			return addiJobStatus;
		}
	}

	/**
	 * 周次学习时间解析后
	 */
	public String[] getStudyTimes() {
		return CodeUtil.splitStudyTimes(studyTimeLong);
	}

	public Short getMemory() {
		return memory;
	}

	public void setMemory(Short memory) {
		this.memory = memory;
	}

	public String getAddiMemory() {
		return addiMemory;
	}

	public void setAddiMemory(String addiMemory) {
		this.addiMemory = addiMemory;
	}

	public String getMemoryStr() {
		if (StringUtils.isBlank(addiMemory)) {
			if (memory == null) {
				return "";
			} else {
				return Contants.stuMemory.get(memory);
			}
		} else {
			return addiMemory;
		}
	}

	public String getExamKill() {
		return examKill;
	}

	public void setExamKill(String examKill) {
		this.examKill = examKill;
	}

	public void setExamKill(List<Examkill> examkillList) {
		StringBuffer examkill = new StringBuffer("");
		if (examkillList != null && examkillList.size() > 0) {
			for (int i = 0; i < examkillList.size(); i++) {
				Examkill ek = examkillList.get(i);
				examkill.append(ek.getExamName()).append(",")
						.append(ek.getYear()).append(",").append(ek.getScore());
				if (i < examkillList.size() - 1) {
					examkill.append("|");
				}
			}
		}
		if (StringUtils.isNotBlank(examkill.toString())) {
			this.examKill = examkill.toString();
		}
	}

	public String getAddiExamKill() {
		return addiExamKill;
	}

	public void setAddiExamKill(String addiExamKill) {
		this.addiExamKill = addiExamKill;
	}

	public String getExamKillStr() {
		if (StringUtils.isBlank(addiExamKill)) {
			return examKill;
		} else {
			return addiExamKill;
		}
	}

	public String getStudyHabit() {
		return studyHabit;
	}

	public void setStudyHabit(String studyHabit) {
		this.studyHabit = studyHabit;
	}

	public void setStudyHabit(Short studyHabitID) {
		if (studyHabitID != null && studyHabitID < 3) {
			this.studyHabit = Contants.studyHabitMap.get(studyHabitID);
		}
	}

	public String getStudyHabitStr() {
		if (StringUtils.isBlank(addiStudyHabit)) {
			return studyHabit;
		} else {
			return addiStudyHabit;
		}
	}

	public Short getStudyWay() {
		return studyWay;
	}

	public void setStudyWay(Short studyWay) {
		this.studyWay = studyWay;
	}

	public String getAddiStudyWay() {
		return addiStudyWay;
	}

	public void setAddiStudyWay(String addiStudyWay) {
		this.addiStudyWay = addiStudyWay;
	}

	public String getStudyWayStr() {
		if (StringUtils.isBlank(addiStudyWay)) {
			if (studyWay == null) {
				return "";
			} else {
				return Contants.stuStudyWay.get(studyWay);
			}
		} else {
			return addiStudyWay;
		}
	}

	public Integer getDefineID() {
		return defineID;
	}

	public void setDefineID(Integer defineID) {
		this.defineID = defineID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddiStudyHabit() {
		return addiStudyHabit;
	}

	public void setAddiStudyHabit(String addiStudyHabit) {
		this.addiStudyHabit = addiStudyHabit;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public Short getEducation() {
		return education;
	}

	public void setEducation(Short education) {
		this.education = education;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getStudyTimeLong() {
		return studyTimeLong;
	}

	public void setStudyTimeLong(String studyTimeLong) {
		this.studyTimeLong = studyTimeLong;
	}

	public void setStudyTimeLong() {
		StringBuffer sb = new StringBuffer("");
		this.studyTimeLong = sb.append(getWeek1()).append("|")
				.append(getWeek2()).append("|").append(getWeek3()).append("|")
				.append(getWeek4()).append("|").append(getWeek5()).append("|")
				.append(getWeek6()).append("|").append(getWeek7()).toString();
	}

	public Integer getOccupationID() {
		return occupationID;
	}

	public void setOccupationID(Integer occupationID) {
		this.occupationID = occupationID;
	}

	public String getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public void setStudyTime(List<StudyTime> studyTimeList) {
		StringBuffer studyTime = new StringBuffer("");
		if (studyTimeList != null && studyTimeList.size() > 0) {
			// 还要排除相同的时段
			for (int i = 0; i < studyTimeList.size(); i++) {
				StudyTime st = studyTimeList.get(i);
				String str = st.getStartTime() + "-" + st.getEndTime();
				if (studyTime.indexOf(str) == -1) {
					studyTime.append(str);
					if (i < studyTimeList.size() - 1) {
						studyTime.append("|");
					}
				}
			}
		}
		if (!studyTime.equals("") && !studyTime.equals("00:00-00:00")) {
			this.studyTime = studyTime.toString();
		}
	}

	public String getLlikeTeacher() {
		return llikeTeacher;
	}

	public void setLlikeTeacher(String llikeTeacher) {
		this.llikeTeacher = llikeTeacher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSpecialtyID() {
		return specialtyID;
	}

	public void setSpecialtyID(Integer specialtyID) {
		this.specialtyID = specialtyID;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getWeek1() {
		return week1;
	}

	public void setWeek1(String week1) {
		if (StringUtils.isBlank(week1)) {
			week1 = "0";
		}
		this.week1 = week1;
	}

	public String getWeek2() {
		return week2;
	}

	public void setWeek2(String week2) {
		if (StringUtils.isBlank(week2)) {
			week2 = "0";
		}
		this.week2 = week2;
	}

	public String getWeek3() {
		return week3;
	}

	public void setWeek3(String week3) {
		if (StringUtils.isBlank(week3)) {
			week3 = "0";
		}
		this.week3 = week3;
	}

	public String getWeek4() {
		return week4;
	}

	public void setWeek4(String week4) {
		if (StringUtils.isBlank(week4)) {
			week4 = "0";
		}
		this.week4 = week4;
	}

	public String getWeek5() {
		return week5;
	}

	public void setWeek5(String week5) {
		if (StringUtils.isBlank(week5)) {
			week5 = "0";
		}
		this.week5 = week5;
	}

	public String getWeek6() {
		return week6;
	}

	public void setWeek6(String week6) {
		if (StringUtils.isBlank(week6)) {
			week6 = "0";
		}
		this.week6 = week6;
	}

	public String getWeek7() {
		return week7;
	}

	public void setWeek7(String week7) {
		if (StringUtils.isBlank(week7)) {
			week7 = "0";
		}
		this.week7 = week7;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public void setWeek(String studytimelongstr) {
		if (studytimelongstr != null) {
			String[] studytimelongArr = StringUtils
					.split(studytimelongstr, "|");
			if (studytimelongArr.length == 1) {
				setWeek1(studytimelongArr[0]);
			} else if (studytimelongArr.length == 2) {
				setWeek1(studytimelongArr[0]);
				setWeek2(studytimelongArr[1]);
			} else if (studytimelongArr.length == 3) {
				setWeek1(studytimelongArr[0]);
				setWeek2(studytimelongArr[1]);
				setWeek3(studytimelongArr[2]);
			} else if (studytimelongArr.length == 4) {
				setWeek1(studytimelongArr[0]);
				setWeek2(studytimelongArr[1]);
				setWeek3(studytimelongArr[2]);
				setWeek4(studytimelongArr[3]);
			} else if (studytimelongArr.length == 5) {
				setWeek1(studytimelongArr[0]);
				setWeek2(studytimelongArr[1]);
				setWeek3(studytimelongArr[2]);
				setWeek4(studytimelongArr[3]);
				setWeek5(studytimelongArr[4]);
			} else if (studytimelongArr.length == 6) {
				setWeek1(studytimelongArr[0]);
				setWeek2(studytimelongArr[1]);
				setWeek3(studytimelongArr[2]);
				setWeek4(studytimelongArr[3]);
				setWeek5(studytimelongArr[4]);
				setWeek6(studytimelongArr[5]);
			} else if (studytimelongArr.length == 7) {
				setWeek1(studytimelongArr[0]);
				setWeek2(studytimelongArr[1]);
				setWeek3(studytimelongArr[2]);
				setWeek4(studytimelongArr[3]);
				setWeek5(studytimelongArr[4]);
				setWeek6(studytimelongArr[5]);
				setWeek7(studytimelongArr[6]);
			}
		} else {
			setWeek1("0");
			setWeek2("0");
			setWeek3("0");
			setWeek4("0");
			setWeek5("0");
			setWeek6("0");
			setWeek7("0");
		}
	}

	/**
	 * 考试时间显示值
	 */
	public String getExamDateStr() {
		if (this.examDate == null) {
			return "无";
		}
		return DateUtil.getNowDateString(this.examDate, "yyyy-MM-dd HH:mm");
	}

	public Double getSum() {
		Double sum = 0.0;
		if (StringUtils.isNotBlank(week1)) {
			sum = sum + Double.parseDouble(week1);
		}
		if (StringUtils.isNotBlank(week2)) {
			sum = sum + Double.parseDouble(week2);
		}
		if (StringUtils.isNotBlank(week3)) {
			sum = sum + Double.parseDouble(week3);
		}
		if (StringUtils.isNotBlank(week4)) {
			sum = sum + Double.parseDouble(week4);
		}
		if (StringUtils.isNotBlank(week5)) {
			sum = sum + Double.parseDouble(week5);
		}
		if (StringUtils.isNotBlank(week6)) {
			sum = sum + Double.parseDouble(week6);
		}
		if (StringUtils.isNotBlank(week7)) {
			sum = sum + Double.parseDouble(week7);
		}
		return sum;
	}

}