package pl.camp.it.book.store.database.hibernate;

import pl.camp.it.book.store.database.IEntitySaver;
import pl.camp.it.book.store.model.Writable;

public class EntitySaverStub implements IEntitySaver {

    private int persistEntityCalls = 0;
    @Override
    public void persistEntity(Writable entity) {
        persistEntityCalls++;
    }

    @Override
    public void updateEntity(Writable entity) {

    }

    @Override
    public void deleteEntity(Writable entity) {

    }

    public int getPersistEntityCalls() {
        return persistEntityCalls;
    }
}
