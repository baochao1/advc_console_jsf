package com.cdel.advc.strategy.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.strategy.domain.StrategyOrder;

@FacesConverter("strategyOrderConverter")
public class StrategyOrderConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		StrategyOrder s = new StrategyOrder();
		if (arg2 != null) {
			String[] arr = StringUtils.split(arg2, "||");
			s.setCourseName(arr[0]);
			s.setCourseID(new Integer(arr[1]));
			s.setStrategyID(new Integer(arr[2]));
			return s;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		StrategyOrder s = (StrategyOrder) arg2;
		if (arg2 != null) {
			return s.getCourseName() + "||" + s.getCourseID() + "||"
					+ s.getStrategyID();
		} else {
			return null;
		}
	}

}
