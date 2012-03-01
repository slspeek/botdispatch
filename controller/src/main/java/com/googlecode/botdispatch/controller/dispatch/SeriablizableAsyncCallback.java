package com.googlecode.botdispatch.controller.dispatch;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;

interface SeriablizableAsyncCallback<T> extends Serializable, AsyncCallback<T> {}