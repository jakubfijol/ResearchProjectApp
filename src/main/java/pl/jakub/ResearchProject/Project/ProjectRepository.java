package pl.jakub.ResearchProject.Project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class ProjectRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Project> getAll() {
        return jdbcTemplate.query("SELECT id,name FROM project",
                BeanPropertyRowMapper.newInstance(Project.class));
    }

    public Project getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id,name FROM project WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Project.class), id);
    }

    public int save(List<Project> projects) {
        projects.forEach(project -> jdbcTemplate
                .update("INSERT INTO project(name) VALUES (?)",
                        project.getName()));
        return 1;
    }

    public int update(Project project) {
        return jdbcTemplate.update("UPDATE project SET name=? WHERE id=?",
                project.getName(), project.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM project WHERE id=?", id);
    }

}
