package java.base.a2equals;

/**
 * @program: datastructure
 * @description:
 * @author: 来建培
 * @create: 2018-12-31
 */
public class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (this.getClass() != obj.getClass())
            return false;
        Student student = (Student) obj;
        return this.name.equals(student.getName()) && this.id.equals(student.getId());
    }

    @Override
    public int hashCode() {
        return this.name.toUpperCase().hashCode() + Integer.parseInt(this.id);
    }
}
