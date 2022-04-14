package pl.jakub.ResearchProject.LabTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.ResearchProject.Patient.Patient;
import pl.jakub.ResearchProject.PatientConsent.PatientConsent;
import pl.jakub.ResearchProject.PatientConsent.PatientConsentRepository;

import java.util.List;

@RestController
@RequestMapping("/labtests")
public class LabTestController {


    @Autowired
    LabTestRepository labTestRepository;

    @GetMapping("")
    public List<LabTest> getAll() {
        return labTestRepository.getAll();
    }

    @GetMapping("/patientId/{id}")
    public List<LabTest> getListById(@PathVariable("id") int patientId) {
        return labTestRepository.getListById(patientId);
    }

    @GetMapping("/{id}")
    public LabTest getById(@PathVariable("id") int id) {
        return labTestRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<LabTest> labTests) {
        return labTestRepository.save(labTests);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody LabTest updatedLabTest) {
        LabTest labTest = labTestRepository.getById(id);
        if (labTest != null) {
            labTest.setProjectId(updatedLabTest.getProjectId());
            labTest.setPatientId(updatedLabTest.getPatientId());
            labTest.setDate(updatedLabTest.getDate());
            labTest.setType(updatedLabTest.getType());
            labTest.setResult(updatedLabTest.getResult());
            labTestRepository.update(labTest);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody LabTest updatedLabTest) {
        LabTest labTest = labTestRepository.getById(id);
        if (labTest != null) {
            if (updatedLabTest.getProjectId() > -1) labTest.setProjectId(updatedLabTest.getProjectId());
            if (updatedLabTest.getPatientId() > -1) labTest.setPatientId(updatedLabTest.getPatientId());
            if (updatedLabTest.getDate() != null) labTest.setDate(updatedLabTest.getDate());
            if (updatedLabTest.getType() != null) labTest.setType(updatedLabTest.getType());
            if (updatedLabTest.getResult() != null) labTest.setResult(updatedLabTest.getResult());
            labTestRepository.update(labTest);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return labTestRepository.delete(id);
    }


}
