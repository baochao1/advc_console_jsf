DROP TABLE COURSEIDS
SELECT 
	tc.courseID INTO COURSEIDS -- �γ�
FROM 
	ADVC_COURSE tc 
WHERE
	tc.courseName = '�ߵ���ѧ(һ)[ʵ���](�ײ�)[����һ]' OR
	tc.courseName = '�ߵ���ѧ(һ)[ʵ���](�ײ�)[���ڶ�]' OR
	tc.courseName = '�ߵ���ѧ(һ)[ʵ���](�ײ�)[������]' OR
	tc.courseName = '�ߵ���ѧ(һ)[ʵ���](�ײ�)[������]' OR
	tc.courseName = 'Ӣ��(һ)[ʵ���](�ײ�)[����һ]' OR
	tc.courseName = 'Ӣ��(һ)[ʵ���](�ײ�)[���ڶ�]' OR
	tc.courseName = 'Ӣ��(һ)[ʵ���](�ײ�)[������]' OR
	tc.courseName = 'Ӣ��(һ)[ʵ���](�ײ�)[������]' OR
	tc.courseName = 'Ӣ��(��)[ʵ���](�ײ�)[����һ]' OR
	tc.courseName = 'Ӣ��(��)[ʵ���](�ײ�)[���ڶ�]' OR
	tc.courseName = 'Ӣ��(��)[ʵ���](�ײ�)[������]' OR
	tc.courseName = 'Ӣ��(��)[ʵ���](�ײ�)[������]'
	
DROP TABLE planIDs
SELECT 
	DISTINCT P.planID INTO planIDs -- �γ���ؼƻ�
FROM 
	ADVC_STUDYPLAN P, COURSEIDS C 
WHERE  ',' + P.studyCourse + ',' LIKE '%,' + convert(varchar(200), C.courseID) + ',%'

-- DROP TABLE planChpIDs
SELECT 
	DISTINCT C.planChpID INTO planChpIDs  -- ��ѯ�Ѳ����ڿγ��½ڵ� �ƻ��µļƻ��½�
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
 
DELETE FROM ADVC_STUDYPLAN_CHAPTER -- ɾ�ƻ��½�
WHERE  planChpID IN(SELECT planChpID FROM planChpIDs)

DELETE FROM ADVC_PLAN_CHAPTER_SEQUENCE_SET  --ɾ�½ڼƻ�����
WHERE planChpID IN(SELECT planChpID FROM planChpIDs)

DELETE FROM ADVC_MEMBER_CHAPTER  --ɾѧԱ�½���ɽ��� 
WHERE planChpID IN(SELECT planChpID FROM planChpIDs)



