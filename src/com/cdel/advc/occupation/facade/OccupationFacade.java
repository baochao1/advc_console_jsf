package com.cdel.advc.occupation.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.occupation.domain.Occupation;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class OccupationFacade extends BaseFacadeImpl<Occupation, Integer>
		implements Serializable {

}
