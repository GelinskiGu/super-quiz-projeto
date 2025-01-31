package com.gelinski.superquiz.mapper.singleton;

import com.gelinski.superquiz.mapper.PhaseOneQuestionMapper;
import org.mapstruct.factory.Mappers;

public class PhaseOneQuestionMapperSingleton {
    private static final PhaseOneQuestionMapper INSTANCE = Mappers.getMapper(PhaseOneQuestionMapper.class);

    private PhaseOneQuestionMapperSingleton() {
    }

    public static PhaseOneQuestionMapper getInstance() {
        return INSTANCE;
    }
}