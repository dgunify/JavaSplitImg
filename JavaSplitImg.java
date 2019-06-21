import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JavaSplitImg {
	private redUrl = "E:\\images";//需要分割图片所在路径
	private saveUrl = "E:\\img";//分割后保存路径
	public static void main(String[] args) {
		try {
			//读取文件路径
			File fileDir = new File(redUrl);
		    if (null != fileDir && fileDir.isDirectory()) {
		        File[] files = fileDir.listFiles();
		        if (null != files) {
		            for (int i = 0; i < files.length; i++) {
		                // 如果是文件夹 继续读取
		                if (1==1) {
		                    File f = files[i];
		                    splitImage(f.getPath());
		                }
		            }
		        }
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void splitImage(String fileurl) throws IOException {
        String originalImg = fileurl;
        // 读入大图
        File file = new File(originalImg);
        FileInputStream fis = new FileInputStream(file);
        BufferedImage image = ImageIO.read(fis);
		//需要分割成几份
		int cols = 2;
        // 宽度除份
        int chunkWidth = image.getWidth() / cols;
		//高度不变
        int chunkHeight = image.getHeight();
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[cols];
        for (int i = 0; i < imgs.length; i++) {
        	//设置小图的大小和类型
            imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
            //写入图像内容
            Graphics2D gr = imgs[count++].createGraphics();
            gr.drawImage(image, 0, 0,chunkWidth,chunkHeight,chunkWidth*i,0,chunkWidth*i+chunkWidth,chunkHeight,null);
            gr.dispose();
		}
        //保存图
        for (int i = 0; i < imgs.length; i++) {
        	//设置输出路径
            ImageIO.write(imgs[i], "jpg", new File(saveUrl +"\\"+ System.currentTimeMillis() + ".jpg"));
        }
        System.out.println("完成分割！");
	}
}
