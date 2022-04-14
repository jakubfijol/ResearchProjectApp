package pl.jakub.ResearchProject.PatientConsent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.ResearchProject.LabTest.LabTest;

import java.util.List;

@RestController
@RequestMapping("/consents")
public class PatientConsentController {

    @Autowired
    PatientConsentRepository patientConsentRepository;

    @GetMapping("")
    public List<PatientConsent> getAll() {
        return patientConsentRepository.getAll();
    }

    @GetMapping("/patientId/{id}")
    public List<PatientConsent> getListById(@PathVariable("id") int patientId) {
        return patientConsentRepository.getListById(patientId);
    }

    @GetMapping("/{id}")
    public PatientConsent getById(@PathVariable("id") int patientId) {
        return patientConsentRepository.getById(patientId);
    }

    @PostMapping("")
    public int add(@RequestBody List<PatientConsent> patientConsents) {
        return patientConsentRepository.save(patientConsents);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody PatientConsent updatedPatientConsent) {
        PatientConsent patientConsent = patientConsentRepository.getById(id);
        if (patientConsent != null) {
            patientConsent.setProjectId(updatedPatientConsent.getProjectId());
            patientConsent.setPatientId(updatedPatientConsent.getPatientId());
            patientConsent.setConsent(updatedPatientConsent.getConsent());
            patientConsentRepository.update(patientConsent);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody PatientConsent updatedPatientConsent) {
        PatientConsent patientConsent = patientConsentRepository.getById(id);
        if (patientConsent != null) {
            if (updatedPatientConsent.getProjectId() > 0)
                patientConsent.setProjectId(updatedPatientConsent.getProjectId());
            if (updatedPatientConsent.getPatientId() > 0)
                patientConsent.setPatientId(updatedPatientConsent.getPatientId());
            if (updatedPatientConsent.getConsent() > -1) patientConsent.setConsent(updatedPatientConsent.getConsent());
            patientConsentRepository.update(patientConsent);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return patientConsentRepository.delete(id);
    }


}
