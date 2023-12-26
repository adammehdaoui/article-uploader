package org.saveursdo.server.service;

import org.saveursdo.server.model.Article;
import org.saveursdo.server.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

}
