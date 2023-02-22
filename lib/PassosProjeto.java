package lib;

/**
 *
 * @author isaac.leder
 */
public class PassosProjeto {
    
    // Escola - nao funciona o alterar - corrigir em todos     -----------
    //* Ao fechar a tela, nao deve fechar o sistema- em properties das telas mudar o DefaultCloseOperation para dispose em todas as telas
                                                                                       // posicao inicial
    //e final----------------
    // formatar a data das notas assim-> 05/10/2022 , usar substring -> data.substring(0,4), pegar dia e mes e ano
    
    // formatar data ao salvar e ao carregar dados testar 
    
    //* CORRIGIR MSG DE cLIENTE nAO EXISTE *-------------------
    
    //* BOTAO DE EXCLUIR DEVE FICAR DESHABILITADO QUANDO ESTA INSERINDO DADOS...TODAS AS TELAS-----------
    
    // CAMPOS DE PESQUISA, PROGRAMAR
    public static void main(String[] args) {
        String data = "2022-10-05";
        
        String ano = data.substring(0,4);
        
//        // mes
//        String ano = data.substring(0,4);
//        // dia
//        String ano = data.substring(0,4);
        
        System.out.println("Ano:" + ano);
    }
    
    // PROBLEMA DO ALTERAR - CORRIGIR EM TODAS AS TELAS -----------------
    
    // PROBLEMA DO LOGIN ------------
    // 1 - FAZER O LOGIN - NAO TA FAZENDO
    
}
