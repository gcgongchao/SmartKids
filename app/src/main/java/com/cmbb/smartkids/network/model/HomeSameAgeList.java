package com.cmbb.smartkids.network.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "attent",
        "bigImg",
        "context",
        "cream",
        "date",
        "id",
        "loginTimes",
        "nike",
        "plateName",
        "relpys",
        "smallImg",
        "smallImgHeight",
        "smallImgWidth",
        "stick",
        "store",
        "title",
        "type",
        "userId",
        "userSmallHeadImg",
        "weixinImg"
})
public class HomeSameAgeList {

    @JsonProperty("attent")
    private Integer attent;
    @JsonProperty("bigImg")
    private String bigImg;
    @JsonProperty("context")
    private String context;
    @JsonProperty("cream")
    private Integer cream;
    @JsonProperty("date")
    private String date;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("loginTimes")
    private Integer loginTimes;
    @JsonProperty("nike")
    private String nike;
    @JsonProperty("plateName")
    private String plateName;
    @JsonProperty("relpys")
    private Integer relpys;
    @JsonProperty("smallImg")
    private String smallImg;
    @JsonProperty("smallImgHeight")
    private Integer smallImgHeight;
    @JsonProperty("smallImgWidth")
    private Integer smallImgWidth;
    @JsonProperty("stick")
    private Integer stick;
    @JsonProperty("store")
    private Integer store;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("userSmallHeadImg")
    private String userSmallHeadImg;
    @JsonProperty("weixinImg")
    private String weixinImg;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The attent
     */
    @JsonProperty("attent")
    public Integer getAttent() {
        return attent;
    }

    /**
     * @param attent The attent
     */
    @JsonProperty("attent")
    public void setAttent(Integer attent) {
        this.attent = attent;
    }

    /**
     * @return The bigImg
     */
    @JsonProperty("bigImg")
    public String getBigImg() {
        return bigImg;
    }

    /**
     * @param bigImg The bigImg
     */
    @JsonProperty("bigImg")
    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    /**
     * @return The context
     */
    @JsonProperty("context")
    public String getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    @JsonProperty("context")
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * @return The cream
     */
    @JsonProperty("cream")
    public Integer getCream() {
        return cream;
    }

    /**
     * @param cream The cream
     */
    @JsonProperty("cream")
    public void setCream(Integer cream) {
        this.cream = cream;
    }

    /**
     * @return The date
     */
    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The loginTimes
     */
    @JsonProperty("loginTimes")
    public Integer getLoginTimes() {
        return loginTimes;
    }

    /**
     * @param loginTimes The loginTimes
     */
    @JsonProperty("loginTimes")
    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    /**
     * @return The nike
     */
    @JsonProperty("nike")
    public String getNike() {
        return nike;
    }

    /**
     * @param nike The nike
     */
    @JsonProperty("nike")
    public void setNike(String nike) {
        this.nike = nike;
    }

    /**
     * @return The plateName
     */
    @JsonProperty("plateName")
    public String getPlateName() {
        return plateName;
    }

    /**
     * @param plateName The plateName
     */
    @JsonProperty("plateName")
    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    /**
     * @return The relpys
     */
    @JsonProperty("relpys")
    public Integer getRelpys() {
        return relpys;
    }

    /**
     * @param relpys The relpys
     */
    @JsonProperty("relpys")
    public void setRelpys(Integer relpys) {
        this.relpys = relpys;
    }

    /**
     * @return The smallImg
     */
    @JsonProperty("smallImg")
    public String getSmallImg() {
        return smallImg;
    }

    /**
     * @param smallImg The smallImg
     */
    @JsonProperty("smallImg")
    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    /**
     * @return The smallImgHeight
     */
    @JsonProperty("smallImgHeight")
    public Integer getSmallImgHeight() {
        return smallImgHeight;
    }

    /**
     * @param smallImgHeight The smallImgHeight
     */
    @JsonProperty("smallImgHeight")
    public void setSmallImgHeight(Integer smallImgHeight) {
        this.smallImgHeight = smallImgHeight;
    }

    /**
     * @return The smallImgWidth
     */
    @JsonProperty("smallImgWidth")
    public Integer getSmallImgWidth() {
        return smallImgWidth;
    }

    /**
     * @param smallImgWidth The smallImgWidth
     */
    @JsonProperty("smallImgWidth")
    public void setSmallImgWidth(Integer smallImgWidth) {
        this.smallImgWidth = smallImgWidth;
    }

    /**
     * @return The stick
     */
    @JsonProperty("stick")
    public Integer getStick() {
        return stick;
    }

    /**
     * @param stick The stick
     */
    @JsonProperty("stick")
    public void setStick(Integer stick) {
        this.stick = stick;
    }

    /**
     * @return The store
     */
    @JsonProperty("store")
    public Integer getStore() {
        return store;
    }

    /**
     * @param store The store
     */
    @JsonProperty("store")
    public void setStore(Integer store) {
        this.store = store;
    }

    /**
     * @return The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The userId
     */
    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The userId
     */
    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return The userSmallHeadImg
     */
    @JsonProperty("userSmallHeadImg")
    public String getUserSmallHeadImg() {
        return userSmallHeadImg;
    }

    /**
     * @param userSmallHeadImg The userSmallHeadImg
     */
    @JsonProperty("userSmallHeadImg")
    public void setUserSmallHeadImg(String userSmallHeadImg) {
        this.userSmallHeadImg = userSmallHeadImg;
    }

    /**
     * @return The weixinImg
     */
    @JsonProperty("weixinImg")
    public String getWeixinImg() {
        return weixinImg;
    }

    /**
     * @param weixinImg The weixinImg
     */
    @JsonProperty("weixinImg")
    public void setWeixinImg(String weixinImg) {
        this.weixinImg = weixinImg;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "HomeSameAgeList{" +
                "attent=" + attent +
                ", bigImg='" + bigImg + '\'' +
                ", context='" + context + '\'' +
                ", cream=" + cream +
                ", date='" + date + '\'' +
                ", id=" + id +
                ", loginTimes=" + loginTimes +
                ", nike='" + nike + '\'' +
                ", plateName='" + plateName + '\'' +
                ", relpys=" + relpys +
                ", smallImg='" + smallImg + '\'' +
                ", smallImgHeight=" + smallImgHeight +
                ", smallImgWidth=" + smallImgWidth +
                ", stick=" + stick +
                ", store=" + store +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                ", userSmallHeadImg='" + userSmallHeadImg + '\'' +
                ", weixinImg='" + weixinImg + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
