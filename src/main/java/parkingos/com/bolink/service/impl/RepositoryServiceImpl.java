package parkingos.com.bolink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingos.com.bolink.dao.spring.CommonDao;
import parkingos.com.bolink.models.YsDepartmentTb;
import parkingos.com.bolink.models.YsProductTb;
import parkingos.com.bolink.models.YsRepositoryTb;
import parkingos.com.bolink.qo.PageOrderConfig;
import parkingos.com.bolink.service.RepositoryService;
import parkingos.com.bolink.utils.OrmUtil;
import parkingos.com.bolink.utils.RequestUtil;
import parkingos.com.bolink.utils.TimeTools;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    Logger logger = Logger.getLogger( RepositoryServiceImpl.class );

    @Autowired
    private CommonDao commonDao;

    @Override
    public String create(HttpServletRequest request) {
        //接收参数
        Long id = RequestUtil.getLong( request, "id", -1L );
        String product_id = RequestUtil.processParams( request, "product_id" );
        Integer total = RequestUtil.getInteger( request,"total",0 );
        String username = RequestUtil.processParams( request, "username" );
        String create_time = RequestUtil.processParams( request, "create_time" );
        String describe = RequestUtil.processParams( request, "describe" );
        Integer type = RequestUtil.getInteger( request,"type",0 );

        Long createtime = TimeTools.getLongMilliSecondFrom_HHMMDD(create_time) / 1000+24*60*60;
        if(createtime>System.currentTimeMillis()/1000){
            createtime=System.currentTimeMillis()/1000;
        }
        //封装
        YsRepositoryTb ysRepositoryTb = new YsRepositoryTb();
        ysRepositoryTb.setCreateTime( createtime );
        ysRepositoryTb.setProductId( product_id );
        ysRepositoryTb.setTotal( total );
        ysRepositoryTb.setUsername( username );
        ysRepositoryTb.setType( type );
        ysRepositoryTb.setDescribe( describe );
        ysRepositoryTb.setState( 0 );

        int update = 0;
        if (id < 0) {
            //添加操作
            update = commonDao.insert( ysRepositoryTb );
        } else {
            //修改操作
            ysRepositoryTb.setId( id );
            update = commonDao.updateByPrimaryKey( ysRepositoryTb );
        }

        return "{\"state\":" + update + "}";
    }
    @Override
    public String quickquery(HttpServletRequest req) {
        Integer pageNum = RequestUtil.getInteger( req, "page", 1 );
        Integer pageSize = RequestUtil.getInteger( req, "rp", 20 );
        String str = "{\"total\":0,\"page\":1,\"rows\":[]}";
        JSONObject result = JSONObject.parseObject( str );

        YsRepositoryTb ysRepositoryTb = new YsRepositoryTb();
        //state状态 0为正常使用 1为删除状态
        ysRepositoryTb.setState( 0 );

        int count = commonDao.selectCountByConditions( ysRepositoryTb );
        result.put( "total", count );
        if (count > 0) {
            /**分页处理*/
            PageOrderConfig config = new PageOrderConfig();
            config.setPageInfo( pageNum, pageSize );
            List<YsRepositoryTb> list = commonDao.selectListByConditions( ysRepositoryTb, config );
            List<Map<String, Object>> resList = new ArrayList<>();

            YsProductTb ysProductTb = new YsProductTb();
            ysProductTb.setState( 0 );
            List<YsProductTb> productTbs = commonDao.selectListByConditions( ysProductTb );
            if (list != null && !list.isEmpty()) {
                for (YsRepositoryTb sb : list) {
                    OrmUtil<YsRepositoryTb> otm = new OrmUtil<>();
                    Map<String, Object> map = otm.pojoToMap( sb );
                    for(YsProductTb p :productTbs){
                        if(sb.getProductId()!=null&&sb.getProductId().equals( p.getProductId() )){
                            map.put( "color",p.getColor() );
                            map.put( "pname",p.getName() );
                            map.put( "unity",p.getUnity() );
                            map.put( "specifications",p.getSpecifications() );
                            map.put( "product_classify_id",p.getProductClassifyId() );
                        }
                    }
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
            YsRepositoryTb ysRepositoryTb = new YsRepositoryTb();
            ysRepositoryTb.setId( id );
            ysRepositoryTb.setState( 1 );
            //删除操作将state状态修改为1
            delete = commonDao.updateByPrimaryKey( ysRepositoryTb );
        }

        return "{\"state\":" + delete + "}";
    }

    @Override
    public String queryByProduct(HttpServletRequest req) {
        Integer pageNum = RequestUtil.getInteger( req, "page", 1 );
        Integer pageSize = RequestUtil.getInteger( req, "rp", 20 );
        String str = "{\"total\":0,\"page\":1,\"rows\":[]}";
        JSONObject result = JSONObject.parseObject( str );

        YsProductTb ysProductTb = new YsProductTb();
        //state状态 0为正常使用 1为删除状态
        ysProductTb.setState( 0 );

        int count = commonDao.selectCountByConditions( ysProductTb );
        result.put( "total", count );
        if (count > 0) {
            /**分页处理*/
            PageOrderConfig config = new PageOrderConfig();
            config.setPageInfo( pageNum, pageSize );
            List<YsProductTb> list = commonDao.selectListByConditions( ysProductTb, config );
            List<Map<String, Object>> resList = new ArrayList<>();


            if (list != null && !list.isEmpty()) {
                for (YsProductTb sb : list) {

                    YsRepositoryTb repositoryTb = new YsRepositoryTb();
                    repositoryTb.setState( 0 );
                    repositoryTb.setProductId( sb.getProductId() );
                    List<YsRepositoryTb> productTbs = commonDao.selectListByConditions( repositoryTb );

                    OrmUtil<YsProductTb> otm = new OrmUtil<>();
                    Map<String, Object> map = otm.pojoToMap( sb );
                    Integer total = 0;//总数
                    int remain = 0;//剩余
                    int baofei =0;//报废
                    int use =0;//已消耗
                    if(productTbs!=null&&!productTbs.isEmpty()){
                        for(YsRepositoryTb p :productTbs){
                            if(p.getType()==1){
                                total+=p.getTotal();
                            }
                            if(p.getType()==2){
                                use+=p.getTotal();
                            }
                            if(p.getType()==3){
                                baofei+=p.getTotal();
                            }

                        }
                    }
                    remain=total-baofei-use;
                    map.put( "total",total );
                    map.put( "remain",remain );
                    map.put( "baofei",baofei );
                    map.put( "use",use );
                    resList.add( map );
                }
                result.put( "rows", JSON.toJSON( resList ) );
            }
            result.put( "total", count );
            result.put( "page", pageNum );
        }
        return result.toJSONString();
    }
}
