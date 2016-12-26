package org.record.tiny;

import com.google.common.collect.Lists;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by tiny on 12/22/2016.
 */

public class Demo {
    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);

        Flowable.fromIterable(list).subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Demo -> onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Demo -> Throwable: " + t);
            }

            @Override
            public void onComplete() {
                System.out.println("Demo -> onComplete: ");
            }
        });
    }
}
