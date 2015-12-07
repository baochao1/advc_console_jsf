/*
 * @Title: MemberClassFacade.java
 * @Package com.cdel.advc.memberclass.facade
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-7-11 上午9:19:47
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-7-11                          
 */
package com.cdel.advc.memberclass.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.cdel.advc.memberclass.domain.PhoneNumberLog;
import com.cdel.util.BaseFacadeImpl;
import com.cdel.util.Contants;
import com.cdel.util.ContantsUrl;
import com.cdel.util.DateUtil;
import com.cdel.util.MD5;
import com.cdel.util.RemoteUtil;

/**
 * <p> 更新学员的手机号 实体 facade层</p>
 * @author xuxiaoguang Create at:2013-7-11 上午9:19:47
 */
@SuppressWarnings("serial")
@Service
public class PhoneNumberLogFacade extends BaseFacadeImpl<PhoneNumberLog, Integer> {

	public void updateCellPhoneNumber(PhoneNumberLog vo) throws Exception {
		this.updateJiaoWuCellPhoneNumber(vo);
		// 通过接口更新主库的电话号码
		if(updatePhone(vo)){
			String oldphonenumber = getOldPhonenumberByUserID(vo.getMemberID());
				vo.setOldphonenumber(oldphonenumber);
				vo.setUpdateTime(new Date());
			this.saveCellPhoneNumberLog(vo);
			// 更是时间
			this.updateMemberClassTime(vo);
		}
	}

	 
    /**
     * 更新主库中的学员的手机号
     */
	@SuppressWarnings("unchecked")
	public void updateMainCellPhoneNumber(PhoneNumberLog vo) {
		Map map = new HashedMap();
		map.put(Contants.DATA_KEY,  Contants.ACC_MAIN);
		map.put("newphonenumber", vo.getNewphonenumber());
		map.put("memberID", vo.getMemberID());
		this.baseDao.update(map, "updateMainCellPhoneNumber");
	}
	
	/**
     * 更新教务库中的学员的手机号
     */
	public void updateJiaoWuCellPhoneNumber(PhoneNumberLog vo) {
		this.baseDao.update(vo, "updateJiaoWuCellPhoneNumber");
	}
	
	/**
	 * 新增更新手机号日志记录信息
	 */
	public void saveCellPhoneNumberLog(PhoneNumberLog vo) {
		 this.baseDao.insert(vo, "saveCellPhoneNumberLog");	
	}
	/**
	 * 根据userID得到原先的手机号
	 */
	public String getOldPhonenumberByUserID(Integer userID){
		return (String) this.baseDao.get(userID, "getOldPhonenumberByUserID");
	}
	/**
	 * 更新时间，为外呼提供查询数据
	 */
	public void updateMemberClassTime(PhoneNumberLog vo) {
		 this.baseDao.insert(vo, "updateMemeberClasssUpdateTime");	
	}
	
	/**
	 * 通过接口更新主库中手机号
	 * 
	 */
	public String  updatePhoneFormReomte(PhoneNumberLog vo) throws Exception {
		String urlServer = ContantsUrl.UPDATEPHONE_URL;
		Integer uid = vo.getMemberID();
		String mobilePhone = vo.getNewphonenumber();
		String time = DateUtil.getNowToString("yyyy-MM-dd HH:mm:ss");
		System.out.println(time);
		String URLKEY = "Ui8Ii6Dq9#R6Rx0"; // 私钥URLKEY
		// key:可以为mobilePhone + uid + time三个参数 + "Ui8Ii6Dq9#R6Rx0",md5加密(32位)生成
		String key = MD5.Md5(mobilePhone + uid + time + URLKEY, 32);
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("uid", String.valueOf(uid));
		parameters.put("mobilePhone", mobilePhone);
		parameters.put("time", time);
		parameters.put("key", key);

		return RemoteUtil.getPostRemoteString(urlServer, parameters);
	}
 
	public  boolean updatePhone(PhoneNumberLog vo) throws Exception {
		boolean bool = false;
		String bl =  updatePhoneFormReomte(vo);
		bl = new String(bl.getBytes("iso-8859-1"), "UTF-8").trim();
		String array[] = bl.split("\\|");
		if (array.length == 2) {
			if (array[0].equals("true")) {
				bool = true;
			}else{
				bool = false;
			}
		}
		return bool;
	}
}
