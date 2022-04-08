package ru.specialist.bank;

import org.springframework.data.repository.CrudRepository;

// <Account, AccountId> "Сущность-сложный составной первичный ключ"
public interface AccountRepository extends CrudRepository<Account, AccountId> {

}
