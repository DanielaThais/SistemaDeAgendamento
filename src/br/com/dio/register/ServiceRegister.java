package br.com.dio.register;

import br.com.dio.exception.EmptyStorageException;
import br.com.dio.model.ServiceModel;
import br.com.dio.exception.NotFoundException;

import java.util.List;
import java.util.ArrayList;

public class ServiceRegister {


    private long nextId = 1L;

    private final List<ServiceModel> models = new ArrayList<>();

    public ServiceModel save(final ServiceModel model) {
        model.setId(nextId++);
        models.add(model);
        return model;
    }

    public ServiceModel update(final ServiceModel model) {
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }

    public void delete(final long id) {
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public ServiceModel findById(final long id) {
        verifyStorage();
        var message = String.format("Não existe usuário com o id informado: %d", id);
        return models.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(message));
    }

    public List<ServiceModel> findAll() {
        List<ServiceModel> result = null;
        try {
            verifyStorage();
            result = models;
        } catch (EmptyStorageException ex) {
            ex.printStackTrace();
            result = new ArrayList<>();
        } return result;
    }

    private void verifyStorage() {
        if (models.isEmpty()) throw new EmptyStorageException("O armazenamento está vazio." +
                "=======================================================");
    }
}
