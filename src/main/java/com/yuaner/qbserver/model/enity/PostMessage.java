package com.yuaner.qbserver.model.enity;
import com.yuaner.qbserver.common.common_string.SentCodeString;
import lombok.Data;

@Data
public class PostMessage {
    Long messageId;
    /**
     * 用途
     */
    String messageUse;
    /**
     * 主题
     */
    String messageSubject;
    /**
     * 内容
     */
    String messageContent;

    public PostMessage(Long messageId, String messageUse, String messageSubject, String messageContent) {
        this.messageId = messageId;
        this.messageUse = messageUse;
        this.messageSubject = messageSubject;
        this.messageContent = messageContent;
    }

    public  static PostMessage getPostMessageById(int id) {
        switch (id){
            case 1:return SentCodeString.TYPE1;
            case 2:return SentCodeString.TYPE2;
            case 3:return SentCodeString.TYPE3;
            case 4:return SentCodeString.TYPE4;
        }
        return null;
    }
}
