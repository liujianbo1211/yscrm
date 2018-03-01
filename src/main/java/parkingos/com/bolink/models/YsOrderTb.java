package parkingos.com.bolink.models;

import java.math.BigDecimal;

public class YsOrderTb {
    private Long id;
    private String order_id;
    private String address;
    private String describe;
    private Integer state;
    private BigDecimal total;
    private String manager;
    private String consignee;
    private String handman;
    private Integer shop_type;
    private Integer pay_type;
    private String tiandan;
    private String teller;
    private Long create_time;

    @Override
    public String toString() {
        return "YsOrderTb{" +
                "id=" + id +
                ", order_id='" + order_id + '\'' +
                ", address='" + address + '\'' +
                ", describe='" + describe + '\'' +
                ", state=" + state +
                ", total=" + total +
                ", manager='" + manager + '\'' +
                ", consignee='" + consignee + '\'' +
                ", handman='" + handman + '\'' +
                ", shop_type=" + shop_type +
                ", pay_type=" + pay_type +
                ", tiandan='" + tiandan + '\'' +
                ", teller='" + teller + '\'' +
                ", create_time=" + create_time +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setHandman(String handman) {
        this.handman = handman;
    }

    public void setShop_type(Integer shop_type) {
        this.shop_type = shop_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public void setTiandan(String tiandan) {
        this.tiandan = tiandan;
    }

    public void setTeller(String teller) {
        this.teller = teller;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getId() {

        return id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getAddress() {
        return address;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getState() {
        return state;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getManager() {
        return manager;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getHandman() {
        return handman;
    }

    public Integer getShop_type() {
        return shop_type;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public String getTiandan() {
        return tiandan;
    }

    public String getTeller() {
        return teller;
    }

    public Long getCreate_time() {
        return create_time;
    }
}
