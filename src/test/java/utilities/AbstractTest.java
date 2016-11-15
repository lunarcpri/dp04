package utilities;

import domain.Administrator;
import domain.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import security.LoginService;
import services.AdministratorService;
import services.UserService;

public class AbstractTest {

    // Supporting services --------------------------------

    @Autowired
    private LoginService loginService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected AdministratorService administratorService;

    // Set up and tear down -------------------------------

    @Before
    public void setUp() {
        // Uncomment the following line if you wish your database to be re-populated on every test.
        // PopulateDatabase.main(null);
    }

    // Supporting methods ---------------------------------

    public void authenticate(String username) {
        UserDetails userDetails;
        TestingAuthenticationToken authenticationToken;
        SecurityContext context;

        if (username == null)
            authenticationToken = null;
        else {
            userDetails = loginService.loadUserByUsername(username);
            authenticationToken = new TestingAuthenticationToken(userDetails, null);
        }

        context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticationToken);
    }

}