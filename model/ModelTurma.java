package model;

public class ModelTurma {

    private int codigo;
    private String descricao;
    private String nivel;
    private String periodo;

    // Inicia vazio para quando nao necessitar passar parametros
    public ModelTurma (){

    }

    public ModelTurma(int codigo, String descricao,String nivel,String periodo) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.nivel = nivel;
        this.periodo = periodo;
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
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao.length() > 0) {
            this.descricao = descricao;
        }
    }
    
    
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        if (nivel.length() > 0) {
            this.nivel = nivel;
        }
    }
    
    
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        if (periodo.length() > 0) {
            this.periodo = periodo;
        }
    }
}
