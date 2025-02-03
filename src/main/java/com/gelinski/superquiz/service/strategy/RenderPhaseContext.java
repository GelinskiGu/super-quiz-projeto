package com.gelinski.superquiz.service.strategy;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RenderPhaseContext {
    private final Map<Integer, RenderPhaseService> strategies = new HashMap<>();
    private final RenderPhaseOneService renderPhaseOneService;
    private final RenderPhaseTwoService renderPhaseTwoService;

    @PostConstruct
    public void init() {
        strategies.put(1, renderPhaseOneService);
        strategies.put(2, renderPhaseTwoService);
    }

    public RenderPhaseService getStrategy(Integer phaseNumber) {
        return strategies.get(phaseNumber);
    }
}
