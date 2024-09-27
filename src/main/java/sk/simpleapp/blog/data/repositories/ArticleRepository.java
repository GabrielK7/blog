package sk.simpleapp.blog.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.simpleapp.blog.data.entities.ArticleEntity;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {
}
