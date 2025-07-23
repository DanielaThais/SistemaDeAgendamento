package br.com.dio.register;

import br.com.dio.exception.EmptyStorageException;
import br.com.dio.model.UserModel;
import br.com.dio.exception.NotFoundException;

import java.util.List;
import java.util.ArrayList;

public class UserRegister {


    private long nextId = 1L;

    private final List<UserModel> models = new ArrayList<>();

    public UserModel save(final UserModel model) {
        model.setId(nextId++);
        models.add(model);
        return model;
    }

    public UserModel update(final UserModel model) {
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }

    public void delete(final long id) {
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public UserModel findById(final long id) {
        verifyStorage();
        var message = String.format("Não existe usuário com o id informado: %d", id);
        return models.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(message));
    }

    public List<UserModel> findAll() {
        List<UserModel> result = null;
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
