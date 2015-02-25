package com.tryzens.portal.test.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring/xml/applicationContext.xml" })
@Transactional
public class BaseTests {

	private static final Log LOGGER = LogFactory.getLog(BaseTests.class);

	@Autowired
	SessionFactory sessionFactory;
	
	protected Statistics statistics;

	@Before
	public void enableHibernateStats() {
		statistics = sessionFactory.getStatistics();
		LOGGER.info("Hibernate Statistics is "
				+ statistics.isStatisticsEnabled());
		if (!statistics.isStatisticsEnabled()) {
			statistics.setStatisticsEnabled(true);
			LOGGER.info("Hibernate Statistics is now "
					+ statistics.isStatisticsEnabled());
		}
	}

	protected static void printStats(Statistics stats) {
		stats.logSummary();
	}
}
