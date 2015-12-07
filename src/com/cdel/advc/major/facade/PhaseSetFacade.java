package com.cdel.advc.major.facade;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cdel.advc.major.domain.PhaseSet;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.CheckUtil;

@SuppressWarnings("serial")
@Service
public class PhaseSetFacade extends BaseFacadeImpl<PhaseSet, Integer> implements
		Serializable {
	/**
	 * 验证数据
	 * 
	 * @param phaseSetList
	 * @return
	 */
	public boolean checkPhaseSet(List<PhaseSet> phaseSetList) {
		if (phaseSetList != null && phaseSetList.size() > 0) {
			for (int i = 0; i < phaseSetList.size(); i++) {
				PhaseSet ps = phaseSetList.get(i);
				Short phaseNo = ps.getPhaseNo();
				if (phaseNo == null) {
					return addWarnMessage("第" + (i + 1) + "行阶段名不能为空！");
				}
				Short sequence = ps.getSequence();
				if (sequence == null
						|| !CheckUtil.checkInt(sequence.toString(), 3)) {
					return addWarnMessage("第" + (i + 1)
							+ "行顺序不能为空，且必须为数字,且长度不能超过3个字符！");
				}
				for (int j = 0; j < phaseSetList.size(); j++) {
					if (i != j) {
						if (phaseNo.shortValue() == phaseSetList.get(j)
								.getPhaseNo()) {
							return addWarnMessage("第" + (i + 1) + "行阶段名不能与第"
									+ (j + 1) + "行阶段名一样！");
						}
						if (sequence.shortValue() == phaseSetList.get(j)
								.getSequence()) {
							return addWarnMessage("第" + (i + 1) + "行顺序不能与第"
									+ (j + 1) + "行顺序一样！");
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * 批量更新
	 * 
	 * @param majorID
	 * @param classType
	 * @param phaseSetList
	 * @throws Exception
	 */
	public void updatePhaseSetList(Integer majorID, Short classType,
			List<PhaseSet> phaseSetList) throws Exception {
		PhaseSet ps = new PhaseSet();
		ps.setMajorID(majorID);
		ps.setClassType(classType);
		delete(ps);
		baseDao.insert(phaseSetList, "insertPhaseSetList");
	}

}
