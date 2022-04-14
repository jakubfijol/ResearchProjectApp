package pl.jakub.ResearchProject.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("")
    public List<Patient> getAll() {
        return patientRepository.getAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable("id") int id) {
        return patientRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Patient> patients) {
        return patientRepository.save(patients);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Patient updatedPatient) {
        Patient patient = patientRepository.getById(id);
        if (patient != null) {
            patient.setFirstname(updatedPatient.getFirstname());
            patient.setLastname(updatedPatient.getLastname());
            patient.setPesel(updatedPatient.getPesel());
            patient.setPhonenumber(updatedPatient.getPhonenumber());
            patientRepository.update(patient);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Patient updatedPatient) {
        Patient patient = patientRepository.getById(id);
        if (patient != null) {
            if (updatedPatient.getFirstname() != null) patient.setFirstname(updatedPatient.getFirstname());
            if (updatedPatient.getLastname() != null) patient.setLastname(updatedPatient.getLastname());
            if (updatedPatient.getPesel() != null) patient.setPesel(updatedPatient.getPesel());
            if (updatedPatient.getPhonenumber() != null) patient.setPhonenumber(updatedPatient.getPhonenumber());
            patientRepository.update(patient);
            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id){
        return patientRepository.delete(id);
    }


}
