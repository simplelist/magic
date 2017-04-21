package flyWeight;

/**
 * Created by Administrator on 2016/10/11.
 */
public class SignInfo {
    private String id;
    private String location;
    private String subject;
    private String postAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SignInfo signInfo = (SignInfo) o;

        if (!location.equals(signInfo.location)) return false;
        return subject.equals(signInfo.subject);

    }

    @Override
    public int hashCode() {
        int result = location.hashCode();
        result = 31 * result + subject.hashCode();
        return result;
    }
}
