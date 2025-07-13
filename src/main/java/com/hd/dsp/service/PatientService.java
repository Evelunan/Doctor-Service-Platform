package com.hd.dsp.service;

import com.hd.dsp.pojo.vo.UserVo;
import org.springframework.stereotype.Service;


public interface PatientService {

    UserVo getPatientArchive(Integer id);
}
