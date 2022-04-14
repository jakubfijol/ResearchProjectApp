package pl.jakub.ResearchProject.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("")
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    @GetMapping("/{id}")
    public Project getById(@PathVariable("id") int id) {
        return projectRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Project> projects) {
        return projectRepository.save(projects);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody Project updatedProject) {
        Project project = projectRepository.getById(id);
        if (project != null) {
            project.setName(updatedProject.getName());
            projectRepository.update(project);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity partiallyUpdate(@PathVariable("id") int id, @RequestBody Project updatedProject) {
        Project project = projectRepository.getById(id);
        if (project != null) {
            if (updatedProject.getName() != null) project.setName(updatedProject.getName());
            projectRepository.update(project);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return projectRepository.delete(id);
    }
}
