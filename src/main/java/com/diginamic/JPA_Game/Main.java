package com.diginamic.JPA_Game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import database.DatabaseHandle;
import model.Engin;
import model.Partie;
import model.Aeronef;
import model.Avatar;
import model.Bolide;
import model.Player;

public class Main {

	public static void main(String[] args) {
		
		 final Logger logger = LoggerFactory.getLogger(Main.class);


		EntityManager em = DatabaseHandle.getEntityManagerFactory();

		EntityTransaction transaction = em.getTransaction();

		// Begin the transaction
		transaction.begin();

		// the record is read and the object is now managed by Hibernate in memory
		/* Player p = em.find(Player.class, 1);
		 System.out.println(p.getName());

		 TypedQuery<Player> query = em.createQuery("FROM Player",Player.class);
		 System.out.println("Nombre de joueurs : " + query.getResultList().size());
		 System.out.println(query.getResultList());*/

		// the persistent object is modified
		/*p.setName("Peter");*/

		// the new objet is transient
		/*Player p2 = new Player();
		p2.setId(4);
		p2.setName("James BOND");
		p2.setMail("jamesbond@test.fr");
		p2.setPseudo("jaja");*/

		// this object become a persistent object
		//em.persist(p2);

		//create player1
		 Player p1 = new Player();
		  
		 p1.setName("John DOE"); 
		 p1.setMail("johndoe@test.fr"); 
		 p1.setPseudo("jojo");
		 p1.setDateNaissance(LocalDate.now()); 
		 
		 
		 
		 /*p1.setName("John DOE2");*/
		 
		//create player2
		 Player p2 = new Player();
		  
		 p2.setName("James BOND"); 
		 p2.setMail("jamesbond@test.fr");
		 p2.setPseudo("jaja"); 
		 p2.setDateNaissance(LocalDate.now()); 
		 
		  
		 /*TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE name = :name", Player.class);
		 query.setParameter("name", "John DOE2");
		  
		 p1 = query.getSingleResult(); 
		 System.out.println(p1.toString());*/
		 
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
		pp2.setDate(LocalDate.of(2019, 02, 26));
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
		logger.info("Requête player: {}",pq.getResultList());
		
		//find John Doe
		TypedQuery<Player> query = em.createQuery("SELECT p FROM Player p WHERE name = :name", Player.class);
		query.setParameter("name", "John DOE");
		logger.info("Requête player: {}",query.getResultList());
		
		//sort by pseudo
		Query query1 = em.createQuery("SELECT p.pseudo FROM Player p ORDER BY p.pseudo");
		logger.info("Requête player: {}",query1.getResultList());
		
		//players names which play today
		Query query2 = em.createQuery("SELECT DISTINCT pl.name FROM Partie pa, Player pl WHERE pa.date = :date");
		query2.setParameter("date", LocalDate.now());
		logger.info("Requête player et partie: {}",query2.getResultList());
		
		TypedQuery<String> query3 = em.createQuery("SELECT DISTINCT pl.name FROM Player pl JOIN pl.parties pa JOIN pa.players WHERE pa.date = :date",String.class);
		query3.setParameter("date", LocalDate.now());
		logger.info("Requête player: {}",query3.getResultList());
		
		//for a player, print engin, score and niveau
		Query query4 = em.createQuery("SELECT DISTINCT pl.name, pa.niveau,pa.score, a.nom, pa.id, e.couleur FROM Player pl JOIN pl.parties pa JOIN pa.players JOIN pl.avatar a, Engin e WHERE e.avatar = a.id AND pl.id = :id AND pa.date=:date");
		query4.setParameter("id", 2);
		query4.setParameter("date", LocalDate.now());
		List<Object[]> tests = query4.getResultList();
		for (Object[] objects : tests) {
			System.out.println("Nom joueur : "+objects[0]+"    Niveau : " + objects[1]+"    Score : "+objects[2]+"      nom avatar : "+objects[3]+"      Partie N° : "+objects[4]+"       Couleur Engin : "+objects[5] );
		}
		
		//update engin : bolide to aeronef
		Aeronef a2 = new Aeronef();
		a2.setCouleur("noir");
		a2.setVitesseMax(3000);
		a2.setType("chouette");
		a2.setAutonomieKm(99);
		a2.setAvatar(perso2);
		
		Query query5 = em.createQuery("UPDATE Engin e SET e.couleur=:couleur, e.vitesseMax=:vitessemax WHERE e.id = :id ");
		query5.setParameter("couleur", a2.getCouleur());
		query5.setParameter("vitessemax", a2.getVitesseMax());
		query5.setParameter("id", b.getId());
		query5.executeUpdate();
		
		Query query6 = em.createQuery("SELECT DISTINCT pl.name, pa.niveau,pa.score, a.nom, pa.id, e.couleur FROM Player pl JOIN pl.parties pa JOIN pa.players JOIN pl.avatar a, Engin e WHERE e.avatar = a.id AND pl.id = :id AND pa.date=:date");
		query6.setParameter("id", 2);
		query6.setParameter("date", LocalDate.now());
		List<Object[]> tests1 = query6.getResultList();
		
		for (Object[] objects : tests1) {
			logger.info("Nom joueur : "+objects[0]+"    Niveau : " + objects[1]+"    Score : "+objects[2]+"      nom avatar : "+objects[3]+"      Partie N° : "+objects[4]+"       Couleur Engin : "+objects[5] );
		}
		
		//add 1 to niveau
		TypedQuery<Partie> q = em.createQuery("FROM Partie", Partie.class);

		try {
			for (Partie p : q.getResultList()) {

				p.setNiveau(p.getNiveau() + 1);

			}

			logger.info("Requête player: {}",q.getResultList());
			
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
