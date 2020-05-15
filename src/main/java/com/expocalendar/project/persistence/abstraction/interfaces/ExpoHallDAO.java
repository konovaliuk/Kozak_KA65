package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.ExpoHall;

import java.util.List;

public interface ExpoHallDAO {
    /**
     * Obtain all ExpoHall from DB
     * @return list of ExpoHall
     */
    List<ExpoHall> findAll();

    /**
     * Get ExpoHall from database
     * @param id of ExpoHall in database
     * @return ExpoHall
     */
    ExpoHall findExpoHall(int id);

    /**
     * Create new ExpoHall
     * @param expoHall new ExpoHall to be created in DB
     * @return boolean result of operation
     */
    boolean createExpoHall(ExpoHall expoHall);

    /**
     * Update ExpoHall data in database
     * @param expoHall to be updated
     * @return boolean result of operation
     */
    boolean updateExpoHall(ExpoHall expoHall);

    /**
     * Delete ExpoHall from database
     * @param id of ExpoHall to be deleted
     * @return boolean result of operation
     */
    boolean deleteExpoHall(int id);
}
 