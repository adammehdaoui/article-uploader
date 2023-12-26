package org.saveursdo.server.service;

import org.saveursdo.server.model.Article;
import org.saveursdo.server.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

}
