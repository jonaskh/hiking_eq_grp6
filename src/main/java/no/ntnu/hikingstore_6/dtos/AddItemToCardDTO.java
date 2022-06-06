package no.ntnu.hikingstore_6.dtos;

import no.ntnu.hikingstore_6.entities.Product;

public class AddItemToCardDTO {
    private int id;
    private String productName;
    private String productCategory;
    private int price;
    private String size;
    private String productImage;
    private String productDesc;
    private int productAmount;

    public AddItemToCardDTO(int id, String productName, String productCategory, int price, String productImage, String productDesc, int productAmount, String size) {
        this.id = id;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.size = size;
        this.productImage = productImage;
        this.productDesc = productDesc;
        this.productAmount = productAmount;
    }

    public Product createProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setProductName(this.productName);
        product.setProductCategory(this.productCategory);
        product.setPrice(this.price);
        product.setSize(this.size);
        product.setProductImage(this.productImage);
        product.setProductDesc(this.productDesc);
        product.setProductAmount(this.productAmount);

        return product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }
}
