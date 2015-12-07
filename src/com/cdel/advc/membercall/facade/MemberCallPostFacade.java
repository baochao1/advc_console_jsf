package com.cdel.advc.membercall.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.membercall.domain.MemberCallPost;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class MemberCallPostFacade extends
		BaseFacadeImpl<MemberCallPost, Integer> implements Serializable {

}
