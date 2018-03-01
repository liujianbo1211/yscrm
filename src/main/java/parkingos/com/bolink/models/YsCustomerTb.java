package parkingos.com.bolink.models;

public class YsCustomerTb {

    private Long id;
    private String name;
    private String address;
    private String qq;
    private String phone;
    private String email;
    private String describe;
    private String main;
    private Long beginTime;
    private Integer state;

    @Override
    public String toString() {
        return "YsCustomerTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", describe='" + describe + '\'' +
                ", main='" + main + '\'' +
                ", beginTime=" + beginTime +
                ", state=" + state +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
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

    public String getAddress() {
        return address;
    }

    public String getQq() {
        return qq;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDescribe() {
        return describe;
    }

    public String getMain() {
        return main;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public Integer getState() {
        return state;
    }
}
