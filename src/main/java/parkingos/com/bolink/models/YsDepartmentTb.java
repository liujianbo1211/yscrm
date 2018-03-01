package parkingos.com.bolink.models;

public class YsDepartmentTb {
    private Long id;
    private String name;
    private String personLiable;
    private String phone;
    private String shortNumber;
    private String describe;
    private Integer state;

    @Override
    public String toString() {
        return "YsDepartmentTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personLiable='" + personLiable + '\'' +
                ", phone='" + phone + '\'' +
                ", shortNumber='" + shortNumber + '\'' +
                ", describe='" + describe + '\'' +
                ", state=" + state +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPersonLiable() {
        return personLiable;
    }

    public String getPhone() {
        return phone;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public String getDescribe() {
        return describe;
    }

    public Integer getState() {
        return state;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonLiable(String personLiable) {
        this.personLiable = personLiable;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
