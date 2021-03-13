package bookstore.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtil {
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            BeanUtils.populate(bean, map);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String s, int defaultValue){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
            return defaultValue;
        }


    }
}
