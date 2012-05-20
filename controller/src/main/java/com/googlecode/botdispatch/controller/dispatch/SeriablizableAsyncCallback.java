package com.googlecode.botdispatch.controller.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.io.Serializable;

interface SeriablizableAsyncCallback<T> extends Serializable, AsyncCallback<T> {
}