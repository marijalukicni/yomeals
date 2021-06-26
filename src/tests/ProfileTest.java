package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 1)
	public void editProfileTest() throws InterruptedException {
		driver.get(baseUrl + "guest-user/login-form");
		Thread.sleep(1000);
		this.locationPopUpPage.closeDialog();
		this.loginPage.login(email, password);
		Thread.sleep(500);
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("Login Successfull"), "[ERROR] Unexpected login message");

		driver.get(baseUrl + "member/profile");
		this.profilePage.changePersonalInformation("Robert", "Jones", "111 Maryland Ave.", "212-352-3212", "20850", "United States", "Maryland", "Rockville");
		Thread.sleep(1000);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Setup Successful"), "[ERROR] Unexpected setup message");

		this.authPage.logout();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successfull"), "[ERROR] Unexpected logout message");
		
	}
	
	@Test(priority = 2)
	public void changeImgTest() throws IOException, InterruptedException {
		
		driver.get(baseUrl + "guest-user/login-form");
		Thread.sleep(1000);
		this.locationPopUpPage.closeDialog();
		this.loginPage.login(email, password);
		Thread.sleep(500);
		Assert.assertTrue(this.notificationSystemPage.getMessageText().contains("Login Successfull"), "[ERROR] Unexpected login message");
		
		driver.get(baseUrl + "member/profile");
		String imgPath = new File("img/ITBootcamp.png").getCanonicalPath();
		this.profilePage.uploadImg(imgPath);
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Uploaded Successfully"), "[ERROR] Upload image failed");
		this.notificationSystemPage.waitUntilMessageDisappears();
		
		this.profilePage.removeImage();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Deleted Successfully"), "[ERROR] Delete image failed");
		this.notificationSystemPage.waitUntilMessageDisappears();
		
		this.authPage.logout();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successfull"), "[ERROR] Unexpected logout message");
		this.notificationSystemPage.waitUntilMessageDisappears();
		
	}
}