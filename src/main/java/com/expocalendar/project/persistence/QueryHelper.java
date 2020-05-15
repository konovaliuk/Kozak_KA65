package com.expocalendar.project.persistence;


import java.util.Map;

public class QueryHelper {

    private static final String SELECT_BY_DATE = "expositions WHERE " +
            "(date_from BETWEEN '%s' AND '%s' OR date_to BETWEEN '%s' AND " +
            "'%s' OR date_from <= '%s' AND date_to >= '%s')";
    private static final String SELECT_BY_THEME = " AND theme = '%s'";
    private static final String SELECT_BY_HALL = " AND expohall_id = %s";
    private static final String LIMIT_OFFSET = " LIMIT %d OFFSET %d";


    private QueryHelper() {
    }


    public static String parseQuery(Map<String, String> parameters, int limit, int offset) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM ");

        String dateFrom = parameters.get("dateFrom");
        String dateTo = parameters.get("dateTo");

        stringBuilder.append(String.format(SELECT_BY_DATE, dateFrom, dateTo, dateFrom, dateTo, dateFrom, dateTo));

        if (!parameters.get("theme").equals("all")) {
            stringBuilder.append(String.format(SELECT_BY_THEME, parameters.get("theme")));
        }
        if (!parameters.get("hallId").equalsIgnoreCase("")) {
            stringBuilder.append(String.format(SELECT_BY_HALL, parameters.get("hallId")));
        }
        stringBuilder.append(" ORDER BY date_from ASC");
        stringBuilder.append(String.format(LIMIT_OFFSET, limit, offset));


        return stringBuilder.toString();
    }


    public static String countQuery(Map<String, String> parameters) {
        StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(*) AS total FROM ");
        String dateFrom = parameters.get("dateFrom");
        String dateTo = parameters.get("dateTo");
        stringBuilder.append(String.format(SELECT_BY_DATE, dateFrom, dateTo, dateFrom, dateTo, dateFrom, dateTo));

        if (!parameters.get("theme").equals("all")) {
            stringBuilder.append(String.format(SELECT_BY_THEME, parameters.get("theme")));
        }
        if (!parameters.get("hallId").equalsIgnoreCase("")) {
            stringBuilder.append(String.format(SELECT_BY_HALL, parameters.get("hallId")));
        }

        return stringBuilder.toString();
    }


}
 