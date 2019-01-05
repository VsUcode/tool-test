package com.keyboardman.tool.zhaofeng.model;

public class HealthZF {

    private int id;
    private String username;
    private String inheritedDisease; //遗传病
    private String operation; //手术
    private String pestilence; // 传染病
    private String apriority; //先天性疾病
    private String health; //近期身体状况
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInheritedDisease() {
        return inheritedDisease;
    }

    public void setInheritedDisease(String inheritedDisease) {
        this.inheritedDisease = inheritedDisease;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getPestilence() {
        return pestilence;
    }

    public void setPestilence(String pestilence) {
        this.pestilence = pestilence;
    }

    public String getApriority() {
        return apriority;
    }

    public void setApriority(String apriority) {
        this.apriority = apriority;
    }

    public String getHealthy() {
        return health;
    }

    public void setHealthy(String health) {
        this.health = health;
    }
}
