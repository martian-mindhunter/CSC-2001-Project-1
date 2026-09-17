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

    static void displaySingleSession(Session session, javax.swing.JTextArea outputarea){
        if(session == null){
            outputarea.setText("Session is not found");
        } else {
            outputarea.setText(
                    "Session ID: " + session.id()
                            + " | Title: " + session.title()
                            + " | Mentor: " + session.mentor()
                            + " | Date: " + session.date()
                            + " | Location: " + session.location()
                            + " | Participants: " + session.maxParts()
                            + "\n"
            );
        }
    }

    static SessionList removeSingleSession(SessionList sessions, int idToRemove, javax.swing.JTextArea outputarea){
        return switch (sessions){
            case null -> null;
            case SessionList(Session session, SessionList r) -> {
                if(session.id() == idToRemove){
                    outputarea.setText("Session of Session ID " + session.id() + " removed");
                    yield r;
                } else {
                    yield new SessionList(session, removeSingleSession(r, idToRemove, outputarea));
                }
            }
        };
    }

    static SessionList addNewParticipant(SessionList sessions, int idToAdd, javax.swing.JTextArea outputarea){
        return switch (sessions){
            case null -> null;
            case SessionList(Session session, SessionList r) -> {
                if(session.id() == idToAdd){
                    outputarea.setText("Session of Session ID " + session.id() + " +1 Participant");
                    Session addedPartSession = new Session(session.id(), session.title(), session.mentor(), session.date(), session.location(), session.maxParts() + 1);
                    yield new SessionList(addedPartSession, r);
                } else {
                    yield new SessionList(session, addNewParticipant(r, idToAdd, outputarea));
                }
            }
        };
    }

    static void main(String[] args) {

    }

}
