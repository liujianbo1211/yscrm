package parkingos.com.bolink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingos.com.bolink.dao.spring.CommonDao;
import parkingos.com.bolink.models.YsDepartmentTb;
import parkingos.com.bolink.qo.PageOrderConfig;
import parkingos.com.bolink.service.DepartmentService;
import parkingos.com.bolink.utils.OrmUtil;
import parkingos.com.bolink.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    Logger logger = Logger.getLogger( DepartmentServiceImpl.class );

    @Autowired
    private CommonDao commonDao;

    @Override
    public String quickquery(HttpServletRequest req) {
        Integer pageNum = RequestUtil.getInteger( req, "page", 1 );
        Integer pageSize = RequestUtil.getInteger( req, "rp", 20 );
        String str = "{\"total\":0,\"page\":1,\"rows\":[]}";
        JSONObject result = JSONObject.parseObject( str );

        YsDepartmentTb ysDepartmentTb = new YsDepartmentTb();
        //state状态 0为正常使用 1为删除状态
        ysDepartmentTb.setState( 0 );

        int count = commonDao.selectCountByConditions( ysDepartmentTb );
        result.put( "total", count );
        if (count > 0) {
            /**分页处理*/
            PageOrderConfig config = new PageOrderConfig();
            config.setPageInfo( pageNum, pageSize );
            List<YsDepartmentTb> list = commonDao.selectListByConditions( ysDepartmentTb, config );
            List<Map<String, Object>> resList = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (YsDepartmentTb sb : list) {
                    OrmUtil<YsDepartmentTb> otm = new OrmUtil<>();
                    Map<String, Object> map = otm.pojoToMap( sb );
                    resList.add( map );
                }
                result.put( "rows", JSON.toJSON( resList ) );
            }
            result.put( "total", count );
            result.put( "page", pageNum );
        }
        return result.toJSONString();
    }

    @Override
    public String create(HttpServletRequest request) {
        //接收参数
        Long id = RequestUtil.getLong( request, "id", -1L );
        String name = RequestUtil.processParams( request, "name" );
        String person_liable = RequestUtil.processParams( request, "person_liable" );
        String phone = RequestUtil.processParams( request, "phone" );
        String short_number = RequestUtil.processParams( request, "short_number" );
        String describe = RequestUtil.processParams( request, "describe" );

        //封装
       YsDepartmentTb ysDepartmentTb = new YsDepartmentTb();
       ysDepartmentTb.setName( name );
       ysDepartmentTb.setPersonLiable( person_liable );
       ysDepartmentTb.setPhone( phone );
       ysDepartmentTb.setShortNumber( short_number );
       ysDepartmentTb.setDescribe( describe );
       ysDepartmentTb.setState( 0 );

        int update = 0;
        if (id < 0) {
            //添加操作
            update = commonDao.insert( ysDepartmentTb );
        } else {
            //修改操作
            ysDepartmentTb.setId( id );
            update = commonDao.updateByPrimaryKey( ysDepartmentTb );
        }

        return "{\"state\":" + update + "}";
    }

    @Override
    public String delete(HttpServletRequest request) {
        Long id = RequestUtil.getLong( request, "id", -1L );
        int delete = 0;
        if (id > 0) {
            YsDepartmentTb ysDepartmentTb = new YsDepartmentTb();
            ysDepartmentTb.setId( id );
            ysDepartmentTb.setState( 1 );
            //删除操作将state状态修改为1
            delete = commonDao.updateByPrimaryKey( ysDepartmentTb );
        }

        return "{\"state\":" + delete + "}";
    }

    @Override
    public String getDepartments(HttpServletRequest request) {
        YsDepartmentTb ysDepartmentTb = new YsDepartmentTb();
        ysDepartmentTb.setState( 0 );
        List<YsDepartmentTb> list = commonDao.selectListByConditions( ysDepartmentTb );
        if(list!=null&&!list.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append( "[" );
            int i = 0;
            for(YsDepartmentTb departmenet:list){
                sb.append( "{\"value_no\":\""+departmenet.getId()+"\",\"value_name\":\""+departmenet.getName()+"\"}" );
                if(i<list.size()-1){
                    sb.append( "," );
                }else{
                    sb.append( "]" );
                }
                i++;
            }
            return sb.toString();
        }
        return null;
    }
}
