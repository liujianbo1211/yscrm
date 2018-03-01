package parkingos.com.bolink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingos.com.bolink.dao.spring.CommonDao;
import parkingos.com.bolink.models.YsCustomerTb;
import parkingos.com.bolink.models.YsDepartmentTb;
import parkingos.com.bolink.qo.PageOrderConfig;
import parkingos.com.bolink.service.CustomerService;
import parkingos.com.bolink.utils.OrmUtil;
import parkingos.com.bolink.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = Logger.getLogger( CustomerServiceImpl.class );

    @Autowired
    private CommonDao commonDao;

    @Override
    public String add(HttpServletRequest request) {
        //接收参数
        Long id = RequestUtil.getLong( request, "id", -1L );
        String name = RequestUtil.processParams( request, "name" );
        String address = RequestUtil.processParams( request, "address" );
        String qq = RequestUtil.processParams( request, "qq" );
        String phone = RequestUtil.processParams( request, "phone" );
        String email = RequestUtil.processParams( request, "email" );
        String describe = RequestUtil.processParams( request, "describe" );
        String main = RequestUtil.processParams( request, "main" );
        Integer state = 0;

        //封装
        YsCustomerTb ysCustomerTb = new YsCustomerTb();
        ysCustomerTb.setName( name );
        ysCustomerTb.setAddress( address );
        ysCustomerTb.setQq( qq );
        ysCustomerTb.setPhone( phone );
        ysCustomerTb.setEmail( email );
        ysCustomerTb.setDescribe( describe );
        ysCustomerTb.setState( state );

        int update = 0;
        if (id < 0) {
            //添加操作
            ysCustomerTb.setBeginTime( System.currentTimeMillis()/1000 );
            update = commonDao.insert( ysCustomerTb );
        } else {
            //修改操作
            ysCustomerTb.setId( id );
            update = commonDao.updateByPrimaryKey( ysCustomerTb );
        }

        return "{\"state\":" + update + "}";
    }

    @Override
    public String quickquery(HttpServletRequest req) {
        Integer pageNum = RequestUtil.getInteger( req, "page", 1 );
        Integer pageSize = RequestUtil.getInteger( req, "rp", 20 );
        String str = "{\"total\":0,\"page\":1,\"rows\":[]}";
        JSONObject result = JSONObject.parseObject( str );

        YsCustomerTb ysCustomerTb = new YsCustomerTb();
        //state状态 0为正常使用 1为删除状态
        ysCustomerTb.setState( 0 );

        int count = commonDao.selectCountByConditions( ysCustomerTb );
        result.put( "total", count );
        if (count > 0) {
            /**分页处理*/
            PageOrderConfig config = new PageOrderConfig();
            config.setPageInfo( pageNum, pageSize );
            List<YsCustomerTb> list = commonDao.selectListByConditions( ysCustomerTb, config );
            List<Map<String, Object>> resList = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (YsCustomerTb sb : list) {
                    OrmUtil<YsCustomerTb> otm = new OrmUtil<>();
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
    public String delete(HttpServletRequest request) {
        Long id = RequestUtil.getLong( request, "id", -1L );
        int delete = 0;
        if (id > 0) {
            YsCustomerTb ysCustomerTb = new YsCustomerTb();
            ysCustomerTb.setId( id );
            ysCustomerTb.setState( 1 );
            //删除操作将state状态修改为1
            delete = commonDao.updateByPrimaryKey( ysCustomerTb );
        }

        return "{\"state\":" + delete + "}";
    }
}
