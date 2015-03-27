package com.cmbb.smartkids.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by N.Sun
 */
public final class Constants {

    private Constants() {

    }

    public static final String BASE_URL = "http://192.168.100.151:8089/spring";

    public static final class Auth {
        private Auth() {

        }

        // Account type id
        public static final String SMARTKIDS_ACCOUNT_TYPE = "com.cmbb.smartkids";

        // Account name
        public static final String SMARTKIDS_ACCOUNT_NAME = "smartkids";

        //provider id
        public static final String SMARTKIDS_PROVIDER_AUTHORITY = "com.cmbb.smartkids.sync";

        // auth token type
        public static final String AUTHTOKEN_TYPE = SMARTKIDS_ACCOUNT_TYPE;
    }

    public static final class User {
        private User() {

        }

        public static final String REGISTER_URL = BASE_URL + "/login/register";
        public static final String VALIDPHONE_URL = BASE_URL + "/login/validPhone";
        public static final String LOGINS_URL = BASE_URL + "/login/logins";

        public static boolean isMobileNo(String phone) {
            String match = "^((13|15|18|17)\\d{9})|147\\d{8}$";
            Pattern pattern = Pattern.compile(match);
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        }

    }

    public static final class Home {

        private Home() {

        }

        public static final String AREATYPEPLATE_URL = BASE_URL + "/plate/findByAreaTypeFromPlate";

    }

    public static final class Test {

        public static final String[] testUrl = {
                "http://pic.58pic.com/58pic/12/77/30/44758PIC2er.jpg",
                "http://img4.imgtn.bdimg.com/it/u=3763887074,2416496220&fm=11&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=769147042,2358140694&fm=11&gp=0.jpg",
                "http://img5.imgtn.bdimg.com/it/u=835925941,1324011916&fm=21&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=2706117047,3698169813&fm=11&gp=0.jpg",
                "http://img4.imgtn.bdimg.com/it/u=3110370447,1273506947&fm=21&gp=0.jpg",
                "http://img1.imgtn.bdimg.com/it/u=3833978351,2510224374&fm=21&gp=0.jpg"};
    }

    public static final class Sync {
        private Sync() {

        }

        public static final String FEED_URL = "http://android-developers.blogspot.com/atom.xml";
        public static final String ACCOUNT_TYPE = "com.cmbb.smartkids.syncadapter.account";
    }

    public static final String INTENT_ACTION_EXIT_APP = "com.dmbi.childrenclub.intent.action.exitapp";
    public static final String INTENT_ACTION_Toast = "com.dmbi.childrenclub.intent.action.toast";
}
