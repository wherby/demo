package com.example.demo.incident;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Incident {
    @Id
    @SequenceGenerator(
            name="incident_sequence",
            sequenceName="incident_sequence",
            allocationSize=1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "incident_sequence")
    private Long id;
    private String type;
    private String description;
    private Integer priority;
    private LocalDate createTime;
    private LocalDate updateTime;
    private String reportName;
    private String fixName;

    public Incident() {
    }

    public Incident(Long id, String type, String description, Integer priority, LocalDate createTime, LocalDate updateTime, String reportName, String fixName) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.priority = priority;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.reportName = reportName;
        this.fixName = fixName;
    }

    public Incident(String type, String description, Integer priority, LocalDate createTime, LocalDate updateTime, String reportName, String fixName) {
        this.type = type;
        this.description = description;
        this.priority = priority;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.reportName = reportName;
        this.fixName = fixName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getFixName() {
        return fixName;
    }

    public void setFixName(String fixName) {
        this.fixName = fixName;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", reportName='" + reportName + '\'' +
                ", fixName='" + fixName + '\'' +
                '}';
    }
}
