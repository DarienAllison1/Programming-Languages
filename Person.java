/**
 * Abstract class representing a person associated with the library.
 */
abstract class Person {
    protected String name;
    protected String id;

    /**
     * Constructs a new Person with the specified name and ID.
     *
     * @param name The name of the person.
     * @param id   The ID of the person.
     */
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Provides a description of the person.
     * This method must be implemented by subclasses.
     *
     * @return A string describing the type of person.
     */
    public abstract String describe();
}
