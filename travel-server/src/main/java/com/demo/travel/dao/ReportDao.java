package com.demo.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.travel.entity.Report;
import com.demo.travel.entity.ReportProjection;

@RepositoryRestResource(excerptProjection = ReportProjection.class)
public interface ReportDao extends JpaRepository<Report, Long> {

}
