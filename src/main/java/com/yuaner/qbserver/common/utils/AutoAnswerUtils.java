package com.yuaner.qbserver.common.utils;

import com.yuaner.qbserver.service.QqbotAnswerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AutoAnswerUtils {

    private static QqbotAnswerService service;
    @Resource
    private void setQqbotAnswerService(QqbotAnswerService service) {
        this.service = service;
    }
    /**
     * 调用自动回答
     * @param question
     * @return
     */
    public  String autoAnswer(String question){
        if(question.startsWith("?")){
            return "你在这扣问号干嘛？";
        }
        return  service.getAnswer(question);
    }
}
