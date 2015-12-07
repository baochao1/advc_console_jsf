package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.ReserveCallAll;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ReserveCallAllFacade extends
		BaseFacadeImpl<ReserveCallAll, Integer> implements Serializable {

}
