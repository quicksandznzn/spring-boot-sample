package com.spring.data.solr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.spring.data.solr.common.field.SearchableSupplyDefinition;

@SolrDocument(solrCoreName = "supply")
public class SupplyDoument implements SearchableSupplyDefinition {

	private @Id @Indexed(SUPPLU_ID_FIELD_NAME) String supplyId;

	private @Indexed(SUPPLY_IMG_FIELD_NAME) String supplyImg;

	private @Indexed(CRE_DATE_FIELD_NAME) String creDate;

	private @Indexed(SUPPLY_PRICE_FIELD_NAME) String supplyPrice;

	private @Indexed(TECH_PROSPECT_FIELD_NAME) String techProspect;

	private @Indexed(SUPPLY_TITLE_FIELD_NAME) String supplyTitle;

	public String getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}

	public String getSupplyImg() {
		return supplyImg;
	}

	public void setSupplyImg(String supplyImg) {
		this.supplyImg = supplyImg;
	}

	public String getCreDate() {
		return creDate;
	}

	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}

	public String getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(String supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	public String getTechProspect() {
		return techProspect;
	}

	public void setTechProspect(String techProspect) {
		this.techProspect = techProspect;
	}

	public String getSupplyTitle() {
		return supplyTitle;
	}

	public void setSupplyTitle(String supplyTitle) {
		this.supplyTitle = supplyTitle;
	}

}
