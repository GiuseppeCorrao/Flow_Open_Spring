package develhope.Flow_Open_Spring.auth.entities;

public class RestorePasswordDTO {

    private String restorePasswordCode;
    private String newPassword;

    public RestorePasswordDTO(String restorePasswordCode, String newPassword) {
        this.restorePasswordCode = restorePasswordCode;
        this.newPassword = newPassword;
    }

    public RestorePasswordDTO() {
    }

    public String getRestorePasswordCode() {
        return restorePasswordCode;
    }

    public void setRestorePasswordCode(String restorePasswordCode) {
        this.restorePasswordCode = restorePasswordCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
