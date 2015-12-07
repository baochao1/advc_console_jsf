/*
 * @Title: Constant.java
 * @Package com.cdeledu.plat.api.callCenter
 * @Description: TODO
 * @author Du Haiying duhaiying1985@chinaacc.com
 * @date 2013-10-14 下午2:13:51
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-10-14                          
 */
package com.cdel.api.callCenter;

/** 
 * <p>相关常量</p>
 * 
 * @author Du Haiying
 * Create at:2013-10-14 下午2:13:51
 */
public class Constant {
	 
	//------------------------------呼叫中心-----------------------------------//

	/** 呼叫中心sqlMap 前缀 */
	public static final String PREFIX = "api.callCenter.";
	
	/** 根据 classes 例子查询班级 */
	public static final String FINDCLASSES = PREFIX + "classes.findClasses";

	/** 根据 classes 例子查询班级 */
	public static final String FINDMEMBERSBYCLASSID = PREFIX + "member.findMembersByClassID";
	
	/** 根据 classes 最后一次更新时间  查询班级 */
	public static final String FINDCLASSESBYUPDATETIME = PREFIX + "classes.findClassesByUpdateTime";

	/** 根据 member的最后一次更新时间  查询学员的信息**/
	public static final String FINDMEMBERSBYUPDATETIME = PREFIX + "member.findMembersByUpdateTime";

	/** 批量插入回访记录   **/
	public static final String INSERTMEMBERCALL = PREFIX + "memberCall.insertMemberCall";

	/** 更新插入回访记录   **/
	public static final String UPDATEMEMBERCALL = PREFIX + "memberCall.updateMemberCall";
	
	/** 根据班级id 查询班主任id  **/
	public static final String GETTEACHERBYCLASSID = PREFIX + "memberCall.getTeacherByClassID";
    /** 从关联表中  查询 CallID **/
	public static final String GETCALLID = PREFIX + "memberCall.getCallID";
    /** 保存回访记录的关联表**/
	public static final String INSERTCALLID = PREFIX + "memberCall.insertCallID";;

}
