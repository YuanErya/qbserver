package com.yuaner.qbserver.common.common_string;

import com.yuaner.qbserver.model.enity.PostMessage;

public abstract class SentCodeString {
    public final static String username = "email@email.email";
    public final static String password = "email-password";
    public final static String smtp_host = "smtp.qq.com";
    public final static String smtp_port = "587";
    //数据库模板中使用的占位符
    public final static String user_replace = "&&$user$&&";
    //数据库模板中使用的占位符
    public final static String code_replace = "&&$code$&&";
    //邮件模板的id
    public final static Integer ID_message_register = 1;
    public final static Integer ID_message_login = 2;
    public final static Integer ID_message_find_password = 3;
    public final static Integer ID_message_change_password = 4;

    //取或者存验证码时候的分级前缀
    //key=key1+typeId+key2+email
    public final static String get_code_key1 = "code:codeTypeId:";
    public final static String get_code_key2 = ":email:";

    public final static String md5_salt = "we are winner";

    public final static Integer code_redis_timeout =10;

    public final static PostMessage TYPE1=new PostMessage(1L,"注册验证","WELCOME TO YUANER~~~验证码","<!DOCTYPE html>\n" +
            "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"description\" content=\"email code\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "</head>\n" +
            "<!--邮箱验证码模板-->\n" +
            "<body>\n" +
            "    <div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
            "        <table cellpadding=\"0\" align=\"center\"\n" +
            "               style=\"width: 800px;height: 100%; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
            "            <tbody>\n" +
            "                <tr>\n" +
            "                    <th valign=\"middle\"\n" +
            "                        style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: RGB(135,206,235); background-color: RGB(135,206,235); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
            "                        <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">Nice to meet you !</font>\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"word-break:break-all\">\n" +
            "                        <div style=\"padding:25px 35px 40px; background-color:#fff;opacity:0.8;\">\n" +
            "\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        尊敬的&&$user$&&：\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <!-- 中文 -->\n" +
            "                            <p>您好！感谢您注册使用YuanEr账号，您的正在进行账号注册操作，为了验证这是您本人邮箱，请输入以下验证码进行验证：                          \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                        <center>\n" +
            "                                            &&$code$&&\n" +
            "                                        </center>\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <br>\n" +
            "                            有效期10分钟，请尽快填写验证码完成验证！</p><br>\n" +
            "                            <!-- 英文 -->\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        Dear &&$user$&&:\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <p>\n" +
            "                                Hello! Thanks for using YuanEr, Your account is currently being registered. To verify that this is your email address, please enter the following verification code for verification:\n" +
            "                                <br>                                \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                    <font color=\"#733333\" style=\"line-height: 20px; \">\n" +
            "                                        <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                            <center>\n" +
            "                                                &&$code$&&\n" +
            "                                            </center>\n" +
            "                                        </font>\n" +
            "                                    </font>\n" +
            "                                </h2>\n" +
            "                                <br>\n" +
            "                                valid for 10 minutes. Please fill in the verification code as soon as\n" +
            "                                possible!\n" +
            "                            </p>\n" +
            "                            <div style=\"width:100%;margin:0 auto;\">\n" +
            "                                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
            "                                    <p>种一棵树最好的时间是十年前,其次是<b>现在</b></p>\n" +
            "                                    <p>Power By: YuanEr</p>\n" +
            "                                    <br>\n" +
            "                                    <p>\n" +
            "                                        如果您没有申请邮箱验证，请忽略此邮件。<br>\n" +
            "                                        此为系统邮件，请勿回复<br>\n" +
            "                                        Please do not reply to this system email\n" +
            "                                    </p>\n" +
            "                                    <!--<p>©***</p>-->\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n" +
            "\n");
    public final static PostMessage TYPE2=new PostMessage(2L,"登录验证","LOGIN IN YUANER~~~验证码","<!DOCTYPE html>\n" +
            "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"description\" content=\"email code\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "</head>\n" +
            "<!--邮箱验证码模板-->\n" +
            "<body>\n" +
            "    <div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
            "        <table cellpadding=\"0\" align=\"center\"\n" +
            "               style=\"width: 800px;height: 100%; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
            "            <tbody>\n" +
            "                <tr>\n" +
            "                    <th valign=\"middle\"\n" +
            "                        style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: RGB(135,206,235); background-color: RGB(135,206,235); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
            "                        <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">Welcome back !</font>\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"word-break:break-all\">\n" +
            "                        <div style=\"padding:25px 35px 40px; background-color:#fff;opacity:0.8;\">\n" +
            "\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        尊敬的&&$user$&&：\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <!-- 中文 -->\n" +
            "                            <p>您好！您正在使用邮箱登录YuanEr账号，为了验证这是您本人邮箱，请输入以下验证码进行验证：                          \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                        <center>\n" +
            "                                            &&$code$&&\n" +
            "                                        </center>\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <br>\n" +
            "                            有效期10分钟，请尽快填写验证码完成验证！</p><br>\n" +
            "                            <!-- 英文 -->\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        Dear &&$user$&&:\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <p>\n" +
            "                                Hello! You are using the mailbox verification code to log in to Yuaner account, in order to verify this is your own mailbox, please enter the following verification code to verify:\n" +
            "                                <br>                                \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                    <font color=\"#733333\" style=\"line-height: 20px; \">\n" +
            "                                        <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                            <center>\n" +
            "                                                &&$code$&&\n" +
            "                                            </center>\n" +
            "                                        </font>\n" +
            "                                    </font>\n" +
            "                                </h2>\n" +
            "                                <br>\n" +
            "                                valid for 10 minutes. Please fill in the verification code as soon as\n" +
            "                                possible!\n" +
            "                            </p>\n" +
            "                            <div style=\"width:100%;margin:0 auto;\">\n" +
            "                                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
            "                                    <p>世界灿烂盛大,也有人欢迎你<b>回家</b></p>\n" +
            "                                    <p>Power By: YuanEr</p>\n" +
            "                                    <br>\n" +
            "                                    <p>\n" +
            "                                        如果您没有申请邮箱验证，请忽略此邮件。<br>\n" +
            "                                        此为系统邮件，请勿回复<br>\n" +
            "                                        Please do not reply to this system email\n" +
            "                                    </p>\n" +
            "                                    <!--<p>©***</p>-->\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n" +
            "\n");
    public final static PostMessage TYPE3=new PostMessage(3L,"找回密码验证","FIND THE PASSWORD~~~验证码","<!DOCTYPE html>\n" +
            "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"description\" content=\"email code\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "</head>\n" +
            "<!--邮箱验证码模板-->\n" +
            "<body>\n" +
            "    <div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
            "        <table cellpadding=\"0\" align=\"center\"\n" +
            "               style=\"width: 800px;height: 100%; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
            "            <tbody>\n" +
            "                <tr>\n" +
            "                    <th valign=\"middle\"\n" +
            "                        style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: RGB(135,206,235); background-color: RGB(135,206,235); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
            "                        <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">More exciting next time !</font>\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"word-break:break-all\">\n" +
            "                        <div style=\"padding:25px 35px 40px; background-color:#fff;opacity:0.8;\">\n" +
            "\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        尊敬的&&$user$&&：\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <!-- 中文 -->\n" +
            "                            <p>很遗憾，你忘记了你的登录密码！您正在使用邮箱验证身份找回密码，为了验证这是您本人邮箱，请输入以下验证码进行验证：                          \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                        <center>\n" +
            "                                            &&$code$&&\n" +
            "                                        </center>\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <br>\n" +
            "                            有效期10分钟，请尽快填写验证码完成验证！</p><br>\n" +
            "                            <!-- 英文 -->\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        Dear &&$user$&&:\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <p>\n" +
            "                                Sorry, you forgot your login password! You are using mailbox authentication to retrieve your password. To verify that this is your mailbox, please enter the following authentication code:\n" +
            "                                <br>                                \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                    <font color=\"#733333\" style=\"line-height: 20px; \">\n" +
            "                                        <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                            <center>\n" +
            "                                                &&$code$&&\n" +
            "                                            </center>\n" +
            "                                        </font>\n" +
            "                                    </font>\n" +
            "                                </h2>\n" +
            "                                <br>\n" +
            "                                valid for 10 minutes. Please fill in the verification code as soon as\n" +
            "                                possible!\n" +
            "                            </p>\n" +
            "                            <div style=\"width:100%;margin:0 auto;\">\n" +
            "                                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
            "                                    <p>没有什么会使我停留，除了——<b>目的</b></p>\n" +
            "                                    <p>Power By: YuanEr</p>\n" +
            "                                    <br>\n" +
            "                                    <p>\n" +
            "                                        如果您没有申请邮箱验证，请忽略此邮件。<br>\n" +
            "                                        此为系统邮件，请勿回复<br>\n" +
            "                                        Please do not reply to this system email\n" +
            "                                    </p>\n" +
            "                                    <!--<p>©***</p>-->\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n" +
            "\n");
    public final static PostMessage TYPE4=new PostMessage(4L,"修改密码验证","CHANGE THE PASSWORD~~~验证码","<!DOCTYPE html>\n" +
            "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
            "<head>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"description\" content=\"email code\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "</head>\n" +
            "<!--邮箱验证码模板-->\n" +
            "<body>\n" +
            "    <div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
            "        <table cellpadding=\"0\" align=\"center\"\n" +
            "               style=\"width: 800px;height: 100%; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
            "            <tbody>\n" +
            "                <tr>\n" +
            "                    <th valign=\"middle\"\n" +
            "                        style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: RGB(135,206,235); background-color: RGB(135,206,235); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
            "                        <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">Look forward to the next surprise !</font>\n" +
            "                    </th>\n" +
            "                </tr>\n" +
            "                <tr>\n" +
            "                    <td style=\"word-break:break-all\">\n" +
            "                        <div style=\"padding:25px 35px 40px; background-color:#fff;opacity:0.8;\">\n" +
            "\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        尊敬的&&$user$&&：\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <!-- 中文 -->\n" +
            "                            <p>你好！您正在进行修改密码操作，这属于高风险行为，为了验证这是您本人操作，请输入以下验证码进行验证：                          \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                        <center>\n" +
            "                                            &&$code$&&\n" +
            "                                        </center>\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <br>\n" +
            "                            有效期10分钟，请尽快填写验证码完成验证！</p><br>\n" +
            "                            <!-- 英文 -->\n" +
            "                            <h2 style=\"margin: 5px 0px; \">\n" +
            "                                <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
            "                                    <font style=\"line-height: 22px; \" size=\"4\">\n" +
            "                                        Dear &&$user$&&:\n" +
            "                                    </font>\n" +
            "                                </font>\n" +
            "                            </h2>\n" +
            "                            <p>\n" +
            "                                Hello! You Are Changing the password operation, which is a high-risk behavior, in order to verify this is your operation, please enter the following verification code to verify:\n" +
            "                                <br>                                \n" +
            "                                <h2 style=\"margin: 5px 0px; \">\n" +
            "                                    <font color=\"#733333\" style=\"line-height: 20px; \">\n" +
            "                                        <font style=\"line-height: 22px; \" size=\"10\">\n" +
            "                                            <center>\n" +
            "                                                &&$code$&&\n" +
            "                                            </center>\n" +
            "                                        </font>\n" +
            "                                    </font>\n" +
            "                                </h2>\n" +
            "                                <br>\n" +
            "                                valid for 10 minutes. Please fill in the verification code as soon as\n" +
            "                                possible!\n" +
            "                            </p>\n" +
            "                            <div style=\"width:100%;margin:0 auto;\">\n" +
            "                                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
            "                                    <p>自己的命运自己掌控，脚下的路不<b>停步</b></p>\n" +
            "                                    <p>Power By: YuanEr</p>\n" +
            "                                    <br>\n" +
            "                                    <p>\n" +
            "                                        如果您没有申请邮箱验证，请忽略此邮件。<br>\n" +
            "                                        此为系统邮件，请勿回复<br>\n" +
            "                                        Please do not reply to this system email\n" +
            "                                    </p>\n" +
            "                                    <!--<p>©***</p>-->\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </td>\n" +
            "                </tr>\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "</body>\n" +
            "</html>\n" +
            "\n");
}
