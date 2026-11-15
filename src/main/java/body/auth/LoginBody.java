package body.auth;

import org.json.JSONObject;
import utils.ConfigReader;

public class LoginBody {

    public String loginData(String emailKey, String passwordKey) {

        JSONObject body = new JSONObject();

        body.put("email", ConfigReader.getProperty(emailKey));
        body.put("password", ConfigReader.getProperty(passwordKey));

        return body.toString();
    }
}