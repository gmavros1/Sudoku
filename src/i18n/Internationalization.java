package i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
    private String lang;//γλώσσα
    private String country;//χώρα
    Locale lo;
    ResourceBundle bundle;

    public Internationalization(String l, String c){
        lang=l;
        country=c;
        lo= new Locale( lang,country );
        bundle=ResourceBundle.getBundle("i18n/Bundle",lo);
    }
    public String getTranslatedMessage(String message){
        return bundle.getString( message );

    }
}
