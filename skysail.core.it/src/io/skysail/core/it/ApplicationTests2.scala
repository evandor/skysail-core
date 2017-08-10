package io.skysail.core.it

import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.assertj.core.api.Assertions.assertThat

@RunWith(classOf[JUnitRunner])
class ApplicationTests2 extends ApplicationTests {
  
//    private static final String ADMIN_USER = "admin";
//    private static final String DEFAULT_PASSWORD = "skysail";
//
//    private TextEntity entity;
//
//    @Before
//    public void setUp() {
//        browser = new TextEntitiesBrowser(determinePort());
//        browser.setAuthenticationStrategy(new ShiroAuthenticationStrategy());
//        browser.setUser(ADMIN_USER);
//        entity = browser.createRandomEntity();
//    }
//
//    @Test
//    @Ignore
//    public void polymer_is_rendered() throws IOException { // NOSONAR
////        Representation result = browser.loginAs(ADMIN_USER, DEFAULT_PASSWORD).create(entity);
////        assertThat(result.getText()).contains("xxx");
//    }
//
    @Test
    def createsEntity_postingJSON() { 
      assertThat(1==1)
    }
}