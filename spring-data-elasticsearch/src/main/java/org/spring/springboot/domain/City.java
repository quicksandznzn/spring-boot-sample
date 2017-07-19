package org.spring.springboot.domain;

import java.io.Serializable;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by quiclsandzn@gmail.com on 2017/7/19.
 */
@Document(indexName = "city", type = "city")
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    private Long id;

    private String name;

    private String desc;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
