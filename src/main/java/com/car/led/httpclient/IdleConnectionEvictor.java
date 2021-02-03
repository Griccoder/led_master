package com.car.led.httpclient;

/**
 * Created by Guoyi on 17/4/18.
 */
import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdleConnectionEvictor extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(IdleConnectionEvictor.class);

	@Autowired
	private HttpClientConnectionManager connMgr;

	private volatile boolean shutdown;

	public IdleConnectionEvictor() {
		super();
		super.start();
	}

	@Override
	public void run() {
		while (!shutdown) {
			synchronized (this) {
				try {
					wait(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage(), e);
					Thread.currentThread().interrupt();
				}
				// 关闭失效的连接
				connMgr.closeExpiredConnections();
			}
		}
	}

	// 关闭清理无效连接的线程
	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}
}