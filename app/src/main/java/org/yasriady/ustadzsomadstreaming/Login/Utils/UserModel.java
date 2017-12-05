package org.yasriady.ustadzsomadstreaming.Login.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dedy on 12/1/17.
 */

public class UserModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("app_id")
    @Expose
    private String appId;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("business_name")
    @Expose
    private String businessName;
    @SerializedName("hp_no")
    @Expose
    private String hpNo;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("picture_url")
    @Expose
    private Object pictureUrl;
    @SerializedName("privilege_id")
    @Expose
    private String privilegeId;
    @SerializedName("current_role_id")
    @Expose
    private String currentRoleId;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("enable")
    @Expose
    private String enable;
    @SerializedName("privileges")
    @Expose
    private String privileges;
    @SerializedName("ts")
    @Expose
    private String ts;
    @SerializedName("ts")
    @Expose
    private String remark;

    public UserModel(){}

    public UserModel(UserModel userModel) {
        this.id = userModel.getId();
        this.appId = userModel.getAppId();
        this.userid = userModel.getUserid();
        this.password = userModel.getPassword();
        this.provider = userModel.getProvider();
        this.name = userModel.getName();
        this.firstname = userModel.getFirstname();
        this.lastname = userModel.getLastname();
        this.email = userModel.getEmail();
        this.gender = userModel.gender;
        this.birthday = userModel.getBirthday();
        this.businessName = userModel.getBusinessName();
        this.hpNo = userModel.getHpNo();
        this.location = userModel.getLocation();
        this.pictureUrl = userModel.getPictureUrl();
        this.privilegeId = userModel.getPrivilegeId();
        this.currentRoleId = userModel.getCurrentRoleId();
        this.role = userModel.getRole();
        this.enable = userModel.getEnable();
        this.privileges = userModel.getPrivileges();
        this.ts = userModel.getTs();
        this.remark = userModel.getRemark();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getHpNo() {
        return hpNo;
    }

    public void setHpNo(String hpNo) {
        this.hpNo = hpNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(Object pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(String currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
