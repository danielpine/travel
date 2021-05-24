package com.demo.travel.controller.admin;

import com.demo.travel.common.PageResult;
import com.demo.travel.common.Result;
import com.demo.travel.common.StatusCode;
import com.demo.travel.entity.Hotel_orders;
import com.demo.travel.entity.Orders;
import com.demo.travel.entity.User;
import com.demo.travel.service.Hotel_ordersService;
import com.demo.travel.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
