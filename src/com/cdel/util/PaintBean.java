package com.cdel.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.cdel.advc.common.JsfHelper;

@ManagedBean
@SuppressWarnings("serial")
public class PaintBean implements Serializable {
	private StreamedContent image;
	Font textFont = new Font("Comic Sans ms", Font.PLAIN, 18);
	String str = "0,2,5,7,1,9,4,6,8,3";
	Random random = new Random();

	public PaintBean() throws IOException {
		PaintData data = new PaintData();
		Integer width = data.getWidth();
		Integer height = data.getHeight();
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = img.getGraphics();
		graphics.setColor(data.getBackground());
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(data.getDrawColor());
		graphics.drawRect(0, 0, width - 1, height - 1);
		graphics.setFont(textFont);
		// 绘制干扰线
		for (int i = 0; i < 160; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.setColor(getRandColor(100, 250));
			graphics.drawLine(x, y, x + xl, y + yl);
		}
		// 绘制验证码
		graphics.setColor(((PaintData) data).getDrawColor());
		String code = "";
		String[] array = str.split(",");
		for (int i = 0; i < 4; i++) {
			String rand = array[random.nextInt(array.length)];
			code += rand;
			graphics.drawString(rand, 13 * i + 6, 16);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession ses = JsfHelper.getSession(context);
		ses.setAttribute(Contants.YZ, code);
		// 开释此图形的高低文以及它应用的所有体系资料，类似于封闭流
		graphics.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(img, "jpeg", imageOut);
		imageOut.close();
		InputStream stream = new ByteArrayInputStream(output.toByteArray());
		image = new DefaultStreamedContent(stream, "image/jpeg");
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public StreamedContent getImage() {
		return image;
	}

}
