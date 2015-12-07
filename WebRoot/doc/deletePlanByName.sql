DECLARE @planIDs TABLE
        (
           classID   INT NOT NULL, 
           className   VARCHAR(100) NOT NULL,
           planID    INT NOT NULL,
					 planName 	VARCHAR(100) NOT NULL
        )
DECLARE @classNames TABLE
        (
           className   VARCHAR(100) NOT NULL
        )
DECLARE @startNumber INT
DECLARE @endNumber   INT

SET @startNumber=22
SET @endNumber=26

WHILE @startNumber <= @endNumber
	BEGIN		
		INSERT INTO @classNames VALUES('中级精品(' + convert(varchar(2), @startNumber) + ')班')
		SET @startNumber = @startNumber +1
  END

SELECT * FROM @classNames

INSERT INTO @planIDs
SELECT C.classID,C.className,P.planID,P.planName
FROM ADVC_STUDYPLAN P ,ADVC_CLASS C
WHERE P.classID = C.classID AND p.planType = 0 
			AND C.className IN(SELECT * FROM @classNames)

SELECT * FROM @planIDs

DELETE FROM ADVC_STUDYPLAN_CHAPTER -- 删计划章节
WHERE planID IN(SELECT planID	FROM @planIDs)

DELETE FROM ADVC_STUDYPLAN_LOG  --删计划操作日志
WHERE planID IN(SELECT planID	FROM @planIDs)

DELETE FROM ADVC_PLAN_CHAPTER_SEQUENCE_SET  --删章节计划排序
WHERE planID IN(SELECT planID	FROM @planIDs)

DELETE FROM ADVC_STUDYPLAN_HOURS  -- 删每天学习时间设置
WHERE planID IN(SELECT planID	FROM @planIDs)

DELETE FROM ADVC_STUDYPLAN_SPECIAL  -- 删特殊学习时间
WHERE planID IN(SELECT planID	FROM @planIDs)

DELETE FROM ADVC_MEMBER_CHAPTER  --删学员章节完成进度 
WHERE planID IN(SELECT planID	FROM @planIDs)

DELETE FROM ADVC_STUDYPLAN --删计划表 
WHERE planID IN(SELECT planID	FROM @planIDs)
