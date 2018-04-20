package com.bestgo.admanager.bean;

import java.lang.reflect.Method;

public class Test {

	public static void main(String[] args) {
		Object a = 1 ;
		Class cla = a.getClass();
		
		Class declaringClass = cla.getDeclaringClass();
		System.out.println("declaringClass="+declaringClass);
		
		if(a instanceof Integer){
			System.out.println("a instanceof Integer = true");
			cla = int.class ;
		}
/*
		Object obj = test(1,"a");
		
		Class class1 = obj.getClass();
		System.out.println(class1);
		*/
		Test t = new Test();
		Class class2 = t.getClass();
		Method[] methods = class2.getDeclaredMethods();
		
		for (Method method : methods) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (Class<?> class1 : parameterTypes) {
				System.out.println("==="+class1);
			}
		}
		
	}
	
	public  Object test(int i, String s){
		
		Object obj = i ;
		
		return obj ;
	}

}
