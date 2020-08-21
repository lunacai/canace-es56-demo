package com.example.canaceesdemo;

public class Usermodel {
    String id;
    String index;
    String indexname;
    String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndexname() {
        return indexname;
    }

    public void setIndexname(String indexname) {
        this.indexname = indexname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Usermodel{" +
                "id='" + id + '\'' +
                ", index='" + index + '\'' +
                ", indexname='" + indexname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
