package com.sameer.accounts.service.impl;

import com.sameer.accounts.controller.AccountsConstants;
import com.sameer.accounts.dto.AccountsDto;
import com.sameer.accounts.dto.CustomerDto;
import com.sameer.accounts.entity.Accounts;
import com.sameer.accounts.entity.Customer;
import com.sameer.accounts.exception.CustomerAlreadyExistsException;
import com.sameer.accounts.exception.ResourceNotFoundException;
import com.sameer.accounts.mapper.AccountMapper;
import com.sameer.accounts.mapper.CustomerMapper;
import com.sameer.accounts.repository.AccountRepository;
import com.sameer.accounts.repository.CustomerRepository;
import com.sameer.accounts.service.AccountServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class AccountServicesImpl  implements AccountServices {
    private static final Logger log = LoggerFactory.getLogger(AccountServicesImpl.class);
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    /**
     * Creates an account with the provided CustomerDto object and saved in to db.
     *
     * @param customerDto the CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapTocustomer(customerDto,new Customer());
        log.info("find new Customer{}" +customer);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        log.info("find mobile number{}",optionalCustomer);
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already register with given number: " +
                    customerDto.getMobileNumber());
        }

        Customer  savedCustomer  =  customerRepository.save(customer);
        log.info("Saved customer: {}", savedCustomer);

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("anonymous");
       accountRepository.save(createNewAccount(savedCustomer));

    }

    /**
     * * Creates an account with the provided CustomerDto object.
     *
     * @param mobileNumber -input mobile number
     * @return Account details fetch  on given mobile number
     */
    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
                );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).
                orElseThrow(()-> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    /**
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated  = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
         Accounts accounts =   accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","AccountNumber",accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapTAccounts(accountsDto,accounts);
           accounts = accountRepository.save(accounts);
           Long  customerId = accounts.getCustomerId();
         Customer customer =  customerRepository.findById(customerId).orElseThrow(
                   () -> new ResourceNotFoundException("Customer","customerId",customerId.toString()));
           CustomerMapper.mapTocustomer(customerDto,customer);
           customerRepository.save(customer);
           isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber -input mobile number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer  customer =  customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow( () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        newAccounts.setCreatedAt(LocalDateTime.now());
        newAccounts.setCreatedBy("anonymous");
        return newAccounts;

    }
}
