package id.gravicodev.cashgo.Model;

/**
 * Created by Julio Alfian on 17/11/2017.
 */

public class User {
    public String nama;
    public String email;
    public String nomor_telepon;
    public String saldo;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String nama, String email, String nomor_telepon, String saldo) {
        this.nama = nama;
        this.email = email;
        this.nomor_telepon = nomor_telepon;
        this.saldo = saldo;
    }
}
