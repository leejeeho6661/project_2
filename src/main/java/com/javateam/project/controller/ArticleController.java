package com.javateam.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.project.domain.ArticleDTO;
import com.javateam.project.domain.ArticleVO;
import com.javateam.project.domain.PageVO;
import com.javateam.project.service.DAOService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ArticleController {

	@Autowired
	private DAOService daoSvc;
	
	@Autowired
	 private ServletContext servletContext;
	
	//@Autowired
	//private FileSystemResource uploadDirResource; // fileupload metadata wiring
	
	
	//글작성
	@RequestMapping("/article.do")
	public String article(@ModelAttribute("ArticleDTO") ArticleDTO articleDTO) throws IOException {
		
		log.info("== writeArticleProc ==");
		log.info("VO : {}", articleDTO);
		
		MultipartFile file = articleDTO.getBoardWritePicture(); // 업로드 파일
		int boardWriteNum = 0;

		// 추가 : 신규 boardNum 값 구하기 (sequence)
		boardWriteNum = daoSvc.getArticleNumByLastSeq();

		log.info("시퀀스 게시글 번호 : " + boardWriteNum);
		ArticleVO articleVO = new ArticleVO(articleDTO);
		
		//파일을 저장하게 해주는 클래스
		FileOutputStream fos = null;
				
		//우리가 저장할 경로
		Resource resource = new ServletContextResource(servletContext, "\\resources\\article_img\\");
		String tempPath = resource.getFile().getPath()+"\\";
//		log.info("==========tempPath1 : "+tempPath);
		tempPath = tempPath.substring(0, tempPath.indexOf(".metadata")) + "project\\src\\main\\webapp\\resources\\article_img\\";
		log.info("==========tempPath2 : "+tempPath);
		File outFileName = null;
		try {
			// 파일의 실제 데이터
			byte[] bytes = file.getBytes();

			log.debug(tempPath + file.getOriginalFilename());

			// 파일 객체 생성 및 실제 저장 파일의 이름을 선언
			outFileName = new File(tempPath+articleDTO.getBoardWriteCategory()+"_"+articleVO.getBoardWritePicture());

			// 실제 파일이 생성
			fos = new FileOutputStream(outFileName);

			// 데이터를 써준다.
			fos.write(bytes);

			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		articleVO.setBoardWritePicture(!articleDTO.getBoardWritePicture().isEmpty() && file != null ? articleDTO.getBoardWriteCategory()+"_"+articleVO.getBoardWritePicture() : "");
		articleVO.setBoardWriteNum(boardWriteNum);
		log.info("############### articleVO : {}", articleVO);

		daoSvc.insertArticle(articleVO);

		return "redirect:/Article/"+articleVO.getBoardWriteCategory()+"/1.do";
	}
	
	
	// article_list
	@RequestMapping("/Article/{boardWriteCategory}/{currentPage}.do")
	public String Article(@PathVariable Optional<Integer> currentPage, 
							 @PathVariable String boardWriteCategory,
							 Model model) {
		log.info("전체 게시글 보기");

		int limit = 10; // 페이지당 글수
		List<ArticleVO> article_list;

				
		int page = currentPage.isPresent() ? currentPage.get() : 1; // page 설정

		// 총페이지수
		int listCount = daoSvc.getListArticleCountByCategory(boardWriteCategory);

		article_list = daoSvc.getArticleListByCategory(page, limit, boardWriteCategory);

		// 총 페이지 수
		int maxPage = (int) ((double) listCount / limit + 0.95); // 0.95를 더해서 올림 처리
		// 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21,...)
		int startPage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30, ...)
		int endPage = startPage + 10 - 1;

		if (endPage > maxPage)
			endPage = maxPage;

		PageVO pageVO = new PageVO();
		pageVO.setEndPage(endPage);
		pageVO.setListCount(listCount);
		pageVO.setMaxPage(maxPage);
		pageVO.setPage(page);
		pageVO.setStartPage(startPage);

		log.info("=============="+article_list.size());
		
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("article_list", article_list);
		model.addAttribute("category",boardWriteCategory);
		
		return "article/Article_"+boardWriteCategory;
	}
	//글작성 페이지로 이동
	@RequestMapping("/Article_write.do")
	public String article_write() {
		log.debug("board_write");
		return "article/Article_write";
	}
	
	
	// 상세
	@RequestMapping("/Article_detail.do")
	public String article_detail(@RequestParam("boardWriteNum") int boardWriteNum,
			Model model) {
		log.debug(" === Article_detail ==== ");
		daoSvc.updateReadCountArtice(boardWriteNum);
		ArticleVO articleVO = daoSvc.getArticle(boardWriteNum);
		log.info("ArticleVO"+articleVO);
		model.addAttribute("ArticleVO",articleVO);
		model.addAttribute("likeCount",daoSvc.likeCount(boardWriteNum));
		return"article/Article_detail";
	}
	
	@RequestMapping("/article_update_view.do")
	public String article_update_view(@RequestParam("boardWriteNum") int boardWriteNum,
								    Model model) {
		log.info("게시판 수정 폼");
		ArticleVO article = daoSvc.getArticle(boardWriteNum);
		model.addAttribute("ArticleVO",article);
		return "article/Article_update";
	}
	
	@RequestMapping("/article_delete.do")
	public void article_delete(@RequestParam("boardWriteNum") int boardWriteNum,
							   @RequestParam("boardWriteCategory") String boardWriteCategory,
							   HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		log.info("기사 삭제");
		boolean flag = daoSvc.articleDelete(boardWriteNum);
		
		if(flag) {
			out.println("<script>location.href='/project/Article/"+boardWriteCategory+"/1.do';</script>");
	        out.flush();
		}
		else {
			out.println("<script>alert('기사가 존재하지 않습니다.'); history.go(-1);</script>");
            out.flush();
		}
	}
	
	@RequestMapping("/article_update.do")
	public void article_update(@RequestParam("boardWriteNum")int boardWriteNum,
								 @ModelAttribute("ArticleDTO") ArticleDTO newArticleDTO,
								 HttpServletResponse response,
								 Model model) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		log.info("=========controller article_update======");
		log.info("=====newDTO : "+newArticleDTO.toString());
		log.info("새로운 파일 이름 : "+newArticleDTO.getBoardWritePicture().getOriginalFilename());
		
		ArticleVO oldVO = daoSvc.getArticle(boardWriteNum);
		ArticleVO newVO = new ArticleVO(newArticleDTO);
		
		if(!newArticleDTO.getBoardWritePicture().getOriginalFilename().equals(oldVO.getBoardWritePicture()) 
				|| newArticleDTO.getBoardWritePicture().getOriginalFilename() == null) {
			MultipartFile file = newArticleDTO.getBoardWritePicture(); // 업로드 파일
			//파일을 저장하게 해주는 클래스
			FileOutputStream fos = null;
					
			//우리가 저장할 경로
			Resource resource = new ServletContextResource(servletContext, "\\resources\\article_img\\");
			String tempPath = resource.getFile().getPath()+"\\";
//			log.info("==========tempPath1 : "+tempPath);
			tempPath = tempPath.substring(0, tempPath.indexOf(".metadata")) + "project\\src\\main\\webapp\\resources\\article_img\\";
			log.info("==========tempPath2 : "+tempPath);
			File outFileName = null;
			try {
				// 파일의 실제 데이터
				byte[] bytes = file.getBytes();

				log.debug(tempPath + file.getOriginalFilename());

				// 파일 객체 생성 및 실제 저장 파일의 이름을 선언
				outFileName = new File(tempPath+newArticleDTO.getBoardWriteCategory()+"_"+newVO.getBoardWritePicture());

				// 실제 파일이 생성
				fos = new FileOutputStream(outFileName);

				// 데이터를 써준다.
				fos.write(bytes);

				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			newVO.setBoardWritePicture(!newVO.getBoardWritePicture().isEmpty() && file != null ? newVO.getBoardWriteCategory()+"_"+newVO.getBoardWritePicture() : oldVO.getBoardWritePicture());
			log.info("############### articleVO : {}", newVO);
			
		}
		else
			newVO.setBoardWritePicture(oldVO.getBoardWritePicture());
		
		newVO.setBoardWriteNum(boardWriteNum);
		if(daoSvc.articleUpdate(newVO)) {
			model.addAttribute("ArticleVO",newVO);
			out.println("<script>location.href='/project/Article_detail.do?boardWriteNum="+boardWriteNum+"';</script>");
	        out.flush();
		}
		else {
			out.println("<script>alert('수정에 실패하였습니다.'); history.go(-1);</script>");
            out.flush();
		}
	}
	

}
