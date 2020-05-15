package com.expocalendar.project.persistence;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class QueryHelperTest {

    @Test
    public void testParseQuery() {
        String expected = "SELECT * FROM expositions WHERE " +
                "(date_from BETWEEN '2020-05-21' " +
                "AND '2020-05-21' OR date_to BETWEEN '2020-05-21' " +
                "AND '2020-05-21' OR date_from <= '2020-05-21' " +
                "AND date_to >= '2020-06-21') " +
                "AND theme = 'Art' " +
                "AND expohall_id = 3 ORDER BY date_from ASC LIMIT 9 OFFSET 0";

        Map<String, String> params = new HashMap<>();
        params.put("dateFrom", "2020-05-21");
        params.put("dateTo", "2020-06-21");
        params.put("theme", "Art");
        params.put("hallId", "3");

        String actual = QueryHelper.parseQuery(params, 9, 0);
        assertEquals(expected, actual);

    }

    @Test
    public void testCountQuery() {
        String expected = "SELECT COUNT(*) AS total FROM expositions " +
                "WHERE (" + "date_from BETWEEN '2020-05-21' " +
                "AND '2020-05-21' OR date_to BETWEEN " + "'2020-05-21' " +
                "AND '2020-02-21' OR date_from <= '2020-05-21' " +
                "AND date_to >= '2020-06-21') " +
                "AND theme = 'Art' AND expohall_id = 3";

        Map<String, String> params = new HashMap<>();
        params.put("dateFrom", "2020-01-21");
        params.put("dateTo", "2020-02-21");
        params.put("theme", "Art");
        params.put("hallId", "3");

        String actual = QueryHelper.countQuery(params);
        assertEquals(expected, actual);
    }
}