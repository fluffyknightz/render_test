package com.spcu.dims.SPCUDIMS.services;

import com.spcu.dims.SPCUDIMS.daos.SampleDao;
import com.spcu.dims.SPCUDIMS.models.SampleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Autowired
    SampleDao sampleDao;

    public SampleModel saveSample(SampleModel sampleModel){
        return sampleDao.save(sampleModel);
    }
}
