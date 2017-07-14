package com.spring.data.solr.service;

import com.spring.data.solr.document.ArticleDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    Page<ArticleDocument> findBySearchTerms(String searchTerm, Pageable pageable);

    ArticleDocument findById(String id);
}
