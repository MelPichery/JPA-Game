package com.diginamic.JPA_Game;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import database.DatabaseHandle;
import model.Partie;
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
		// Player p2 = new Player();
		// p2.setId(4);
		// p2.setName("James BOND");
		// p2.setMail("jamesbond@test.fr");
		// p2.setPseudo("jaja");

		// this object become a persistent object
		// em.persist(p2);

		/*
		 * Player p1 = new Player();
		 * 
		 * p1.setName("John DOE"); p1.setMail("johndoe@test.fr"); p1.setPseudo("jojo");
		 * p1.setDateNaissance(LocalDate.now()); em.persist(p1);
		 * 
		 * p1.setName("John DOE2");
		 * 
		 * 
		 * Player p2 = new Player();
		 * 
		 * p2.setName("James BOND"); p2.setMail("jamesbond@test.fr");
		 * p2.setPseudo("jaja"); p2.setDateNaissance(LocalDate.now()); em.persist(p2);
		 * 
		 * TypedQuery<Player> query =
		 * em.createQuery("SELECT p FROM Player p WHERE name = :name", Player.class);
		 * query.setParameter("name", "John DOE2");
		 * 
		 * p1 = query.getSingleResult(); System.out.println(p1.toString());
		 */

		Partie pp1 = new Partie();

		pp1.setNiveau(3);
		pp1.setDate(LocalDate.now());
		pp1.setScore(200);
		em.persist(pp1);

		Partie pp2 = new Partie();

		pp2.setNiveau(1);
		pp2.setDate(LocalDate.now());
		pp2.setScore(22);
		em.persist(pp2);

		TypedQuery<Partie> q = em.createQuery("FROM Partie", Partie.class);

		try {
			for (Partie p : q.getResultList()) {

				p.setNiveau(p.getNiveau() + 1);

			}

			System.out.println(q.getResultList());
			// all the objects will be detached
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Pas d'ajout de niveau");
			transaction.rollback();
		} finally {
			em.close();
			System.exit(0);
		}

	}

}
