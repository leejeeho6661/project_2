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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;

import com.javateam.project.domain.BoardDTO;
import com.javateam.project.domain.BoardVO;
import com.javateam.project.domain.PageVO;
import com.javateam.project.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	 private ServletContext servletContext;
	
	//게시판 리스트
	@RequestMapping("boardList.do/{currentPage}")
	public String listBoard(@PathVariable Optional<Integer> currentPage, Model model) {

		log.info("전체 게시글 보기");

		int limit = 10; // 페이지당 글수
		List<BoardVO> board_list;

		int page = currentPage.isPresent() ? currentPage.get() : 1; // page 설정

		// 총페이지수
		int listCount = boardService.getListCount();

		board_list = boardService.getArticleList(page, limit);

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

		
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("board_list", board_list);
		return "board/Board_List";
	}

	//글쓰기 폼
	@RequestMapping("/board_write_view.do")
	public String board_write_view() {
		log.info("board_write_view");
		return "board/Board_Write";
	}

	// 업로드
	@RequestMapping(value = "/write_Board.do", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public String writeBoardProc(@ModelAttribute("boardDTO") BoardDTO boardDTO) throws IOException {

		log.info("############# writeBoardProc ##################");

		log.info("VO : {}", boardDTO);

		MultipartFile file = boardDTO.getBoard_file(); // 업로드 파일
		int board_num = 0;

		// 추가 : 신규 boardNum 값 구하기 (sequence)
		board_num = boardService.getBoardNumByLastSeq();

		log.info("시퀀스 게시글 번호 : " + board_num);
		BoardVO boardVO = new BoardVO(boardDTO);
		
		//파일을 저장하게 해주는 클래스
		FileOutputStream fos = null;
				
		//우리가 저장할 경로
		Resource resource = new ServletContextResource(servletContext, "\\resources\\board_img\\");
		String tempPath = resource.getFile().getPath()+"\\";
//		log.info("==========tempPath1 : "+tempPath);
		tempPath = tempPath.substring(0, tempPath.indexOf(".metadata")) + "project\\src\\main\\webapp\\resources\\board_img\\";
		//String tempPath = uploadDirResourceBoard.getPath().replace("/", "\\") + "\\";

		try {
			// 파일의 실제 데이터
			byte[] bytes = file.getBytes();

			log.debug(tempPath + file.getOriginalFilename());

			// 파일 객체 생성 및 실제 저장 파일의 이름을 선언
			File outFileName = new File(tempPath + boardVO.getBoard_file());

			// 실제 파일이 생성
			fos = new FileOutputStream(outFileName);

			// 데이터를 써준다.
			fos.write(bytes);

			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boardVO.setBoard_file(!boardDTO.getBoard_file().isEmpty() && file != null ? file.getOriginalFilename() : "");

		boardVO.setBoard_num(board_num);
		log.info("############### boardVO : {}", boardVO);

		boardService.writeBoard(boardVO);

		return "redirect:/boardList.do/1";
	} //

	//게시판 상세 페이지
	@RequestMapping("/board_detail.do")
	public String board_detail(@RequestParam("board_num") int board_num,
			Model model) {
		
		log.debug("=========보드 상세보기==========");
		boardService.updateReadCount(board_num);
		BoardVO boardVO = boardService.getBoard(board_num);
		log.info("BoardVO : "+boardVO);
		
		model.addAttribute("boardVO",boardVO);
		
		return "board/Board_Detail";
	}
	
	//검색 후 리스트
	@RequestMapping("boardListbySearch.do")
	public String listBoardbySearch(@RequestParam(value="page",defaultValue="1")int page,
									@RequestParam("search_kind")String search_kind,
									@RequestParam("search_word")String search_word,
									Model model) {
		log.info("###### 검색 게시글 보기"+search_kind+"/"+search_word);
		int limit = 10;	//페이지당 글 수
		List<BoardVO> boardList;
		
		page = page != 0 ? page : 1;
		boardList= boardService.getBoardBySearch(search_kind, search_word.trim(), limit, page);
		int listCount = boardList.size();
		
		log.info("검색 게시글 수 : {}", listCount);
		
		// 총 페이지 수
   		int maxPage=(int)((double)listCount/limit+0.95); //0.95를 더해서 올림 처리
		// 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21,...)
   		int startPage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30, ...)
   	    int endPage = startPage + 10 - 1;
   	    
   	    if (endPage> maxPage) endPage= maxPage;
   	    
   	    PageVO pageVO = new PageVO();
		pageVO.setEndPage(endPage);
		pageVO.setListCount(listCount);
		pageVO.setMaxPage(maxPage);
		pageVO.setPage(page);
		pageVO.setStartPage(startPage);
		
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("board_list", boardList);
		
		// 추가 : 페이징 부분에서 검색 페이지 주소 반영위한 플래그 변수
		// 검색어 재전송
		model.addAttribute("search_YN", "search");
		model.addAttribute("search_kind", search_kind);
		model.addAttribute("search_word", search_word);
		
		return "board/Board_List";
	}
	@RequestMapping("/board_update_view.do")
	public String board_modify_view(@RequestParam("board_num") int board_num,
								    Model model) {
		log.info("게시판 수정 폼");
		BoardVO board = boardService.getBoard(board_num);
		model.addAttribute("boardVO",board);
		return "board/Board_Update_View";
	}
	
	@RequestMapping("/board_delete.do")
	public void board_delete(@RequestParam("board_num") int board_num,
							 HttpServletResponse response) throws IOException{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		log.info("게시판 삭제");
		boolean flag = boardService.deleteBoard(board_num);
		
		if(flag) {
			out.println("<script>location.href='/project/boardList.do/1';</script>");
	        out.flush();
		}
		else {
			out.println("<script>alert('게시물이 존재하지 않습니다.'); history.go(-1);</script>");
            out.flush();
		}
	}
	
	@RequestMapping("/board_update.do")
	public String board_update(@ModelAttribute("boardDTO") BoardDTO boardDTO,
							   Model model) throws IOException {
		
		log.info("게시판 수정");
		BoardVO pastBoardVO = boardService.getBoard(boardDTO.getBoard_num());
		boolean flag = false;
		
		log.debug("기존 게시판정보 : " + pastBoardVO);
		log.debug("수정 게시판정보 : " + boardDTO);
		
		BoardVO newBoard = new BoardVO(boardDTO);
		//======================if문의 수정이 필요 / 파일업로드 경로와 파일 이름에대한 수정 요함
		if(boardDTO.getBoard_file().getOriginalFilename() != null &&
				!boardDTO.getBoard_file().getOriginalFilename().equals(pastBoardVO.getBoard_file())) {
			
			MultipartFile file = boardDTO.getBoard_file(); // 업로드 파일
			//파일을 저장하게 해주는 클래스
			FileOutputStream fos = null;
					
			//우리가 저장할 경로
			Resource resource = new ServletContextResource(servletContext, "\\resources\\board_img\\");
			String tempPath = resource.getFile().getPath()+"\\";
			//String tempPath = uploadDirResourceBoard.getPath().replace("/", "\\") + "\\";

			try {
				// 파일의 실제 데이터
				byte[] bytes = file.getBytes();

				log.debug(tempPath + file.getOriginalFilename());

				// 파일 객체 생성 및 실제 저장 파일의 이름을 선언
				File outFileName = new File(tempPath + newBoard.getBoard_file());

				// 실제 파일이 생성
				fos = new FileOutputStream(outFileName);

				// 데이터를 써준다.
				fos.write(bytes);

				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			newBoard.setBoard_file(!boardDTO.getBoard_file().isEmpty() && file != null ? file.getOriginalFilename() : "");
		}
		else {
			newBoard.setBoard_file(pastBoardVO.getBoard_file());
		}
		flag = boardService.updateBoard(newBoard);
		
		if(flag) {
			newBoard = boardService.getBoard(newBoard.getBoard_num());
		}
		
		model.addAttribute("boardVO",newBoard);
		return "board/Board_Detail";
	}
}
