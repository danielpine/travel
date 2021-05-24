package com.demo.travel.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器层
 *
 * @author Administrator
 */
@Controller
@CrossOrigin
@RequestMapping("/reports")
public class ReportsController {

    @RequestMapping(value = "/reportsList")
    public String reportsList() {
        return "admin/reportsmanage/reportslist";
    }


}
