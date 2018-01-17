package org.alpha.shadowstudio.mylifeisagame;

/**
 * Created by alu20487670y on 17/01/18.
 */

public class Usuario {
    String uid;
    String nombre;
    String correo;
    String nickUsuario;
    int nivel;

    public Usuario(String uid, String nombre, String correo, String nickUsuario) {
        this.uid = uid;
        this.nombre = nombre;
        this.correo = correo;
        this.nickUsuario = nickUsuario;
        nivel=0;
    }

}
