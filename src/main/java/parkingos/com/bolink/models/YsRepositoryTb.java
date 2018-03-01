package parkingos.com.bolink.models;

public class YsRepositoryTb {

    private Long id;
    private String productId;
    private String describe;
    private Integer state;
    private Integer total;
    private String username;
    private Long createTime;
    private Integer type;
    private Long orderId;

    @Override
    public String toString() {
        return "YsRepositoryTb{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", describe='" + describe + '\'' +
                ", state=" + state +
                ", total=" + total +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", orderId=" + orderId +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getId() {

        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getState() {
        return state;
    }

    public Integer getTotal() {
        return total;
    }

    public String getUsername() {
        return username;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Integer getType() {
        return type;
    }

    public Long getOrderId() {
        return orderId;
    }
}
