package domain;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CandidateService {
    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public void save(Candidate domain) {
        repository.save(domain);
    }

    public List<Candidate> findAll() {
        return repository.findAll();
    }

    public Candidate findById(String id) {
        return repository.findById(id).orElseThrow();
    }
}
