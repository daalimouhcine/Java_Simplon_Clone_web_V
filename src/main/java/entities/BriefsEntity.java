package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "briefs", schema = "public", catalog = "simplon_clone_web_v")
public class BriefsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "technologies")
    private String technologies;
    @Basic
    @Column(name = "promoid")
    private Integer promoid;
    @Basic
    @Column(name = "launched")
    private Boolean launched;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public Integer getPromoid() {
        return promoid;
    }

    public void setPromoid(Integer promoid) {
        this.promoid = promoid;
    }

    public Boolean getLaunched() {
        return launched;
    }

    public void setLaunched(Boolean launched) {
        this.launched = launched;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BriefsEntity that = (BriefsEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (technologies != null ? !technologies.equals(that.technologies) : that.technologies != null) return false;
        if (promoid != null ? !promoid.equals(that.promoid) : that.promoid != null) return false;
        if (launched != null ? !launched.equals(that.launched) : that.launched != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (technologies != null ? technologies.hashCode() : 0);
        result = 31 * result + (promoid != null ? promoid.hashCode() : 0);
        result = 31 * result + (launched != null ? launched.hashCode() : 0);
        return result;
    }
}
