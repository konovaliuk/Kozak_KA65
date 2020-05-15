package com.expocalendar.project.service.implementation;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.QueryHelper;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.service.interfaces.SelectionService;
import org.apache.log4j.Logger;

import java.util.*;

public class SelectionServiceImpl implements SelectionService {
    private final static Logger LOGGER = Logger.getLogger(SelectionServiceImpl.class);

    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;

    public SelectionServiceImpl(ExpositionDAO expositionDAO, ExpoHallDAO expoHallDAO) {
        this.expositionDAO = expositionDAO;
        this.expoHallDAO = expoHallDAO;
    }

    @Override
    public List<ExpoHall> getExpoHalls() {
        return expoHallDAO.findAll();
    }

    @Override
    public ExpoHall getExpoHall(int id) {
        return expoHallDAO.findExpoHall(id);
    }

    @Override
    public List<Exposition> getAllExpositions() {
        return expositionDAO.findAll();
    }

    @Override
    public List<Exposition> findExpositions(Map<String, String> requestParameters, int limit, int offset) {
        String query = QueryHelper.parseQuery(requestParameters, limit, offset);
        LOGGER.info("New query string formed from request parameters " + query);
        return expositionDAO.findExpositions(query);
    }

    @Override
    public int getNumberOfExpositions(Map<String, String> requestParameters) {
        String countQuery = QueryHelper.countQuery(requestParameters);
        LOGGER.info("New query string formed from request parameters " + countQuery);
        return expositionDAO.countExpositions(countQuery);
    }

    @Override
    public List<String> findThemes() {
        return expositionDAO.findThemes();
    }

    @Override
    public Exposition getExposition(Integer id) {
        return expositionDAO.findExposition(id);
    }
}
