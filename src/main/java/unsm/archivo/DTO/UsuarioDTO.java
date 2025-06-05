package unsm.archivo.DTO;

public class UsuarioDTO {
    Integer id;
    String name;
    String lastname;
    String address;
    String phone;
    Integer cargoid; // CAMBIO AQUÍ

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCargoid() {
        return cargoid;
    }

    public void setCargoid(Integer cargoid) {
        this.cargoid = cargoid;
    }

    public UsuarioDTO(Integer id, String name, String lastname, String address, String phone, Integer cargoid) {
        super();
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.phone = phone;
        this.cargoid = cargoid;
    }
}
