package org.saveursdo.server.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String home() {
        return "Application is working!";
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/articles")
    public Iterable<Article> getArticles() {
        return articleService.getArticles();
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping(
            path = "{id}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadArticleImage(@PathVariable("id") int articleID, @RequestParam(value = "file") MultipartFile file)
            throws IOException {
        articleService.uploadArticleImage(articleID, file);
    }

}
