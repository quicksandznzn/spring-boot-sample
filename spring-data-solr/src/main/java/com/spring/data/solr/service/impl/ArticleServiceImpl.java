package com.spring.data.solr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.data.solr.document.ArticleDocument;
import com.spring.data.solr.repository.ArticleRepository;
import com.spring.data.solr.service.ArticleService;

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
