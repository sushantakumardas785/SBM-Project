package in.ashokit.dto;

public class Customer {

    private Integer id;
    private String name;
    private Long phno;

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

    public Long getPhno() {
        return phno;
    }

    public void setPhno(Long phno) {
        this.phno = phno;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phno=" + phno +
                '}';
    }
}
