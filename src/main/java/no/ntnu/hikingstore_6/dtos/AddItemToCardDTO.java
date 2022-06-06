package no.ntnu.hikingstore_6.dtos;

public class AddItemToCardDTO {
    private int id;
    private String productName;
    private String productCategory;
    private int price;
    private String productImage;
    private String productDesc;
    private int productAmount;

    public AddItemToCardDTO(int id, String productName, String productCategory, int price, String productImage, String productDesc, int productAmount) {
        this.id = id;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.productImage = productImage;
        this.productDesc = productDesc;
        this.productAmount = productAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
