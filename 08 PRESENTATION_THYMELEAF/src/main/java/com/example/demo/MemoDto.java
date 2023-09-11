package com.example.demo;

public class MemoDto {
    private int id;
    private String text;

    public MemoDto(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
