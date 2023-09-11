package com.example.demo.listener;

import com.example.demo.domain.dto.MemoDto;
import org.springframework.context.ApplicationEvent;

public class MemoEvent extends ApplicationEvent {
    private MemoDto dto;
    public MemoEvent(Object source, MemoDto dto) {
        super(source);
        this.dto = dto;
    }

    @Override
    public String toString() {
        return "MemoEvent{" +
                "dto=" + dto +
                '}';
    }
}
