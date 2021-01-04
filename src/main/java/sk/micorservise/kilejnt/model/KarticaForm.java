package sk.micorservise.kilejnt.model;

public class KarticaForm {

    private long brojKartice;
    private String imeVlasnika;
    private String prezimeVlasnika;
    private int sigurnosniBroj;



    public long getBrojKartice() {
        return brojKartice;
    }

    public void setBrojKartice(long brojKartice) {
        this.brojKartice = brojKartice;
    }

    public String getImeVlasnika() {
        return imeVlasnika;
    }

    public void setImeVlasnika(String imeVlasnika) {
        this.imeVlasnika = imeVlasnika;
    }

    public String getPrezimeVlasnika() {
        return prezimeVlasnika;
    }

    public void setPrezimeVlasnika(String prezimeVlasnika) {
        this.prezimeVlasnika = prezimeVlasnika;
    }

    public int getSigurnosniBroj() {
        return sigurnosniBroj;
    }

    public void setSigurnosniBroj(int sigurnosniBroj) {
        this.sigurnosniBroj = sigurnosniBroj;
    }
}
