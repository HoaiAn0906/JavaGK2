package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class CouSubscriber<T> implements Subscriber<T> {
	private List<T> list;
	private Subscription subscription;
	private CountDownLatch latch;

	public CouSubscriber() {
		list = new ArrayList<>();
		latch = new CountDownLatch(1);
	}

	@Override
	public void onSubscribe(Subscription s) {
		subscription = s;
		subscription.request(1);
	}

	@Override
	public void onNext(T t) {
		list.add(t);
		subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		t.printStackTrace();
	}

	@Override
	public void onComplete() {
		latch.countDown();
	}

	public List<T> getResults() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public T getSingleResult() {
		return getResults().size() > 0 ? getResults().get(0) : null;
	}
}
