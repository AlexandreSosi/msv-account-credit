package service;

import mapper.Mappers;
import model.AccountRest;
import org.springframework.stereotype.Service;
import repository.AccountRepository;

@Service
public class AccountService {


    public AccountService(AccountRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    private AccountRepository arquivoRepository;

    public void salvar(AccountRest accountRest) {
        arquivoRepository.save(Mappers.toAccount(accountRest));
    }

    public AccountRest buscar(String nome) {
        return Mappers.toAccountRest(arquivoRepository.findByName(nome));
    }


}
