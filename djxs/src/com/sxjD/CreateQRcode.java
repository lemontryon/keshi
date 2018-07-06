package com.sxjD;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRcode {
	public static void main(String[] args) throws IOException {
		Qrcode qrcode=new Qrcode();
		qrcode.setQrcodeErrorCorrect('H');                                                                                                                                    
		qrcode.setQrcodeEncodeMode('B');
		qrcode.setQrcodeVersion(6);
		String url="http://www.baidu.com";
		byte[] data=url.getBytes();
		boolean [][] qrdata=qrcode.calQrcode(data);
		int imageSize=67+12*(6-1);
		//int height=67+12*(6-1);
		int pixoff=4;
	
		//设置图片缓冲
		BufferedImage bufferedImage=new BufferedImage(imageSize,imageSize,BufferedImage.TYPE_INT_RGB);
		//图片绘画
		Graphics2D gs=bufferedImage.createGraphics();
		//设置背景色
		gs.setBackground(Color.PINK);
		//清除画布
		gs.clearRect(0,0,imageSize,imageSize);
		//二维码绘画
		
		int startR=255,startG=0,startB=0;
		int endR=0,endG=0,endB=255;
		for(int i=0;i<qrdata.length;i++){
			for(int j=0;j<qrdata.length;j++){
				if(qrdata[i][j]){
					/*Random rand=new Random();
					int num1=rand.nextInt(255);
					int num2=rand.nextInt(255);
					int num3=rand.nextInt(255);*/

					int num1=startR+(endR-startR)*(i+1)/qrdata.length;
					int num2=startG+(endG-startG)*(i+1)/qrdata.length;
					int num3=startB+(endB-startB)*(i+1)/qrdata.length;
					Color color=new Color(num1,num2,num3);
					gs.setColor(color);
					gs.fillRect(i*3+pixoff/2, j*3+pixoff/2, 3, 3);
				}
			}
		}
		BufferedImage logo=ImageIO.read(new File("D:/logo.jpg"));
		int logoSize=imageSize/4;
		int o=(imageSize-logoSize)/2;
		gs.drawImage(logo, o, o, logoSize, logoSize,null);
		gs.dispose();
		bufferedImage.flush();
		try{
			ImageIO.write(bufferedImage,"png",new File("D:/qrcode.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("产生了问题");
		}
		System.out.println("我们胜利了！");
	}

}
