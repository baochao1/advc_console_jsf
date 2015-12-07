package com.cdel.plat.grant.facade;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.course.domain.Course;
import com.cdel.advc.course.facade.CourseFacade;
import com.cdel.advc.major.domain.Major;
import com.cdel.advc.major.facade.MajorFacade;
import com.cdel.advc.website.domain.Website;
import com.cdel.advc.website.facade.WebsiteFacade;
import com.cdel.qz.business.domain.QzBusiness;
import com.cdel.qz.business.facade.QzBusinessFacade;
import com.cdel.util.Contants;

/**
 * @author dell 保存课程信息到cookie
 */
@Service
public class SaveWebSite {
	@Autowired
	private WebsiteFacade websiteFacade;
	@Autowired
	private MajorFacade majorFacade;
	@Autowired
	private QzBusinessFacade qzBusinessFacade;
	@Autowired
	private CourseFacade courseFacade;

	/**
	 * 保存网站信息到session
	 * 
	 * @param siteID
	 * @throws Exception
	 */
	public int saveWebSiteToSession(Integer siteID, HttpSession session)
			throws Exception {
		int result = 0;
		Website website = null;
		if (siteID != null) {
			website = websiteFacade.findByID(siteID);
			session.setAttribute(Contants.ADVC_CCSTRATEGY_WEBSITE, website);
			result = 1;
		}
		return result;
	}

	/**
	 * 保存网站信息到session
	 * 
	 * @param courseID
	 */
	public int saveCourseToSession(Integer courseID, HttpSession session) {
		int result = 0;
		if (courseID != null) {
			Course course = courseFacade.findByID(courseID);
			if (course != null) {
				Major major = majorFacade.findByID(course.getMajorID());
				if (major != null) {
					QzBusiness business = qzBusinessFacade.findByID(major
							.getBusinessID());
					if (business != null) {
						session.setAttribute(Contants.QZ_CCSTRATEGY_BUSINESS,
								business);
						session.setAttribute(Contants.QZ_CCSTRATEGY_MAJOR,
								major);
						session.setAttribute(Contants.QZ_CCSTRATEGY_COURSE,
								course);
						result = 1;
					}
				}
			}
		}
		return result;
	}
}
