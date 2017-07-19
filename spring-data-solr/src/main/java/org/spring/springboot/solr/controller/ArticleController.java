package org.spring.springboot.solr.controller;

import org.spring.springboot.solr.common.field.SearchableArticleDefinition;
import org.spring.springboot.solr.document.ArticleDocument;
import org.spring.springboot.solr.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/findById.do", method = RequestMethod.GET)
    public ArticleDocument findById(String id) {
        return articleService.findById(id);
    }

    @RequestMapping(value = "/findBySearchTerm.do", method = RequestMethod.GET)
    public Page<ArticleDocument> findBySsearchTerm(String searchTerm,
        @PageableDefault(page = 1, size = 10, sort = {
            SearchableArticleDefinition.CREATETIME_FIELD_NAME}, direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.findBySearchTerms(searchTerm, pageable);
    }
}
