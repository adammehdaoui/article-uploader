package org.saveursdo.server.article;

import org.saveursdo.server.article.Article;
import org.saveursdo.server.article.ArticleRepository;
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
