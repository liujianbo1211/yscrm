package parkingos.com.bolink.actions;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import parkingos.com.bolink.service.CustomerService;
import parkingos.com.bolink.service.DepartmentService;
import parkingos.com.bolink.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/customer")
public class CustomerAction {
    Logger logger = Logger.getLogger( CustomerAction.class );

    @Autowired
    private CustomerService customerService;

    /**
     * 创建
     */
    @RequestMapping("/add")
    public String create(HttpServletRequest req, HttpServletResponse resp) {
        String result = customerService.add( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }

    /**
     * 查询
     */
    @RequestMapping("/query")
    public String query(HttpServletRequest req, HttpServletResponse resp) {
        String result = customerService.quickquery( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse resp) {
        String result = customerService.delete( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
}
