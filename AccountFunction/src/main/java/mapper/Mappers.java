package mapper;

import entities.Account;
import model.AccountRest;
import org.springframework.beans.BeanUtils;

public class Mappers {

    public static Account toAccount(AccountRest accountRest){
        Account account = new Account();
        BeanUtils.copyProperties(accountRest,account);
        return  account;
    }

    public static AccountRest toAccountRest(Account account){
        AccountRest accountRest = new AccountRest();
        BeanUtils.copyProperties(account,accountRest);
        return  accountRest;
    }
}
