package tkachCourses;

public class Client {

    Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    private String id;
    private String fullName;
    private String greeting;

    String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGreeting(String gr) {
        this.greeting = gr;
    }

    public String getGreeting() {
        return greeting;
    }
}
