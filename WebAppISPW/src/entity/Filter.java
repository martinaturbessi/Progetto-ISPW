package entity;

public class Filter {
    private String categoria;
    private String lingua;
    private int stelle;
    private String dataMin;
    private String dataMax;

    public Filter(String categoria, String lingua, int stelle, String dataMin, String dataMax) {
        this.categoria = categoria;
        this.lingua = lingua;
        this.stelle = stelle;
        this.dataMin = dataMin;
        this.dataMax = dataMax;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getLingua() {
        return lingua;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public int getStelle() {
        return stelle;
    }

    public void setDataMin(String dataMin) {
        this.dataMin = dataMin;
    }

    public String getDataMin() {
        return dataMin;
    }

    public void setDataMax(String dataMax) {
        this.dataMax = dataMax;
    }

    public String getDataMax() {
        return dataMax;
    }
}
