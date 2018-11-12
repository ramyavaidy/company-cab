package com.company.cab.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DropPointControllerTest.class, RoutePlanServiceTest.class, TeamMemberServiceTest.class,DroppointServiceTest.class })
public class AllTests {

}
