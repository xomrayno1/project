package com.tampro.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tampro.entities.UserAccount;

@Repository
public interface UserAccountRepository  extends CrudRepository<UserAccount, Long>{

}
