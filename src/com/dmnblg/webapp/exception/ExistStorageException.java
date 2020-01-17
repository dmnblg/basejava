package com.dmnblg.webapp.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("В хранилище уже содержится резюме с UID " + uuid, uuid);
    }

}
