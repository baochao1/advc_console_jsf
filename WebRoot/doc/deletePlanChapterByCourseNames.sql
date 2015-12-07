DROP TABLE COURSEIDS
SELECT 
	tc.courseID INTO COURSEIDS -- 课程
FROM 
	ADVC_COURSE tc 
WHERE
	tc.courseName = '高等数学(一)[实验班](套餐)[考期一]' OR
	tc.courseName = '高等数学(一)[实验班](套餐)[考期二]' OR
	tc.courseName = '高等数学(一)[实验班](套餐)[考期三]' OR
	tc.courseName = '高等数学(一)[实验班](套餐)[考期四]' OR
	tc.courseName = '英语(一)[实验班](套餐)[考期一]' OR
	tc.courseName = '英语(一)[实验班](套餐)[考期二]' OR
	tc.courseName = '英语(一)[实验班](套餐)[考期三]' OR
	tc.courseName = '英语(一)[实验班](套餐)[考期四]' OR
	tc.courseName = '英语(二)[实验班](套餐)[考期一]' OR
	tc.courseName = '英语(二)[实验班](套餐)[考期二]' OR
	tc.courseName = '英语(二)[实验班](套餐)[考期三]' OR
	tc.courseName = '英语(二)[实验班](套餐)[考期四]'
	
DROP TABLE planIDs
SELECT 
	DISTINCT P.planID INTO planIDs -- 课程相关计划
FROM 
	ADVC_STUDYPLAN P, COURSEIDS C 
WHERE  ',' + P.studyCourse + ',' LIKE '%,' + convert(varchar(200), C.courseID) + ',%'

-- DROP TABLE planChpIDs
SELECT 
	DISTINCT C.planChpID INTO planChpIDs  -- 查询已不存在课程章节的 计划下的计划章节
FROM 
	ADVC_STUDYPLAN P
LEFT JOIN 
	ADVC_STUDYPLAN_CHAPTER C ON P.planID = C.planID
LEFT JOIN
	ADVC_TEMP_CHAPTER TC ON C.chapterID = TC.chapterID
LEFT JOIN 
	ADVC_COURSE cou ON cou.courseID = TC.courseID
WHERE
	TC.chapterID IS NULL AND C.planID IN (SELECT planID FROM planIDs)
	
SELECT COUNT(*) AS courseCount FROM COURSEIDS    
SELECT COUNT(*) AS planCount FROM planIDs
SELECT COUNT(*)  AS planChapterCount FROM planChpIDs
 
DELETE FROM ADVC_STUDYPLAN_CHAPTER -- 删计划章节
WHERE  planChpID IN(SELECT planChpID FROM planChpIDs)

DELETE FROM ADVC_PLAN_CHAPTER_SEQUENCE_SET  --删章节计划排序
WHERE planChpID IN(SELECT planChpID FROM planChpIDs)

DELETE FROM ADVC_MEMBER_CHAPTER  --删学员章节完成进度 
WHERE planChpID IN(SELECT planChpID FROM planChpIDs)



