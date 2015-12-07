package com.cdel.advc.course.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import com.cdel.advc.course.domain.Course;

@FacesConverter("courseConverter")
public class CourseConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		Course c = new Course();
		if (arg2 != null) {
			String[] arr = StringUtils.split(arg2, "||");
			c.setCourseName(arr[0]);
			c.setCourseID(new Integer(arr[1]));
			return c;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		Course c = (Course) arg2;
		if (arg2 != null) {
			return c.getCourseName() + "||" + c.getCourseID();
		} else {
			return null;
		}
	}

}
