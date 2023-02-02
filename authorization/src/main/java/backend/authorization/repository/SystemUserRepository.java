package backend.authorization.repository;

import backend.authorization.model.SystemUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SystemUserRepository {
    private final AuthorizationExistDBOperations exist;

    public SystemUserRepository(AuthorizationExistDBOperations exist) {
        this.exist = exist;
    }

    public SystemUser findUserByEmail(String email) throws Exception {
        return exist.findUserByEmail(email);
    }

    public void save(SystemUser user) throws Exception {
        exist.save(user);
    }

    public ArrayList<SystemUser> getAll() throws Exception {
        return exist.getAll();
    }
}
