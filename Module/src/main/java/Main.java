public class Main {

    static SessionList addNewSession(SessionList sessions, Session sToAdd){

        return switch(sessions){
            case null -> new SessionList(sToAdd, null);
            case SessionList(Session session, SessionList r) ->
                    new SessionList(session, addNewSession(r, sToAdd));
        };

    }


    static void main(String[] args) {

    }

}
