package com.kh.semi.common;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MvcFileRenamePolicy implements FileRenamePolicy {

	/**
	 * D:\\Workspaces\\web_server_workspace\\03_hello_mvc\\WebContent\\upload\\board\\김밥사랑.txt
	 * 
	 * 20211008_145030_999.txt
	 * 
	 */
	@Override
	public File rename(File originalFile) {
		File newFile = null;
		
		do {
			// 확장자 가져오기
			String ext = "";
			String originalFilename = originalFile.getName();
			int dot = originalFilename.lastIndexOf(".");
			if(dot > -1)
				ext = originalFilename.substring(dot); // .txt
			
			// 형식지정
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
			DecimalFormat df = new DecimalFormat("000"); // 30 -> 030
			
			// 새파일명
			String newFilename = sdf.format(new Date()) + df.format(Math.random() * 999) + ext;
			
			// 새 파일객체 생성
			newFile = new File(originalFile.getParent(), newFilename);
			
		} while(!createNewFile(newFile));
		
		return newFile;
	}

	/**
	 * f가 실제경로에 존재하지 않으면 파일 생성후 true리턴
	 * f가 실제경로에 존재하면 파일생성하지 않고, IOException유발, false리턴
	 * @param f
	 * @return
	 */
	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		} catch (IOException ignored) {
			return false;
		}
	}
}
