package service;

import entities.Arquivo;
import org.springframework.stereotype.Service;
import repository.ArquivoRepository;

@Service
public class ArquivoService {


    public ArquivoService(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    private ArquivoRepository arquivoRepository;

    public void salvar(String nome, String conteudo) {
        arquivoRepository.save(new Arquivo(nome, conteudo));
    }

    public Arquivo busca(String nome) {
        return arquivoRepository.findByNome(nome);
    }


}
