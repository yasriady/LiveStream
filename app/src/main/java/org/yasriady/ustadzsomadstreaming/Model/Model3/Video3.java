package org.yasriady.ustadzsomadstreaming.Model.Model3;

/**
 * Created by dedy on 11/27/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video3 {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("affected_rows")
    @Expose
    private Integer affectedRows;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("opmode")
    @Expose
    private Integer opmode;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("ec")
    @Expose
    private String ec;
    @SerializedName("errmsg")
    @Expose
    private String errmsg;
    @SerializedName("sql")
    @Expose
    private String sql;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAffectedRows() {
        return affectedRows;
    }

    public void setAffectedRows(Integer affectedRows) {
        this.affectedRows = affectedRows;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOpmode() {
        return opmode;
    }

    public void setOpmode(Integer opmode) {
        this.opmode = opmode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}