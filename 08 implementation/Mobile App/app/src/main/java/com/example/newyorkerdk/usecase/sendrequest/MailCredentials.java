package com.example.newyorkerdk.usecase.sendrequest;

public class MailCredentials {

    private MailCredentials() {}

    private static final  String EMAIL = "danijelgitanovic@gmail.com";
    private static final  String PASSWORD = "yzmcqoojwegmsnno";
    private static final String NewYorkerMail = "info@new-yorker.dk";
    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getNewYorkerMail() {
        return NewYorkerMail;
    }
}
