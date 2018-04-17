package org.spring.springboot.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */
public class Order {
    private int orderId;
    private Timestamp createTime;//创建时间
    private Timestamp lastChangeTime;//最后一次修改时间
    private String userPhone;
    private String userName;
    private String hostPhone;
    private long houseId;
    private String houseImgUrl;//房源缩略图
    private String houseTitle;//房源标题
    private String houseRentalType;//出租类型
    private String houseLocation;//房源地址
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private int dayNum;
    private int totalHouseMoney;//总共的房费
    private int deposit;//定金
    private int totalMoney;//总共的订单总额
    private List<CheckInPeople> checkInPeopleUserInfoList;//入住人信息
    private String checkInPeopleIdList;//入住人id 列表，用String拼接保存到数据库
    private String payWay;
    private int payWayCode;
    private int status;//订单状态

    public String getCheckInPeopleIdList() {
        return checkInPeopleIdList;
    }

    public void setCheckInPeopleIdList(String checkInPeopleIdList) {
        this.checkInPeopleIdList = checkInPeopleIdList;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Timestamp lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHostPhone() {
        return hostPhone;
    }

    public void setHostPhone(String hostPhone) {
        this.hostPhone = hostPhone;
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public String getHouseImgUrl() {
        return houseImgUrl;
    }

    public void setHouseImgUrl(String houseImgUrl) {
        this.houseImgUrl = houseImgUrl;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }

    public String getHouseRentalType() {
        return houseRentalType;
    }

    public void setHouseRentalType(String houseRentalType) {
        this.houseRentalType = houseRentalType;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getTotalHouseMoney() {
        return totalHouseMoney;
    }

    public void setTotalHouseMoney(int totalHouseMoney) {
        this.totalHouseMoney = totalHouseMoney;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<CheckInPeople> getCheckInPeopleUserInfoList() {
        return checkInPeopleUserInfoList;
    }

    public void setCheckInPeopleUserInfoList(List<CheckInPeople> checkInPeopleUserInfoList) {
        this.checkInPeopleUserInfoList = checkInPeopleUserInfoList;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public int getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(int payWayCode) {
        this.payWayCode = payWayCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static final int STATUS_FINISH = 1;//订单完成，及完成支付
    public static final int STATUS_UNPAY = 2;//订单未支付
    public static final int STATUS_CANCEL = -1;//订单取消

    public static final int ALI_PAY_CODE = 1;//支付宝支付
    public static final int WX_PAY_CODE = 2;//微信支付

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createTime=" + createTime +
                ", lastChangeTime=" + lastChangeTime +
                ", userPhone='" + userPhone + '\'' +
                ", userName='" + userName + '\'' +
                ", hostPhone='" + hostPhone + '\'' +
                ", houseId=" + houseId +
                ", houseImgUrl='" + houseImgUrl + '\'' +
                ", houseTitle='" + houseTitle + '\'' +
                ", houseRentalType='" + houseRentalType + '\'' +
                ", houseLocation='" + houseLocation + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", dayNum=" + dayNum +
                ", totalHouseMoney=" + totalHouseMoney +
                ", deposit=" + deposit +
                ", totalMoney=" + totalMoney +
                ", checkInPeopleUserInfoList=" + checkInPeopleUserInfoList +
                ", checkInPeopleIdList='" + checkInPeopleIdList + '\'' +
                ", payWay='" + payWay + '\'' +
                ", payWayCode=" + payWayCode +
                ", status=" + status +
                '}';
    }
}
