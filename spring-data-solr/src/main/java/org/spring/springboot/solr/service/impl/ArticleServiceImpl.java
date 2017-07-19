package org.spring.springboot.solr.service.impl;

import org.spring.springboot.solr.document.ArticleDocument;
import org.spring.springboot.solr.repository.ArticleRepository;
import org.spring.springboot.solr.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Page<ArticleDocument> findBySearchTerms(String searchTerm, Pageable pageable) {
        return articleRepository.findBySearchTerms(searchTerm, pageable);
    }

    @Override
    public ArticleDocument findById(String id) {
        return articleRepository.findOne(id);
    }

}
