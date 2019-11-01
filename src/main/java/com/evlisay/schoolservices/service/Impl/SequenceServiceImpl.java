package com.evlisay.schoolservices.service.Impl;

import com.evlisay.schoolservices.service.SequenceService;
import com.evlisay.schoolservices.utils.sequen.Sequence;
import org.springframework.stereotype.Service;

/**
 * @Author: EvilSay
 * @Date: 2019/7/10 20:23
 */
@Service
public class SequenceServiceImpl implements SequenceService {
    private Sequence sequence = new Sequence(0,0);

    @Override
    public String getSequenceId() {

        return String.valueOf(sequence.nextId());
    }
}
