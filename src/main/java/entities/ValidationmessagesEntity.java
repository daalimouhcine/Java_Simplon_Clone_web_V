package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "validationmessages", schema = "public", catalog = "simplon_clone_web_v")
public class ValidationmessagesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "student_id")
    private int studentId;
    @Basic
    @Column(name = "brief_id")
    private int briefId;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "repolink")
    private String repolink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBriefId() {
        return briefId;
    }

    public void setBriefId(int briefId) {
        this.briefId = briefId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRepolink() {
        return repolink;
    }

    public void setRepolink(String repolink) {
        this.repolink = repolink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidationmessagesEntity that = (ValidationmessagesEntity) o;

        if (id != that.id) return false;
        if (studentId != that.studentId) return false;
        if (briefId != that.briefId) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (repolink != null ? !repolink.equals(that.repolink) : that.repolink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + studentId;
        result = 31 * result + briefId;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (repolink != null ? repolink.hashCode() : 0);
        return result;
    }
}
