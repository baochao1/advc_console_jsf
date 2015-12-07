package com.cdel.qz.pointList.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.qz.pointList.domain.PointList;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class PointListFacade extends BaseFacadeImpl<PointList, Integer>
		implements Serializable {

}
