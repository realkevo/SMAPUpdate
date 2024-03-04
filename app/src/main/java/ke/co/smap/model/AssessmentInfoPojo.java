package ke.co.smap.model;

public class AssessmentInfoPojo {
   private String workId, details, points,  SHIFT, SUPERVISOR,
     imageUrl;
    public AssessmentInfoPojo() {
    }

    public AssessmentInfoPojo(String WORK_ID, String DETAILS, String POINTS,
                               String SHIFT, String SUPERVISOR,
                              String IMAGE_URL) {



        this.workId = WORK_ID;
        this.details = DETAILS;
        this.points = POINTS;
        this.SHIFT = SHIFT;
        this.SUPERVISOR = SUPERVISOR;
        this.imageUrl = IMAGE_URL;
    }

    public String getWORK_ID() {
        return workId;
    }

    public String getDETAILS() {
        return details;
    }

    public String getPOINTS() {
        return points;
    }



    public String getSHIFT() {
        return SHIFT;
    }

    public String getSUPERVISOR() {
        return SUPERVISOR;
    }


    public String getIMAGE_URL() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "AssessmentInfoPojo{" +
                "WORK_ID='" + workId + '\'' +
                ", DETAILS='" + details + '\'' +
                ", POINTS='" + points + '\'' +
                ", SHIFT='" + SHIFT + '\'' +
                ", SUPERVISOR='" + SUPERVISOR + '\'' +
                ", IMAGE_URL='" + imageUrl + '\'' +
                '}';
    }
}
