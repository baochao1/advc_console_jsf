package com.cdel.advc.gdb.member.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.cdel.advc.gdb.member.domain.AdvanceMember;

@FacesConverter("advanceMemberConverter")
public class AdvanceMemberConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		AdvanceMember am = new AdvanceMember();
		if (arg2 != null) {
			am.setId(new Integer(arg2));
			return am;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		// TODO Auto-generated method stub
		AdvanceMember am = (AdvanceMember) arg2;
		if (arg2 != null) {
			return am.getId() + "";
		} else {
			return null;
		}
	}

}
