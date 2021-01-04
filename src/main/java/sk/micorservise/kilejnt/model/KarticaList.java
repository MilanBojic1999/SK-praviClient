package sk.micorservise.kilejnt.model;

import java.util.ArrayList;
import java.util.List;

public class KarticaList {

    private List<KarticaForm> list;

    public KarticaList(List<KarticaForm> list) {
        this.list = list;
    }

    public KarticaList() {
        list = new ArrayList<>();
    }

    public List<KarticaForm> getList() {
        return list;
    }

    public void setList(List<KarticaForm> list) {
        this.list = list;
    }
}
