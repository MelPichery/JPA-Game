package com.diginamic.JPA_Game;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import database.DatabaseHandle;
import model.Engin;
import model.Partie;
import model.Aeronef;
import model.Avatar;
import model.Bolide;
import model.Player;

public class Main {

	public static void main(String[] args) {

		EntityManager em = DatabaseHandle.getEntityManagerFactory();

		EntityTransaction transaction = em.getTransaction();

		// Begin the transaction
		transaction.begin();

		// the record is read and the object is now managed by Hibernate in memory
		// Player p = em.find(Player.class, 1);
		// System.out.println(p.getName());

		// TypedQuery<Player> query = em.createQuery("FROM Player",Player.class);
		// System.out.println("Nombre de joueurs : " + query.getResultList().size());
		// System.out.println(query.getResultList());

		// the persistent object is modified
		// p.setName("Peter");

		// the new objet is transient
		//Player p2 = new Player();
		//p2.setId(4);
		//p2.setName("James BOND");
		//p2.setMail("jamesbond@test.fr");
		//p2.setPseudo("jaja");

		// this object become a persistent object
		//em.persist(p2);

		//create player1
		 Player p1 = new Player();
		  
		 p1.setName("John DOE"); 
		 p1.setMail("johndoe@test.fr"); 
		 p1.setPseudo("jojo");
		 p1.setDateNaissance(LocalDate.now()); 
		 
		 
		 
		 //p1.setName("John DOE2");
		 
		//create player2
		 Player p2 = new Player();
		  
		 p2.setName("James BOND"); 
		 p2.setMail("jamesbond@test.fr");
		 p2.setPseudo("jaja"); 
		 p2.setDateNaissance(LocalDate.now()); 
		 
		  
		 //TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE name = :name", Player.class);
		 //query.setParameter("name", "John DOE2");
		  
		 //p1 = query.getSingleResult(); 
		 //System.out.println(p1.toString());
		 
		 //create personnage1
		 Avatar perso1 = new Avatar();
		 perso1.setNom("perso1");
		 perso1.setPuissance(333);
		 
		 //add avatar to player1
		 p1.setAvatar(perso1);
		 
		 //create personnage2	
		 Avatar perso2 = new Avatar();
		 perso2.setNom("perso2");
		 perso2.setPuissance(666);
		 
		//add avatar to player2
		 p2.setAvatar(perso2);
		 
		 //create partie1
		Partie pp1 = new Partie();

		pp1.setNiveau(3);
		pp1.setDate(LocalDate.now());
		pp1.setScore(200);

		
		//create set for adding partie1 to player1
		Set<Partie> parties1 = new HashSet<Partie>();
		parties1.add(pp1);
		p1.setParties(parties1);

		 //create partie2
		Partie pp2 = new Partie();

		pp2.setNiveau(1);
		pp2.setDate(LocalDate.now());
		pp2.setScore(22);
		
		
		//create set for adding partie2 to player2
		Set<Partie> parties2 = new HashSet<Partie>();
		parties2.add(pp2);
		parties2.add(pp1);
		p2.setParties(parties2);
		
		//create engin1
		Aeronef a = new Aeronef();
		a.setCouleur("noir");
		a.setVitesseMax(3000);
		a.setType("chouette");
		a.setAutonomieKm(99);
		a.setAvatar(perso1);
		
		//create engin2
		Bolide b = new Bolide();
		b.setCouleur("vert");
		b.setVitesseMax(50);
		b.setNb_roues(3);
		b.setAutonomieHeure(2);
		b.setAvatar(perso2);
		
		//persist objects in db
		em.persist(p1);
		em.persist(p2);
		em.persist(perso1);
		em.persist(perso2);
		em.persist(a);
		em.persist(b);
		em.persist(pp1);
		em.persist(pp2);
		
		//print players
		TypedQuery<Player> pq = em.createQuery("FROM Player", Player.class);
		System.out.println(pq.getResultList());
		
		//find John Doe
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE name = :name", Player.class);
		query.setParameter("name", "John DOE");
		System.out.println(query.getResultList());
		
		//sort by pseudo
		Query query1 = em.createQuery("SELECT p.pseudo FROM Player p ORDER BY p.pseudo");
		System.out.println(query1.getResultList());
		
		//players names which play today
		Query query2 = em.createQuery("SELECT DISTINCT pl.name FROM Partie pa, Player pl WHERE pa.date = :date");
		query2.setParameter("date", LocalDate.now());
		System.out.println(query2.getResultList());
		
		TypedQuery<String> query3 = em.createQuery("SELECT DISTINCT pl.name FROM Player pl JOIN pl.parties pa JOIN pa.players WHERE pa.date = :date",String.class);
		query3.setParameter("date", LocalDate.now());
		System.out.println(query3.getResultList());
		
		//for a player, print engin, score and niveau
		
		//add 1 to niveau
		TypedQuery<Partie> q = em.createQuery("FROM Partie", Partie.class);

		try {
			for (Partie p : q.getResultList()) {

				p.setNiveau(p.getNiveau() + 1);

			}

			System.out.println(q.getResultList());
			
			transaction.commit();
			
		} catch (Exception e) {
			
			System.out.println("Pas d'ajout de niveau");
			
			transaction.rollback();
			
		} finally {
			// all the objects will be detached
			em.close();
			System.exit(0);
		}

	}

}
