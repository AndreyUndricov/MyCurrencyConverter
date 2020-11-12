package my.utils.myconvertercurrency.model;

public class Currency {
    private int id;
    private String nameCyrrency;
    private String nominal;
    private String codeCyrrency;
    private int imageCyrrency;
    private String value;

    public Currency() {
    }

    public Currency(String nameCyrrency, String nominal, String codeCyrrency, int imageCyrrency, String value) {
        this.nameCyrrency = nameCyrrency;
        this.nominal = nominal;
        this.codeCyrrency = codeCyrrency;
        this.imageCyrrency = imageCyrrency;
        this.value = value;
    }

    public Currency(int id, String nameCyrrency, String nominal, String codeCyrrency, int imageCyrrency, String value) {
        this.id = id;
        this.nameCyrrency = nameCyrrency;
        this.nominal = nominal;
        this.codeCyrrency = codeCyrrency;
        this.imageCyrrency = imageCyrrency;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCyrrency() {
        return nameCyrrency;
    }

    public void setNameCyrrency(String nameCyrrency) {
        this.nameCyrrency = nameCyrrency;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getCodeCyrrency() {
        return codeCyrrency;
    }

    public void setCodeCyrrency(String codeCyrrency) {
        this.codeCyrrency = codeCyrrency;
    }

    public int getImageCyrrency() {
        return imageCyrrency;
    }

    public void setImageCyrrency(int imageCyrrency) {
        this.imageCyrrency = imageCyrrency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
