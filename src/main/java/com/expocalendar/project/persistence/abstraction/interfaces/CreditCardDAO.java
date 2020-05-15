package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.CreditCard;


public interface CreditCardDAO {
    /**
     * @param cardId identifier of Credit Card to be found in database
     * @return Credit Card found in database
     */
    CreditCard findCard(int cardId);

}
