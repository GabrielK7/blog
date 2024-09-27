package sk.simpleapp.blog.models.services;

import org.springframework.stereotype.Service;
import sk.simpleapp.blog.models.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {
    void create(ArticleDTO article);
    List<ArticleDTO> getAll();
    ArticleDTO getById(long articleId);
    void edit(ArticleDTO article);
    void remove(long articleId);

}
