package com.cdel.advc.major.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.major.domain.PromptTime;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class PromptTimeFacade extends BaseFacadeImpl<PromptTime, Integer>
		implements Serializable {

}
