package com.cdel.advc.serviceItem.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.serviceItem.domain.ServiceItem;

@FacesConverter("serviceItemConverter")
public class serviceItemConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		ServiceItem c = new ServiceItem();
		if (arg2 != null) {
			String[] arr = StringUtils.split(arg2, "||");
			c.setServiceName(arr[0]);
			c.setServiceID(new Integer(arr[1]));
			return c;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		ServiceItem c = (ServiceItem) arg2;
		if (arg2 != null) {
			return c.getServiceName() + "||" + c.getServiceID();
		} else {
			return null;
		}
	}

}
