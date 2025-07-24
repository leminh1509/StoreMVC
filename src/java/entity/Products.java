package entity;


public class Products {
    private int product_id;
    private String product_name;
    private int model_year;
    private double list_price;
    private String brand_name;
    private String category_name;

    public Products() {
    }

    public Products(int product_id, String product_name, int model_year, double list_price, String brand_name, String category_name) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.model_year = model_year;
        this.list_price = list_price;
        this.brand_name = brand_name;
        this.category_name = category_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getModel_year() {
        return model_year;
    }

    public double getList_price() {
        return list_price;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "Products{" + "product_id=" + product_id + ", product_name=" + product_name + ", model_year=" + model_year + ", list_price=" + list_price + ", brand_name=" + brand_name + ", category_name=" + category_name + '}';
    }
    
    
}
