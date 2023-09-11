package com.example.demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component
public class MemoAddEventListener implements ApplicationListener<MemoEvent> {

    @Override
    public void onApplicationEvent(MemoEvent event) {
        System.out.println("[EVENT] Memo add : " + event);

    }
}
