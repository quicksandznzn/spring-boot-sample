package com.spring.data.solr.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.data.solr.document.ArticleDocument;

public interface ArticleService {

	Page<ArticleDocument> findBySearchTerms(String searchTerm, Pageable pageable);

	ArticleDocument findById(String id);
}
