public class Main {

    static SessionList addNewSession(SessionList sessions, Session sToAdd) {

        return switch (sessions) {
            case null -> new SessionList(sToAdd, null);

            case SessionList(Session session, SessionList r) -> {
                    //compareTo checks to see if the current session has an earlier date
                    if (sToAdd.date().compareTo(session.date()) < 0) {
                        yield new SessionList(sToAdd, sessions);
                    } else {
                        yield new SessionList(session, addNewSession(r, sToAdd));
                    }
                }
            };

        }

    static Session searchByID(SessionList sessions, int target){
        return switch(sessions){
            case null -> null;

            case SessionList(Session session, SessionList r) -> {
                if(session.id() == target){
                    yield session;
                }
                else{
                    yield searchByID(r,target);
                }
            }
        };
    }

    static void main(String[] args) {

    }

}
