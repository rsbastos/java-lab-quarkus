package domain;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {

    void save(List<Candidate> candidates);

    default void save(Candidate candidate) {
        save(List.of(candidate));
    }

    List<Candidate> findAll();

    Optional<Candidate> findById(String id);
}
