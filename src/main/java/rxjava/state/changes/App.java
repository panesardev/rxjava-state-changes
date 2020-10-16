package rxjava.state.changes;

import io.reactivex.rxjava3.core.Observable;

public class App {
    private static int start = 3, count = 5;

    public static void main(String[] args) {
        // Observable without state changes
        obsDemo();
        // Observable with state changes
        deferObsDemo();
    }

    static void obsDemo() {
        Observable<Integer> obs = Observable.range(start, count);
        obs.subscribe(n -> System.out.format("First Subscription %d\n", n));
        // On changing count variable here will not update state of observable
        count = 7;
        obs.subscribe(n -> System.out.format("Second Subscription %d\n", n));
        
        // OUTPUT
        // First Subscription 3
        // First Subscription 4
        // First Subscription 5
        // First Subscription 6
        // First Subscription 7
        // Second Subscription 3
        // Second Subscription 4
        // Second Subscription 5
        // Second Subscription 6
        // Second Subscription 7
    }

    static void deferObsDemo() {
        Observable<Integer> obs = Observable.defer(() -> Observable.range(start, count));
        obs.subscribe(n -> System.out.format("First Subscription %d\n", n));
        // By using defer, any changes to count variable will update start of initial observable
        start = 5;
        count = 7;
        obs.subscribe(n -> System.out.format("Second Subscription %d\n", n));

        // OUTPUT
        // First Subscription 3
        // First Subscription 4
        // First Subscription 5
        // First Subscription 6
        // First Subscription 7
        // Second Subscription 5
        // Second Subscription 6
        // Second Subscription 7
        // Second Subscription 8
        // Second Subscription 9
        // Second Subscription 10
        // Second Subscription 11
    }
}
