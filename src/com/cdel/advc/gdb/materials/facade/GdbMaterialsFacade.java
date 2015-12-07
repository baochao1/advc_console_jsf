package com.cdel.advc.gdb.materials.facade;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.cdel.advc.gdb.materials.domain.GdbMaterials;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class GdbMaterialsFacade extends BaseFacadeImpl<GdbMaterials, Integer>
		implements Serializable {
	/**
	 * 校验保存数据
	 * 
	 * @throws Exception
	 */
	public boolean checkData(byte flag, List<String> filePath,
			GdbMaterials updateGdbMaterials) {
		if (updateGdbMaterials.getSiteCourseID() == null) {
			if (flag == 0) {
				return addWarnMessage("课程不能为空！");
			} else {
				return addWarnMessage("info", "课程不能为空！");
			}
		}
		if (flag == 0) {
			if (filePath == null || filePath.size() == 0) {
				return addWarnMessage("请先上传文件！");
			}
		}
		if (flag == 1) {
			if (StringUtils.isBlank(updateGdbMaterials.getMaterialName())) {
				return addWarnMessage("info", "资料名称不能为空！");
			}
		}
		return true;
	}

}