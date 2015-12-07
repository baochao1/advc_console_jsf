package com.cdel.qz.business.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.qz.business.domain.QzBusiness;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class QzBusinessFacade extends BaseFacadeImpl<QzBusiness, Integer>
		implements Serializable {

}
