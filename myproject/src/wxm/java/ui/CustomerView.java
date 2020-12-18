package wxm.java.ui;

import wxm.java.bean.Customer;

/**
 * @author xmwang
 * @Description CustomerView为主模块，负责菜单的显示和处理用户操作
 * @date 2020年12月18日
 */

import wxm.java.service.CustomerList;
import wxm.java.util.CMUtility;

public class CustomerView {
	private CustomerList customerList = new CustomerList(10);

	public CustomerView() {

		Customer customer = new Customer("Marry", '女', 23, "13277659870", "Marry@163.com");
		customerList.addCustomer(customer);
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 显示《客户信息管理软件》界面的方法
	 * @date 2020年12月18日
	 */
	public void enterMainMenu() {
		boolean isFlag = true;
		while (isFlag) {
			System.out.println("\n------------------------客户信息管理软件--------------------------");
			System.out.println("                           1. 添加客户");
			System.out.println("                           2. 修改客户");
			System.out.println("                           3. 删除客户");
			System.out.println("                           4. 客户列表");
			System.out.println("                           5. 退   出\n");
			System.out.print("                           请选择（1~5）：");

			char menu = CMUtility.readMenuSelection();
			switch (menu) {
			case '1':
				addCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomer();
				break;
			case '5':
				System.out.print("是否确认退出（Y/N）：");
				char isExit = CMUtility.readConfirmSelection();
				if (isExit == 'Y')
					isFlag = false;
			}

		}
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 添加客户
	 * @date 2020年12月18日
	 */
	public void addCustomer() {
		System.out.println("--------------------------添加客户--------------------------");
		System.out.print("姓名：");
		String name = CMUtility.readString(10);

		System.out.print("性别：");
		char gender = CMUtility.readChar();

		System.out.print("年龄：");
		int age = CMUtility.readInt();

		System.out.print("电话：");
		String phone = CMUtility.readString(13);

		System.out.print("邮箱：");
		String email = CMUtility.readString(30);

		// 将上述数据封装到对象中
		Customer customer = new Customer(name, gender, age, phone, email);
		boolean isSuccess = customerList.addCustomer(customer);
		if (isSuccess)
			System.out.println("--------------------------添加完成--------------------------");
		else
			System.out.println("---------------------客户目录已满，添加失败---------------------");
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 修改客户
	 * @date 2020年12月18日
	 */
	public void modifyCustomer() {
		System.out.println("--------------------------修改客户--------------------------");
		Customer cust;
		int number;
		while (true) {
			System.out.print("请选择待修改客户的编号（-1退出）：");
			number = CMUtility.readInt();
			if (number == -1)
				return;
			cust = customerList.getCustomer(number - 1);
			if (cust == null)
				System.out.println("无法找到客户！");
			else
				break;
		}
		// 修改客户信息
		System.out.print("姓名(" + cust.getName() + "：)");
		String name = CMUtility.readString(10, cust.getName());

		System.out.print("性别(" + cust.getGender() + "：)");
		char gender = CMUtility.readChar(cust.getGender());

		System.out.print("年龄(" + cust.getAge() + "：)");
		int age = CMUtility.readInt(cust.getAge());

		System.out.print("电话(" + cust.getPhone() + "：)");
		String phone = CMUtility.readString(13, cust.getPhone());

		System.out.print("邮箱(" + cust.getEmail() + "：)");
		String email = CMUtility.readString(30, cust.getEmail());

		Customer customer = new Customer(name, gender, age, phone, email);
		boolean isReplaced = customerList.replaceCustomer(number - 1, customer);
		if (isReplaced)
			System.out.println("--------------------------修改完成--------------------------");
		else
			System.out.println("--------------------------修改失败--------------------------");
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 删除客户
	 * @date 2020年12月18日
	 */
	public void deleteCustomer() {
		System.out.println("--------------------------删除客户--------------------------");
		System.out.print("选择待删除客户的编号（-1退出）：");
		Customer customer;
		int number;
		while (true) {
			number = CMUtility.readInt();
			if (number == -1)
				return;
			customer = customerList.getCustomer(number - 1);
			if (customer == null)
				System.out.println("无法找到客户！");
			else
				break;
		}
		System.out.println("确认是否删除（Y/N）：");
		char isDelete = CMUtility.readConfirmSelection();
		if (isDelete == 'Y') {
			boolean deleteSuccess = customerList.deleteCustomer(number - 1);
			if (deleteSuccess)
				System.out.println("--------------------------删除成功--------------------------");
			else
				System.out.println("--------------------------删除失败--------------------------");
		}
	}

	/**
	 * 
	 * @author xmwang
	 * @Description 显示客户列表的操作
	 * @date 2020年12月18日
	 */
	public void listAllCustomer() {
		System.out.println("--------------------------客户列表----------------------------");
		int total = customerList.getTotal();
		if (total == 0)
			System.out.println("没有客户记录！");
		else {
			System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			Customer[] custs = customerList.getAllCustomers();
			for (int i = 0; i < custs.length; i++)
				System.out.println((i + 1) + "\t" + custs[i].getName() + "\t" + custs[i].getGender() + "\t"
						+ custs[i].getAge() + "\t" + custs[i].getPhone() + "\t" + custs[i].getEmail());

		}
		System.out.println("------------------------客户列表完成---------------------------");

	}

	public static void main(String[] args) {
		CustomerView view = new CustomerView();
		view.enterMainMenu();
	}
}
