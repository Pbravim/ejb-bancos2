package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDao;

    public List<Mensagem> listar() {
        return mensagemDao.listar();
    }

    public void inserir(long id, String textoMensagem) {
        if (contemPalavrao(textoMensagem)) {
            throw new RuntimeException("Mensagem possui palavrões.");
        }

        Mensagem novaMensagem = new Mensagem(id, textoMensagem);
        mensagemDao.inserir(novaMensagem);
    }

    private boolean contemPalavrao(String texto) {
        String[] palavroes = {"palavrão1", "palavrão2", "boboca"};

        String textoLower = texto.toLowerCase();

        for (String palavrao : palavroes) {
            if (textoLower.contains(palavrao.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
