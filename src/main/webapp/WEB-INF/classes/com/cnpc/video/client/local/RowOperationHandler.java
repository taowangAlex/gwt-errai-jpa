package com.cnpc.video.client.local;

public interface RowOperationHandler<T> {

	/**
	 * Handle the event by doing whatever you want!
	 */
	void handle(T modelObject);
}
