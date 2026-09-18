import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Session s1 = new Session(1, "a", "a", "2026-01-01", "a", 1);
    Session s2 = new Session(2, "b", "b", "2026-02-02", "b", 2);
    Session s3 = new Session(3, "c", "c", "2026-03-03", "c", 3);
    Session s1b = new Session(1, "s", "s", "2026-01-01", "s", 1);
    Session sNull = null;

    @Test
    void testAddSession() {

        SessionList result = Main.addNewSession(null, s1);
        assertEquals(new SessionList(s1, null), result);

        result = Main.addNewSession(result, s2);
        assertEquals(new SessionList(s1, new SessionList(s2, null)), result);

        result = Main.addNewSession(result, s3);
        assertEquals(new SessionList(s1, new SessionList(s2, new SessionList(s3, null))), result);

        result = Main.addNewSession(null, s1);
        result = Main.addNewSession(result, s1b);
        assertEquals(new SessionList(s1, new SessionList(s1b, null)), result);

    }

    @Test
    void testSearchByID() {

        SessionList list = new SessionList(s1, new SessionList(s2, new SessionList(s3, null)));

        Session result = Main.searchByID(list, 2);
        assertEquals(s2, result);

        result = Main.searchByID(list, 999);
        assertEquals(sNull, result);

        result = Main.searchByID(null, 1);
        assertEquals(sNull, result);

    }

    @Test
    void testSearchByMentor() {

        SessionList list = new SessionList(s1, new SessionList(s2, new SessionList(s3, null)));

        SessionList result = Main.searchByMentor(list, "a");
        assertEquals(new SessionList(s1, null), result);

        result = Main.searchByMentor(list, "z");
        assertEquals(null, result);

        result = Main.searchByMentor(null, "a");
        assertEquals(null, result);

    }

    @Test
    void testDisplayAllSessions() {

        SessionList list = new SessionList(s1, new SessionList(s2, null));
        JTextArea testarea = new JTextArea();

        SessionList result = Main.displayAllSessions(list, testarea);

        String outputtext = "Session ID: 1 | Title: a | Mentor: a | Date: 2026-01-01 | Location: a | Participants: 1\n"
                + "Session ID: 2 | Title: b | Mentor: b | Date: 2026-02-02 | Location: b | Participants: 2\n";

        assertEquals(outputtext, testarea.getText());
        assertEquals(list, result);

    }

    @Test
    void testRemoveSingleSession() {

        SessionList list = new SessionList(s1, new SessionList(s2, new SessionList(s3, null)));
        JTextArea testarea = new JTextArea();

        SessionList result = Main.removeSingleSession(list, 2, testarea);

        assertEquals(new SessionList(s1, new SessionList(s3, null)), result);
        assertEquals("Session of Session ID 2 removed", testarea.getText());

    }

    @Test
    void testAddNewParticipant() {

        SessionList list = new SessionList(s1, new SessionList(s2, null));
        JTextArea testarea = new JTextArea();

        SessionList result = Main.addNewParticipant(list, 1, testarea);

        Session regResult = new Session(1, "a", "a", "2026-01-01", "a", 2);
        assertEquals(new SessionList(regResult, new SessionList(s2, null)), result);
        assertEquals("Session of Session ID 1 +1 Participant", testarea.getText());
        
    }

}
