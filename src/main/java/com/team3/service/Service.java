package com.team3.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.team3.admin.map.dao.AdminMapDao;
import com.team3.admin.map.dto.MapDto;
import com.team3.aop.LogAspect;
import com.team3.user.member.dao.MemberDao;
import com.team3.user.member.dto.MemberDto;

@Component
public class Service implements ServiceInterface {

	@Autowired
	private JavaMailSender mailSender; // email

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private AdminMapDao adminMapDao;

	@Override
	public String newsfeedParsing(HttpServletRequest request, HttpServletResponse response) {
		String url = "http://rss.donga.com/book.xml";

		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);

		int statusCode;

		try {
			statusCode = client.executeMethod(method);

			if (statusCode == HttpStatus.SC_OK) {
				String res = method.getResponseBodyAsString();
				PrintWriter out = response.getWriter();
				out.print(res);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void searchPwd(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		int authNum = (int) (Math.random() * 999999) + 100000;

		String email = req.getParameter("email") + "@" + req.getParameter("emailAddress");
		// System.out.println(email);

		// 메일 내용을 작성한다
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("miy003@naver.com");
		msg.setTo(email);
		msg.setSubject("㈜산책 이메일 인증번호");
		msg.setText("귀하의 이메일 인증번호는 " + authNum + "입니다.");

		try {
			mailSender.send(msg);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		mav.addObject("authNum", authNum);
		mav.setViewName("searchPwd.empty");
	}

	@Override
	public void memberLoginOK(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		LogAspect.logger.info("로그인시작:" + id + "\t" + password);

		/* MemberDto memberDto=memberDao. */
	}

	@Override
	public void zipcode(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String dong = request.getParameter("dong");

		if (dong != null) {
			// List<ZipcodeDto> zipList=memberDao.zipcodeDto(dong);
			// mav.addObject("zipcodeList", zipList);
		}
		mav.setViewName("zipcode.empty");
	}

	@Override
	public void createMap(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");

		MapDto mapDto = (MapDto) map.get("mapDto");
		List<MultipartFile> fileList = request.getFiles("map_img_file");
		addfile(fileList, mapDto);

		mapDto.setDirections(mapDto.getDirections().replace("\r\n", "<br>"));
		mapDto.setStore_explanation(mapDto.getStore_explanation().replace("\r\n", "<br>"));
		
		LogAspect.logger.info(LogAspect.logMsg + mapDto.toString());
		int check = adminMapDao.mapInsert(mapDto);
		LogAspect.logger.info(LogAspect.logMsg + check);

		mav.addObject("check", check);
		mav.setViewName("adminMapOk.admin");
	}

	public void addfile(List<MultipartFile> fileList, MapDto mapDto) {
		for (int i = 0; i < fileList.size(); i++) {
			long fileSize = fileList.get(i).getSize();
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + fileList.get(i).getOriginalFilename();
			if (fileSize != 0) {
				String realPath = Service.class.getResource("").getPath()
						.replace("apache-tomcat-8.0.47/wtpwebapps", "workspace")
						.replace("WEB-INF/classes/com/team3/service/", "src/main/webapp");
				File path = new File(realPath + "/adminImg");
				path.mkdir();

				if (path.exists() && path.isDirectory()) {
					File file = new File(path, fileName);
					try {
						fileList.get(i).transferTo(file);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (mapDto.getImg_path() == null) {
						mapDto.setImg_path(fileName);
					} else {
						mapDto.setImg_path(mapDto.getImg_path() + "," + fileName);
					}
				}
			}
		}
	}

	@Override
	public void readMap(ModelAndView mav) {
		List<MapDto> mapList = adminMapDao.mapRead();
		// LogAspect.logger.info(LogAspect.logMsg+mapList.size());
		// LogAspect.logger.info(LogAspect.logMsg+mapList.get(0).toString());

		mav.addObject("mapList", mapList);
		mav.setViewName("adminMap.admin");
	}

	@Override
	public void updateMap(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();

		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		MapDto mapDto = (MapDto) map.get("mapDto");
		
		List<MultipartFile> fileList = request.getFiles("map_img_file");
		
		LogAspect.logger.info(LogAspect.logMsg+fileList.get(0).getSize());
		
//		addfile(fileList, mapDto);
		
//		String[] oldPathList=request.getParameter("hidden_path").split(",");
		
//		deleteFile(fileList, mapDto, oldPathList);
		
		
	}
	
	public void deleteFile(List<MultipartFile> fileList, MapDto mapDto,String[] oldPathList) {
		String realPath = Service.class.getResource("").getPath()
				.replace("apache-tomcat-8.0.47/wtpwebapps", "workspace")
				.replace("WEB-INF/classes/com/team3/service/", "src/main/webapp");
		
		for(int i=0;i<oldPathList.length;i++) {
			File file=new File(realPath+ "/adminImg",oldPathList[i]);
			file.delete();
		}
	}
	
	@Override
	public void deleteMap(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest) map.get("request");
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String store_name=request.getParameter("store_name");
		
		HashMap<String, String> stringMap=new HashMap<String, String>();
		stringMap.put("id", id);
		stringMap.put("password", password);
		stringMap.put("name", name);
		stringMap.put("store_name", store_name);
		
		LogAspect.logger.info(LogAspect.logMsg+stringMap.get("id"));
		LogAspect.logger.info(LogAspect.logMsg+stringMap.get("password"));
		LogAspect.logger.info(LogAspect.logMsg+stringMap.get("name"));
		LogAspect.logger.info(LogAspect.logMsg+stringMap.get("store_name"));
//		int check=adminMapDao.mapDelete(stringMap);
	}
}
