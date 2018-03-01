package parkingos.com.bolink.actions;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import parkingos.com.bolink.service.DepartmentService;
import parkingos.com.bolink.service.YsProductClassifyService;
import parkingos.com.bolink.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ysproductclassify")
public class YsProducClassifytAction {
    Logger logger = Logger.getLogger( YsProducClassifytAction.class );

    @Autowired
    private YsProductClassifyService ysProductService;

    /**
     * 部门查询
     */
    @RequestMapping("/query")
    public String query(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println( "111" );
        String result = ysProductService.quickquery( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
    /**
     * 部门创建
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

    @RequestMapping("/getProductClassity")
    public String getDepartments(HttpServletRequest request, HttpServletResponse resp) {
        String result = ysProductService.getDepartments( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
}
