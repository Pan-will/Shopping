package com.weiwei.shopping.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 发送邮件工具类
 * @author 潘唯
 */
public class MailUtils {
	/**
	 * 发送邮件的方法
	 * @param to 收件人
	 * @param code 激活码
	 */
	public static void sendMail(String to, String code){
		/**
		 * 1.获得一个session对象
		 * 2.创建一个代表邮件的对象的Message
		 * 3.发送邮件Transport
		 */
		//1.获得一个session对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");//指定发送邮件的主机是谁：localhost
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("WWLeGouService@shop.com", "55555");
			}
		});
		//2.创建一个代表邮件的对象的Message
		Message message = new MimeMessage(session);
		try {
			//2.1:设置发件人
			message.setFrom(new InternetAddress("WWLeGouService@shop.com"));
			//2.2:设置收件人
			/*抄送：CC ； 密送：BCC*/
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//2.3:设置邮件标题
			message.setSubject("唯唯乐购商城官方账号激活邮件！");
			//2.4:设置邮件正文
			message.setContent("<h2>欢迎您使用唯唯乐购,这是唯唯乐购官方发送的账号激活邮件正文，请勿泄露<br/>此邮件，以免给用户带来损失！请点击后面的链接完成账户激活操作。</h2><h3><a href='http://127.0.0.1:8080/Shopping/user_active.action?code="+code+"'>http://127.0.0.1:8080/Shopping/user_active.action/code="+code+"</a></h3>", "text/html;charset=UTF-8");
			//3.发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	 public static void main(String[] args){
		sendMail("ceshi1@shop.com", "55555");
	}
}
