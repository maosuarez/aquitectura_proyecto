package com.magnix.app.bridge;

public class Post {
    private String author;
    private String content;
    private PostType type;
    private IPostBridge renderer;

    public Post(String author, String content, PostType type, IPostBridge renderer) {
        this.author = author;
        this.content = content;
        this.type = type;
        this.renderer = renderer;
    }

    public void display() {
        renderer.render(this);
    }

    public String getFormattedContent() {
        return "👤 Autor: " + author + "\n"
             + "🏷️ Tipo: " + type + "\n\n"
             + content;
    }

    public PostType getType() {
        return type;
    }
}

