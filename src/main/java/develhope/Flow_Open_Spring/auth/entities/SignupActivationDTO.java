package develhope.Flow_Open_Spring.auth.entities;

public class SignupActivationDTO {

    private String activationCode;

    public SignupActivationDTO(String activationCode) {
        this.activationCode = activationCode;
    }

    public SignupActivationDTO() {
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
    public String getActivationCode() {
        return activationCode;
    }
}
