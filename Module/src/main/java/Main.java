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

    static SessionList searchByMentor(SessionList sessions, String targetMentor){
        return switch(sessions){
            case null -> null;

            case SessionList(Session session,SessionList r) -> {
                SessionList matchingRest = searchByMentor(r,targetMentor);

                if(session.mentor().equals(targetMentor)){
                    yield new SessionList(session,matchingRest);
                } else {
                    yield matchingRest;
                }
            }
        };
    }

    static SessionList displayAllSessions(SessionList sessions, javax.swing.JTextArea outputarea){
        return switch (sessions){
            case null -> null;
            case SessionList(Session session, SessionList r) -> {
                outputarea.append(
                        "Session ID: " + session.id()
                                + " | Title: " + session.title()
                                + " | Mentor: " + session.mentor()
                                + " | Date: " + session.date()
                                + " | Location: " + session.location()
                                + " | Participants: " + session.maxParts()
                                + "\n"
                );
                yield new SessionList(session, displayAllSessions(r, outputarea));
            }
        };
    }

    static void main(String[] args) {

    }

}
