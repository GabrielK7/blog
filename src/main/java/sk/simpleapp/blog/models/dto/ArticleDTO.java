package sk.simpleapp.blog.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArticleDTO {
    private long articleId;
    @NotBlank(message = "Vyplňte nadpis")
    @Size(max = 100, message = "Nadpis je príliš dlhý.")
    private String title;
    @NotBlank(message = "Vyplňte popis")
    private String description;
    @NotBlank(message = "Vyplňte obsah")
    private String content;

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
