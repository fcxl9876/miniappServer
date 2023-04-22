//package xin.fcxl9876.miniappserver.util;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Field;
//import java.net.URLDecoder;
//import java.sql.Connection;
//import java.util.*;
//
///**
// * @ClassName:CommonAuth
// * @Author:dk
// * @Description:和权限有关的公共方法
// * @Date:2022/9/15 9:20
// **/
//
//@Component
//@Slf4j
//public class CommonAuth {
//
////    private static DataSource dataSource= null;
//    @Autowired
//    private IUserService userService;
//
////    public static void setDataSource(DataSource dataSour){
////        dataSource = dataSour;
////    }
//
//    /**
//     * 通过用户id获得用户名，没有返回Null
//     */
//    public  String userIdTOuserName(String userId) {
//        Connection conn = null;
//        try {
////            DataSource ds = dataSource;
////            conn = ds.getConnection();
////            List<Entity> result = SqlExecutor.query(conn, "select user_name  from db_oms_subject.sys_user su where user_id = ? ", new EntityListHandler(), userId);
////            List<Entity> result = Db.use().query("select user_name  from db_oms_subject.sys_user su where user_id = ? ",userId);
//            UserInfoDto userInfo = userService.getUserInfoByUserId(userId);
//            if(userInfo != null){
////                return result.get(0).getStr("user_name");
//                return userInfo.getUserName();
//            }
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    /** 根据集合里面的用户id填入用户名称（当T上有泛型声明的时候，无法使用，因为class无法附加Type,可以从T上直接get）*/
//    /**
//     * @Param list 集合
//     * @Param fieldId 要取出用户Id的字段名
//     * @Param fieldName 要填充用户名称的字段名
//     * @Param clazz
//     **/
//    public   <T> List<T> replaceListIdtoName(List<T> list, String fieldId, String fieldName, Class<?> clazz) {
//        Map<String, String> cache = new HashMap<>();
//        if (clazz == Map.class) {
//            for (T t : list) {
//                Map<String, Object> map = (Map<String, Object>) t;
//                String userId = map.get(fieldId) + "";
//                if (userId == null) {
//                    continue;
//                }
//                if (cache.containsKey(userId)) {
//                    if (cache.get(userId) != null) {
//                        map.put(fieldName, cache.get(userId));
//                    }
//                    continue;
//                }
//                Optional<String> op = Optional.ofNullable(userIdTOuserName(userId));
//                cache.put(userId, op.orElse(null));
//                if (op.isPresent()) {
//                    map.put(fieldName, op.get());
//                }
//            }
//        } else {
//            try {
//                Field field_id = getField(fieldId, clazz);
//                field_id.setAccessible(true);
//                Field field_name = getField(fieldName, clazz);
//                field_name.setAccessible(true);
//                for (T t : list) {
//                    try {
//                        String userId = (String) field_id.get(t);
//                        if (userId == null) {
//                            continue;
//                        }
//                        if (cache.containsKey(userId)) {
//                            if (cache.get(userId) != null) {
//                                field_name.set(t, cache.get(userId));
//                            }
//                            continue;
//                        }
//                        Optional<String> op = Optional.ofNullable(userIdTOuserName(userId));
//                        cache.put(userId, op.orElse(null));
//                        if (op.isPresent()) {
//                            field_name.set(t, op.get());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//                log.warn(e.getMessage());
//            }
//        }
//        return list;
//    }
//
//    private static Field getField(String fieldName, Class<?> clazz) throws NoSuchFieldException {
//        Field field = null;
//        while (field == null) {
//            try {
//                field = clazz.getDeclaredField(fieldName);
//            } catch (NoSuchFieldException e) {
//                clazz = clazz.getSuperclass();
//                if (clazz == Object.class) {
//                    throw new NoSuchFieldException(fieldName + "is not found in " + clazz.getSimpleName() + " and its superClass");
//                }
//            }
//        }
//        return field;
//    }
//
//    public String getUserIdByCookie(){
//        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//        // 获取请求体 request
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        String userInfo = CookiesUtil.getCookieValue(request, "userInfo");
//        if (StringUtils.isBlank(userInfo)){
//            throw new RuntimeException("未登录");
//        }
//        String str = null;
//        try {
//            str = URLDecoder.decode(userInfo, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        if (StringUtils.isBlank(str)) {
//            return null;
//        }
//        UserInfoDto userInfoDto = JSONObject.parseObject(str, UserInfoDto.class);
//        String userId =userInfoDto.getUserId();
//        return userId;
//    }
//    public static void main(String[] args) throws NoSuchFieldException {
//        Map<String,Object> map1 = new HashMap<>();
//        Map<String,Object> map2 = new HashMap<>();
//        List<Map<String,Object>> list = new ArrayList<>();
//
//        map1.put("id","0000");
//        map2.put("id","2362");
//        list.add(map1);
//        list.add(map2);
//
//
////        list = this.replaceListIdtoName(list,"id","name",Map.class);
//
//        System.out.println(list);
//    }
//}
//
//
