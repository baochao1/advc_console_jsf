package com.cdel.advc.qzUploadFile.facade;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cdel.advc.qzUploadFile.domain.QzUploadFile;
import com.cdel.util.BaseFacadeImpl;

@SuppressWarnings("serial")
@Service
public class QzUploadFileFacade extends BaseFacadeImpl<QzUploadFile, Integer>
		implements Serializable {
	/**
	 * 校验保存数据
	 * 
	 * @throws Exception
	 */
	public boolean checkData(byte flag, List<String> filePath) {
		if (flag == 0) {
			if (filePath == null || filePath.size() == 0) {
				return addWarnMessage("请先上传文件！");
			}
		}
		return true;
	}
}