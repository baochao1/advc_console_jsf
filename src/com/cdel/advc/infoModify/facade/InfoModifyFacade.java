package com.cdel.advc.infoModify.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdel.advc.infoModify.domain.InfoModify;
import com.cdel.advc.membermsg.facade.MembermsgFacade;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class InfoModifyFacade extends BaseFacadeImpl<InfoModify, Integer> {
	@Autowired
	private MembermsgFacade membermsgFacade;

	/**
	 * 更新消息内容
	 * 
	 * @param update
	 * @throws Exception
	 */
	public void updateContent(InfoModify update) throws Exception {
		membermsgFacade.updateContent(update);
	}

}
