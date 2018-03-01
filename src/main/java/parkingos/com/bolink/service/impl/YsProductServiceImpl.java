package parkingos.com.bolink.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parkingos.com.bolink.dao.spring.CommonDao;
import parkingos.com.bolink.models.YsDepartmentTb;
import parkingos.com.bolink.models.YsProductClassifyTb;
import parkingos.com.bolink.models.YsProductTb;
import parkingos.com.bolink.qo.PageOrderConfig;
import parkingos.com.bolink.service.YsProductService;
import parkingos.com.bolink.utils.OrmUtil;
import parkingos.com.bolink.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class YsProductServiceImpl implements YsProductService {

    Logger logger = Logger.getLogger( YsProductServiceImpl.class );

    @Autowired
    private CommonDao commonDao;

    @Override
    public String quickquery(HttpServletRequest req) {
        Integer pageNum = RequestUtil.getInteger( req, "page", 1 );
        Integer pageSize = RequestUtil.getInteger( req, "rp", 20 );
        String str = "{\"total\":0,\"page\":1,\"rows\":[]}";
        JSONObject result = JSONObject.parseObject( str );

         YsProductTb ysProductTb = new YsProductTb();
        //state状态 0为正常使用 1为删除状态
        ysProductTb.setState( 0 );

        int count = commonDao.selectCountByConditions( ysProductTb );
        commonDao.selectSequence( YsProductTb.class );
        result.put( "total", count );
        if (count > 0) {
            /**分页处理*/
            PageOrderConfig config = new PageOrderConfig();
            config.setPageInfo( pageNum, pageSize );
            List<YsProductTb> list = commonDao.selectListByConditions( ysProductTb, config );
            List<Map<String, Object>> resList = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (YsProductTb sb : list) {
                    OrmUtil<YsProductTb> otm = new OrmUtil<>();
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
        String unity = RequestUtil.processParams( request, "unity" );
        Double price = RequestUtil.getDouble( request,"price",0D );
        Long product_classify_id = RequestUtil.getLong( request,"product_classify_id",0L );
        String specifications = RequestUtil.processParams( request, "specifications" );
        String describe = RequestUtil.processParams( request, "describe" );
        String color = RequestUtil.processParams( request, "color" );//产品序列编号

        //封装
        YsProductTb ysProductTb = new YsProductTb();
        ysProductTb.setState( 0 );
        ysProductTb.setName( name );
        ysProductTb.setUnity( unity );
        ysProductTb.setDescribe( describe );
        ysProductTb.setPrice( new BigDecimal( price ) );
        ysProductTb.setSpecifications( specifications );
        ysProductTb.setProductClassifyId( product_classify_id );
        ysProductTb.setColor( color );


        int update = 0;
        if (id < 0) {
            //添加操作
            Long pid = commonDao.selectSequence( ysProductTb.getClass() );
            String productId = "YSP";
            if(pid<10){
                productId+="000"+pid;
            }else if(pid<100){
                productId+="00"+pid;
            }else  if(pid <1000){
                productId+="0"+pid;
            }else{
                productId+=pid;
            }
            ysProductTb.setId( pid );
            ysProductTb.setProductId( productId );
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
            YsProductTb ysProductTb = new YsProductTb();
            ysProductTb.setId( id );
            ysProductTb.setState( 1 );
            //删除操作将state状态修改为1
            delete = commonDao.updateByPrimaryKey( ysProductTb );
        }

        return "{\"state\":" + delete + "}";
    }

    @Override
    public String getProducts(HttpServletRequest request) {
        YsProductTb ysProductTb = new YsProductTb();
        ysProductTb.setState( 0 );
        List<YsProductTb> list = commonDao.selectListByConditions( ysProductTb );
        if(list!=null&&!list.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append( "[" );
            int i = 0;
            for(YsProductTb departmenet:list){
                sb.append( "{\"value\":\""+departmenet.getProductId()+"\",\"value_name\":\""+departmenet.getName()+"\"}" );
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

    @Override
    public String getProductMsg(HttpServletRequest request) {
        String product_id = RequestUtil.processParams( request,"product_id" );
        YsProductTb ysProductTb = new YsProductTb();
        ysProductTb.setProductId( product_id );
        ysProductTb.setState( 0 );

        ysProductTb = (YsProductTb) commonDao.selectObjectByConditions( ysProductTb );

        String str = "{}";
        JSONObject result = JSONObject.parseObject( str );
        if(ysProductTb!=null){
            result.put( "product",ysProductTb );
        }
        return result.toJSONString();
    }
}
