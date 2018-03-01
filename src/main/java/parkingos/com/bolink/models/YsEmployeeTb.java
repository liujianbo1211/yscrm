package parkingos.com.bolink.models;

import java.math.BigDecimal;

public class YsEmployeeTb {

    private Long id;
    private String name;
    private String address;
    private String cardId;
    private String phone;
    private Integer departmentId;
    private BigDecimal salary;
    private String shortNumber;
    private Integer isleave;
    private Long beginTime;
    private Long endTime;
    private String describe;
    private Integer state;
    private String leavingReason;

    @Override
    public String toString() {
        return "YsEmployeeTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cardId='" + cardId + '\'' +
                ", phone='" + phone + '\'' +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                ", shortNumber='" + shortNumber + '\'' +
                ", isleave=" + isleave +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", describe='" + describe + '\'' +
                ", state=" + state +
                ", leavingReason='" + leavingReason + '\'' +
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

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public void setIsleave(Integer isleave) {
        this.isleave = isleave;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setLeavingReason(String leavingReason) {
        this.leavingReason = leavingReason;
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

    public String getCardId() {
        return cardId;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public Integer getIsleave() {
        return isleave;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getState() {
        return state;
    }

    public String getLeavingReason() {
        return leavingReason;
    }
}
