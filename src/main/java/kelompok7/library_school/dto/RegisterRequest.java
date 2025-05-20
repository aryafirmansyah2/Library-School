package kelompok7.library_school.dto;

public class RegisterRequest {
    private String namaDepan;
    private String namaBelakang;
    private String kelas;
    private String noHP;
    private String email;
    private String password;
    private String role;

    public String getNamaDepan() {
        return namaDepan;
    }
    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }
    public String getNamaBelakang() {
        return namaBelakang;
    }
    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }
    public String getKelas() {
        return kelas;
    }
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    public String getNoHP() {
        return noHP;
    }
    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
