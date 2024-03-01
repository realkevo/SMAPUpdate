package ke.co.smap.model;

public class AssessPojo {
    private String details, imageUrl, points, shift, station,
            supervisor, tarehe, workId;

    public AssessPojo() {
        //This empty constructor needed here
    }

    public AssessPojo(String details,
                      String imageUrl,
                      String points, String shift, String station,
                      String supervisor, String tarehe, String workId) {
        this.details = details;
        this.imageUrl = imageUrl;
        this.points = points;
        this.shift = shift;
        this.station = station;
        this.supervisor = supervisor;
        this.tarehe = tarehe;
        this.workId = workId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getTarehe() {
        return tarehe;
    }

    public void setTarehe(String tarehe) {
        this.tarehe = tarehe;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    @Override
    public String toString() {
        return "AssessPojo{" +
                "details='" + details + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", points='" + points + '\'' +
                ", shift='" + shift + '\'' +
                ", station='" + station + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", tarehe='" + tarehe + '\'' +
                ", workId='" + workId + '\'' +
                '}';
    }
}
