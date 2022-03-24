package advisor;

import advisor.auth.Auth;

public class Authorization {
    public static boolean auth(String url){
        Auth auth = new Auth(url);
        auth.run();
        return auth.isAuth();
    }
}
