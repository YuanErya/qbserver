package com.yuaner.qbserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yuaner.qbserver.mapper.QqbotAjMapper;
import com.yuaner.qbserver.model.enity.AnswerAj;
import com.yuaner.qbserver.service.QqbotAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QqbotAnswerServiceImpl implements QqbotAnswerService {
    @Autowired
    QqbotAjMapper qqbotAjMapper;

    /**
     * 词库匹配搜索逻辑，略显拉跨
     * @param question
     * @return
     */
    @Override
    public String getAnswer(String question) {
        //type==1时为傲娇类型回复
            LambdaQueryWrapper<AnswerAj> lqw = new LambdaQueryWrapper<>();
            lqw.like(AnswerAj::getQuestion, question);
            List<AnswerAj> list = qqbotAjMapper.selectList(lqw);
            if (list.size() != 0) {
                return list.get((int) (Math.random() * list.size())).getAnswer();
            }
            for (int i = 0; i < 5&&question.length()>1; i++) {
                if (i % 2 == 1) {
                    //头删
                    question = question.substring(getNum(question));
                } else {
                    //尾删
                    question = question.substring(0,question.length() -getNum(question));
                }
                lqw.clear();
                lqw.like(AnswerAj::getQuestion, question);
                List<AnswerAj> tmpList = qqbotAjMapper.selectList(lqw);
                if(tmpList.size()!=0){
                    return tmpList.get((int) (Math.random() * list.size())).getAnswer();
                }
            }
            return "词库不匹配";
    }
    public int getNum(String question) {
        int num = question.length();
        if (num <= 5) {
            return 1;
        } else if (num <= 10) {
            return 2;
        } else if (num <= 20) {
            return 5;
        } else {
            return 10;
        }
    }
}





