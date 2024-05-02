package infrastructure.repositories;

import java.util.List;

import domain.Candidate;
import domain.CandidateQuery;
import domain.CandidateRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SQLCandidateRepository implements CandidateRepository {

    @Override
    public void save(List<Candidate> candidates) {
   
    }

    @Override
    public List<Candidate> find(CandidateQuery query) {
        return List.of();
    }  

}
