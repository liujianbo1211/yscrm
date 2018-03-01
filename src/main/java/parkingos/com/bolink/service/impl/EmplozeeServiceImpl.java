package parkingos.com.bolink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingos.com.bolink.dao.spring.CommonDao;
import parkingos.com.bolink.models.YsDepartmentTb;
import parkingos.com.bolink.models.YsEmployeeTb;
import parkingos.com.bolink.qo.PageOrderConfig;
import parkingos.com.bolink.service.EmplozeeService;
import parkingos.com.bolink.utils.OrmUtil;
import parkingos.com.bolink.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmplozeeServiceImpl implements EmplozeeService {

    Logger logger = Logger.getLogger( EmplozeeServiceImpl.class );

    @Autowired
    private CommonDao commonDao;

    @Override
    public String create(HttpServletRequest request) {
        //接收参数
        Long id = RequestUtil.getLong( request, "id", -1L );
        String name = RequestUtil.processParams( request, "name" );
        String address = RequestUtil.processParams( request, "address" );
        String card_id = RequestUtil.processParams( request, "card_id" );
        String phone = RequestUtil.processParams( request, "phone" );
        Integer department_id = RequestUtil.getInteger( request,"department_id",0 );
        BigDecimal salary = new BigDecimal( RequestUtil.getDouble( request,"salary",0D ) );
        String short_number = RequestUtil.processParams( request, "short_number" );
        Integer isleave = RequestUtil.getInteger( request,"isleave",0 );
        Long begin_time = RequestUtil.getLong( request,"begin_time",0L );
        Long end_time = RequestUtil.getLong( request,"end_time",0L );
        String leaving_reason = RequestUtil.processParams( request,"leaving_reason" );
        String describe = RequestUtil.processParams( request,"describe" );
        //封装
        YsEmployeeTb ysEmployeeTb = new YsEmployeeTb();
        ysEmployeeTb.setName( name );
        ysEmployeeTb.setAddress( address );
        ysEmployeeTb.setCardId( card_id );
        ysEmployeeTb.setPhone( phone );
        ysEmployeeTb.setDepartmentId( department_id );
        ysEmployeeTb.setSalary( salary );
        ysEmployeeTb.setShortNumber( short_number );
        ysEmployeeTb.setIsleave( isleave );
        if(begin_time>0){
            ysEmployeeTb.setBeginTime( begin_time/1000 );
        }
        if(end_time>0){
            ysEmployeeTb.setEndTime( end_time/1000 );
        }
        ysEmployeeTb.setLeavingReason( leaving_reason );
        ysEmployeeTb.setDescribe( describe );
        ysEmployeeTb.setState( 0 );

        int update = 0;
        if (id < 0) {
            //添加操作
            update = commonDao.insert( ysEmployeeTb );
        } else {
            //修改操作
            ysEmployeeTb.setId( id );
            update = commonDao.updateByPrimaryKey( ysEmployeeTb );
        }

        return "{\"state\":" + update + "}";
    }

    @Override
    public String quickquery(HttpServletRequest req) {
        Integer pageNum = RequestUtil.getInteger( req, "page", 1 );
        Integer pageSize = RequestUtil.getInteger( req, "rp", 20 );
        String str = "{\"total\":0,\"page\":1,\"rows\":[]}";
        JSONObject result = JSONObject.parseObject( str );

        YsEmployeeTb ysEmployeeTb = new YsEmployeeTb();
        //state状态 0为正常使用 1为删除状态
        ysEmployeeTb.setState( 0 );

        int count = commonDao.selectCountByConditions( ysEmployeeTb );
        result.put( "total", count );
        if (count > 0) {
            /**分页处理*/
            PageOrderConfig config = new PageOrderConfig();
            config.setPageInfo( pageNum, pageSize );
            List<YsEmployeeTb> list = commonDao.selectListByConditions( ysEmployeeTb, config );
            List<Map<String, Object>> resList = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (YsEmployeeTb sb : list) {
                    OrmUtil<YsEmployeeTb> otm = new OrmUtil<>();
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
        System.out.println( id );
        if (id > 0) {
            YsEmployeeTb ysEmployeeTb = new YsEmployeeTb();
            ysEmployeeTb.setId( id );
            ysEmployeeTb.setState( 1 );
            //删除操作将state状态修改为1
            delete = commonDao.updateByPrimaryKey( ysEmployeeTb );
        }

        return "{\"state\":" + delete + "}";
    }
}
