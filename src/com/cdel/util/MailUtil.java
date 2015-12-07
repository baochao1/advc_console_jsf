package com.cdel.util;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {

	private MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象
	private Properties props; // 系统属性

	private String username = ""; // smtp认证用户名和密码
	private String password = "";

	private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

	private static Session sendMailSession;

	static {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.chinaacc.com");
		props.put("mail.user", "csdept@chinaacc.com");
		sendMailSession = Session.getInstance(props, null);
	}

	public MailUtil(String smtp) {
		setSmtpHost(smtp);
		createMimeMessage();
	}

	public MailUtil() {
	}

	/**
	 * @param hostName
	 *            String
	 */
	public void setSmtpHost(String hostName) {
		// System.out.println("设置系统属性：mail.smtp.host = " + hostName);
		if (props == null)
			props = System.getProperties(); // 获得系统属性对象

		props.put("mail.smtp.host", hostName); // 设置SMTP主机
	}

	/**
	 * @return boolean
	 */
	public boolean createMimeMessage() {
		try {
			// System.out.println("准备获取邮件会话对象！");
			session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
		} catch (Exception e) {
			// System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}

		System.out.println("准备创建MIME邮件对象！");
		try {
			mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败！" + e);
			return false;
		}
	}

	/**
	 * @param need
	 *            boolean
	 */
	public void setNeedAuth(boolean need) {
		// System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
		if (props == null)
			props = System.getProperties();

		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	/**
	 * @param mailSubject
	 *            String
	 * @return boolean
	 */
	public boolean setSubject(String mailSubject) {
		// System.out.println("设置邮件主题！");
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			// System.err.println("设置邮件主题发生错误！");
			return false;
		}
	}

	/**
	 * @param mailBody
	 *            String
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent(
					"<meta http-equiv=Content-Type content=text/html; charset="
							+ Contants.CODE + ">" + mailBody,
					"text/html;charset=" + Contants.CODE);
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			// System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean addFileAffix(String filename) {

		// System.out.println("增加邮件附件：" + filename);

		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			bp.setFileName(fileds.getName());

			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			// System.err.println("增加邮件附件：" + filename + "发生错误！" + e);
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setFrom(String from) {
		// System.out.println("设置发信人！");
		try {
			mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setTo(String to) {
		if (to == null)
			return false;

		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
					.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean setCopyTo(String copyto) {
		if (copyto == null)
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC,
					(Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param name
	 *            String
	 * @param pass
	 *            String
	 */
	public boolean sendout() {
		try {
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			// System.out.println("正在发送邮件....");

			Session mailSession = Session.getInstance(props, null);
			Transport transport = mailSession.getTransport("smtp");
			transport.connect((String) props.get("mail.smtp.host"), username,
					password);
			transport.sendMessage(mimeMsg, mimeMsg
					.getRecipients(Message.RecipientType.TO));

			// System.out.println("发送邮件成功！");
			transport.close();

			return true;
		} catch (Exception e) {
			// System.err.println("邮件发送失败！" + e);
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("static-access")
	public boolean mailSendOut(String toEmail, String subject, String text) {
		Boolean status = null;
		try {

			Transport transport;

			Message newMessage = new MimeMessage(sendMailSession);
			newMessage.setFrom(new InternetAddress("csdept@chinaacc.com",
					MimeUtility.encodeText("网校教务部", Contants.CODE, "B")));
			newMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmail));
			newMessage.setSubject(MimeUtility.encodeText(subject,
					Contants.CODE, "B"));
			newMessage.setSentDate(new Date());
			// 下面为设置内容
			Multipart mpp = new MimeMultipart();
			BodyPart bp = new MimeBodyPart();
			bp.setContent(
					"<meta http-equiv=Content-Type content=text/html; charset="
							+ Contants.CODE + ">" + text, "text/html;charset="
							+ Contants.CODE);
			mpp.addBodyPart(bp);

			newMessage.setContent(mpp);

			transport = sendMailSession.getTransport("smtp");
			transport.send(newMessage);
			status = true;
		} catch (Exception m) {
			status = false;
		}
		return status;
	}

	public static void main(String[] args) {
		MailUtil m = new MailUtil();
		m.mailSendOut("zhangsulei@cdeledu.com", "测试标题", "测试内容");
	}
}