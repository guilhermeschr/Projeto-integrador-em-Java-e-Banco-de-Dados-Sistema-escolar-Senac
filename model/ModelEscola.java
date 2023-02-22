package model;

public class ModelEscola {

    private int codigo;
    private String nome;

    // Inicia vazio para quando nao necessitar passar parametros
    public ModelEscola (){

    }

    public ModelEscola(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

//    public ModelEscola(int codigo, String nome) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo > 0) {
            this.codigo = codigo;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
        }
    }
}
