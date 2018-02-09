package org.alpha.shadowstudio.mylifeisagame;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alu20487670y on 17/01/18.
 */

public class Tarea implements Serializable{
    private int id;
    private int categoria;
    private String titulo;
    private String descripcion;
    private boolean encurso;
    private boolean canceladaOFallida;
    private long dateFin;
    private long dateInit;
    private List<Usuario> usuarios;


    public Tarea(int categoria, String titulo, String descripcion, long dateInit, long dateFin) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dateFin = dateFin;
        this.dateInit = dateInit;
        encurso = false;
        canceladaOFallida = false;
        id = (int) Math.random() * 10000000;
    }

    public Tarea(int categoria, String titulo, String descripcion, long dateFin, long dateInit, List<Usuario> usuarios) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dateFin = dateFin;
        this.dateInit = dateInit;
        this.usuarios = usuarios;
        encurso = false;
        canceladaOFallida = false;
        id = (int) Math.random() * 10000000;
    }

    public Tarea(int id, String titulo, String descripcion, int categoria, long dateFin, long dateInit, List<Usuario> usuarios) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dateFin = dateFin;
        this.dateInit = dateInit;
        this.usuarios = usuarios;
        encurso = false;
        canceladaOFallida = false;
    }

    public Tarea(int id, int categoria, String titulo, String descripcion, long dateFin, long dateInit) {
        this.id = id;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dateFin = dateFin;
        this.dateInit = dateInit;
        usuarios=null;
        encurso = false;
        canceladaOFallida = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEncurso() {
        return encurso;
    }

    public void setEncurso(boolean encurso) {
        this.encurso = encurso;
    }

    public long getDateFin() {
        return dateFin;
    }

    public void setDateFin(long dateFin) {
        this.dateFin = dateFin;
    }

    public long getDateInit() {
        return dateInit;
    }

    public void setDateInit(long dateInit) {
        this.dateInit = dateInit;
    }

    public boolean isCanceladaOFallida() {
        return canceladaOFallida;
    }

    public void setCanceladaOFallida(boolean canceladaOFallida) {
        this.canceladaOFallida = canceladaOFallida;
    }

    public static void comprocionEnCurso(Tarea tarea, float date) {
        if (tarea.getDateInit() > date && date < tarea.getDateFin()) {
            tarea.setEncurso(true);
        }
    }

    public int getId() {
        return id;
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
