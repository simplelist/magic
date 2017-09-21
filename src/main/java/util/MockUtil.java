package util;
import com.kun.domain.Employee;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;
/**
 * Copyright (C), 2011-2017 温州贷
 * FileName: util.MockUtil.java
 * Author: shijikun
 * Email: shijikun@wzdai.com
 * Date: 21/09/2017 17:33
 * Description:
 * History:
 */
public class MockUtil {
	private static Random random = new Random();
	/**
	 * 根据字段类型,设置随机值
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T mockIt(Class<T> clazz) {
		try {
			T obj = (T) Class.forName(clazz.getName()).newInstance();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Method method = clazz.getDeclaredMethod("set" + StrUtil.upperFirst(field.getName()), field.getType());
				method.invoke(obj, getValue(field.getType()));
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Object getValue(Class<?> type) {
		String typeStr = type.toString();
		if ("class java.lang.Integer".equalsIgnoreCase(typeStr)) {
			return RandomUtil.randomInt();
		} else if ("class java.lang.String".equalsIgnoreCase(typeStr)) {
			return RandomUtil.randomString(8);
		} else if ("class java.lang.Float".equalsIgnoreCase(typeStr)) {
			return random.nextFloat();
		} else if ("class java.lang.Double".equalsIgnoreCase(typeStr)) {
			return random.nextDouble();
		}
		return null;
	}

	public static void main(String args[]) throws Exception {
		System.out.println(mockIt(Employee.class));
	}
}
