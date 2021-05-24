package com.demo.travel.dao;

import com.demo.travel.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReportDao extends JpaRepository<Report, Long> {

}
