package team18.team18_be.userInformation.dto.request;

public class VisaRequest {

    private String foreginerNumber;
    private String visaGenereteDate;

    public VisaRequest() {
    }

    public VisaRequest(String foreginerNumber, String visaGenereteDate) {
        this.foreginerNumber = foreginerNumber;
        this.visaGenereteDate = visaGenereteDate;
    }

    public String getForeginerNumber() {
        return foreginerNumber;
    }

    public String getVisaGenereteDate() {
        return visaGenereteDate;
    }
}
