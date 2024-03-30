package org.saveursdo.server.article;

import jakarta.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "imageLink")
    private String imageLink;

    public Article() {
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Article a
                && id == a.id
                && content.equals(a.content);
    }

    @Override
    public int hashCode(){
        return id ^ content.hashCode();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

}
