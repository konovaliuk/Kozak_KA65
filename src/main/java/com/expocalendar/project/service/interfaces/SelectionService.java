package com.expocalendar.project.service.interfaces;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;

import java.util.List;
import java.util.Map;

/**
 * Interface provides service for getting
 * objects of subject area: Expositions, ExpoHalls, Themes.
 * Methods implementations implies buisness logic
 * interacting with appropriate DAO
 *
 * @author Nicolas
 */

public interface SelectionService {
    /**
     * getting all ExpoHalls from database
     *
     * @return List of ExpoHalls
     */

    List<ExpoHall> getExpoHalls();

    /**
     * getting ExpoHall from database
     *
     * @param id of ExpoHall to be found in database
     * @return ExpoHall found by id
     */

    ExpoHall getExpoHall(int id);

    /**
     * getting all Expositions from database
     *
     * @return List of Expositions
     */

    List<Exposition> getAllExpositions();

    /**
     * getting Expositions from database whose parameters corresponds to user's request
     *
     * @param requestParameters extracted from HttpServletRequest object
     * @param limit             number of exposition to be displayed per page
     * @param offset            row depending on current page
     * @return List of Expositions
     */

    List<Exposition> findExpositions(Map<String, String> requestParameters, int limit, int offset);

    /**
     * getting number of Expositions whose parameters corresponds to user's request
     *
     * @return number of Expositions
     */

    int getNumberOfExpositions(Map<String, String> requestParameters);

    /**
     * getting all available themes
     *
     * @return List of themes
     */

    List<String> findThemes();

    /**
     * getting Exposition from database
     *
     * @param id of Exposition to be found in database
     * @return Exposition found by id
     */

    Exposition getExposition(Integer id);
}
