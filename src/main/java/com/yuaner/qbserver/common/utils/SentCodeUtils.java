package com.yuaner.qbserver.common.utils;

import com.yuaner.qbserver.common.common_string.SentCodeString;
import com.yuaner.qbserver.model.enity.PostMessage;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件验证码
 */
@Slf4j
public class SentCodeUtils {
    /**
     *
     * @param code 验证码
     * @param toUser 接收对象
     * @param postMessage 消息模板对象
     * @return 返回发送状态
     */
    public static boolean sentCode(String code, String toUser, PostMessage postMessage) {
        log.info("执行发送代码");
        Session session = Session.getInstance(getProperties(),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SentCodeString.username,SentCodeString.password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SentCodeString.username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toUser));
            message.setSubject(postMessage.getMessageSubject(), "utf-8");
            message.setText(replaceContent(code,toUser,postMessage.getMessageContent()), "utf-8", "html");
            Transport.send(message);
            log.info("邮件已成功投递至： "+toUser);
        } catch (MessagingException e) {
            log.error("发送邮件发生错误："+e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * smtp服务连接配置
     * @return
     */
    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SentCodeString.smtp_host);
        props.put("mail.smtp.port", SentCodeString.smtp_port);
        return props;
    }

    /**
     *
     * @param code 验证码
     * @param toUser 邮件接收对象
     * @param content 邮件内容模板
     * @return 替换后的结果
     */
    private static String replaceContent(String code, String toUser,String content) {
        String result =content.replace(SentCodeString.user_replace, toUser.substring(0, toUser.indexOf("@")));
        result = result.replace(SentCodeString.code_replace, code);
        return result;
    }
}
