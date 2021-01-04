package sk.micorservise.kilejnt.model;

public class UserInfo extends RegistrationForm{

    private String rank;

    public UserInfo(RegistrationForm form){
        this.setIme(form.getIme());
        this.setPrezime(form.getPrezime());
        this.setPasos(form.getPasos());
        this.setEmail(form.getEmail());
        this.setSifra(form.getSifra());
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
