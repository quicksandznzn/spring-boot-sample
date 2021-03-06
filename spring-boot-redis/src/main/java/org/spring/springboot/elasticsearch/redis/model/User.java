package org.spring.springboot.elasticsearch.redis.model;

public class User {

    private String id;
    private String name;

    public User() {
        super();
    }

    public User(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
