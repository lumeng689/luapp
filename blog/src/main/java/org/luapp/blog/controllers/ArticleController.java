package org.luapp.blog.controllers;

import org.luapp.blog.domain.ArticleForm;
import org.luapp.blog.entities.Article;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Created by lumeng on 2015/4/4.
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> get() {
        return new ArrayList<Article>();
    }

    @RequestMapping(value="/{day}", method = RequestMethod.GET)
    public Map<String, Article> getForDay(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                                              Date day, Model model) {
        return null;
    }

    /**
     *  GET /pets/42;q=11;r=22
     *
     * @param petId
     * @param q
     */
    @RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET)
    public void findPet(@PathVariable String petId, @MatrixVariable int q) {
// petId == 42
// q == 11
    }
    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ArticleForm getNewForm() {
        return new ArticleForm();
    }
    @RequestMapping(method = RequestMethod.POST)
    public String add(@Valid ArticleForm Article, BindingResult result) {
        if (result.hasErrors()) {
            return "articles/new";
        }
//        ArticleBook.addArticle(Article);
        return "redirect:/articles";
    }
}
