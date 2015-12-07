package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.ReserveCallHistory;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ReserveCallHistoryFacade extends
		BaseFacadeImpl<ReserveCallHistory, Integer> implements Serializable {

}
