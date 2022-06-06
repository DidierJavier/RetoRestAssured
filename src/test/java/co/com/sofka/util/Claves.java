package co.com.sofka.util;

public enum Claves {

    USER("admin"),
    PASSWORD("password123"),

    TOKEN("token");

    public String clave;

    Claves(String clave) {
        this.clave = clave;
    }
}
