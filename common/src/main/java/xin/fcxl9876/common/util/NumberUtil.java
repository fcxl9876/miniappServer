package xin.fcxl9876.common.util;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * @author shaolay
 * @date 2023/3/16 8:31
 */
public class NumberUtil {


    /**
     * 计算两数百分比 (dividend / divisor * 100)%
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param decimal  保留几位小数
     * @return
     */
    public static String percentage(Number dividend, Number divisor, int decimal) {
        if (divisor.intValue() == 0) {
            divisor = 1;
        }

        return new BigDecimal(dividend.toString())
                .multiply(new BigDecimal(100))
                .divide(new BigDecimal(divisor.toString()), decimal, RoundingMode.HALF_UP) + "%";
    }

    /**
     * 计算增长率
     *
     * @param dividend
     * @param divisor
     * @param decimal
     * @return
     */
    public static String rateOfRise(Number dividend, Number divisor, int decimal) {
        if (divisor.intValue() == 0) {
            divisor = 1;
        }
        if (dividend.intValue() == 0) {
            dividend = 1;
        }
        return new BigDecimal(dividend.toString())
                .subtract(new BigDecimal(divisor.toString()))
                .multiply(new BigDecimal(100))
                .divide(new BigDecimal(divisor.toString()), decimal, BigDecimal.ROUND_HALF_UP)
                .toString() + "%";
    }

    /**
     * 修约 ROUND_HALF_EVEN:四舍六入五成双
     *
     * @param num
     * @param scale
     * @return
     */
    public static String setScale(Object num, int scale, Integer unscaledValue) {
        if (ObjectUtil.isNotNull(num)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(num.toString());
                bigDecimal = bigDecimal.setScale(scale, unscaledValue);
                return bigDecimal.toString();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 计算集合的平均值
     *
     * @param values
     * @param scale
     * @param <T>
     * @return
     */
    public static <T> String calAvg(List<T> values, int scale, Integer unscaledValue) {

        if (ObjectUtil.isNotNull(values) && !values.isEmpty()) {
            Double avg = values.stream().filter(l -> ObjectUtil.isNotNull(l)).mapToDouble(l -> Double.parseDouble(l.toString())).average().getAsDouble();

            if (ObjectUtil.isNotNull(avg)) {
                return setScale(avg, scale, unscaledValue);
            }
        }

        return null;
    }

    /**
     * 判断数据是否在范围内
     *
     * @param value   数值
     * @param upper   上限
     * @param upperEq 是否可取上限
     * @param lower   下限
     * @param lowerEq 是否可取下限
     * @return
     */
    public static boolean inTheRange(Object value, Object upper, int upperEq, Object lower, int lowerEq) {
        boolean flag = false;

        Integer upResult = compareTo(value, upper);
        Integer doResult = compareTo(value, lower);

        if (ObjectUtil.isNotNull(upResult) && ObjectUtil.isNotNull(doResult)) {
            if (upResult == -1 || (upResult == 0 && upperEq == 1)) {
                if (doResult == 1 || (doResult == 0 && lowerEq == 1)) {
                    return true;
                }
            }
        }

        return flag;

    }

    /**
     * 判断
     * v1 > v2 = 1
     * v1 = v2 = 0
     * v1 < v2 = -1
     * 无法判断 返回null
     *
     * @param v1
     * @param v2
     * @return
     */
    public static Integer compareTo(Object v1, Object v2) {
        if (ObjectUtil.isNotNull(v1) && ObjectUtil.isNotNull(v2)) {
            return new BigDecimal(v1.toString()).compareTo(new BigDecimal(v2.toString()));
        }
        return null;
    }

}
