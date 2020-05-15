package com.expocalendar.project.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ICommand {

    /**
     * @param request  HttpServletRequest request
     * @param response HttpServletResponse response
     * @return path to be forwarded to Controller for dispatching
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
