package org.spring.springboot.domain;

/**
 * Created by Administrator on 2018/3/9.
 */
public class House {

    /**
     * 房间编号
     */
    private Long id;

    private String hostId;

    private String title;

    private String description;

    private String houseInfo;

    private String trafficCondition;

    private String surroundCondition;

    private String bathroom;

    private String electricAppliances;

    private String facility;

    private String claim;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public String getTrafficCondition() {
        return trafficCondition;
    }

    public void setTrafficCondition(String trafficCondition) {
        this.trafficCondition = trafficCondition;
    }

    public String getSurroundCondition() {
        return surroundCondition;
    }

    public void setSurroundCondition(String surroundCondition) {
        this.surroundCondition = surroundCondition;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getElectricAppliances() {
        return electricAppliances;
    }

    public void setElectricAppliances(String electricAppliances) {
        this.electricAppliances = electricAppliances;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }
}
