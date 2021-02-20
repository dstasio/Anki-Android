
package com.ichi2.lowanki.exception;

public class StorageAccessException extends Exception {

    public StorageAccessException(String msg, Throwable e) {
        super(msg, e);
    }


    public StorageAccessException(String msg) {
        super(msg);
    }
}
