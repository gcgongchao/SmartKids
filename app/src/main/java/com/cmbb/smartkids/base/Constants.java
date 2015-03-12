package com.cmbb.smartkids.base;

/**
 * Created by N.Sun
 */
public final class Constants {

    private Constants() {

    }

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

    public static final String INTENT_ACTION_EXIT_APP = "com.dmbi.childrenclub.intent.action.exitapp";
}
