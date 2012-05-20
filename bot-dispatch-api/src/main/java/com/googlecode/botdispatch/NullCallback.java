package com.googlecode.botdispatch;

final public class NullCallback<T> implements
        SerializableAsyncCallback<T> {

    private static final long serialVersionUID = -1088918879456763579L;

    @Override
    public void onFailure(Throwable caught) {
    }

    @Override
    public void onSuccess(T result) {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().equals(getClass())) {
            return true;
        } else {
            return false;
        }
    }
}