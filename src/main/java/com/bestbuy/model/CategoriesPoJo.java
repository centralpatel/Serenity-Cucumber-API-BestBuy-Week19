package com.bestbuy.model;

public class CategoriesPoJo {
    private String name;
    private String id;

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

    public static CategoriesPoJo getCategoriesPojo(String name, String id){
        CategoriesPoJo categoriesPojo = new CategoriesPoJo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);
        return categoriesPojo;
    }
}

