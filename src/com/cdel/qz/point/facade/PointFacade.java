package com.cdel.qz.point.facade;

import java.io.Serializable;
import org.primefaces.model.LazyDataModel;
import org.springframework.stereotype.Service;
import com.cdel.qz.point.domain.Point;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class PointFacade extends BaseFacadeImpl<Point, Integer> implements
		Serializable {
	public LazyDataModel<Point> findPageAdd(Point entity) {
		return this.baseDao.findPage(entity, "countAddPoint",
				"findPageAddPoint");
	}

}
