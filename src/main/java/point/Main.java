package point;

import javax.persistence.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Abre conexão com banco
        // (Cria novo banco se não existir):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("C:\\Users\\SONDA_ADMIN\\Downloads\\points-console\\points-console\\points.odb");
        EntityManager em = emf.createEntityManager();

        // Salva 1000 objetos do tipo ponto no banco:
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Retorna o quantidade de objetos do tipo Ponto no banco:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Retorna a média do valor x do pontos:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Obtém todos os objetos do tipo Ponto do banco:
        TypedQuery<Point> query =
            em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        // Fecha conexão com banco:
        em.close();
        emf.close();
    }
}