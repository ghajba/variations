package biz.hahamo.dev.variations.controller.repository.internal;


import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import biz.hahamo.dev.variations.controller.repository.PersistenceQuery;
import biz.hahamo.dev.variations.controller.repository.internal.hibernate.GenericHibernateRepository;
import biz.hahamo.dev.variations.controller.repository.internal.hibernate.HibernateQueryCreator;

@Test
public class GenericHibernateRepositoryTest
{
    private GenericHibernateRepository genericHibernateRepository;
    @Mock
    private HibernateQueryCreator<PersistenceQuery> hibernateQueryCreator;

    @BeforeMethod
    public void init()
    {
        MockitoAnnotations.initMocks(this);
        genericHibernateRepository = new GenericHibernateRepository(hibernateQueryCreator);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testFindNullPrimaryKey()
    {
        genericHibernateRepository.find(Object.class, null);
    }
}
