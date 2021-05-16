package com.demo.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.demo.travel.entity.Scenic;

import java.util.List;
import java.util.Optional;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ScenicDao extends JpaRepository<Scenic,String>,JpaSpecificationExecutor<Scenic>{


    List<Scenic> findByContryLike(String name);


}
