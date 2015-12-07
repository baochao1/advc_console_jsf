 SELECT 
 			DISTINCT f.faqID, 
 			f.faqType, 
 			f.parentID, 
 			f.faqTitle, 
 			f.faqContent, 
 			f.userID, 
 			f.createTime, 
 			f.status, 
 			f.courseID, 
 			m.userName, 
 			c.className, 
 			f.hit, 
 			f.classID, 
 			course.courseName, 
 			MM.majorName, 
 			f.chapterNum, 
 			ROW_NUMBER() Over(ORDER BY f.faqID DESC ) AS rowNum 
 		FROM 
 			ADVC_FAQ f WITH (NOLOCK) 
 		LEFT JOIN 
 			( SELECT 
 				DISTINCT parentID, 
 				userID 
 			  FROM
 			  	ADVC_FAQ WITH (NOLOCK) 
 			  WHERE userID=? 
 			  ) ff 
 			  ON ff.parentID = f.faqID 
 		LEFT JOIN 
 			ADVC_MEMBER m WITH (NOLOCK) 
 			ON m.userID = f.userID 
 		LEFT JOIN 
 			( SELECT 
 				DISTINCT c.* 
 			  FROM 
 			  	ADVC_CLASS c WITH (NOLOCK) 
 			  LEFT JOIN 
 			  	ADVC_CLASS_TEACHER ct WITH (NOLOCK) 
 			 	 ON c.classID = ct.classID 
 			  LEFT JOIN 
 			  	ADVC_TEACHER t WITH (NOLOCK) 
 			 	 ON t.teacherID = ct.teacherID 
 			  WHERE 
 			  	t.orgID =? 
 			  ) c 
 			 	 ON c.classID = f.classID 
 			  LEFT JOIN 
 			  	ADVC_MAJOR MM WITH (NOLOCK) 
 			 	 ON f.majorID = MM.majorID 
 			  LEFT JOIN 
 			  	ADVC_COURSE course WITH (NOLOCK) 
 			  	ON course.courseID = f.courseID 
 			  WHERE 
 			  	f.parentID = 0 
 			  	AND
 			  	c.classID IS NOT NULL f.classID=?