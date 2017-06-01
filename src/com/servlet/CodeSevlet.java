package com.servlet;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

/**
 *随机验证码
 * Servlet implementation class CodeSevlet
 */
@WebServlet("/CodeSevlet")
public class CodeSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 switch (random.nextInt(2)) {
         case 0:
             cs.setFilterFactory(new DoubleRippleFilterFactory());
             break;
         case 1:
             cs.setFilterFactory(new WobbleRippleFilterFactory());
             break;
         case 2:
             cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
             break;
         case 3:
             cs.setFilterFactory(new MarbleRippleFilterFactory());
             break;
         case 4:
             cs.setFilterFactory(new DiffuseRippleFilterFactory());
             break;
     }
     HttpSession session = request.getSession(false);
     if (session == null) {
         session = request.getSession();
     }
     setResponseHeaders(response);
     String token = EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
     session.setAttribute("captchaToken", token);
	}
	 protected void setResponseHeaders(HttpServletResponse response) {
	        response.setContentType("image/png");
	        response.setHeader("Cache-Control", "no-cache, no-store");
	        response.setHeader("Pragma", "no-cache");
	        long time = System.currentTimeMillis();
	        response.setDateHeader("Last-Modified", time);
	        response.setDateHeader("Date", time);
	        response.setDateHeader("Expires", time);
	    }

    public void crimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
    }

    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

    private static Random random = new Random();

    static {
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(255, 255, 255);
            }
        });
        SingleColorBackgroundFactory backgroundFactory = new SingleColorBackgroundFactory(new Color(9, 122, 221));
        RandomWordFactory wf = new RandomWordFactory();
        wf.setCharacters("234567890");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
        cs.setBackgroundFactory(backgroundFactory);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
