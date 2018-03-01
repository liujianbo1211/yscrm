package parkingos.com.bolink.actions;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import parkingos.com.bolink.service.DepartmentService;
import parkingos.com.bolink.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/department")
public class DepartmentAction {
    Logger logger = Logger.getLogger( DepartmentAction.class );

    @Autowired
    private DepartmentService departmentService;

    /**
     * 部门查询
     */
    @RequestMapping("/query")
    public String query(HttpServletRequest req, HttpServletResponse resp) {
        String result = departmentService.quickquery( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
    /**
            * 部门创建
     */
    @RequestMapping("/create")
    public String create(HttpServletRequest req, HttpServletResponse resp) {
        String result = departmentService.create( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse resp) {
        String result = departmentService.delete( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
    @RequestMapping("/getDepartments")
    public String getDepartments(HttpServletRequest request, HttpServletResponse resp) {
        String result = departmentService.getDepartments( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
}
