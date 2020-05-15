package com.expocalendar.project.service;

import com.expocalendar.project.entities.CreditCard;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Validator {
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String CARD_DATE_PATTERN = "MMyy";

    private final static Logger LOGGER = Logger.getLogger(Validator.class);

    public static void validateSelectionParameters(Map<String, String> requestParameters) {
        requestParameters.putIfAbsent("theme", "all");
        requestParameters.putIfAbsent("hallId", "");

        if (requestParameters.get("dateFrom") == null || requestParameters.get("dateFrom").equals("")) {
            requestParameters.put("dateFrom", getCurrentDate());
        }

        if (requestParameters.get("dateTo") == null || requestParameters.get("dateTo").equals("")) {
            requestParameters.put("dateTo", getCurrentDate());
        }

        if (invalidDateRange(requestParameters)) {
            swapDate(requestParameters);
        }
    }

    private static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(date);
    }


    private static boolean invalidDateRange(Map<String, String> requestParameters) {
        DateFormat format = new SimpleDateFormat(DATE_PATTERN);
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            date1 = format.parse(requestParameters.get("dateFrom"));
            date2 = format.parse(requestParameters.get("dateTo"));

        } catch (ParseException e) {
            LOGGER.error("ParseException occurred in " + Validator.class.getSimpleName(), e);
        }

        return date1.after(date2);
    }

    private static void swapDate(Map<String, String> requestParameters) {
        String dateFrom = requestParameters.get("dateFrom");
        String dateTo = requestParameters.get("dateTo");

        requestParameters.put("dateFrom", dateTo);
        requestParameters.put("dateTo", dateFrom);
    }

    public static void validateTime(Map<String, String> requestParameters) {
        String time = requestParameters.get("beginTime");
        if (time.length() == 5) {
            time += ":00";
        }
        requestParameters.put("beginTime", time);
    }

    public static boolean validCard(Map<String, String> requestParameters, CreditCard creditCard, double withdraw) {
        String target = requestParameters.get("month") + requestParameters.get("year");
        DateFormat df = new SimpleDateFormat(CARD_DATE_PATTERN);
        Date result = null;
        try {
            result = df.parse(target);
        } catch (ParseException e) {
            LOGGER.error("ParseException occurred in " + Validator.class.getSimpleName(), e);
        }

        return creditCard.getBalance() >= withdraw &&
                requestParameters.get("cardHolder").equals(creditCard.getHolder()) &&
                requestParameters.get("cardNumber").equals(creditCard.getNumber()) &&
                Integer.valueOf(requestParameters.get("cvv")) == (creditCard.getCVV()) &&
                Integer.valueOf(requestParameters.get("month")) == (creditCard.getMonth()) &&
                Integer.valueOf(requestParameters.get("year")) == (creditCard.getYear()) &&
                result != null && result.after(new Date());
    }
}
