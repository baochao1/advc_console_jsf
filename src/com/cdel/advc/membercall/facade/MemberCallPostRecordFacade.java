package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.MemberCallPostRecord;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MemberCallPostRecordFacade extends
		BaseFacadeImpl<MemberCallPostRecord, Integer> implements Serializable {

}
