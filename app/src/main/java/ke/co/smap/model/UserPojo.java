package ke.co.smap.model;

public class UserPojo {
    private String name, workId, compId,  station,
            phone, password, rank;

    public UserPojo() {
    }

    public UserPojo(String name, String workId, String compId,
                    String station, String phone, String password, String rank) {
        this.name = name;
        this.workId = workId;
        this.compId = compId;
        this.station = station;
        this.phone = phone;
        this.password = password;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "name='" + name + '\'' +
                ", workId='" + workId + '\'' +
                ", compId='" + compId + '\'' +
                ", station='" + station + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
