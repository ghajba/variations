package biz.hahamo.dev.variations.controller.repository;

import java.util.List;

import biz.hahamo.dev.variations.model.User;

/**
 * Class for accessing User data from the database.
 * 
 * @author GHajba
 * 
 */
public class UserRepository {

    private final GenericRepository genericRepository;
    private final PersistenceQueryFactory queryFactory;

    /**
     * IoC constructor
     * 
     * @param genericRepository
     *            injected
     */
    public UserRepository(final GenericRepository genericRepository, final PersistenceQueryFactory queryFactory) {
        this.genericRepository = genericRepository;
        this.queryFactory = queryFactory;
    }

    /**
     * Queries a User from the database by its ID.
     * 
     * @param id
     *            of the User
     * @return the User object or null if not found
     */
    public User findById(Long id) {
        return genericRepository.find(User.class, id);
    }

    public User findByLogin(String login) {
        return genericRepository.findByQuery(queryFactory.createJpqlQuery("userByLogin",
                "FROM User u WHERE login = :login", "login", login));
    }
    
    public Long saveUser(User user) {
        genericRepository.save(user);
        return user.getId();
    }

    public List<User> getAllUsers() {
        return genericRepository.findListByQuery(queryFactory.createJpqlQuery("all.users", "FROM User"));
    }

    public User findByEmail(String emailAddress) {
        return genericRepository.findByQuery(queryFactory.createJpqlQuery("userByEmail",
                "FROM User u WHERE emailAddress = :email", "email", emailAddress));
    }

}
