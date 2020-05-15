package com.expocalendar.project.service.implementation;

import com.expocalendar.project.entities.ExpoHall;
import com.expocalendar.project.entities.Exposition;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpoHallDAO;
import com.expocalendar.project.persistence.abstraction.interfaces.ExpositionDAO;
import com.expocalendar.project.service.interfaces.AdminService;
import com.expocalendar.project.service.Validator;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Map;

public class AdminServiceImpl implements AdminService {
    private ExpositionDAO expositionDAO;
    private ExpoHallDAO expoHallDAO;

    public AdminServiceImpl(ExpositionDAO expositionDAO, ExpoHallDAO expoHallDAO) {
        this.expositionDAO = expositionDAO;
        this.expoHallDAO = expoHallDAO;
    }

    @Override
    public boolean createExposition(Map<String, String> requestParameters) throws MalformedURLException {
        Exposition exposition = buildExposition(requestParameters);
        return expositionDAO.createExposition(exposition);

    }

    @Override
    public boolean updateExposition(Map<String, String> requestParameters) throws MalformedURLException {
        Exposition exposition = buildExposition(requestParameters);
        exposition.setId(Integer.valueOf(requestParameters.get("expositionId")));
        return expositionDAO.updateExposition(exposition);
    }

    @Override
    public boolean deleteExposition(int expositionId) {
        return expositionDAO.deleteExposition(expositionId);
    }

    @Override
    public boolean createExpoHall(Map<String, String> requestParameters) {
        ExpoHall expoHall = new ExpoHall(requestParameters.get("name"), requestParameters.get("address"));
        return expoHallDAO.createExpoHall(expoHall);
    }

    @Override
    public boolean updateExpoHall(Map<String, String> requestParameters) {
        ExpoHall expoHall = new ExpoHall(Integer.valueOf(requestParameters.get("hallId")),
                requestParameters.get("name"), requestParameters.get("address"));
        return expoHallDAO.updateExpoHall(expoHall);
    }

    @Override
    public boolean deleteExpoHall(int expoHallId) {
        return expoHallDAO.deleteExpoHall(expoHallId);
    }


    private Exposition buildExposition(Map<String, String> requestParameters) throws MalformedURLException {
        Validator.validateTime(requestParameters);
        return Exposition.newBuilder().
                setTitle(requestParameters.get("title")).
                setDateFrom(Date.valueOf(requestParameters.get("dateFrom"))).
                setDateTo(Date.valueOf(requestParameters.get("dateTo"))).
                setTheme(requestParameters.get("theme")).
                setTicketPrice(Double.valueOf(requestParameters.get("price"))).
                setExpoHallId(Integer.valueOf(requestParameters.get("hallId"))).
                setDescription(requestParameters.get("description")).
                setBeginTime(Time.valueOf(requestParameters.get("beginTime"))).
                setPicture(new URL(requestParameters.get("url"))).build();
    }

}
