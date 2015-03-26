package com.cmbb.smartkids.network.model;

import com.google.gson.annotations.Expose;

public class ValidPhoneModel {

    @Expose
    private String code;
    @Expose
    private Context context;

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The context
     */
    public Context getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(Context context) {
        this.context = context;
    }

}
