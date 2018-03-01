package parkingos.com.bolink.models;

import java.math.BigDecimal;

public class YsProductTb {

    private Long id;
    private String name;
    private String productId;  //字符串编号
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {

        return color;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {

        return productId;
    }

    private Long productClassifyId;
    private String unity;
    private BigDecimal price;
    private String specifications;
    private String describe;
    private Integer state;

    @Override
    public String toString() {
        return "YsProductTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productClassifyId=" + productClassifyId +
                ", unity='" + unity + '\'' +
                ", price=" + price +
                ", specifications='" + specifications + '\'' +
                ", describe='" + describe + '\'' +
                ", state=" + state +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductClassifyId(Long productClassifyId) {
        this.productClassifyId = productClassifyId;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public Long getProductClassifyId() {
        return productClassifyId;
    }

    public String getUnity() {
        return unity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSpecifications() {
        return specifications;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getState() {
        return state;
    }
}
