package com.cdel.advc.plan.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang.StringUtils;
import com.cdel.advc.plan.domain.PlanChapterSequenceSet;

@FacesConverter("planChapterSequenceSetConverter")
public class PlanChapterSequenceSetConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		if (context == null || component == null) {
			throw new NullPointerException();
		}
		if (value == null) {
			return null;
		}
		value = value.trim();
		if (value.length() < 1) {
			return null;
		}

		PlanChapterSequenceSet pc = new PlanChapterSequenceSet();
		String[] values = StringUtils.split(value, "||");
		pc.setChapterID(Integer.parseInt(values[0]));
		pc.setSequence(Integer.parseInt(values[1]));

		return pc;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (context == null || component == null) {
			throw new NullPointerException();
		}
		if (value == null) {
			return null;
		}

		PlanChapterSequenceSet pc = (PlanChapterSequenceSet) value;

		return pc.getChapterID() + "||" + pc.getSequence();
	}

}
