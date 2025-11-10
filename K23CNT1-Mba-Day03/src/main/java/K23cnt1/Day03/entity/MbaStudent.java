package K23cnt1.Day03.entity;

public class MbaStudent {

    Long mbaId;
    String mbaName;
    int mbaAge;
    String mbaGender;
    String mbaAddress;
    String mbaPhone;
    String mbaEmail;

    public MbaStudent() {
    }

    public MbaStudent(Long mbaId, String mbaName, int mbaAge, String mbaGender,
                      String mbaAddress, String mbaPhone, String mbaEmail) {
        this.mbaId = mbaId;
        this.mbaName = mbaName;
        this.mbaAge = mbaAge;
        this.mbaGender = mbaGender;
        this.mbaAddress = mbaAddress;
        this.mbaPhone = mbaPhone;
        this.mbaEmail = mbaEmail;
    }

    public Long getMbaId() {
        return mbaId;
    }

    public void setMbaId(Long mbaId) {
        this.mbaId = mbaId;
    }

    public String getMbaName() {
        return mbaName;
    }

    public void setMbaName(String mbaName) {
        this.mbaName = mbaName;
    }

    public int getMbaAge() {
        return mbaAge;
    }

    public void setMbaAge(int mbaAge) {
        this.mbaAge = mbaAge;
    }

    public String getMbaGender() {
        return mbaGender;
    }

    public void setMbaGender(String mbaGender) {
        this.mbaGender = mbaGender;
    }

    public String getMbaAddress() {
        return mbaAddress;
    }

    public void setMbaAddress(String mbaAddress) {
        this.mbaAddress = mbaAddress;
    }

    public String getMbaPhone() {
        return mbaPhone;
    }

    public void setMbaPhone(String mbaPhone) {
        this.mbaPhone = mbaPhone;
    }

    public String getMbaEmail() {
        return mbaEmail;
    }

    public void setMbaEmail(String mbaEmail) {
        this.mbaEmail = mbaEmail;
    }
}
