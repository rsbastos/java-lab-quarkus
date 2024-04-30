package domain;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import java.util.Optional;

//import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@QuarkusTest
class CandidateServiceTest {

    @Inject
    CandidateService service;

    @InjectMock
    CandidateRepository repository;    

    @Test
    void save() {
        Candidate candidate = Instancio.create(Candidate.class);       
       
        service.save(candidate);

        verify(repository).save(candidate);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();

        when(repository.findAll()).thenReturn(candidates);

        List<Candidate> result = service.findAll();

        verify(repository).findAll();
        verifyNoMoreInteractions(repository);

        assertEquals(candidates, result);
    }

    @Test
    void findById() {
        Candidate candidate = Instancio.create(Candidate.class);           

        when(repository.findById(candidate.id())).thenReturn(Optional.of(candidate));

        Candidate result = service.findById(candidate.id());

        verify(repository).findById(candidate.id());
        verifyNoMoreInteractions(repository);

        assertEquals(candidate, result);
    }
}
