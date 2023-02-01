package backend.authorization.repository;

import backend.authorization.model.SystemUser;

import java.util.ArrayList;

public class SystemUserRepository {
    AuthorizationExistDBOperations exist = new AuthorizationExistDBOperations();

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
