package com.estsoft.guestbook.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estsoft.guestbook.domain.Guestbook;

@Repository
public class GuestbookRepository {
	
	@PersistenceContext //factory가 인식할 수 있게끔
	private EntityManager em;
	
	@Transactional
	public void save(Guestbook guestbook){
		guestbook.setRegDate(new Date());
		em.persist(guestbook);
	}
	
	public List<Guestbook> findAll(){
		TypedQuery query = em.createQuery("select gb from Guestbook gb order by gb.regDate desc", Guestbook.class);
		List<Guestbook> list = query.getResultList();
		return list;
	}
	
	//:의 용도는  setParameter
	public Boolean remove(Guestbook guestbook){
		TypedQuery query = em.createQuery("select gb from Guestbook gb where gb.no= :no and gb.password = :password", Guestbook.class);
		query.setParameter("no", guestbook.getNo());
		query.setParameter("password", guestbook.getPassword());
		
//		TypedQuery query = em.createQuery("select gb from Guestbook gb where gb.no= ?1 and gb.password = ?2", Guestbook.class);
//		query.setParameter(1, guestbook.getNo());
//		query.setParameter(2, guestbook.getPassword());

		//Guestbook result = query.getSingleResult();// 결과가 잘못되면 error handling을 해야하는 번잡함이 있다.
		
		//해서 요렇게 쓴다.
		List<Guestbook> list = query.getResultList();
		if(list.size() != 1)
			return false;
		
		em.remove(list.get(0));
		return true;
	}
}
