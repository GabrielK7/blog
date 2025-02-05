package sk.simpleapp.blog.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.simpleapp.blog.models.dto.ArticleDTO;
import sk.simpleapp.blog.models.dto.mappers.ArticleMapper;
import sk.simpleapp.blog.models.exceptions.ArticleNotFoundException;
import sk.simpleapp.blog.models.services.ArticleService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleMapper articleMapper;

    @GetMapping
    public String renderIndex(Model model) {
        List<ArticleDTO> articles = articleService.getAll();
        model.addAttribute("articles", articles);
        return "pages/articles/index";
    }

    @GetMapping("/create")
    public String renderCreateForm(@ModelAttribute ArticleDTO article) {
        return "pages/articles/create";
    }

    @PostMapping("create")
    public String createArticle(@Valid @ModelAttribute ArticleDTO article,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return renderCreateForm(article);
        }
        articleService.create(article);
        redirectAttributes.addFlashAttribute("success", "Článok vytvorený");
        System.out.println(article.getTitle() + " - " + article.getDescription());

        return "redirect:/articles";
    }

    @GetMapping("{articleId}")

    public String renderDetail(@PathVariable long articleId,
                               Model model) {

        ArticleDTO article = articleService.getById(articleId);
        model.addAttribute("article", article);

        return "pages/articles/detail";
    }


    @GetMapping("/edit/{articleId}")
    public String renderEditForm(@PathVariable Long articleId,
                                 ArticleDTO article) {
        ArticleDTO fetchedArticle = articleService.getById(articleId);
        articleMapper.updateArticleDTO(fetchedArticle, article);
        return "pages/articles/edit";

    }

    @PostMapping("edit/{articleId}")
    public String editArticle(@PathVariable long articleId,
                              @Valid ArticleDTO article,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return renderEditForm(articleId, article);
        }
        article.setArticleId(articleId);
        articleService.edit(article);
        redirectAttributes.addFlashAttribute("success", "Článok upravený.");
        return "redirect:/articles";

    }

    @GetMapping("delete/{articleId}")
    public String deleteArticle(@PathVariable long articleId, RedirectAttributes redirectAttributes) {
        articleService.remove(articleId);
        redirectAttributes.addFlashAttribute("success", "Článok vymazaný");
        return "redirect:/articles";
    }
@ExceptionHandler(ArticleNotFoundException.class)
    public String handleArticleNotFoundException(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("error",  "Článok nenájdený");
   return "redirect:/articles";
    }


}
