package com.cdel.plat.grant.auth;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;
import com.cdel.advc.common.JsfHelper;
import com.cdel.util.Contants;

public class LoginCheck implements PhaseListener {
	private static final long serialVersionUID = 6186106530012195240L;

	/*
	 * (non-Javadoc)验证是否登录
	 * 
	 * @see
	 * javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent event) {
		PhaseId phaseId = event.getPhaseId();
		if (phaseId == PhaseId.RESTORE_VIEW
				|| phaseId == PhaseId.INVOKE_APPLICATION) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = JsfHelper.getSession(facesContext);
			Integer memberID = (Integer) session
					.getAttribute(Contants.ADMIN_USER_ID);
			String viewId = facesContext.getViewRoot().getViewId();
			if (viewId.indexOf("login.xhtml") == -1
					&& viewId.indexOf("compelUpdatePwd.xhtml") == -1
					&& viewId.indexOf("/api/") == -1 && memberID == null) {
				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application
						.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "login");
				facesContext.renderResponse();
			}
		}
	}

	public void beforePhase(PhaseEvent event) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
