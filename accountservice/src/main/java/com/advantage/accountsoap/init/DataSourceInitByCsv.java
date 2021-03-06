package com.advantage.accountsoap.init;

import com.advantage.accountsoap.model.Account;
import com.advantage.accountsoap.model.Country;
import com.advantage.accountsoap.util.fs.FileSystemHelper;
import com.advantage.common.SystemParameters;
import com.advantage.common.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataSourceInitByCsv {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private Environment env;

    public void init() throws Exception {
        if (!SystemParameters.getHibernateHbm2ddlAuto(env.getProperty("account.hibernate.db.hbm2ddlAuto")).equals("validate")) {
            SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
            Session session = sessionFactory.openSession();
            Transaction transaction;

            //  Get countries list in CSV (Comma Separated Values) file
            ClassPathResource filePathCSV = new ClassPathResource("countries_20150630.csv");
            File countriesCSV = filePathCSV.getFile();

            ObjectMapper objectMapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            transaction = session.beginTransaction();

        /* Countries */
            List<String> countries = FileSystemHelper.readFileCsv(countriesCSV.getAbsolutePath());
            Map<Long, Country> countryMap = new HashMap<>();

            for (String str : countries) {
                String[] substrings = str.split(",");
                Country country = new Country(substrings[1], substrings[2], Integer.valueOf(substrings[3]));
                session.persist(country);
                countryMap.put(country.getId(), country);
            }
            transaction.commit();

            transaction = session.beginTransaction();

        /*
        ===========================================================================================================================================================================================================
        new Account(accountType, lastName, firstName, loginName, password, countryCode+"L", phoneNumber, stateProvinceName, cityName, address, zipcodeString, email, offerPromotionBoolean)
        ===========================================================================================================================================================================================================
         */
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Avinu", "Avraham", "avinu.avraham", "Avraham1", countryMap.get(12L), "077-7654321", "Jerusalem1", "Alonei Mamreh", "address", "9876543", "a@b.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Avinu", "itshak", "avinu.itshak", "Itshak1", countryMap.get(12L), "077-7654321", "Jerusalem1", "Alonei Mamreh", "address", "9876543", "a@b.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Avinu", "jakob", "avinu.jakob", "Israel7", countryMap.get(12L), "077-7654321", "Jerusalem1", "Alonei Mamreh", "address", "9876543", "a@b.com", true));

            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "imenu", "Sara", "sara.imenu", "Saramom2", countryMap.get(18L), "077-7654321", "Jerusalem1", "Alonei Mamreh", "address", "9876543", "a@b.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "imenu", "Rivka", "rivka.imenu", "Rivka2", countryMap.get(18L), "077-7654321", "Jerusalem1", "Alonei Mamreh", "address", "9876543", "a@b.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "imenu", "Lea", "lea.imenu", "Motherlea2", countryMap.get(18L), "077-7654321", "Jerusalem1", "Alonei Mamreh", "address", "9876543", "a@b.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "imenu", "Rachel", "rachel.imenu", "Rachel21", countryMap.get(128L), "077-7654321", "Jerusalem1", "Alonei Mamreh", "address", "9876543", "a@b.com", true));

            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "King", "David", "king.david", "DavidK1", countryMap.get(128L), "077-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "a@b.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "King", "Solomon", "king.solomon", "SolomonK2", countryMap.get(128L), "077-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "a@b.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Queen", "Sheeba", "queen.sheeba", "SheebaQ1", countryMap.get(10L), "077-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "a@b.com", true));


            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Fiskin", "Evgeney", "fizpok", "ASas12", countryMap.get(10L), "052-4898919", "Jerusalem1", "Jerusalem", "address", "9876543", "evgeney.fiskin@hpe.com", true));

            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Regev", "Binyamin", "beni.regev", "Qe7uwt2v!", countryMap.get(128L), "054-7654321", "Jerusalem", "Jerusalem", "Holly Land", "9876543", "nakdimon@ben-guryon.com", false));
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Mercury", "Admin User", "Mercury", "Mercury", countryMap.get(10L), "077-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "mercury@hpe.com", true));
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "Adminov", "Admin", "admin", "adm1n", countryMap.get(10L), "052-1234567", "Jerusalem1", "Jerusalem", "address", "9876543", "admin@admin.ad", true));
            session.persist(new Account(AccountType.ADMIN.getAccountTypeCode(), "TestFirst", "TestLast", "test", "Test1", countryMap.get(10L), "052-1234567", "Jerusalem1", "Jerusalem", "address", "9876543", "test@gmail.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Gilat", "Naor", "gilat", "gG123", countryMap.get(128L), "052-7654321", "Jerusalem1", "Jerusalem", "address", "9876543", "d0r1@gmail.com", true));
            session.persist(new Account(AccountType.USER.getAccountTypeCode(), "Regev", "Beni", "bbnnii", "bB123", countryMap.get(128L), "058-7654321", "Gush Dan", "Afula", "Ben Gorion 1973", "9876543", "b@ni.com", false));

            transaction.commit();
        }
    }
}
