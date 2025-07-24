/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class ProductCart {
    private int product_id;
    private String product_name;
    private int quantity;
    private double list_price;

    public ProductCart() {
    }

    public ProductCart(int product_id, String product_name, int quantity, double list_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.list_price = list_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    @Override
    public String toString() {
        return "ProductCart{" + "product_id=" + product_id + ", product_name=" + product_name + ", quantity=" + quantity + ", list_price=" + list_price + '}';
    }
    
}

