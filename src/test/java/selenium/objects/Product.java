package selenium.objects;

import selenium.utils.JacksonUtils;

import java.io.IOException;

public class Product {
    private int id;
    private String name;
    private boolean featured;
public Product(){}
    public Product(int id) throws IOException {
    Product[] products= JacksonUtils.deserializeJson("products.json", Product[].class);
        for (Product product:products) {
            if(product.getId()==id){
                this.id=id;
                this.name=product.name;
            }
        }

    }
    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
