package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.ReserveCallPost;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class ReserveCallPostFacade extends
		BaseFacadeImpl<ReserveCallPost, Integer> implements Serializable {

}
