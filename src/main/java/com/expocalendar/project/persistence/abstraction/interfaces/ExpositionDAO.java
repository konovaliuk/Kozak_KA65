package com.expocalendar.project.persistence.abstraction.interfaces;

import com.expocalendar.project.entities.Exposition;

import java.util.List;

public interface ExpositionDAO {
    /**
     * Obtain all Expositions from database
     * @return list of Expositions
     */
    List<Exposition> findAll();

    /**
     * find Expositions corresponds to user's request
     * @param query String depending on selected parameters
     * @return boolean result of operation
     */
    List<Exposition> findExpositions(String query);

    /**
     * count Expositions corresponds to user's request
     * @param query String depending on selected parameters
     * @return boolean result of operation
     */
    int countExpositions(String query);

    /**
     * Find exposition in database
     * @param expositionId of Exposition to be found in database
     * @return boolean result of operation
     */
    Exposition findExposition(int expositionId);

    /**
     * Obtain all themes from database
     * @return list of themes
     */
    List<String> findThemes();

    /**
     *
     * @param exposition to be updated
     * @return boolean result of operation
     */
    boolean createExposition(Exposition exposition);

    /**
     *
     * @param exposition to be updated
     * @return boolean result of operation
     */
    boolean updateExposition(Exposition exposition);

    /**
     *
     * @param expositionId of Exposition to be deleted
     * @return boolean result of operation
     */
    boolean deleteExposition(int expositionId);

}
 