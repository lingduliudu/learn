package com.test.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class TransferData {
	
	@Test
	public void transFile(){
		String fromUrl = "C:/Users/ToT/git/learn/demo\target/";
		String sourceUrl = "E:/eclipse_apache-tomcat-8.5.92/webapps/";
		InputStream inputStream = null;
		OutputStream outStream = null;
		try {
			//先删除原始的数据
			File file = new File(sourceUrl);
			file.deleteOnExit();
			inputStream = new FileInputStream(new File(fromUrl));
			outStream = new FileOutputStream(new File(sourceUrl));
			IOUtils.copy(inputStream, outStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				outStream.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Test
	public void testUrl(){
		String url = "http://www.baidu.com";
		try {
			System.out.println(IOUtils.toString(new URL(url)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
