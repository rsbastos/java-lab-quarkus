package api;

import java.util.List;

import api.dto.out.Candidate;
import domain.CandidateService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CandidateApi {

    private final CandidateService service;

    public CandidateApi(CandidateService service) {
        this.service = service;
    }

    public void create(api.dto.in.CreateCandidate dto) {
        service.save(dto.toDomain());
    }

    public api.dto.out.Candidate update(String id, api.dto.in.UpdateCandidate dto) {
        service.save(dto.toDomain(id));  
        return api.dto.out.Candidate.fromDomain(service.findById(id)); 
    }

    public List<Candidate> list() {
        return service.findAll().stream().map(Candidate::fromDomain).toList();
    } 

}
