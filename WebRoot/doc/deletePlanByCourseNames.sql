-- DROP TABLE COURSEIDS
SELECT 
	tc.courseID INTO COURSEIDS 
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
	
-- DROP TABLE planIDs
SELECT 
	DISTINCT P.planID INTO planIDs
FROM 
	ADVC_STUDYPLAN P, COURSEIDS C 
WHERE  ',' + P.studyCourse + ',' LIKE '%,' + convert(varchar(200), C.courseID) + ',%'

SELECT COUNT(*) FROM planIDs
SELECT COUNT(*) FROM COURSEIDS

SELECT * FROM planIDs

DELETE FROM ADVC_STUDYPLAN_CHAPTER -- 删计划章节
WHERE planID IN(SELECT planID	FROM planIDs)

DELETE FROM ADVC_STUDYPLAN_LOG  --删计划操作日志
WHERE planID IN(SELECT planID	FROM planIDs)

DELETE FROM ADVC_PLAN_CHAPTER_SEQUENCE_SET  --删章节计划排序
WHERE planID IN(SELECT planID	FROM planIDs)

DELETE FROM ADVC_STUDYPLAN_HOURS  -- 删每天学习时间设置
WHERE planID IN(SELECT planID	FROM planIDs)

DELETE FROM ADVC_STUDYPLAN_SPECIAL  -- 删特殊学习时间
WHERE planID IN(SELECT planID	FROM planIDs)

DELETE FROM ADVC_MEMBER_CHAPTER  --删学员章节完成进度 
WHERE planID IN(SELECT planID	FROM planIDs)

DELETE FROM ADVC_STUDYPLAN --删计划表 
WHERE planID IN(SELECT planID	FROM planIDs)
