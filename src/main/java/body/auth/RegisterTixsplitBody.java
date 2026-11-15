
package body.auth;

import org.json.JSONObject;
import utils.ConfigReader;
import utils.Utils;

public class RegisterTixsplitBody {

    // Untuk email random
    public String registerData(String nameKey, String passwordKey) {

        JSONObject body = new JSONObject();

        body.put("name", ConfigReader.getProperty(nameKey));
        body.put("email", Utils.generateRandomEmail());
        body.put("password", ConfigReader.getProperty(passwordKey));

        return body.toString();
    }

    // Untuk email yang sudah ditentukan
    public String registerDataWithEmail(
            String nameKey,
            String emailKey,
            String passwordKey
    ) {

        JSONObject body = new JSONObject();

        body.put("name", ConfigReader.getProperty(nameKey));
        body.put("email", ConfigReader.getProperty(emailKey));
        body.put("password", ConfigReader.getProperty(passwordKey));

        return body.toString();
    }
}
