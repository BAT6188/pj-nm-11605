package com.harmonywisdom.dshbcbp.exelaw.service;

import com.harmonywisdom.dshbcbp.exelaw.bean.ProblemCorrect;
import com.harmonywisdom.framework.service.IBaseService;

public interface ProblemCorrectService extends IBaseService<ProblemCorrect, String> {
    /**
     * 整改情况更新状态
     * @param id
     */
    int updateStatus(String id);
}