/**
 * Represents a library member.
 */
class Member extends Person {

    /**
     * Constructs a new Member with the specified name and ID.
     *
     * @param name The name of the member.
     * @param id   The member ID.
     */
    public Member(String name, String id) {
        super(name, id);
    }

    /**
     * Describes the member.
     *
     * @return A string describing the member.
     */
    @Override
    public String describe() {
        return "Library Member: " + name + " (ID: " + id + ")";
    }
}
