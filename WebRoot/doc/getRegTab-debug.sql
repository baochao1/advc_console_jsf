DECLARE @preCourses NVARCHAR(128)
DECLARE @userID INT
DECLARE @classID INT

-- DECLARE @planType
SET @userID=26404575
SET @classID=4313
SET @preCourses = ''

-- SET @planType =0
-----------------
DECLARE @strategyID INT
DECLARE @studyCourse VARCHAR(130)

-- if @planType=0 
-- 	begin
SELECT @studyCourse = studyCourse
FROM   ADVC_MEMBER_CLASS WITH (nolock)
WHERE  userID = @userID
       AND classID = @classID

-- 	end
-- else
-- 	begin
-- 		set @studyCourse=@preCourses
-- 	end
SELECT @strategyID = b.strategyID
FROM   ADVC_CLASS a WITH (nolock)
       LEFT JOIN ADVC_CLASS_STRATEGY b WITH (nolock)
              ON b.strategyID = a.strategyID
WHERE  a.classID = @classID

---------------------------------------
DECLARE @tmpCourseIDs TABLE
  (
     tmpCourseID INT NOT NULL,
     tmpPhaseNo  INT NOT NULL
  )
-------------------------
DECLARE @tabA TABLE
  (
     id        INT IDENTITY(1, 1) PRIMARY KEY,
     courseID  INT NOT NULL,
     chapterID INT NOT NULL,
     phaseNo   INT NOT NULL,
     sequence  INT NOT NULL,
     studyTime INT NOT NULL
  )
DECLARE @courseID VARCHAR(10)
DECLARE @studyCourses VARCHAR(130)
DECLARE @count INT
DECLARE @tmp TABLE
  (
     courseID    INT NOT NULL,
     chapterID   INT NOT NULL,
     phaseNo     INT NOT NULL,
     sequence    INT NOT NULL,
     chpSequence INT NOT NULL,
     studyTime   INT NOT NULL
  )

SET @count=0
SET @studyCourses=@studyCourse + ','

-------判断用户报了几门课(@count)以确定计划项类型   (循环截取课程ID以得到课程个数)--------- 
DECLARE @tmpIndex INT

SET @tmpIndex = Charindex(',', @studyCourses)

WHILE @tmpIndex > 0
  BEGIN
      INSERT INTO @tmpCourseIDs
      SELECT DISTINCT C.courseID,
                      C.phaseNo
      FROM   ADVC_TEMP_CHAPTER C WITH (nolock)
             LEFT JOIN ADVC_CLASS_STRATEGY_COURSEORDER SC
                    ON C.courseID = SC.courseID
                       AND SC.strategyID = @strategyID
      WHERE  C.courseID = Cast(Substring(@studyCourses, 1, @tmpIndex - 1) AS INT)
             AND C.openStatus != 2
      ORDER  BY C.courseID,
                C.phaseNo

      SET @studyCourses = Stuff(@studyCourses, 1, @tmpIndex, '')
      SET @tmpIndex = Charindex(',', @studyCourses)
      SET @count = @count + 1
  END

IF @count >= 4
  BEGIN
      SET @count=4
  END

--------存的所有报名课程的学习时长记录（得到指定策略,课程,计划项类型 的章节计划项要求等信息）------------
SET @studyCourses=@studyCourse + ','

DECLARE @countOut INT
DECLARE @countInt INT
DECLARE @courseID1 INT
DECLARE @phaseNo1 INT

SELECT @countOut = Count(*)
FROM   @tmpCourseIDs

WHILE @countOut > 0
  BEGIN
      SELECT TOP 1 @courseID1 = tmpCourseID,
                   @phaseNo1 = tmpPhaseNo
      FROM   @tmpCourseIDs

      INSERT INTO @tmp
      SELECT a.courseID,
             a.chapterID,
             a.phaseNo,
             CASE
               WHEN c.sequence IS NULL THEN a.sequence
               ELSE c.sequence
             END                       sequence,
             Row_number()
               OVER(
                 ORDER BY a.chapterID) AS chpSequence,
             b.suggestStyTime
      FROM   ADVC_TEMP_CHAPTER a WITH (nolock)
             LEFT JOIN ADVC_CLASS_STRATEGY_COURSEORDER c
                    ON a.courseID = c.courseID,
             ADVC_TEMP_CHAPTER_STUDYASK b WITH (nolock)
      WHERE  a.chapterID = b.chapterID
             AND a.courseID = @courseID1
             AND a.phaseNo = @phaseNo1
             AND b.type = @count
      ORDER  BY a.phaseNo,
                a.sequence,
                a.courseID,
                a.chapterID

      DELETE FROM @tmpCourseIDs
      WHERE  tmpCourseID = @courseID1
             AND tmpPhaseNo = @phaseNo1

      SELECT @countOut = Count(*)
      FROM   @tmpCourseIDs
  END

SELECT *
FROM   @tmp
ORDER  BY phaseNo,
          chpsequence

--判断是否设置了策略课程顺序
DECLARE @isCourseOrder INT

SELECT @isCourseOrder = Count(*)
FROM   ADVC_CLASS_STRATEGY_COURSEORDER uth WITH(nolock)
WHERE  strategyID = @strategyID

IF @isCourseOrder = 0
  BEGIN
      --把各个学科的所有章节平均分配，重新排序
      DECLARE @minCount TABLE
        (
           minPhaseNo INT NOT NULL,
           minPercent NUMERIC(10, 2) NOT NULL
        )
      DECLARE @tmp2 TABLE
        (
           countCourseID INT NOT NULL,
           countPhaseNo  INT NOT NULL,
           countPercent  NUMERIC(10, 2) NOT NULL
        )

      INSERT INTO @tmp2
      SELECT courseID,
             phaseNo,
             Count(chapterID)
      FROM   @tmp
      GROUP  BY courseID,
                phaseNo

      INSERT INTO @minCount
      SELECT countPhaseNo,
             Min(countPercent)
      FROM   @tmp2
      GROUP  BY countPhaseNo

      UPDATE @tmp2
      SET    countPercent = CONVERT(NUMERIC(4, 2), minPercent / countPercent)
      FROM   @minCount
      WHERE  minPhaseNo = countPhaseNo

      UPDATE @tmp
      SET    chpSequence = chpSequence * countPercent * 100
      FROM   @tmp2
      WHERE  courseID = countCourseID
             AND phaseNo = countPhaseNo

      INSERT INTO @tabA
      SELECT courseID,
             chapterID,
             phaseNo,
             chpSequence,
             studyTime
      FROM   @tmp
      ORDER  BY phaseNo,
                chpSequence
  END
ELSE
  BEGIN
      INSERT INTO @tabA
      SELECT courseID,
             chapterID,
             phaseNo,
             sequence,
             studyTime
      FROM   @tmp
      ORDER  BY phaseNo,
                sequence,
                chpSequence,
                courseID,
                chapterID
  END

SELECT phaseNo,
       sequence,
       courseID,
       chapterID
FROM   @tabA
ORDER  BY phaseNo,
          sequence,
          courseID
--删除学员要求的不需要生成学习计划的章节
--       DELETE FROM @tabA
--       WHERE  chapterID IN (SELECT chapterID
--                            FROM   ADVC_MEMBER_CHAPTER_SET
--                            WHERE  userID = @userID
--                                   AND isStudy = 0)
-- 
--       IF @isRetain = 1
--         BEGIN
--             DELETE FROM @tabA
--             WHERE  chapterID IN (SELECT chapterID
--                                  FROM   ADVC_STUDYPLAN_CHAPTER
--                                  WHERE  planID = @planID)
--         END
