package model;

/**
 *
 * @author Gelvazio Camargo
 */
public class ModelUsuario {

    private int codigo;
    private String nome;
    private String email;
    private String senha;
    private int ativo;

    public ModelUsuario() {

    }

    public ModelUsuario(int codigo, String nome, String email, String senha, int ativo) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public ModelUsuario(String nome, String email, String senha, int ativo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }



}
