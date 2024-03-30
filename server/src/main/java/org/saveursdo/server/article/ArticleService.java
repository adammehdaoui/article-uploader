package org.saveursdo.server.article;

import org.saveursdo.server.article.Article;
import org.saveursdo.server.article.ArticleRepository;
import org.saveursdo.server.bucket.BucketName;
import org.saveursdo.server.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileStore fileStore;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, FileStore fileStore) {
        this.articleRepository = articleRepository;
        this.fileStore = fileStore;
    }

    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    public void uploadArticleImage(int articleID, MultipartFile file) throws IOException {
        Objects.requireNonNull(file);
        Objects.requireNonNull(file.getContentType());
        Objects.requireNonNull(file.getOriginalFilename());

        if(file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() + "]");
        }

        if(!file.getContentType().startsWith("image")) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }

        fileStore.save(
                "article-" + articleID,
                file.getOriginalFilename(),
                file.getInputStream()
        );

        Optional<Article> article = articleRepository.findById((long) articleID);
        if(article.isEmpty()) {
            throw new IllegalStateException("Article with ID [" + articleID + "] does not exist");
        }

        Article articleObject = article.get();

        articleObject.setImageLink(file.getOriginalFilename());
    }
}
