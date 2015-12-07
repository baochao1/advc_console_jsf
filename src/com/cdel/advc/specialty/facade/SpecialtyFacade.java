package com.cdel.advc.specialty.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.specialty.domain.Specialty;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class SpecialtyFacade extends BaseFacadeImpl<Specialty, Integer>
		implements Serializable {

}
