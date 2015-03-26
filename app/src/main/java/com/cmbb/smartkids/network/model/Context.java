package com.cmbb.smartkids.network.model;

import com.google.gson.annotations.Expose;

public class Context {

    @Expose
    private String presentation;
    @Expose
    private String token;

    /**
     *
     * @return
     * The presentation
     */
    public String getPresentation() {
        return presentation;
    }

    /**
     *
     * @param presentation
     * The presentation
     */
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

}