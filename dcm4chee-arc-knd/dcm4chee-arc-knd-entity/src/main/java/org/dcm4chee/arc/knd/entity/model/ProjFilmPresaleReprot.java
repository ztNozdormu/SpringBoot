package org.dcm4chee.arc.knd.entity.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "act_evt_log")
public class ProjFilmPresaleReprot {
    @Id
    @Column(name = "LOG_NR_")
    private Long logNr;

    @Column(name = "TYPE_")
    private String type;

    @Column(name = "PROC_DEF_ID_")
    private String procDefId;

    @Column(name = "PROC_INST_ID_")
    private String procInstId;

    @Column(name = "EXECUTION_ID_")
    private String executionId;

    @Column(name = "TASK_ID_")
    private String taskId;

    @Column(name = "TIME_STAMP_")
    private Date timeStamp;

    @Column(name = "USER_ID_")
    private String userId;

    @Column(name = "LOCK_OWNER_")
    private String lockOwner;

    @Column(name = "LOCK_TIME_")
    private Date lockTime;

    @Column(name = "IS_PROCESSED_")
    private Byte isProcessed;

    @Column(name = "DATA_")
    private byte[] data;

    /**
     * @return LOG_NR_
     */
    public Long getLogNr() {
        return logNr;
    }

    /**
     * @param logNr
     */
    public void setLogNr(Long logNr) {
        this.logNr = logNr;
    }

    /**
     * @return TYPE_
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return PROC_DEF_ID_
     */
    public String getProcDefId() {
        return procDefId;
    }

    /**
     * @param procDefId
     */
    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    /**
     * @return PROC_INST_ID_
     */
    public String getProcInstId() {
        return procInstId;
    }

    /**
     * @param procInstId
     */
    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    /**
     * @return EXECUTION_ID_
     */
    public String getExecutionId() {
        return executionId;
    }

    /**
     * @param executionId
     */
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    /**
     * @return TASK_ID_
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return TIME_STAMP_
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return USER_ID_
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return LOCK_OWNER_
     */
    public String getLockOwner() {
        return lockOwner;
    }

    /**
     * @param lockOwner
     */
    public void setLockOwner(String lockOwner) {
        this.lockOwner = lockOwner;
    }

    /**
     * @return LOCK_TIME_
     */
    public Date getLockTime() {
        return lockTime;
    }

    /**
     * @param lockTime
     */
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    /**
     * @return IS_PROCESSED_
     */
    public Byte getIsProcessed() {
        return isProcessed;
    }

    /**
     * @param isProcessed
     */
    public void setIsProcessed(Byte isProcessed) {
        this.isProcessed = isProcessed;
    }

    /**
     * @return DATA_
     */
    public byte[] getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(byte[] data) {
        this.data = data;
    }
}