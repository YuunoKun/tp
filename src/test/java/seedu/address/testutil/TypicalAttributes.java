package seedu.address.testutil;

import seedu.address.model.attendance.Attributes;
import seedu.address.model.attendance.Participation;
import seedu.address.model.attendance.Presence;

public class TypicalAttributes {
    public static final Presence IS_PRESENT = new Presence(true);
    public static final Presence IS_ABSENT = new Presence(false);
    public static final Participation HAS_PARTICIPATED = new Participation(true);
    public static final Participation HAS_NOT_PARTICIPATED = new Participation(false);

    public static final Attributes DEFAULT_PARTICIPATION = new Attributes(); // absent and has not participated
    public static final Attributes PRESENT_BUT_HAS_NOT_PARTICIPATED = new Attributes(IS_PRESENT, HAS_NOT_PARTICIPATED);
    public static final Attributes ABSENT_BUT_HAS_PARTICIPATED = new Attributes(IS_ABSENT, HAS_PARTICIPATED);
    public static final Attributes PRESENT_AND_HAS_PARTICIPATED = new Attributes(IS_PRESENT, HAS_PARTICIPATED);
}
