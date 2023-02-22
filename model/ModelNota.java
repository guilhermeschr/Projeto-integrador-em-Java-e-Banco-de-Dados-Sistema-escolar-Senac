package model;

import java.util.Date;

public class ModelNota {

    private int codigoprofessor;
    private int codigomateria;
    private int codigonota;
    private int codigoaluno;
    private Date data;
    private double nota;

    // Inicia vazio para quando nao necessitar passar parametros
    public ModelNota (){ }

    //	codigo serial4 NOT NULL,
//	codigoprofessor int4 NOT NULL,
//	codigomateria int4 NOT NULL,
//	codigoaluno int4 NOT NULL,
//	datanota date NOT NULL,
//	nota numeric(10, 2) NOT NULL,
//	CONSTRAINT pk_tbnota PRIMARY KEY (codigo)
//);
//
    public ModelNota(int codigoprofessor, int codigomateria, int codigoaluno, int codigonota, Date data, double nota) {
        this.codigoprofessor = codigoprofessor;
        this.codigoaluno = codigoaluno;
        this.codigomateria = codigomateria;
        this.data = data;
        this.nota = nota;
        this.codigonota = codigonota;
    }

    public ModelNota(int codigoprofessor, int codigomateria, int codigoaluno, Date data, double nota) {
        this.codigoprofessor = codigoprofessor;
        this.codigoaluno = codigoaluno;
        this.codigomateria = codigomateria;
        this.data = data;
        this.nota = nota;
    }

    public int getCodigoprofessor() {
        return codigoprofessor;
    }

    public void setCodigoprofessor(int codigoprofessor) {
        this.codigoprofessor = codigoprofessor;
    }

    public int getCodigomateria() {
        return codigomateria;
    }

    public void setCodigomateria(int codigomateria) {
        this.codigomateria = codigomateria;
    }

    public int getCodigonota() {
        return codigonota;
    }

    public void setCodigonota(int codigonota) {
        this.codigonota = codigonota;
    }

    public int getCodigoaluno() {
        return codigoaluno;
    }

    public void setCodigoaluno(int codigoaluno) {
        this.codigoaluno = codigoaluno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}