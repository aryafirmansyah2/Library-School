package kelompok7.library_school.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserUpdateRequest {

    @NotBlank(message = "Nama depan tidak boleh kosong")
    private String nama_depan;

    @NotBlank(message = "Nama belakang tidak boleh kosong")
    private String nama_belakang;

    @NotBlank(message = "Kelas tidak boleh kosong")
    private String kelas;

    @NotBlank(message = "Nomor HP tidak boleh kosong")
    private String no_hp;

    @Email(message = "Format email tidak valid")
    @NotBlank(message = "Email tidak boleh kosong")
    private String email;

    // Getters & Setters

    public String getNama_depan() {
        return nama_depan;
    }

    public void setNama_depan(String nama_depan) {
        this.nama_depan = nama_depan;
    }

    public String getNama_belakang() {
        return nama_belakang;
    }

    public void setNama_belakang(String nama_belakang) {
        this.nama_belakang = nama_belakang;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

