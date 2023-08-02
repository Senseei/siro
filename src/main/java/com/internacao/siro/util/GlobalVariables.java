package com.internacao.siro.util;

import java.time.format.DateTimeFormatter;

public class GlobalVariables {
    public static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
}
