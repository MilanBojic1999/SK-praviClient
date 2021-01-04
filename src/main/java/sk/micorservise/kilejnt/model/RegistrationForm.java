package sk.micorservise.kilejnt.model;

public class RegistrationForm {
    private String ime;
    private String prezime;
    private String email;
    private String sifra;
    private long pasos;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public long getPasos() {
        return pasos;
    }

    public void setPasos(long pasos) {
        this.pasos = pasos;
    }
}
