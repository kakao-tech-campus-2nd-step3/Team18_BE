package team18.team18_be.userInformation.dto.request;

public class RequestVisa {

  private String foreginerNumber;
  private String visaGenereteDate;

  public RequestVisa() {
  }

  public RequestVisa(String foreginerNumber, String visaGenereteDate) {
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
