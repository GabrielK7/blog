package sk.simpleapp.blog.models.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import sk.simpleapp.blog.data.entities.ArticleEntity;
import sk.simpleapp.blog.models.dto.ArticleDTO;
@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleEntity toEntity(ArticleDTO source);
    ArticleDTO toDTO(ArticleEntity source);
    void updateArticleDTO(ArticleDTO source, @MappingTarget ArticleDTO target);
    void updateArticleEntity(ArticleDTO source, @MappingTarget ArticleEntity target);
}
