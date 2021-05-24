package com.demo.travel.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "reportProjection", types = {ReportProjection.class})
public interface ReportProjection {
    Long getId();
    PostProjection getPost();
    User getUser();
    String getReportType();
    String getStatus();
}
