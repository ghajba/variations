package biz.hahamo.dev.variations.controller;

import static org.testng.AssertJUnit.fail;
import liquibase.change.Change;
import liquibase.change.core.CreateViewChange;
import liquibase.change.core.DeleteDataChange;
import liquibase.change.core.EmptyChange;
import liquibase.change.core.InsertDataChange;
import liquibase.change.core.LoadDataChange;
import liquibase.change.core.RawSQLChange;
import liquibase.change.core.UpdateDataChange;
import liquibase.changelog.ChangeLogParameters;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.exception.LiquibaseException;
import liquibase.parser.ChangeLogParser;
import liquibase.parser.ChangeLogParserFactory;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.testng.annotations.Test;

/**
 * This test validates Liquibase changesets and fails if some changeset contains DDL modifications for a specific context
 * 
 * @author GHajba
 *
 */
public class LiquibaseTest
{
    @Test
    public void testContextSpecificChangeSets() throws LiquibaseException
    {
        ClassLoaderResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(this
            .getClass().getClassLoader());
        ChangeLogParser parser = ChangeLogParserFactory.getInstance().getParser("master.xml",
            resourceAccessor);

        DatabaseChangeLog parse = parser.parse("dbchange/master.xml", new ChangeLogParameters(), resourceAccessor);

        for (ChangeSet changeSet : parse.getChangeSets())
        {
            if (hasContext(changeSet))
            {
                checkChanges(changeSet);
            }
        }
    }

    private boolean hasContext(final ChangeSet changeSet)
    {
        return changeSet.getContexts() != null && !changeSet.getContexts().isEmpty();
    }

    private void checkChanges(final ChangeSet changeSet)
    {
        for (Change change : changeSet.getChanges())
        {
            if (isDMLChange(change))
            {
                continue;
            }

            if (change instanceof RawSQLChange)
            {
                RawSQLChange sqlChange = (RawSQLChange) change;
                String sql = sqlChange.getSql().toUpperCase();
                if (sql.contains("ALTER") || sql.contains("DROP") || sql.contains("CREATE")
                    || sql.contains("GRANT") || sql.contains("REVOKE"))
                {
                    fail("changeset " + changeSet.getId() + " has invalid changes!");
                }
                continue;
            }

            fail("changeset " + changeSet.getId() + " has invalid changes (product-specific DDL)!");
        }
    }

    private boolean isDMLChange(final Change change)
    {
        return change instanceof LoadDataChange
            || change instanceof UpdateDataChange
            || change instanceof EmptyChange
            || change instanceof InsertDataChange
            || change instanceof CreateViewChange
            || change instanceof DeleteDataChange;
    }

}
