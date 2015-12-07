package com.cdel.advc.strategy.facade;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.cdel.advc.strategy.domain.StrategyOrder;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class StrategyOrderFacade extends BaseFacadeImpl<StrategyOrder, Integer>
		implements Serializable {

}
