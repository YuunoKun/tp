package atas.model.session;

import static atas.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import atas.commons.core.index.Index;
import atas.model.student.Name;
import atas.model.student.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a particular session at a particular date.
 * Each {@code Session} contains a list of details for each student in ATAS for the TA to keep track of.
 */
public class Session implements Comparable<Session> {

    private final SessionName sessionName;
    private final SessionDate sessionDate;
    private final ObservableList<Attributes> attributeList;
    private Index sessionIndex;

    /**
     * Parametrized constructor.
     */
    public Session(SessionName sessionName, SessionDate sessionDate) {
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.attributeList = FXCollections.observableArrayList();
        this.sessionIndex = Index.fromZeroBased(0);
    }

    /**
     * Parametrized constructor.
     */
    public Session(SessionName sessionName, SessionDate sessionDate, List<Attributes> attributeList) {
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.attributeList = FXCollections.observableArrayList();
        for (int i = 0; i < attributeList.size(); i++) {
            this.attributeList.add(attributeList.get(i));
        }
        this.sessionIndex = Index.fromZeroBased(0);
    }

    public ObservableList<Attributes> getAttributeList() {
        return this.attributeList;
    }

    public Session getCopy() {
        List<Attributes> attributesListCopy = new ArrayList<>();
        for (Attributes a : attributeList) {
            attributesListCopy.add(a.getCopy());
        }
        return new Session(sessionName.getCopy(), sessionDate.getCopy(), attributesListCopy);
    }
    /**
     * Sets the student session presence to <code>true</code>.
     */
    public void setStudentAsPresent(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = attributeList.get(studentId.getZeroBased());
        attributeList.set(studentId.getZeroBased(), attributes.togglePresence());
    }

    /**
     * Sets the student participation status to <code>true</code>.
     */
    public void setStudentAsParticipated(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = attributeList.get(studentId.getZeroBased());
        attributeList.set(studentId.getZeroBased(), attributes.toggleParticipation());
    }

    /**
     * Updates the session after the deletion of a student (with a given student ID)
     */
    public void updateSessionAfterDelete(Index studentId, List<Student> masterList) {
        requireAllNonNull(studentId, masterList);
        // shift the values down by 1, starting from deleted student ID
        for (int i = studentId.getZeroBased(); i < masterList.size(); i++) {
            Attributes temp = attributeList.get(i + 1);
            attributeList.set(i, temp);
        }
        attributeList.remove(masterList.size()); // remove last key-value pair from hashmap
    }

    /**
     * Updates the session after adding a student
     */
    public void updateSessionAfterAdd(List<Student> masterList) {
        requireAllNonNull(masterList);
        Name studentName = masterList.get(masterList.size() - 1).getName();
        attributeList.add(masterList.size() - 1, new Attributes(studentName));
    }

    /**
     * Updates the participation given a range of index.
     */
    public void updateParticipation(IndexRange indexRange) {
        requireNonNull(indexRange);

        // find students that have index in range
        for (int i = indexRange.getZeroBasedLower(); i <= indexRange.getZeroBasedUpper(); i++) {
            Attributes temp = attributeList.get(i);
            // exclude invalid index
            if (temp != null) {
                setStudentAsParticipated(Index.fromZeroBased(i));
            }
        }
    }

    /**
     * Updates the presence given a range of index.
     */
    public void updatePresence(IndexRange indexRange) {
        requireNonNull(indexRange);

        // find students that have index in range
        for (int i = indexRange.getZeroBasedLower(); i <= indexRange.getZeroBasedUpper(); i++) {
            Attributes temp = attributeList.get(i);

            // exclude invalid index
            if (temp != null) {
                setStudentAsPresent(Index.fromZeroBased(i));
            }
        }
    }

    /**
     * Initialize the studentList using the given masterList.
     */
    public void initializeSession(List<Student> masterList) {
        for (int i = 0; i < masterList.size(); i++) {
            attributeList.add(new Attributes(masterList.get(i).getName()));
        }
    }

    public String returnStudentNameStringByIndex(int index) throws Exception {
        return attributeList.get(index).getName();
    }

    public void setAttributeList(List<Attributes> attributeList) {
        this.attributeList.setAll(attributeList);
    }
    /**
     * Returns true if both sessions have the same session name.
     * This defines a weaker notion of equality between two sessions.
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.sessionName.equals(this.sessionName);
    }

    public SessionName getSessionName() {
        return sessionName;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public Index getSessionIndex() {
        return sessionIndex;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Session && this.sessionDate.equals(((Session) other).sessionDate)
                && this.sessionName.equals(((Session) other).sessionName));
    }

    @Override
    public int compareTo(Session other) {
        return this.sessionDate.compareTo(other.sessionDate);
    }

    @Override
    public String toString() {
        return sessionName.toString() + " @ " + sessionDate.toString();
    }
}