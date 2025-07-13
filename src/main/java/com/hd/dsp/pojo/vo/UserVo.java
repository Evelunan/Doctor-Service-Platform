package com.hd.dsp.pojo.vo;

import com.hd.dsp.pojo.DiseaseHistory;
import com.hd.dsp.pojo.FamilyHistory;
import com.hd.dsp.pojo.HealthInfo;
import com.hd.dsp.pojo.User;

import java.util.List;

public class UserVo extends User {
    private HealthInfo healthInfo;
    private List<DiseaseHistory> diseaseHistoryList;
    private List<FamilyHistory> familyHistoryList;
}
