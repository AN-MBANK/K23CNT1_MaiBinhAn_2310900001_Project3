package K23cnt1.Day03.entity;

public class MbaMonHoc {
    private String mbaMamh;
    private String mbaTenmh;
    private int mbaSotiet;

    public MbaMonHoc() {}

    public MbaMonHoc(String mbaMamh, String mbaTenmh, int mbaSotiet) {
        this.mbaMamh = mbaMamh;
        this.mbaTenmh = mbaTenmh;
        this.mbaSotiet = mbaSotiet;
    }

    public String getMbaMamh() {
        return mbaMamh;
    }

    public void setMbaMamh(String mbaMamh) {
        this.mbaMamh = mbaMamh;
    }

    public String getMbaTenmh() {
        return mbaTenmh;
    }

    public void setMbaTenmh(String mbaTenmh) {
        this.mbaTenmh = mbaTenmh;
    }

    public int getMbaSotiet() {
        return mbaSotiet;
    }

    public void setMbaSotiet(int mbaSotiet) {
        this.mbaSotiet = mbaSotiet;
    }
}
