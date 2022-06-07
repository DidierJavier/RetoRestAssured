package co.com.sofka.util;

public enum Claves {

    USER("admin"),
    PASSWORD("password123"),

    BOOKINGID("bookingid"),

    TOKEN("token");

    public String clave;

    Claves(String clave) {
        this.clave = clave;
    }
}
