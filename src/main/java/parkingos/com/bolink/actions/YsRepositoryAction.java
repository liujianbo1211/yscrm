package parkingos.com.bolink.actions;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import parkingos.com.bolink.service.DepartmentService;
import parkingos.com.bolink.service.RepositoryService;
import parkingos.com.bolink.utils.RequestUtil;
import parkingos.com.bolink.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/repository")
public class YsRepositoryAction {

    Logger logger = Logger.getLogger( YsRepositoryAction.class );

    @Autowired
    private RepositoryService repositoryService;


    /**
     * 创建
     */
    @RequestMapping("/create")
    public String create(HttpServletRequest req, HttpServletResponse resp) {

        String result = repositoryService.create( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result.toString() );
        return null;
    }
    /**
     * 部门查询
     */
    @RequestMapping("/query")
    public String query(HttpServletRequest req, HttpServletResponse resp) {
        String result = repositoryService.quickquery( req );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse resp) {
        String result = repositoryService.delete( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }

    @RequestMapping("/queryproduct")
    public String queryByProduct(HttpServletRequest request, HttpServletResponse resp) {
        String result = repositoryService.queryByProduct( request );
        logger.info( result );
        StringUtils.ajaxOutput( resp, result );
        return null;
    }
}
