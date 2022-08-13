package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Writable;

public interface IEntitySaver {
    void persistEntity(Writable entity);
    void updateEntity(Writable entity);
    void deleteEntity(Writable entity);
}
