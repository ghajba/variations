package biz.hahamo.dev.variations.controller.repository.internal;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GenericHibernateRepositoryTest
{
    private GenericHibernateRepository genericHibernateRepository;

    @BeforeMethod
    public void init()
    {
        genericHibernateRepository = new GenericHibernateRepository();
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindNullPrimaryKey()
    {
        genericHibernateRepository.find(Object.class, null);
    }
}
