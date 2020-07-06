package com.agnihotri.planttester;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        BBDTestPlantDAO.class,
        TestNetworkDAO.class,
        MockedServerBDDTestPlantDAO.class,
})

public class DAOTestSuite {
}
