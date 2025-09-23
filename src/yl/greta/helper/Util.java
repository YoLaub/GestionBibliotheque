package yl.greta.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yl.greta.model.Livre;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class Util {

    public static final Logger log = LogManager.getLogger(Util.class);


    public static String dateJour(LocalDate d) throws DateTimeParseException {

        DateTimeFormatter formatFR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return d.format(formatFR);

    }


    }


