package xin.fcxl9876.miniappserver.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * 进舍工具类
 * @author Cold
 *
 */
public class ScaleUtil {
	
//	public static void main(String[] args){
//		double a = sciCal(9.8250, 2);
//		System.out.println(a);
//	}
	
	/** 
	 * @param value 需要科学计算的数据 
	 * @param digit 保留的小数位 
	 * @return 
	 * 功能：四舍六入五成双计算法 
	 * 四舍六入五成双的规则：
		1. 被修约的数字小于5时，该数字舍去；
		2. 被修约的数字大于5时，则进位；
		3. 被修约的数字等于5时，要看5前面的数字，若是奇数则进位，若是偶数则将5舍掉，即修约后末尾数字都成为偶数；若5的后面还有不为“0”的任何数，则此时无论5的前面是奇数还是偶数，均应进位。
		举例，用上述规则对下列数据保留2位小数：
		9.8249=9.82, 9.82671=9.83
		9.8350=9.84, 9.8351 =9.84
		9.8250=9.82, 9.82501=9.83
	 */  
	public static Double sciCalSix(Double value, int digit) {
		if (value == null) {
			return null;
		}
		try {
			double ratio = Math.pow(10, digit);
			BigDecimal dea = new BigDecimal(value.toString());
			BigDecimal deb = new BigDecimal(ratio + "");
//			double _num = value * ratio;
			double _num = dea.multiply(deb).doubleValue();
			double mod = _num % 1;
			double integer = Math.floor(_num);
			double returnNum;
			if (mod > 0.5) {
				returnNum = (integer + 1) / ratio;
			} else if (mod < 0.5) {
				returnNum = integer / ratio;
			} else {
				returnNum = (integer % 2 == 0 ? integer : integer + 1) / ratio;
			}
			BigDecimal bg = BigDecimal.valueOf(returnNum);
			return bg.setScale(digit, RoundingMode.HALF_UP).doubleValue();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	* @Title: sciCalFive
	* @Description: 四舍五入
	* @param value
	* @param digit
	* @return    参数
	* @return double    返回类型
	* @throws
	 */
	public static Double sciCalFive(Double value, int digit) {
		return BigDecimal.valueOf(value)
				.setScale(digit, RoundingMode.HALF_UP).doubleValue();
	}
}
