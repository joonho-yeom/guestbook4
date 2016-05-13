package com.estsoft.guestbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guestbook.domain.Guestbook;
import com.estsoft.guestbook.repository.GuestbookRepository;

@Service
@Transactional //service단에 달리는 것이 맞다 dao단에 달리면 트랜잭션화 하는 의미가 별로 없다. 
public class GuestbookService {
	@Autowired
	GuestbookRepository guestbookRepository;

	public List<Guestbook> getMessageList() {
		System.out.println( guestbookRepository );
		return guestbookRepository.findAll();
	}
	
	public Boolean deleteMessage( Guestbook guestbook ) {
		return guestbookRepository.remove( guestbook );
	}
	
	public void insertMessage( Guestbook guestbook ) {
		guestbookRepository.save( guestbook );
	}
}
