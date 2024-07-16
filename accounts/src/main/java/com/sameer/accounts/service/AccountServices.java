package com.sameer.accounts.service;

import com.sameer.accounts.dto.CustomerDto;

public interface AccountServices {
    /**
     *  * Creates an account with the provided CustomerDto object.
     * @param customerDto-CustomerDto Object
     */

    void createAccount(CustomerDto customerDto);
    /**
     *  * Creates an account with the provided CustomerDto object.
     * @param mobileNumber-input mobile number
     * @return Account details fetch  on given mobile number
     */
    CustomerDto fetchAccountDetails(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber-input mobile number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
