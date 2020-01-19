package com.dmnblg.webapp.exception;

public class OverflowStorageException extends StorageException {

    public OverflowStorageException(String uuid) {
        super("Переполнение хранилища", uuid);
    }
}
