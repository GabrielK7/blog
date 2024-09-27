package sk.simpleapp.blog.models.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.simpleapp.blog.data.entities.ArticleEntity;
import sk.simpleapp.blog.data.repositories.ArticleRepository;
import sk.simpleapp.blog.models.dto.ArticleDTO;
import sk.simpleapp.blog.models.dto.mappers.ArticleMapper;
import sk.simpleapp.blog.models.exceptions.ArticleNotFoundException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public void create(ArticleDTO article) {
        ArticleEntity newArticleEntity = articleMapper.toEntity(article) ;

       /* newArticleEntity.setTitle(article.getTitle());
        newArticleEntity.setDescription(article.getDescription());
        newArticleEntity.setContent(article.getContent());*/

        articleRepository.save(newArticleEntity);


    }

    @Override
    public List<ArticleDTO> getAll() {
        return StreamSupport.stream(articleRepository.findAll().spliterator(), false)
                .map(i->articleMapper.toDTO(i))
                .toList();
    }

    @Override
    public ArticleDTO getById(long articleId) {

       ArticleEntity fetchedArticle = getArticleOrThrow(articleId);

       return articleMapper.toDTO(fetchedArticle);
    }

    @Override
    public void edit(ArticleDTO article) {
       ArticleEntity fetchedArticle = getArticleOrThrow(article.getArticleId());
       articleMapper.updateArticleEntity(article, fetchedArticle);
       articleRepository.save(fetchedArticle);
    }


    private ArticleEntity getArticleOrThrow(long articleId){
        return articleRepository.findById(articleId)
                .orElseThrow(ArticleNotFoundException::new);

    }

    @Override
    public void remove(long articleId) {

        ArticleEntity fetchedEntity = getArticleOrThrow(articleId);
        articleRepository.delete(fetchedEntity);
    }

}
