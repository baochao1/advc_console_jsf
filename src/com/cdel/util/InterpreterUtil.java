package com.cdel.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import bsh.EvalError;
import bsh.Interpreter;

public class InterpreterUtil {

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * 检查表达式是否正确
	 * 
	 * @param exp
	 * @return
	 */
	public boolean checkExp(String exp) {
		Interpreter interpreter = new Interpreter();
		try {
			interpreter.eval(exp);
			return true;
		} catch (Exception e) {
			logger.error("表达式错误");
			return false;
		}
	}

	/**
	 * 执行表达式
	 * 
	 * @param exp
	 */
	public Boolean exeExpReBoolean(String exp) {
		Interpreter interpreter = new Interpreter();
		try {
			interpreter.eval("a=" + exp);
			return (Boolean) interpreter.get("a");
		} catch (Exception e) {
			logger.error("exeExpReBoolean表达式计算错误");
			return null;
		}
	}

	/**
	 * @param args
	 * @throws EvalError
	 */
	public static void main(String[] args) {
		try {
			Interpreter interpreter = new Interpreter();
			interpreter.set("a1", 11);
			interpreter.set("a2", 2);
			interpreter.set("a3", 4);
			interpreter.set("a4", 3);
			interpreter.eval("a=a1+a2+a3+a4");
			interpreter.eval("a2=true||(true&&false)");
			System.out.println(interpreter.get("a"));
			System.out.println(interpreter.get("a2"));
		} catch (EvalError e) {
			System.out.println("表达式错误");
			e.printStackTrace();
		}
	}

}
