package K23cnt1.Day03.entity;

public class MbaEmployee {
    private Long mbaId;
    private String mbaFullName;
    private String mbaGender;
    private int mbaAge;
    private double mbaSalary;

    public MbaEmployee() {}

    public MbaEmployee(Long mbaId, String mbaFullName, String mbaGender, int mbaAge, double mbaSalary) {
        this.mbaId = mbaId;
        this.mbaFullName = mbaFullName;
        this.mbaGender = mbaGender;
        this.mbaAge = mbaAge;
        this.mbaSalary = mbaSalary;
    }

    public Long getMbaId() {
        return mbaId;
    }

    public void setMbaId(Long mbaId) {
        this.mbaId = mbaId;
    }

    public String getMbaFullName() {
        return mbaFullName;
    }

    public void setMbaFullName(String mbaFullName) {
        this.mbaFullName = mbaFullName;
    }

    public String getMbaGender() {
        return mbaGender;
    }

    public void setMbaGender(String mbaGender) {
        this.mbaGender = mbaGender;
    }

    public int getMbaAge() {
        return mbaAge;
    }

    public void setMbaAge(int mbaAge) {
        this.mbaAge = mbaAge;
    }

    public double getMbaSalary() {
        return mbaSalary;
    }

    public void setMbaSalary(double mbaSalary) {
        this.mbaSalary = mbaSalary;
    }
}
