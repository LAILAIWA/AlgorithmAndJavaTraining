//package categories.java.a14lock.ConcurrentSkipListMap;
//
//import org.junit.Test;
//import java.time.ZonedDateTime;
//import java.util.Comparator;
//import java.util.UUID;
//import java.util.concurrent.*;
//import java.util.stream.IntStream;
//
//public class Main {
//    @Test
//    public void givenThreadsProducingEvents_whenGetForEventsFromLastMinute_thenReturnThoseEventsInTheLockFreeWay() throws InterruptedException {
//        //一旦我们使用ConcurrentSkipListMap实现了排序逻辑，我们现在可以通过创建两个writer线程来测试它，每个线程将发送100个事件：
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        EventWindowSort eventWindowSort = new EventWindowSort();
//        int numberOfThreads = 2;
//
//        //每个线程都在调用acceptEvent()方法，发送随机时间从现在开始到“现在减100秒”的事件。
//        Runnable producer = () -> IntStream
//                .rangeClosed(0, 100)
//                .forEach(index -> eventWindowSort.acceptEvent(
//                        new Event(ZonedDateTime.now().minusSeconds(index), UUID.randomUUID().toString()))
//                );
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            executorService.execute(producer);
//        }
//
//        //同时，我们可以调用getEventsFromLastMinute()方法，该方法将返回窗口一分钟内事件的快照：
//        ConcurrentNavigableMap<ZonedDateTime, String> eventsFromLastMinute
//                = eventWindowSort.getEventsFromLastMinute();
//
//        //eventsFromLastMinute中的事件数在每次测试运行中都会有所不同，具体取决于生产者线程将事件发送到EventWindowsPort的速度。
//        //我们可以断言，返回的快照中没有一个事件的时间超过一分钟：
//        long eventsOlderThanOneMinute = eventsFromLastMinute
//                .entrySet()
//                .stream()
//                .filter(e -> e.getKey().isBefore(ZonedDateTime.now().minusMinutes(1)))
//                .count();
//        assertEquals(eventsOlderThanOneMinute, 0);
//
//        //快照中一分钟窗口内的事件大于零：
//        long eventYoungerThanOneMinute = eventsFromLastMinute
//                .entrySet()
//                .stream()
//                .filter(e -> e.getKey().isAfter(ZonedDateTime.now().minusMinutes(1)))
//                .count();
//        assertTrue(eventYoungerThanOneMinute > 0);
//    }
//
//    @Test
//    public void givenThreadsProducingEvents_whenGetForEventsOlderThanOneMinute_thenReturnThoseEventsInTheLockFreeWay() throws InterruptedException {
//
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        EventWindowSort eventWindowSort = new EventWindowSort();
//        int numberOfThreads = 2;
//
//        Runnable producer = () -> IntStream
//                .rangeClosed(0, 100)
//                .forEach(index -> eventWindowSort.acceptEvent(new Event(ZonedDateTime
//                        .now()
//                        .minusSeconds(index), UUID
//                        .randomUUID()
//                        .toString())));
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            executorService.execute(producer);
//        }
//
//        Thread.sleep(500);
//
//        //我们的getEventsFromLastMinute()使用下面的tailMap()。
//        //现在让我们测试getEventsOlderThatOneMinute()，它使用来自ConcurrentSkipListMap的headMap()方法：
//        ConcurrentNavigableMap<ZonedDateTime, String> eventsFromLastMinute
//                = eventWindowSort.getEventsOlderThatOneMinute();
//
//        //这一次我们得到一个超过一分钟的事件快照。我们可以断言，此类事件的数量超过零：
//        long eventsOlderThanOneMinute = eventsFromLastMinute
//                .entrySet()
//                .stream()
//                .filter(e -> e.getKey().isBefore(ZonedDateTime.now().minusMinutes(1)))
//                .count();
//
//        assertTrue(eventsOlderThanOneMinute > 0);
//
//        //接下来，没有一个事件是在最后一分钟内发生的：
//        long eventYoungerThanOneMinute = eventsFromLastMinute
//                .entrySet()
//                .stream()
//                .filter(e -> e.getKey().isAfter(ZonedDateTime.now().minusMinutes(1)))
//                .count();
//
//        assertEquals(eventYoungerThanOneMinute, 0);
//
//        executorService.awaitTermination(1, TimeUnit.SECONDS);
//        executorService.shutdown();
//    }
//
//}
