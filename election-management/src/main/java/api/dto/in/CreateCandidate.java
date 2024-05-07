package api.dto.in;

import java.util.Optional;

import domain.Candidate;

public record CreateCandidate(Optional<String> photo,
                              String givenName,
                              String familyName,
                              String email,
                              Optional<String> phone,
                              Optional<String> jobTitle) {

    public Candidate toDomain() {
        return Candidate.create(photo(), givenName(), familyName(), email(), phone(), jobTitle());        
    }
}
