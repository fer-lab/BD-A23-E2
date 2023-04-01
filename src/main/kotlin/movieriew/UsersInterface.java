package movieriew;

import java.util.Map;

public interface UsersInterface {
    User get(String id);

    User getByUserName(String userName);

    Map<String, User> getAll();
}
