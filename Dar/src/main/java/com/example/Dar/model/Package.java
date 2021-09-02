package com.example.Dar.model;



import java.util.Objects;


public class Package {
    private static long cnt;
    private  long id;
    private String description;
    private Status status;

    {
        cnt++;
    }
    public Package() {
        this.id = cnt;
    }

    public long getId() {
        return id;
    }

    public Package(String description) {
        this();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Package))
            return false;
        Package order = (Package) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
                && this.status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.status);
    }

    @Override
    public String toString() {
        return "Package{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status + '}';
    }

}
