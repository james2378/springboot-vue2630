


















package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 图书
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tushu")
public class TushuController {
    private static final Logger logger = LoggerFactory.getLogger(TushuController.class);

    @Autowired
    private TushuService tushuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("tushuDeleteStart",1);params.put("tushuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = tushuService.queryPage(params);

        //字典表数据转换
        List<TushuView> list =(List<TushuView>)page.getList();
        for(TushuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TushuEntity tushu = tushuService.selectById(id);
        if(tushu !=null){
            //entity转view
            TushuView view = new TushuView();
            BeanUtils.copyProperties( tushu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody TushuEntity tushu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tushu:{}",this.getClass().getName(),tushu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<TushuEntity> queryWrapper = new EntityWrapper<TushuEntity>()
            .eq("tushu_uuid_number", tushu.getTushuUuidNumber())
            .eq("tushu_name", tushu.getTushuName())
            .eq("tushu_types", tushu.getTushuTypes())
            .eq("shujia_types", tushu.getShujiaTypes())
            .eq("tushu_kucun_number", tushu.getTushuKucunNumber())
            .eq("tushu_delete", tushu.getTushuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TushuEntity tushuEntity = tushuService.selectOne(queryWrapper);
        if(tushuEntity==null){
            tushu.setTushuDelete(1);
            tushu.setInsertTime(new Date());
            tushu.setCreateTime(new Date());
            tushuService.insert(tushu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TushuEntity tushu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tushu:{}",this.getClass().getName(),tushu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<TushuEntity> queryWrapper = new EntityWrapper<TushuEntity>()
            .notIn("id",tushu.getId())
            .andNew()
            .eq("tushu_uuid_number", tushu.getTushuUuidNumber())
            .eq("tushu_name", tushu.getTushuName())
            .eq("tushu_types", tushu.getTushuTypes())
            .eq("shujia_types", tushu.getShujiaTypes())
            .eq("tushu_kucun_number", tushu.getTushuKucunNumber())
            .eq("tushu_delete", tushu.getTushuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TushuEntity tushuEntity = tushuService.selectOne(queryWrapper);
        if("".equals(tushu.getTushuPhoto()) || "null".equals(tushu.getTushuPhoto())){
                tushu.setTushuPhoto(null);
        }
        if(tushuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      tushu.set
            //  }
            tushuService.updateById(tushu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<TushuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            TushuEntity tushuEntity = new TushuEntity();
            tushuEntity.setId(id);
            tushuEntity.setTushuDelete(2);
            list.add(tushuEntity);
        }
        if(list != null && list.size() >0){
            tushuService.updateBatchById(list);
        }
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<TushuEntity> tushuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            TushuEntity tushuEntity = new TushuEntity();
//                            tushuEntity.setTushuUuidNumber(data.get(0));                    //图书编号 要改的
//                            tushuEntity.setTushuName(data.get(0));                    //图书名称 要改的
//                            tushuEntity.setTushuPhoto("");//照片
//                            tushuEntity.setTushuTypes(Integer.valueOf(data.get(0)));   //图书类型 要改的
//                            tushuEntity.setShujiaTypes(Integer.valueOf(data.get(0)));   //书架 要改的
//                            tushuEntity.setTushuKucunNumber(Integer.valueOf(data.get(0)));   //图书库存 要改的
//                            tushuEntity.setTushuDelete(1);//逻辑删除字段
//                            tushuEntity.setTushuContent("");//照片
//                            tushuEntity.setInsertTime(date);//时间
//                            tushuEntity.setCreateTime(date);//时间
                            tushuList.add(tushuEntity);


                            //把要查询是否重复的字段放入map中
                                //图书编号
                                if(seachFields.containsKey("tushuUuidNumber")){
                                    List<String> tushuUuidNumber = seachFields.get("tushuUuidNumber");
                                    tushuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tushuUuidNumber = new ArrayList<>();
                                    tushuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tushuUuidNumber",tushuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //图书编号
                        List<TushuEntity> tushuEntities_tushuUuidNumber = tushuService.selectList(new EntityWrapper<TushuEntity>().in("tushu_uuid_number", seachFields.get("tushuUuidNumber")).eq("tushu_delete", 1));
                        if(tushuEntities_tushuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TushuEntity s:tushuEntities_tushuUuidNumber){
                                repeatFields.add(s.getTushuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [图书编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tushuService.insertBatch(tushuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
