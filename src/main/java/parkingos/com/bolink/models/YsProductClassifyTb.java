package parkingos.com.bolink.models;

public class YsProductClassifyTb {

    private Long id;
    private String name;
    private String describe;
    private Integer state;

    @Override
    public String toString() {
        return "YsProductTb{" +
                "id=" + id +
                ", name='" + name + '\'' +
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

    public String getDescribe() {
        return describe;
    }

    public Integer getState() {
        return state;
    }
}
