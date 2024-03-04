package ke.co.smap.model;

public class AssessmentInfoPojo {
   private String workId;
    private String details;
    private String points;
    public String tarehe;
    private String shift;
    private String supervisor;
    public String station;
    private String imageUrl;

    public AssessmentInfoPojo() {
    }
    public AssessmentInfoPojo(String workId, String details, String points, String tarehe, 
                              String shift, String supervisor, String imageUrl, String station) {
        this.workId = workId;
        this.details = details;
        this.points = points;
        this.tarehe = tarehe;
        this.shift = shift;
        this.supervisor = supervisor;
        this.imageUrl = imageUrl;
        this.station = station;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getTarehe() {
        return tarehe;
    }

    public void setTarehe(String tarehe) {
        this.tarehe = tarehe;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "AssessmentInfoPojo{" +
                "workId='" + workId + '\'' +
                ", details='" + details + '\'' +
                ", points='" + points + '\'' +
                ", tarehe='" + tarehe + '\'' +
                ", shift='" + shift + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", station='" + station + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
