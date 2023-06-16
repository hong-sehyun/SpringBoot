package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		Board b = new Board();
//		b.setTitle("title");
//		b.setWriter("writer");
//		b.setContent("content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L); 
//		
//		em.persist(b);
//		
//		tx.commit();
		
		insertData(em);
		
		em.close();
		emf.close();
	}
	
	private static void insertData(EntityManager em) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			for(int i = 1; i < 11; i++) {
				Board b = new Board();
				b.setTitle("title"+i);
				b.setWriter("writer"+i);
				b.setContent("content"+i);
				b.setCreateDate(new Date());
				b.setCnt(0L); 
				
				em.persist(b);
			}
			
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
		
	}

}
