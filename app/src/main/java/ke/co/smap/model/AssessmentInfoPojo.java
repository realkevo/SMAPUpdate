package ke.co.smap.model;

public class AssessmentInfoPojo {
   private String WORK_ID, DETAILS, POINTS, SCORE, SHIFT, SUPERVISOR,
    TAREHE, IMAGE_URL;
    public AssessmentInfoPojo() {
    }

    public AssessmentInfoPojo(String WORK_ID, String DETAILS, String POINTS,
                              String SCORE, String SHIFT, String SUPERVISOR,
                              String TAREHE, String IMAGE_URL) {



        this.WORK_ID = WORK_ID;
        this.DETAILS = DETAILS;
        this.POINTS = POINTS;
        this.SCORE = SCORE;
        this.SHIFT = SHIFT;
        this.SUPERVISOR = SUPERVISOR;
        this.TAREHE = TAREHE;
        this.IMAGE_URL = IMAGE_URL;
    }

    public String getWORK_ID() {
        return WORK_ID;
    }

    public String getDETAILS() {
        return DETAILS;
    }

    public String getPOINTS() {
        return POINTS;
    }

    public String getSCORE() {
        return SCORE;
    }

    public String getSHIFT() {
        return SHIFT;
    }

    public String getSUPERVISOR() {
        return SUPERVISOR;
    }

    public String getTAREHE() {
        return TAREHE;
    }

    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    @Override
    public String toString() {
        return "AssessmentInfoPojo{" +
                "WORK_ID='" + WORK_ID + '\'' +
                ", DETAILS='" + DETAILS + '\'' +
                ", POINTS='" + POINTS + '\'' +
                ", SCORE='" + SCORE + '\'' +
                ", SHIFT='" + SHIFT + '\'' +
                ", SUPERVISOR='" + SUPERVISOR + '\'' +
                ", TAREHE='" + TAREHE + '\'' +
                ", IMAGE_URL='" + IMAGE_URL + '\'' +
                '}';
    }
}
