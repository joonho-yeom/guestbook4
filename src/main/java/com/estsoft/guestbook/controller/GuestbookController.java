package com.estsoft.guestbook.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.guestbook.domain.Guestbook;
import com.estsoft.guestbook.service.GuestbookService;

@Controller
@RequestMapping( "" )
public class GuestbookController {
	
	@Autowired
	GuestbookService guestbookService;

	@RequestMapping("/")
	public String index( Model model ) {
		List<Guestbook> list = guestbookService.getMessageList();
		model.addAttribute( "list", list );
		return "index";
	}
	
	@RequestMapping( "/deleteform/{no}" )
	public String deletefrom( @PathVariable( "no" ) Long no, Model model ) {
		model.addAttribute( "no", no );
		return "deleteform";
	}

	@RequestMapping( "/delete" )
	public String delete( @ModelAttribute Guestbook guestbook ) {
		guestbookService.deleteMessage( guestbook );
		return "redirect:/";
	}

	@RequestMapping( "/insert" )
	public String insert( @ModelAttribute Guestbook guestbook ) { //여기서 받아 아랫단에서 영속화가 일단 되면 여기서 수정해도 메모리에 먹힌다. 하지만 디비 반영은 서비스를 콜해야 된다.
		guestbookService.insertMessage(guestbook);
		return "redirect:/";
	}

}
