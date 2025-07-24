package entity;

public class Stores {
    private int store_id;
    private String store_name;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zip_code;

    public Stores() {
    }

    public Stores(int store_id, String store_name, String phone, String email, String street, String city, String state, String zip_code) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.phone = phone;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
    }

    public int getStore_id() {
        return store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    @Override
    public String toString() {
        return "Stores{" + "store_id=" + store_id + ", store_name=" + store_name + ", phone=" + phone + ", email=" + email + ", street=" + street + ", city=" + city + ", state=" + state + ", zip_code=" + zip_code + '}';
    }
    
    
}
