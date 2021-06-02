package com.example.newyorkerdk.usecase.sendrequest;

public class MailCredentials {
    private static final  String EMAIL = "danijelgitanovic@gmail.com";
    private static final  String PASSWORD = "";
    private static final String NewYorkerMail = "info@new-yorkwe.dk";

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
