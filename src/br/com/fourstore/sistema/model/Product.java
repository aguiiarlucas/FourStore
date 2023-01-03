package br.com.fourstore.sistema.model;

import br.com.fourstore.sistema.enums.CategoryEnum;
import br.com.fourstore.sistema.enums.ColorEnum;
import br.com.fourstore.sistema.enums.SizeEnum;
import br.com.fourstore.sistema.enums.TypeEnum;

public class Product {

    private  String sku;
    private  String id ;
    private String description;
    private TypeEnum type;
    private SizeEnum size;
    private ColorEnum color;
    private CategoryEnum category;
    private Integer quantity;
    private Double purchasePrice;
    private Double salePrice;

    public Product(String sku, Integer quantity, Double purchasePrice, Double salePrice) {

        this.sku = sku;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        parseSku(sku);
        //updateProductBySku
    }

    public Product(String id, String sku, String description, Integer quantity, Double purchasePrice, Double salePrice) {
        this.id = id;
        this.sku = sku;
        this.description = description;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        parseSku(sku);

    }

    public Product(String sku, String id, Integer quantity, Double purchasePrice, Double salePrice) {
        this.sku = sku;
        this.id = id;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }

    public Product(String sku, String id, String description, TypeEnum type, SizeEnum size, ColorEnum color, CategoryEnum category, Integer quantity, Double purchasePrice, Double salePrice) {
        this.sku = sku;
        this.id = id;
        this.description = description;
        this.type = type;
        this.size = size;
        this.color = color;
        this.category = category;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }

    public Product() {

    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public SizeEnum getSize() {
        return size;
    }

    public void setSize(SizeEnum size) {
        this.size = size;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }


    private void parseSku(String sku) {
        //this.id = sku.substring(0, 2);
        this.size = SizeEnum.get(sku.substring(0, 2));
        this.category = CategoryEnum.get(sku.substring(2, 4));
        this.color = ColorEnum.get(sku.substring(4, 6));
        this.type = TypeEnum.get(sku.substring(6, 8));
    }


    public void update(Product product) {
        this.quantity = product.getQuantity();
        this.salePrice = product.getSalePrice();
        this.purchasePrice = product.getPurchasePrice();
    }

    public void decrementProduct(Integer quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "\n\n Id: " + getId()
                +"\nDescricao: " + ((this.description == null) ? "" : this.description)
                +"\nTipo: " + getType()
                +"\nTamanho: "+ getSize()
                +"\nCor: " + getColor()
                +"\nCategoria: " + getCategory()
                +"\nQuantidade: " + getQuantity()
                +"\nPreço de compra: " + getPurchasePrice()
                + "\nPreço de venda: " + getSalePrice() + "\n\n";
    }


}


