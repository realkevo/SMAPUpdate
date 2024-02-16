package ke.co.smap.model;

public class AssessmentInfoPojo {
    private String WORK_ID, DETAILS, POINTS,  SHIFT, SUPERVISOR,
            TAREHE, IMAGE_URL, STATION;
    private String key;

    public AssessmentInfoPojo() {
    }

    public AssessmentInfoPojo(String WORK_ID, String DETAILS,
                              String POINTS, String SHIFT,
                              String SUPERVISOR, String TAREHE
            , String IMAGE_URL, String STATION, String key) {
        this.WORK_ID = WORK_ID;
        this.DETAILS = DETAILS;
        this.POINTS = POINTS;
        this.SHIFT = SHIFT;
        this.SUPERVISOR = SUPERVISOR;
        this.TAREHE = TAREHE;
        this.IMAGE_URL = IMAGE_URL;
        this.STATION = STATION;
        this.key = key;
    }

    public String getWORK_ID() {
        return WORK_ID;
    }

    public void setWORK_ID(String WORK_ID) {
        this.WORK_ID = WORK_ID;
    }

    public String getDETAILS() {
        return DETAILS;
    }

    public void setDETAILS(String DETAILS) {
        this.DETAILS = DETAILS;
    }

    public String getPOINTS() {
        return POINTS;
    }

    public void setPOINTS(String POINTS) {
        this.POINTS = POINTS;
    }

    public String getSHIFT() {
        return SHIFT;
    }

    public void setSHIFT(String SHIFT) {
        this.SHIFT = SHIFT;
    }

    public String getSUPERVISOR() {
        return SUPERVISOR;
    }

    public void setSUPERVISOR(String SUPERVISOR) {
        this.SUPERVISOR = SUPERVISOR;
    }

    public String getTAREHE() {
        return TAREHE;
    }

    public void setTAREHE(String TAREHE) {
        this.TAREHE = TAREHE;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getSTATION() {
        return STATION;
    }

    public void setSTATION(String STATION) {
        this.STATION = STATION;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String  toString() {
        return "AssessmentInfoPojo{" +
                "WORK_ID='" + WORK_ID + '\'' +
                ", DETAILS='" + DETAILS + '\'' +
                ", POINTS='" + POINTS + '\'' +
                ", SHIFT='" + SHIFT + '\'' +
                ", SUPERVISOR='" + SUPERVISOR + '\'' +
                ", TAREHE='" + TAREHE + '\'' +
                ", IMAGE_URL='" + IMAGE_URL + '\'' +
                ", STATION='" + STATION + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}