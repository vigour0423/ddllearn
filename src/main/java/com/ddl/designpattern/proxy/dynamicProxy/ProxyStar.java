package com.ddl.designpattern.proxy.dynamicProxy;

/**
 * 模拟动态生成的代理的结构
 * @author Administrator
 *
 */
public class ProxyStar implements Star {
	
	StarHandler handler;
	
	public ProxyStar(StarHandler handler) {
		super();
		this.handler = handler;
	}

	@Override
	public void bookTicket() {
//		handler.invoke(this,当前方法 , args);
	}

	@Override
	public void collectMoney() {
//		handler.invoke(this,当前方法 , args);
	}

	@Override
	public void confer() {
//		handler.invoke(this,当前方法 , args);
	}

	@Override
	public void signContract() {
//		handler.invoke(this,当前方法 , args);
	}

	@Override
	public void sing() {
//		handler.invoke(this,当前方法 , args);
	}

}
