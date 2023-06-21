package javaTester;

import java.util.Random;

public class Topic_05_Random_Email {

	public static void main(String[] args) {
		// Utitities = tiện ích
		//Data Type: Class/Interface / Collection/ String/ Float/ ..
		Random rand = new Random();
		System.out.println(rand.nextFloat()); // kiểu dữ liệu - k dùng tạo mail
		System.out.println(rand.nextDouble());// kiểu dữ liệu - k dùng tạo mail
		System.out.println(rand.nextInt(9999));// kiểu dữ liệu - k dùng tạo mail - 9999: muốn số chạy từ 0 đến 9999
		System.out.println("automation" + rand.nextInt(9999) + "@gmail.com");// kiểu dữ liệu - k dùng tạo mail
		System.out.println(rand.nextLong());// kiểu dữ liệu - k dùng tạo mail
	

	}

}
