package com.dmnblg.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("В хранилище отсутствут резюме с UID " + uuid, uuid);
    }

}
