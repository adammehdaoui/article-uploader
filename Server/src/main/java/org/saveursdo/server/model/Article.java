package org.saveursdo.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    public Article() {
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Article a
                && id == a.id
                && content.equals(a.content);
    }

    @Override
    public int hashCode(){
        return (int)id ^ content.hashCode();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }

}
