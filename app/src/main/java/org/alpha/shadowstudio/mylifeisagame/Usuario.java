package org.alpha.shadowstudio.mylifeisagame;

public class Usuario {

    private String nombre;
    private String correo;
    private String nick;
    private int nivel;
    private int exp_conseguida;


    public Usuario(String nombre, String correo, String nick) {
        this.nombre = nombre;
        this.correo = correo;
        this.nick = nick;
        nivel=0;
        exp_conseguida=0;
    }

    public Usuario(String nombre, String nick, int nivel) {
        this.nombre = nombre;
        this.nick = nick;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNick() {
        return nick;
    }

    public int getNivel() {
        return nivel;
    }
}
