package model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Products {
    @Id
    @Column(name = "prodid")
    private String prodid;
    @Basic
    @Column(name = "prodname")
    private String prodname;
    @Basic
    @Column(name = "stock")
    private Integer stock;
    @Basic
    @Column(name = "price")
    private Integer price;
    @Basic
    @Column(name = "maker")
    private String maker;

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

}
