package K23cnt1.Day03.entity;

public class MbaKhoa {
    private String mbaMakh;
    private String mbaTenkh;

    public MbaKhoa() {}

    public MbaKhoa(String mbaMakh, String mbaTenkh) {
        this.mbaMakh = mbaMakh;
        this.mbaTenkh = mbaTenkh;
    }

    public String getMbaMakh() {
        return mbaMakh;
    }

    public void setMbaMakh(String mbaMakh) {
        this.mbaMakh = mbaMakh;
    }

    public String getMbaTenkh() {
        return mbaTenkh;
    }

    public void setMbaTenkh(String mbaTenkh) {
        this.mbaTenkh = mbaTenkh;
    }
}
