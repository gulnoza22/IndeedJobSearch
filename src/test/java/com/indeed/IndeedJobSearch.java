package com.indeed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IndeedJobSearch {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
	FileReader reader = new FileReader("jobtitle.txt");
	
	BufferedReader bufReader = new BufferedReader(reader);
	
	ArrayList<String> lst= new ArrayList<String>();
	 
	for (int i = 1; i <=5; i++) {
		  lst.add(bufReader.readLine());
	}
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String url="https://indeed.com";
	driver.get(url);
	
	ArrayList<String>newList=new ArrayList<String>();
		  for (int j = 0; j <lst.size(); j++) {
			driver.findElement(By.id("text-input-what")).clear(); 
		    driver.findElement(By.id("text-input-what")).sendKeys(lst.get(j));
//		    driver.findElement(By.cssSelector(".icl-Button.icl-Button--primary.icl-Button--md.icl-WhatWhere-button")).clear();
			driver.findElement(By.cssSelector(".icl-Button.icl-Button--primary.icl-Button--md.icl-WhatWhere-button")).click();
			
			String count = driver.findElement(By.id("searchCount")).getText();
			String[] strArr = count.split(" ");
	
			
			newList.add(strArr[3]+" "+lst.get(j));
			
			
			driver.navigate().back();
			
		}

	driver.close();
	for (String each : newList) {
		System.out.println("There are "+ each.toString()+" jobs in Roundrock area");
	}
	
	}

}
