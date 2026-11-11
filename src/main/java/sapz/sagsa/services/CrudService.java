package sapz.sagsa.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public abstract class CrudService<T> {
    private final JpaRepository<T, Long> repository;
    private final String resourceName;

    protected CrudService(JpaRepository<T, Long> repository, String resourceName) {
        this.repository = repository;
        this.resourceName = resourceName;
    }

    @Transactional(readOnly = true)
    public T findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, resourceName + " não encontrado. Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public T create(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public T update(Long id, T entity) {
        findById(id);
        return repository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
