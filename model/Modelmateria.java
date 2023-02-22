package model;

/**
 *
 * @author gelvazio.camargo
 */
public class Modelmateria {
    
    private int codigo;
    private String nome;

    public Modelmateria() {}
    
    public Modelmateria(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String descricao) {
        this.nome = nome;
    }

}