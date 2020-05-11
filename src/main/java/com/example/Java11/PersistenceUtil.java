package com.example.Java11;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    public static EntityManagerFactory getFactory() {
        return Persistence.createEntityManagerFactory("GomokuGame");
    }
}
