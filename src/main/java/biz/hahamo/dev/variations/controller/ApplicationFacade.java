package biz.hahamo.dev.variations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import biz.hahamo.dev.variations.controller.repository.GenericRepository;
import biz.hahamo.dev.variations.model.Driver;

/**
 * currently convenient class to use the spring-hibernate mix from the static main application
 * @author GHajba
 *
 */
@Service
public class ApplicationFacade
{
    @Autowired
    private GenericRepository repository;
    
    public void loadData()
    {
        repository.find(Driver.class, 1L);
    }

}
