package com.lin.util;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtils {
    // �����������ַ
    private static String from = "linchengwu_1@163.com";
    // �����˳ƺţ�ͬ�����ַ
    private static String user = "linchengwu_1@163.com";
    // ����������ͻ�����Ȩ��
    private static String password = "131452046aa";
    //�����˵����������
    private static String mailHost = "smtp.163.com";

    /**
     * @param to
     * @param text
     * @param title
     */
	/* ������֤��Ϣ���ʼ� */
    public static boolean sendMail(String to, String text, String title) {
        Properties props = new Properties();
// ���÷����ʼ����ʼ������������ԣ�����ʹ�����׵�smtp��������
        props.put("mail.smtp.host", mailHost);
// ��Ҫ������Ȩ��Ҳ�����л����������У�飬��������ͨ����֤��һ��Ҫ����һ����
        props.put("mail.smtp.auth", "true");
// �øո����úõ�props���󹹽�һ��session
        Session session = Session.getDefaultInstance(props);
// ������������ڷ����ʼ��Ĺ�������console����ʾ������Ϣ��������ʹ�ã�������ڿ���̨��console)�Ͽ��������ʼ��Ĺ��̣�
//                session.setDebug(true);
// ��sessionΪ����������Ϣ����
        MimeMessage message = new MimeMessage(session);
        try {
// ���ط����˵�ַ
            message.setFrom(new InternetAddress(from));
// �����ռ��˵�ַ
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
// ���ر���
            message.setSubject(title);
// ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���
            Multipart multipart = new MimeMultipart();
// �����ʼ����ı�����
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(text, "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            message.saveChanges(); // ����仯
// ���ӷ�����������
            Transport transport = session.getTransport("smtp");
// ���ʼ����ͳ�ȥ
            transport.connect(mailHost, user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("�ʼ����ͳɹ�");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) { // ��������
//String toMail="xxxxx@fenglinggame.com";
            String toMail = "1468494508@qq.com";
        String text = "���,<a href='http://www.baidu.com'>����</a>�о�ϲ��";
        String title = "����С֪ͨ";
        sendMail(toMail, text, title);
    }

}


