package biz.hahamo.dev.variations.controller;

import org.springframework.transaction.annotation.Transactional;

import biz.hahamo.dev.variations.controller.repository.GenericRepository;
import biz.hahamo.dev.variations.controller.repository.PersistenceQueryFactory;
import biz.hahamo.dev.variations.model.Driver;

/**
 * currently convenient class to use the spring-hibernate mix from the static
 * main application
 * 
 * @author GHajba
 * 
 */
@Transactional
public class ApplicationService {
    private final GenericRepository repository;
    private final PersistenceQueryFactory persistenceQueryFactory;

    public ApplicationService(GenericRepository genericRepository, PersistenceQueryFactory persistenceQueryFactory) {
        this.repository = genericRepository;
        this.persistenceQueryFactory = persistenceQueryFactory;
    }

    public void loadData() {
        Driver d = repository.find(Driver.class, 1L);
        System.out.println(d);
        repository.findListByQuery(persistenceQueryFactory.createJpqlQuery("all.drivers", "FROM Driver"));
    }

    public void saveData(Object entity) {
        repository.save(entity);
    }

}
