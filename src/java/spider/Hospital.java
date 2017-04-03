package spider;

/**
 * Created by Administrator on 2017/4/3.
 */
public class Hospital {
    private String province;
    private String city;
    private String name;

    public Hospital() {
    }

    public Hospital(String province, String city, String name) {
        this.province = province;
        this.city = city;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
