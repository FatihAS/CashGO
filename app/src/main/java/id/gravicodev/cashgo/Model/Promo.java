package id.gravicodev.cashgo.Model;

public class Promo {

    private String id, name, store;
    private int picture;

    public Promo() {
    }

    public Promo(String id, String name, String store, int picture) {
        this.id = id;
        this.name = name;
        this.store = store;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
