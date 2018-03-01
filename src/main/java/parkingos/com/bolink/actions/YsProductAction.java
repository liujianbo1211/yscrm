package parkingos.com.bolink.actions;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import parkingos.com.bolink.service.DepartmentService;
import parkingos.com.bolink.service.YsProductService;
import parkingos.com.bolink.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ysproduct")
public class YsProductAction {

    Logger logger = Logger.getLogger( YsProductAction.class );

    @Autowired
    private YsProductService ysProductService ;

    @RequestMapping("/query")
    public String query(HttpServletRequest req, HttpServletResponse resp) {
        String result = ysProductService.quickquery( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }

    /**
     * 创建
     */
    @RequestMapping("/create")
    public String create(HttpServletRequest req, HttpServletResponse resp) {
        String result = ysProductService.create( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse resp) {
        String result = ysProductService.delete( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
    @RequestMapping("/getProducts")
    public String getDepartments(HttpServletRequest request, HttpServletResponse resp) {
        String result = ysProductService.getProducts( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
    @RequestMapping("/getProductMsg")
    public String getProductMsg(HttpServletRequest request, HttpServletResponse resp) {
        String result = ysProductService.getProductMsg( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
}
