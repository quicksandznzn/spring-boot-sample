package com.spring.data.solr.repository;

import com.spring.data.solr.document.ArticleDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface ArticleRepository extends SolrCrudRepository<ArticleDocument, String> {

    @Highlight(prefix = "<font color='red'>", postfix = "</font>")
    @Query(value = "summary : *?0* OR title : *?0*")
    HighlightPage<ArticleDocument> findBySearchTerms(String searchTerm, Pageable pageable);

    // @SortDefault(sort = {SearchableArticleDefinition.CREATETIME_FIELD_NAME },
    // direction =Direction.ASC)

    // @Facet(fields = { SearchableArticleDefinition.TITLE_FIELD_NAME })
    // FacetPage<ArticleDocument> findByNameStartsWith(Collection<String>
    // nameFragments, Pageable pageable);

}
