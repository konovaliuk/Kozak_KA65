package com.expocalendar.project.service.interfaces;


import java.net.MalformedURLException;
import java.util.Map;

/**
 * Interface provides service for administrating operations
 * Methods implementations implies business logic
 * interacting with appropriate DAO
 *
 * @author Nicolas
 */

public interface AdminService {
    /**
     * Creating new Exposition in database
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @return boolean result of Exposition creating operation success
     */

    boolean createExposition(Map<String, String> requestParameters) throws MalformedURLException;

    /**
     * Updating Exposition in database
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @return boolean result of Exposition updating operation success
     */

    boolean updateExposition(Map<String, String> requestParameters) throws MalformedURLException;

    /**
     * Delete Exposition
     *
     * @param expositionId id of Exposition to be deleted from database
     * @return boolean result of Exposition deleting operation success
     */

    boolean deleteExposition(int expositionId);

    /**
     * Creating new ExpoHall in database
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @return boolean result of ExpoHall creating operation success
     */

    boolean createExpoHall(Map<String, String> requestParameters);

    /**
     * Updating ExpoHall in database
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @return boolean result of ExpoHall updating operation success
     */

    boolean updateExpoHall(Map<String, String> requestParameters);

    /**
     * Deleting ExpoHall from database
     *
     * @param expoHallId id of ExpoHall to be deleted from database
     * @return boolean result of deleting operation success
     */

    boolean deleteExpoHall(int expoHallId);
}
