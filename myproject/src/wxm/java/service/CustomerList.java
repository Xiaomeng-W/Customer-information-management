package wxm.java.service;

/**
 * 
 * @author xmwang
 * @Description CustomerList为Customer对象的管理模块，
 * 内部用数组管理一组Customer对象，并提供相应的添加、修改、删除和遍历方法，供CustomerView调用
 * @date 2020年12月18日 09：12
 *
 */

import wxm.java.bean.Customer;

public class CustomerList {
	private Customer[] customers;// 用来保存客户对象的数组
	private int total = 0;// 记录已保存客户对象的数量
	
	/**
	 * 用来初始化customer数组的构造器
	 * @param totalCustomer：用来指定数组的长度
	 */
	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
	}
	
	/**
	 * 
	 * @author xmwang w1239978244@163.com
	 * @Description 将指定的客户添加到数组中
	 * @date 2020年12月18日
	 * @param customer
	 * @return true：添加成功 false：添加失败
	 */
	public boolean addCustomer(Customer customer) {
		if (total >= customers.length) return false;
		
		customers[total++] = customer;
		return true;
	}
	
	/**
	 * 
	 * @author xmwang w1239978244@163.com
	 * @Description 修改指定索引位置的客户信息
	 * @date 2020年12月18日
	 * @param index
	 * @param customer
	 * @return true：修改成功 false：修改失败
	 */
	public boolean replaceCustomer(int index, Customer customer) {
		if (index<0 || index>=total) return false;
		customers[index] = customer;
		return true;
	}
	
	/**
	 * 
	 * @author xmwang w1239978244@163.com
	 * @Description 删除指定索引位置上的客户
	 * @date 2020年12月18日
	 * @param index
	 * @return true：删除成功 false：删除失败
	 */
	public boolean deleteCustomer(int index) {
		if (index<0 || index>=total) return false;
		for (int i=index; i<customers.length-1; i++)
			customers[i] = customers[i+1];
		customers[--total] = null;
		return true;
	}
	
	/**
	 * 
	 * @author xmwang w1239978244@163.com
	 * @Description 获取所有客户的信息
	 * @date 2020年12月18日
	 * @return
	 */
	public Customer[] getAllCustomers() {
		Customer[] custs = new Customer[total];
		for (int i=0; i<total; i++)
			custs[i]= customers[i]; 
		return custs;
	}
	
	/**
	 * 
	 * @author xmwang w1239978244@163.com
	 * @Description 获取指定位置上的客户
	 * @date 2020年12月18日
	 * @param index
	 * @return
	 */
	public Customer getCustomer(int index) {
		if (index<0 || index>=total) return null;
		return customers[index];
	}

	/**
	 * 
	 * @author xmwang w1239978244@163.com
	 * @Description 获取客户的数量
	 * @date 2020年12月18日
	 * @return
	 */
	public int getTotal() {
		return total;
	}
}
