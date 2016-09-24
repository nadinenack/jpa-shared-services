package com.powerreviews.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.powerreviews.config.RepositoryConfiguration;
import com.powerreviews.entities.Config;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.io.FileInputStream;

/**
 * Created by nadinenack on 9/23/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/config-entries.xml")
public class ConfigRepositoryITCase extends DBTestCase {

    @Autowired
    ConfigRepository configRepository;

    private IDatabaseTester databaseTester;

    public ConfigRepositoryITCase() {
        super();
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.h2.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:h2:~/test" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );
    }
    @Test
    public void testFindByKey() {
        Config config = configRepository.findByKey("FTP_USERNAME");
        Assert.assertThat(config.getKey(), Is.is("FTP_USERNAME"));
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("config-entries.xml"));
    }

    protected void setUp() throws Exception
    {
        databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver",
                "jdbc:hsqldb:sample", "sa", "");

        // initialize your dataset here
        IDataSet dataSet = null;
        // ...

        DatabaseConfig dbConfig = databaseTester.getConnection().getConfig();
        dbConfig.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
        databaseTester.setDataSet( dataSet );
        // will call default setUpOperation
        databaseTester.onSetup();
    }

    protected void tearDown() throws Exception
    {
        // will call default tearDownOperation
        databaseTester.onTearDown();
    }
}
