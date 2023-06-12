package facades;

import entities.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TransactionFacade {
    static EntityManagerFactory emf;
    private static TransactionFacade instance;

    public static TransactionFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TransactionFacade();
        }
        return instance;
    }
    public List<Transaction> getAllTransactions() {
       return null;
    }
}
