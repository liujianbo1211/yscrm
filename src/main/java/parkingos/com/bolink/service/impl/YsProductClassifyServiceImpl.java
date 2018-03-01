package parkingos.com.bolink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingos.com.bolink.dao.spring.CommonDao;
import parkingos.com.bolink.models.YsDepartmentTb;
import parkingos.com.bolink.models.YsProductClassifyTb;
import parkingos.com.bolink.qo.PageOrderConfig;
import parkingos.com.bolink.service.YsProductClassifyService;
import parkingos.com.bolink.utils.OrmUtil;
import parkingos.com.bolink.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YsProductClassifyServiceImpl implements YsProductClassifyService {
    Logger logger = Logger.getLogger( YsProductClassifyServiceImpl.class );

    @Autowired
    private CommonDao commonDao;
    @Override
    public String quickquery(HttpServletRequest req) {
        Integer pageNum = RequestUtil.getInteger( req, "page", 1 );
        Integer pageSize = RequestUtil.getInteger( req, "rp", 20 );
        String str = "{\"total\":0,\"page\":1,\"rows\":[]}";
        JSONObject result = JSONObject.parseObject( str );

        YsProductClassifyTb ysProductClassifyTb = new YsProductClassifyTb();
        //state状态 0为正常使用 1为删除状态
        ysProductClassifyTb.setState( 0 );

        int count = commonDao.selectCountByConditions( ysProductClassifyTb );
        result.put( "total", count );
        if (count > 0) {
            /**分页处理*/
            PageOrderConfig config = new PageOrderConfig();
            config.setPageInfo( pageNum, pageSize );
            List<YsProductClassifyTb> list = commonDao.selectListByConditions( ysProductClassifyTb, config );
            List<Map<String, Object>> resList = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (YsProductClassifyTb sb : list) {
                    OrmUtil<YsProductClassifyTb> otm = new OrmUtil<>();
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
        String describe = RequestUtil.processParams( request, "describe" );

        //封装
        YsProductClassifyTb ysProductTb = new YsProductClassifyTb();
        ysProductTb.setName( name );
        ysProductTb.setDescribe( describe );
        ysProductTb.setState( 0 );

        int update = 0;
        if (id < 0) {
            //添加操作
            update = commonDao.insert( ysProductTb );
        } else {
            //修改操作
            ysProductTb.setId( id );
            update = commonDao.updateByPrimaryKey( ysProductTb );
        }

        return "{\"state\":" + update + "}";
    }

    @Override
    public String delete(HttpServletRequest request) {
        Long id = RequestUtil.getLong( request, "id", -1L );
        int delete = 0;
        if (id > 0) {
            YsProductClassifyTb ysProductTb = new YsProductClassifyTb();
            ysProductTb.setId( id );
            ysProductTb.setState( 1 );
            //删除操作将state状态修改为1
            delete = commonDao.updateByPrimaryKey( ysProductTb );
        }

        return "{\"state\":" + delete + "}";
    }

    @Override
    public String getDepartments(HttpServletRequest request) {
        YsProductClassifyTb ysDepartmentTb = new YsProductClassifyTb();
        ysDepartmentTb.setState( 0 );
        List<YsProductClassifyTb> list = commonDao.selectListByConditions( ysDepartmentTb );
        if(list!=null&&!list.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append( "[" );
            int i = 0;
            for(YsProductClassifyTb departmenet:list){
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
